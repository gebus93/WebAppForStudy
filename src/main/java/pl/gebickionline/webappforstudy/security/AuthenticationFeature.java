package pl.gebickionline.webappforstudy.security;

import javax.inject.Inject;
import javax.ws.rs.container.*;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.ext.Provider;
import java.lang.reflect.Method;

@Provider
public class AuthenticationFeature implements DynamicFeature {

    @Inject
    AuthenticationProvider authenticationProvider;

    @Override
    public void configure(ResourceInfo resourceInfo, FeatureContext context) {
        Method method = resourceInfo.getResourceMethod();
        context.register(new ApplicationRequestFilter());
        if (!method.isAnnotationPresent(Public.class)) {
            AuthenticationFilter authenticationFilter = new AuthenticationFilter(authenticationProvider);
            context.register(authenticationFilter);
        }
    }
}