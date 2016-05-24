package org.ict4h.service;

import org.ict4h.domain.configuration.AppConfig;
import org.ict4h.domain.configuration.AppConfigs;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties
public class AppConfiguration {
    private final AppConfigs appConfigs = new AppConfigs();


    public AppConfigs getAppConfigs() {
        return appConfigs;
    }

    public AppConfig getAppConfigForApp(String appName) {
        return appConfigs.getForApp(appName);
    }
}
