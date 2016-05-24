package org.ict4h.service;

import org.apache.log4j.Logger;
import org.ict4h.atomfeed.client.domain.Marker;
import org.ict4h.atomfeed.client.repository.AllFailedEvents;
import org.ict4h.atomfeed.client.repository.AllMarkers;
import org.ict4h.atomfeed.client.repository.jdbc.AllFailedEventsJdbcImpl;
import org.ict4h.atomfeed.client.repository.jdbc.AllMarkersJdbcImpl;
import org.ict4h.atomfeed.jdbc.JdbcConnectionProvider;
import org.ict4h.domain.configuration.AppConfig;
import org.ict4h.domain.Feeds;
import org.ict4h.domain.Feed;
import org.ict4h.jdbc.ConnectionPools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class AppStatusService {
    private static Logger logger = Logger.getLogger(AppStatusService.class);
    ConnectionPools connectionPools;

    @Autowired
    public AppStatusService(ConnectionPools connectionPools)throws SQLException{
        this.connectionPools = connectionPools;
    }

    public Feeds getAppStatus(AppConfig appConfig)throws SQLException{

        AtomfeedConsoleConnectionProvider jdbcConnection = (AtomfeedConsoleConnectionProvider) getJdbcConnection(appConfig.getAppName());
        AllMarkers allMarkers = new AllMarkersJdbcImpl(jdbcConnection);
        AllFailedEvents allFailedEvents = new AllFailedEventsJdbcImpl(jdbcConnection);
        List<Marker> markers = new ArrayList();
        markers = allMarkers.getMarkerList();
        logger.debug(String.format("List of markers read : %s",markers));
        Feeds feeds =new Feeds();

        for(Marker marker : markers){
            int numberOfFailedEvents = allFailedEvents.getNumberOfFailedEvents(marker.getFeedUri().toString());
            Feed feed = new Feed(marker.getFeedUri(), marker.getLastReadEntryId(), numberOfFailedEvents);
            feeds.add(feed);
        }
        logger.debug(String.format("List of feed status prepared for a given application : %s",feeds));
        jdbcConnection.getConnection().close();

        return feeds;
    }

    public JdbcConnectionProvider getJdbcConnection(String appName) {
        org.ict4h.atomfeed.Configuration.getInstance("application.yml");
        try {
            logger.debug(String.format("Opening connection for App : %s",appName));
            return new AtomfeedConsoleConnectionProvider(connectionPools.getConnection(appName));
        } catch (SQLException e) {
            logger.error(String.format("Failed to open connection for App : %s",appName));
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
                logger.debug("Connection closed");
            } catch (SQLException e) {
                logger.error("Failed to close connection");
                throw new RuntimeException(e);
            }
        }
    }

}
