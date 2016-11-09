package org.ict4h.controller;

import org.ict4h.AtomfeedConsoleApplication;
import org.ict4h.BaseTest;
import org.ict4h.controllers.AppStatusController;
import org.ict4h.domain.Feed;
import org.ict4h.domain.Feeds;
import org.ict4h.util.Database;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(AtomfeedConsoleApplication.class)
public class FeedsControllerTest extends BaseTest {

    private static final String CREATE_MARKERS= "insert into markers " +
            "(feed_uri, last_read_entry_id, feed_uri_for_last_read_entry) " +
            "values " +
            "('feed_uri', 'last_read_entry_id', 'feed_uri_for_last_read_entry');";

    @Autowired
    private AppStatusController appStatusController;

    @Test
    public void shouldReturnFeedStatus() throws Exception {

        createMarkers("feed_Uri1");
        createMarkers("feed_Uri2");
        createMarkers("feed_Uri3");
        createMarkers("feed_Uri4");
        createMarkers("feed_Uri5");

        Feeds feeds = appStatusController.getAppStatus("testApp");
        assertThat(feeds.size(), is(equalTo(5)));

        Feed feed = feeds.get(0);
        assertThat(feed.getFeedUri().toString(), is(equalTo("feed_Uri1")));
    }

    private void createMarkers(String feedUri) throws SQLException {
        String markersSql = CREATE_MARKERS.replace("'feed_uri'", "'" + feedUri + "'");
        Database.runStatement(markersSql);
    }
}
