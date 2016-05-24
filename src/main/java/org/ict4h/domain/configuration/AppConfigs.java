package org.ict4h.domain.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class AppConfigs extends ArrayList<AppConfig> {

    public AppConfig getForApp(String appName) {
        for (AppConfig appConfig : this) {
            if (appConfig.isFor(appName)) return appConfig;
        }
        return null;
    }
}
