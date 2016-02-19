package org.ict4h.domain;

import java.util.ArrayList;

public class AppConfigs extends ArrayList<AppConfig> {

    public AppConfig getForApp(String app_name) {
        for (AppConfig appConfig : this) {
            if (appConfig.isFor(app_name)) return appConfig;
        }
        return null;
    }
}
