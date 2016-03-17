package org.ict4h.controllers;

import org.ict4h.domain.configuration.AppConfig;
import org.ict4h.service.AppConfiguration;
import org.ict4h.service.FailedEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

@Controller
public class ResetRetryEventController {

    private FailedEventService failedEventService;
    private AppConfiguration appConfiguration;

    @Autowired
    public ResetRetryEventController(AppConfiguration appConfiguration, FailedEventService failedEventService) {
        this.appConfiguration = appConfiguration;
        this.failedEventService = failedEventService;
    }

    @RequestMapping(value = "/apps/{appName}/failedEvent/{eventId}/resetRetryCount", method = RequestMethod.POST)
    @ResponseBody
    public void resetRetries(@PathVariable String appName,@PathVariable String eventId) throws SQLException, UnsupportedEncodingException {
        AppConfig appConfig = appConfiguration.getAppConfigForApp(appName);
        failedEventService.resetRetriesService(appConfig,eventId);
    }
}
