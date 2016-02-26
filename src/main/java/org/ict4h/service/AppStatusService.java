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
import org.ict4h.jdbc.ConnectionPools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class AppStatusService {
    ConnectionPools connectionPools;

    @Autowired
    public AppStatusService(ConnectionPools connectionPools)throws SQLException{
        this.connectionPools = connectionPools;
    }


    public AppStatus getAppStatus(AppConfig appConfig)throws SQLException{

        AtomfeedConsoleConnectionProvider jdbcConnection = (AtomfeedConsoleConnectionProvider) getJdbcConnection(appConfig.getAppName());
        AllMarkers allMarkers = new AllMarkersJdbcImpl(jdbcConnection);
        AllFailedEvents allFailedEvents = new AllFailedEventsJdbcImpl(jdbcConnection);
        List<Marker> markers = new ArrayList();
        markers = allMarkers.getMarkerList();
        AppStatus appStatus=new AppStatus();

        for(Marker marker : markers){
            FeedStatus feedStatus = new FeedStatus(marker,allFailedEvents);
            appStatus.add(feedStatus);
        }
        jdbcConnection.getConnection().close();

        return appStatus;
    }

    public JdbcConnectionProvider getJdbcConnection(String appName) {
        org.ict4h.atomfeed.Configuration.getInstance(AppConfiguration.DEFAULT_APP_CONFIG_FILE);
        try {
            return new AtomfeedConsoleConnectionProvider(connectionPools.getConnection(appName));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public class AtomfeedConsoleConnectionProvider implements JdbcConnectionProvider{
        private Connection connection;

        public AtomfeedConsoleConnectionProvider(Connection connection) {
            this.connection = connection;
        }

        @Override
        public Connection getConnection() throws SQLException {
            return connection;
        }

        public void closeConnection() {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
