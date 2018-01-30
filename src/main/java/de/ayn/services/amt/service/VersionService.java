package de.ayn.services.amt.service;

import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class VersionService {

    @Value("${application.name}")
    private String name;

    @Value("${application.version}")
    private String version;
    
    @Value("${application.build}")
    private String build;
    
    @Value("${application.deployment}")
    private String deployment;

    public Map<String, Object> simpleVersionInfo() {
        return basicConfiguration();
    }

    private Map<String, Object> basicConfiguration() {
        Map<String, Object> info = new LinkedHashMap<>();
        info.put("application", name);
        info.put("version", version);
        info.put("build", build);
        info.put("deployment", deployment);
        return info;
    }
    
    public Map<String, Object> diagnosticVersionInfo() {
        return basicConfiguration();
    }
}
