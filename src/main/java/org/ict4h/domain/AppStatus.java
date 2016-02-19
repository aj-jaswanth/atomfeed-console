package org.ict4h.domain;

import org.ict4h.atomfeed.client.domain.Marker;

import java.util.ArrayList;
import java.util.List;

public class AppStatus extends ArrayList<Marker> {

    public AppStatus(List<Marker> markerList) {
        super(markerList);
    }
}
