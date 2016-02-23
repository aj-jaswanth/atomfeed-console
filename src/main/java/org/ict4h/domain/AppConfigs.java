package org.ict4h.domain;

import java.util.ArrayList;

public class AppConfigs extends ArrayList<AppConfig> {

    public AppConfig getForApp(String appName) {
        for (AppConfig appConfig : this) {
            if (appConfig.isFor(appName)) return appConfig;
        }
        return null;
    }
}
