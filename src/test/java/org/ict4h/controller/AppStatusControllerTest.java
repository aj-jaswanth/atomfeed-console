package org.ict4h.controller;

import org.ict4h.controllers.AppStatusController;
import org.ict4h.domain.AppStatus;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;

import java.sql.SQLException;

public class AppStatusControllerTest {

    @Autowired
    private AppStatusController appStatusController = new AppStatusController();

    @Test
    public void shouldReturnFeedStatus() throws Exception {
        AppStatus appStatus = appStatusController.getAppStatus("newTestApp");
        System.out.println(appStatus.getMarker());
    }
}
