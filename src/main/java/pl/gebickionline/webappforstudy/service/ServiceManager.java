package pl.gebickionline.webappforstudy.service;

import pl.gebickionline.webappforstudy.exception.*;
import pl.gebickionline.webappforstudy.service.group.ServiceGroup;

import javax.persistence.*;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Transactional
public class ServiceManager {

    @PersistenceContext(unitName = "appPU")
    private EntityManager em;

    public void updateServiceList(@NotNull(message = "Lista usług nie może być wartością null") List<ServiceDTO> request) {
        List<Integer> ids = request
                .stream()
                .filter(s -> s.id != null)
                .map(s -> s.id)
                .collect(toList());

        List<Integer> nonExistingIds = getNonExistingServiceIds(ids);

        if (!nonExistingIds.isEmpty())
            throw new ServiceNotFoundException(nonExistingIds);

        ServiceListDifference diff = getListDifference(ids);

        diff.servicesToDelete
                .stream()
                .forEach(g -> {
                    em.createNamedQuery("ServiceGroup.removeServicesFromGroup").setParameter("group", g).executeUpdate();
                    em.remove(g);
                });

        List<Service> servicesToAddOrUpdate = request
                .stream()
                .filter(req -> req.id == null || diff.servicesToAddOrUpdate.contains(req.id))
                .map(req -> new Service()
                        .id(req.id)
                        .ordinal(req.ordinal)
                        .name(req.name)
                        .visible(req.visible)
                        .maxPrice(req.maxPrice)
                        .minPrice(req.minPrice)
                        .price(req.price)
                        .group(findGroupById(req.groupID))
                )
                .collect(toList());

        servicesToAddOrUpdate.forEach(g -> em.merge(g));
    }

    private ServiceGroup findGroupById(Integer groupID) {
        try {
            return em.createNamedQuery("ServiceGroup.findByID", ServiceGroup.class)
                    .setParameter("id", groupID)
                    .getSingleResult();
        } catch (NoResultException e) {
            throw new ServiceGroupNotFoundException("Nie znaleziono grupy o identyfikatorze " + groupID);
        }
    }

    private ServiceListDifference getListDifference(List<Integer> ids) {
        List<Service> servicesToRemove = findAll()
                .stream()
                .filter(g -> !ids.contains(g.id()))
                .collect(toList());

        List<Integer> servicesToAddOrUpdate = ids.stream()
                .filter(id -> !servicesToRemove.contains(id))
                .collect(toList());

        return new ServiceListDifference(servicesToAddOrUpdate, servicesToRemove);

    }

    private List<Integer> getNonExistingServiceIds(List<Integer> ids) {
        List<Integer> idsOfExistingServices = findAll()
                .stream()
                .map(Service::id)
                .collect(toList());

        return ids.stream()
                .filter(id -> !idsOfExistingServices.contains(id))
                .collect(toList());
    }

    public List<ServiceDTO> getAll() {
        return findAll()
                .stream()
                .map(s ->
                {
                    ServiceDTO dto = new ServiceDTO();
                    dto.id = s.id();
                    dto.groupID = s.group().id();
                    dto.name = s.name();
                    dto.maxPrice = s.maxPrice();
                    dto.minPrice = s.minPrice();
                    dto.price = s.price();
                    dto.visible = s.visible();
                    return dto;
                })
                .collect(toList());
    }

    private List<Service> findAll() {
        return em.createNamedQuery("Service.findAll", Service.class).getResultList();
    }

    public List<ServiceDTO> getVisible() {
        return getAll()
                .stream()
                .filter(s -> s.visible)
                .map(s -> {
                    s.visible = null;
                    return s;
                })
                .collect(toList());
    }


    private class ServiceListDifference {
        private final List<Integer> servicesToAddOrUpdate;
        private final List<Service> servicesToDelete;

        public ServiceListDifference(List<Integer> servicesToAddOrUpdate, List<Service> servicesToDelete) {
            this.servicesToAddOrUpdate = servicesToAddOrUpdate;
            this.servicesToDelete = servicesToDelete;
        }
    }
}
