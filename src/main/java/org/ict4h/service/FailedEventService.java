package org.ict4h.service;

import org.ict4h.atomfeed.client.domain.FailedEvent;
import org.ict4h.atomfeed.client.repository.AllFailedEvents;
import org.ict4h.atomfeed.client.repository.jdbc.AllFailedEventsJdbcImpl;
import org.ict4h.domain.configuration.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FailedEventService {

    @Autowired
    private AppStatusService appStatusService;


    public List<FailedEvent> failedEventStatus (AppConfig appConfig,String feedURI){

        AppStatusService.AtomfeedConsoleConnectionProvider jdbcConnection = (AppStatusService.AtomfeedConsoleConnectionProvider) appStatusService.getJdbcConnection(appConfig.getAppName());
        AllFailedEvents allFailedEvents = new AllFailedEventsJdbcImpl(jdbcConnection);
        List<FailedEvent> failedEventList = allFailedEvents.getFailedEvents(feedURI);
        jdbcConnection.closeConnection();
        return failedEventList;

    }

    public void resetRetriesService(AppConfig appConfig,String failedEventId){
        AppStatusService.AtomfeedConsoleConnectionProvider jdbcConnection = (AppStatusService.AtomfeedConsoleConnectionProvider) appStatusService.getJdbcConnection(appConfig.getAppName());
        AllFailedEvents allFailedEvents = new AllFailedEventsJdbcImpl(jdbcConnection);
        FailedEvent failedEvent=allFailedEvents.getByEventId(failedEventId);
        failedEvent.setRetries(0);
        allFailedEvents.addOrUpdate(failedEvent);
        jdbcConnection.closeConnection();
    }



}
