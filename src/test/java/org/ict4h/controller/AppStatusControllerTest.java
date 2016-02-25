package org.ict4h.controller;

import org.ict4h.AtomfeedConsoleApplication;
import org.ict4h.controllers.AppStatusController;
import org.ict4h.domain.AppStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(AtomfeedConsoleApplication.class)
public class AppStatusControllerTest {

    @Autowired
    private AppStatusController appStatusController;

    @Test
    public void shouldReturnFeedStatus() throws Exception {
        AppStatus appStatus = appStatusController.getAppStatus("anotherTestApp");
        System.out.println(appStatus);
    }
}
