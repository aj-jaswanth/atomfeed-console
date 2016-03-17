package org.ict4h.controller;

import org.ict4h.AtomfeedConsoleApplication;
import org.ict4h.atomfeed.client.domain.FailedEventRetryLog;;
import org.ict4h.controllers.FailedEventRetryLogController;
import org.ict4h.util.Database;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(AtomfeedConsoleApplication.class)
public class FailedEventRetryLogControllerTest {
    private int failedEventRetryId = 1;

    private int failedEventId = 1;

    private static final String CREATE_FAILED_EVENT = "insert into failed_events " +
            "(id, feed_uri, failed_at, error_message, event_id, event_content, retries) " +
            "values " +
            "(identifier, 'feed_uri', now(), 'error_message', 'event_id', 'event_content', 5);";

    private static final String CREATE_FAILED_EVENT_RETRY_LOG = "insert into failed_event_retry_log " +
            "(id, feed_uri, failed_at, error_message, event_id, event_content) " +
            "values " +
            "(identifier, 'feed_uri', now(), 'error_message', 'event_id', 'event_content');";

    @Autowired
    private FailedEventRetryLogController failedEventRetryLogController;
    private List<FailedEventRetryLog> failedEventRetryLogList;

    @Before
    public void setUp() throws SQLException {
        //Database.createDatabase();

    }

    @Test
    public void shouldReturnFailedEvents() throws Exception {

        //createFailedEvent("event_id");
        createFailedEventRetryLog("event_id");
        createFailedEventRetryLog("event_id");
        createFailedEventRetryLog("event_id");
        createFailedEventRetryLog("event_id");

        failedEventRetryLogList=failedEventRetryLogController.getFailedEventRetryLogs("testApp","event_id");
        assertThat(failedEventRetryLogList.size(), is(equalTo(4)));

    }

    private void createFailedEventRetryLog(String eventId) throws SQLException {

        String failedEventRetryLogSql = CREATE_FAILED_EVENT_RETRY_LOG.replace("'event_id'", "'" + eventId + "'").replace("identifier", String.valueOf(failedEventRetryId));
        failedEventRetryId++;
        Database.runStatement(failedEventRetryLogSql);
    }

    private void createFailedEvent(String eventId) throws SQLException {
        String failedEventSql = CREATE_FAILED_EVENT.replace("'event_id'", "'" + eventId + "'").replace("identifier", String.valueOf(failedEventId));
        failedEventId++;
        Database.runStatement(failedEventSql);
    }

}
