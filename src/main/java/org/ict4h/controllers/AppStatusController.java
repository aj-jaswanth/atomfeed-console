package org.ict4h.controllers;

import org.ict4h.domain.Feeds;
import org.ict4h.service.AppConfiguration;
import org.ict4h.service.AppStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;


@Controller
public class AppStatusController {

    private AppConfiguration appConfiguration;
    private AppStatusService appStatusService;

    @Autowired
    public AppStatusController(AppConfiguration appConfiguration, AppStatusService appStatusService) {
        this.appConfiguration = appConfiguration;
        this.appStatusService = appStatusService;
    }

    @RequestMapping("/apps/{appName}/feedStatus")
    @ResponseBody
    public Feeds getAppStatus(@PathVariable("appName") String appName) throws SQLException {
        return appStatusService.getAppStatus(appConfiguration.getAppConfigForApp(appName));
    }
}
