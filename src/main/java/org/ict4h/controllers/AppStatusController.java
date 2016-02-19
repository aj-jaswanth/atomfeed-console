package org.ict4h.controllers;

import org.ict4h.domain.AppStatus;

import java.sql.SQLException;


public class AppStatusController {

    //String appname = "newTestApp";

    public AppStatus getAppStatus(String appname) throws SQLException {
        AppStatus appStatus = new AppStatus(appname);
        appStatus.setMarker();
        return appStatus;
    }
}
