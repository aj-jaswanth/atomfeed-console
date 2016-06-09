package org.ict4h.controller;

import org.ict4h.AtomfeedConsoleApplication;
import org.ict4h.controllers.ResetRetryEventController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.ict4h.util.Database.*;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(AtomfeedConsoleApplication.class)
public class ResetRetriesForFailedEventTest {

    private int failedEventId = 1;

    private static final String CREATE_FAILED_EVENT = "insert into failed_events " +
            "(id, feed_uri, failed_at, error_message, event_id, event_content, retries) " +
            "values " +
            "(identifier, 'feed_uri', now(), 'error_message', 'event_id', 'event_content', 5);";

    private static String RETRIEVE_RETRY_COUNT = "select * from failed_events " +
            "where event_id = ? ";
    @Autowired
    private ResetRetryEventController resetRetryEventController;

    @Before
    public void setUp() throws SQLException {
    }

    @Test
    public void shouldResetRetries() throws Exception {

        int result;

        resetRetryEventController.resetRetries("testApp","event_id1");
        result= runStatement(RETRIEVE_RETRY_COUNT,"event_id1");
        assertThat(result,is(equalTo(0)));

        resetRetryEventController.resetRetries("testApp","event_id2");
        result= runStatement(RETRIEVE_RETRY_COUNT,"event_id2");
        assertThat(result,is(equalTo(0)));

        resetRetryEventController.resetRetries("testApp","event_id3");
        result= runStatement(RETRIEVE_RETRY_COUNT,"event_id3");
        assertThat(result,is(equalTo(0)));

        resetRetryEventController.resetRetries("testApp","event_id4");
        result= runStatement(RETRIEVE_RETRY_COUNT,"event_id4");
        assertThat(result,is(equalTo(0)));
    }

    private void createFailedEvent(String eventId) throws SQLException {
        String failedEventSql = CREATE_FAILED_EVENT.replace("'event_id'", "'" + eventId + "'").replace("identifier", String.valueOf(failedEventId));
        failedEventId++;
        runStatement(failedEventSql);
    }
}
