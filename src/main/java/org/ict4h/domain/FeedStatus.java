package org.ict4h.domain;

import org.ict4h.atomfeed.client.domain.Marker;
import org.ict4h.atomfeed.client.repository.AllFailedEvents;


import java.net.URI;

public class FeedStatus {

    private URI feedUri;
    private String lastReadEntry;
    private int countOfFailedEvents;

    public FeedStatus(URI feedUri, String lastReadEntry, int countOfFailedEvents) {
        this.feedUri = feedUri;
        this.lastReadEntry = lastReadEntry;
        this.countOfFailedEvents = countOfFailedEvents;
    }

    public FeedStatus(Marker marker,AllFailedEvents allFailedEvents) {
        this.feedUri = marker.getFeedUri();
        this.lastReadEntry = marker.getLastReadEntryId();
        this.countOfFailedEvents = allFailedEvents.getNumberOfFailedEvents(this.getFeedUri().toString());
    }

    public URI getFeedUri() {
        return feedUri;
    }

    public String getLastReadEntry() {
        return lastReadEntry;
    }

    public int getCountOfFailedEvents(){
        return countOfFailedEvents;
    }
}
