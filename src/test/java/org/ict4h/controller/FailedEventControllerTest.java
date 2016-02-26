package org.ict4h.controller;

import org.ict4h.AtomfeedConsoleApplication;
import org.ict4h.atomfeed.client.domain.FailedEvent;
import org.ict4h.controllers.FailedEventController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(AtomfeedConsoleApplication.class)
public class FailedEventControllerTest {

    @Autowired
    private FailedEventController failedEventController;
    private List<FailedEvent> failedEventList = new ArrayList<>();

    @Test
    public void shouldReturnFailedEvents() throws Exception {

        failedEventList = failedEventController.getFailedEventStatus("newTestApp","http://localhost:8080/openelis/ws/feed/patient/recent");
        System.out.println(failedEventList);

    }

}


