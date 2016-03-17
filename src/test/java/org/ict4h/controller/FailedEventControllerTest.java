package org.ict4h.controller;

import org.ict4h.AtomfeedConsoleApplication;
import org.ict4h.atomfeed.client.domain.FailedEvent;
import org.ict4h.controllers.FailedEventController;
import org.ict4h.util.Database;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(AtomfeedConsoleApplication.class)
public class FailedEventControllerTest {

    private int failedEventId = 1;

    private static final String CREATE_FAILED_EVENT = "insert into failed_events " +
            "(id, feed_uri, failed_at, error_message, event_id, event_content, retries) " +
            "values " +
            "(identifier, 'feed_uri', now(), 'error_message', 'event_id', 'event_content', 5);";
    @Autowired
    private FailedEventController failedEventController;
    private List<FailedEvent> failedEventList = new ArrayList<>();

    @Before
    public void setUp() throws SQLException {
        Database.createDatabase();
    }

    @Test
    public void shouldReturnFailedEvents() throws Exception {
        createFailedEvent("event_id");
        createFailedEvent("event_id1");
        createFailedEvent("event_id2");
        createFailedEvent("event_id3");
        createFailedEvent("event_id4");
        createFailedEvent("event_id5");

        failedEventList = failedEventController.getFailedEventStatus("testApp", "feed_uri");
        assertThat(failedEventList.size(), is(equalTo(6)));

        FailedEvent failedEvent = failedEventList.get(0);
        assertThat(failedEvent.getEventId(), is(equalTo("event_id")));
    }

    private void createFailedEvent(String eventId) throws SQLException {
        String failedEventSql = CREATE_FAILED_EVENT.replace("'event_id'", "'" + eventId + "'").replace("identifier", String.valueOf(failedEventId));
        failedEventId++;
        Database.runStatement(failedEventSql);
    }

}


