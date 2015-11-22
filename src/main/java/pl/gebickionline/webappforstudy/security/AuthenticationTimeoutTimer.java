package pl.gebickionline.webappforstudy.security;

import javax.ejb.*;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.logging.Logger;

@Startup
@Singleton
@ApplicationScoped
public class AuthenticationTimeoutTimer {

    private Logger logger = Logger.getLogger(AuthenticationTimeoutTimer.class.getName());

    @Inject
    private AuthenticationCleaner authenticationProvider;

    @Timeout
    @Schedule(hour = "*", minute = "*", second = "*/30")
    public void logoutInactiveUsers() {
        logger.info("AuthenticationTimeoutTimer activated");
        authenticationProvider.logoutAllInactiveUsers();
    }
}
