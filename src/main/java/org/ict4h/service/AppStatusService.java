package org.ict4h.service;

import org.ict4h.atomfeed.client.repository.AllMarkers;
import org.ict4h.atomfeed.client.repository.jdbc.AllMarkersJdbcImpl;
import org.ict4h.atomfeed.jdbc.JdbcConnectionProvider;
import org.ict4h.domain.AppConfig;
import org.ict4h.domain.AppStatus;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class AppStatusService {

    public static final String APP_CONFIG = "Apps.yml";

    public AppStatus getAppStatus(AppConfig appConfig) {
        return new AppStatus(getMarkers(appConfig).getMarkerList());
    }

    private AllMarkers getMarkers(final AppConfig appConfig) {
        org.ict4h.atomfeed.Configuration.getInstance(APP_CONFIG);

        return new AllMarkersJdbcImpl(new JdbcConnectionProvider() {
            @Override
            public Connection getConnection() throws SQLException {
                return DriverManager.getConnection(appConfig.getDb_url(), appConfig.getDb_uid(), appConfig.getDb_pwd());
            }
        });
    }
}
