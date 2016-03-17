package org.ict4h.service;

import org.apache.log4j.Logger;
import org.ict4h.atomfeed.client.domain.FailedEvent;
import org.ict4h.atomfeed.client.repository.AllFailedEvents;
import org.ict4h.atomfeed.client.repository.jdbc.AllFailedEventsJdbcImpl;
import org.ict4h.domain.configuration.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FailedEventService {
    private static Logger logger = Logger.getLogger(FailedEventService.class);
    @Autowired
    private AppStatusService appStatusService;


    public List<FailedEvent> failedEventStatus (AppConfig appConfig,String feedURI){

        AppStatusService.AtomfeedConsoleConnectionProvider jdbcConnection = (AppStatusService.AtomfeedConsoleConnectionProvider) appStatusService.getJdbcConnection(appConfig.getAppName());
        AllFailedEvents allFailedEvents = new AllFailedEventsJdbcImpl(jdbcConnection);
        List<FailedEvent> failedEventList = allFailedEvents.getFailedEvents(feedURI);
        logger.debug(String.format("Read List of failed events for feedUri %s : %s",feedURI,failedEventList));
        jdbcConnection.closeConnection();
        return failedEventList;

    }

    public void resetRetriesService(AppConfig appConfig,String failedEventId){
        AppStatusService.AtomfeedConsoleConnectionProvider jdbcConnection = (AppStatusService.AtomfeedConsoleConnectionProvider) appStatusService.getJdbcConnection(appConfig.getAppName());
        AllFailedEvents allFailedEvents = new AllFailedEventsJdbcImpl(jdbcConnection);
        logger.debug(String.format("Reading failed Event status for eventId %s ",failedEventId));
        FailedEvent failedEvent=allFailedEvents.getByEventId(failedEventId);
        logger.debug(String.format("Resetting retry count for failed event with eventId %s ",failedEventId));
        failedEvent.setRetries(0);
        logger.debug(String.format("Updating the new retry count as %d for eventId %s ",failedEvent.getRetries(),failedEventId));
        allFailedEvents.addOrUpdate(failedEvent);
        jdbcConnection.closeConnection();
    }



}
