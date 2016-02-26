package org.ict4h.controllers;

import org.ict4h.atomfeed.client.domain.FailedEvent;
import org.ict4h.domain.AppConfig;
import org.ict4h.service.AppConfiguration;
import org.ict4h.service.FailedEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.List;


@Controller
public class FailedEventController {

    private AppConfiguration appConfiguration;
    private FailedEventService failedEventService;

    @Autowired
    public FailedEventController(AppConfiguration appConfiguration, FailedEventService failedEventService) {
        this.appConfiguration = appConfiguration;
        this.failedEventService = failedEventService;
    }

    @RequestMapping("/apps/{appName}/failedEvent")
    @ResponseBody
    public List<FailedEvent> getFailedEventStatus(@PathVariable("appName") String appName, @RequestParam(value="feedUri",required=true) String feedUri) throws SQLException, UnsupportedEncodingException {
        AppConfig appConfig = appConfiguration.getAppConfigForApp(appName);
        return failedEventService.failedEventStatus(appConfig, URLDecoder.decode(feedUri, "utf-8"));
    }

}
