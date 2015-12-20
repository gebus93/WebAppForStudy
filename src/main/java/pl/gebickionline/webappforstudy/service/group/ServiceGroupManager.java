package pl.gebickionline.webappforstudy.service.group;

import org.jetbrains.annotations.NotNull;
import pl.gebickionline.webappforstudy.exception.ServiceGroupNotFoundException;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Stateless
public class ServiceGroupManager {

    @PersistenceContext(unitName = "appPU")
    private EntityManager em;
    private List<ServiceGroup> serviceGroups;

    public void updateGroupList(@NotNull("Lista grup nie może być wartością NULL") List<ServiceGroupDTO> request) {
        List<Integer> ids = request
                .stream()
                .map(g -> g.id)
                .collect(toList());

        List<Integer> nonExistingIds = getNonExistingGroupsIds(ids);

        if (!nonExistingIds.isEmpty())
            throw new ServiceGroupNotFoundException(ids);

        GroupListDifference diff = getListDifference(ids);

        diff.groupsToDelete
                .stream()
                .forEach(g -> {
                    em.createNamedQuery("ServiceGroup.removeServicesFromGroup").setParameter("group", g).executeUpdate();
                    em.remove(g);
                });

        List<ServiceGroup> groupsToAddOrUpdate = request
                .stream()
                .filter(req -> diff.groupsToAddOrUpdate.contains(req.id))
                .map(req -> new ServiceGroup().id(req.id).ordinal(req.ordinal).name(req.name).visible(req.visible))
                .collect(toList());

        groupsToAddOrUpdate.forEach(g -> em.merge(g));
    }

    private GroupListDifference getListDifference(List<Integer> ids) {
        List<ServiceGroup> groupsToRemove = findAllGroups()
                .stream()
                .filter(g -> !ids.contains(g.id()))
                .collect(toList());

        List<Integer> groupsToAddOrUpdate = ids.stream()
                .filter(id -> !groupsToRemove.contains(id))
                .collect(toList());

        return new GroupListDifference(groupsToAddOrUpdate, groupsToRemove);
    }

    private List<Integer> getNonExistingGroupsIds(List<Integer> ids) {
        List<Integer> idsOfExistingGroups = findAllGroups()
                .stream()
                .map(ServiceGroup::id)
                .collect(toList());

        return ids.stream()
                .filter(id -> !idsOfExistingGroups.contains(id))
                .collect(toList());
    }

    private List<ServiceGroup> findAllGroups() {
        if (serviceGroups == null)
            serviceGroups = em.createNamedQuery("ServiceGroup.findAll", ServiceGroup.class).getResultList();

        return serviceGroups;
    }

    public List<ServiceGroupDTO> getAll() {
        return findAllGroups()
                .stream()
                .map(g -> {
                            ServiceGroupDTO dto = new ServiceGroupDTO();
                            dto.id = g.id();
                            dto.name = g.name();
                            dto.ordinal = g.ordinal();
                            dto.visible = g.visible();
                            return dto;
                        }
                ).collect(toList());
    }

    public List<ServiceGroupDTO> getVisible() {
        return getAll().stream()
                .filter(g -> g.visible)
                .map(g -> {
                            g.visible = null;
                            return g;
                        }
                ).collect(toList());
    }

    private class GroupListDifference {
        private final List<Integer> groupsToAddOrUpdate;
        private final List<ServiceGroup> groupsToDelete;

        public GroupListDifference(List<Integer> groupsToAddOrUpdate, List<ServiceGroup> groupsToDelete) {
            this.groupsToAddOrUpdate = groupsToAddOrUpdate;
            this.groupsToDelete = groupsToDelete;
        }
    }
}
