package org.ict4h.service;

import org.ict4h.atomfeed.client.domain.Marker;
import org.ict4h.atomfeed.client.repository.AllFailedEvents;
import org.ict4h.atomfeed.client.repository.AllMarkers;
import org.ict4h.atomfeed.client.repository.jdbc.AllFailedEventsJdbcImpl;
import org.ict4h.atomfeed.client.repository.jdbc.AllMarkersJdbcImpl;
import org.ict4h.atomfeed.jdbc.JdbcConnectionProvider;
import org.ict4h.domain.AppConfig;
import org.ict4h.domain.AppStatus;
import org.ict4h.domain.FeedStatus;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class AppStatusService {

    public AppStatus getAppStatus(AppConfig appConfig) {

        AllMarkers allMarkers = new AllMarkersJdbcImpl(getJdbcConnection(appConfig));
        AllFailedEvents allFailedEvents = new AllFailedEventsJdbcImpl(getJdbcConnection(appConfig));
        List<Marker> markers = new ArrayList();
        markers = allMarkers.getMarkerList();
        AppStatus appStatus=new AppStatus();

        for(Marker marker : markers){
            FeedStatus feedStatus = new FeedStatus(marker,allFailedEvents);
            appStatus.add(feedStatus);
        }

        return appStatus;
    }

    public JdbcConnectionProvider getJdbcConnection(final AppConfig appConfig) {
        org.ict4h.atomfeed.Configuration.getInstance(AppConfiguration.DEFAULT_APP_CONFIG_FILE);

        return new JdbcConnectionProvider() {
            @Override
            public Connection getConnection() throws SQLException {
                return DriverManager.getConnection(appConfig.getDbUrl(), appConfig.getDbUser(), appConfig.getDbPassword());
            }
        };
    }

    public AllFailedEvents getCountOfFailedEvents(AppConfig appConfig) {

        return new AllFailedEventsJdbcImpl(getJdbcConnection(appConfig));

    }
}
