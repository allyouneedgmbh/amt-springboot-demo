package de.ayn.services.amt.rest;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JaxRsConfiguration extends ResourceConfig {

    public JaxRsConfiguration() {
        register(PublicEndpoint.class);
    }

    
}
