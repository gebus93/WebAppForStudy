package pl.gebickionline.webappforstudy.user;

import org.json.JSONObject;
import pl.gebickionline.webappforstudy.security.*;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Optional;
import java.util.logging.Logger;

import static pl.gebickionline.webappforstudy.security.AuthenticationProvider.AUTH_TOKEN_HEADER_KEY;

@Path("/user")
@Consumes("application/json")
@Produces("application/json")
public class UserEndpoint {

    Logger logger = Logger.getLogger(UserEndpoint.class.getName());

    @Inject
    private UserManager userManager;

    @POST
    @Path("login")
    @Public
    public Response login(@NotNull(message = "Należy podać dane logowania") @Valid UserLoginRequest request) {
        logger.info("login: " + request.login);
        logger.info("hasło: " + request.password);
        AuthToken token = userManager.authenticate(request.login, request.password);
        return Response.status(Response.Status.OK)
                .entity(new JSONObject().put("auth_token", token.authToken()).toString())
                .build();
    }

    @POST
    @Path("logout")
    public Response logout(@HeaderParam(AUTH_TOKEN_HEADER_KEY) String authToken) {
        userManager.logout(new AuthToken(authToken));
        return Response.status(Response.Status.OK).build();
    }

    @GET
    public Response userDetails() {
        Optional<UserDetails> userDetails = userManager.userDetails();
        if (!userDetails.isPresent())
            return Response.status(Response.Status.UNAUTHORIZED).build();

        String responseBody = new JSONObject()
                .put("login", userDetails.get().login())
                .toString();

        return Response.status(Response.Status.OK)
                .entity(responseBody)
                .build();
    }
}
