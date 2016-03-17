package org.ict4h.service;

import org.apache.log4j.Logger;
import org.ict4h.atomfeed.client.domain.FailedEventRetryLog;
import org.ict4h.atomfeed.client.repository.AllFailedEvents;
import org.ict4h.atomfeed.client.repository.jdbc.AllFailedEventsJdbcImpl;
import org.ict4h.domain.configuration.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.acl.LastOwnerException;
import java.util.List;

@Component
public class FailedEventRetryLogService {
    private static Logger logger = Logger.getLogger(FailedEventRetryLogService.class);
    @Autowired
    private AppStatusService appStatusService;

    public List<FailedEventRetryLog> getFailedEventRetryLogStatusList(AppConfig appConfig, String eventId){

        AppStatusService.AtomfeedConsoleConnectionProvider jdbcConnection = (AppStatusService.AtomfeedConsoleConnectionProvider) appStatusService.getJdbcConnection(appConfig.getAppName());
        AllFailedEvents allFailedEvents = new AllFailedEventsJdbcImpl(jdbcConnection);
        List<FailedEventRetryLog> failedEventRetryLogList = allFailedEvents.getFailedEventRetryLogs(eventId);
        logger.debug(String.format("Read List of failed event retry log for eventId %s : %s",eventId,failedEventRetryLogList));
        jdbcConnection.closeConnection();
        return failedEventRetryLogList;

    }
}
