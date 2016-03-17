package org.ict4h.domain;

import java.net.URI;

public class Feed {

    private URI feedUri;
    private String lastReadEntry;
    private int countOfFailedEvents;

    public Feed(URI feedUri, String lastReadEntry, int countOfFailedEvents) {
        this.feedUri = feedUri;
        this.lastReadEntry = lastReadEntry;
        this.countOfFailedEvents = countOfFailedEvents;
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
