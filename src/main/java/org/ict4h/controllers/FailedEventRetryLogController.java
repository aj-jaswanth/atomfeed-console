package org.ict4h.controllers;

import org.ict4h.atomfeed.client.domain.FailedEventRetryLog;
import org.ict4h.domain.AppConfig;
import org.ict4h.service.AppConfiguration;
import org.ict4h.service.FailedEventRetryLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

@Controller
public class FailedEventRetryLogController {

    private AppConfiguration appConfiguration;
    private FailedEventRetryLogService failedEventRetryLogService;

    @Autowired
    public FailedEventRetryLogController(AppConfiguration appConfiguration, FailedEventRetryLogService failedEventRetryLogService) {
        this.appConfiguration = appConfiguration;
        this.failedEventRetryLogService = failedEventRetryLogService;
    }



    @RequestMapping("/apps/{appName}/failedEvent/{eventId}/retryLog")
    @ResponseBody
    public List<FailedEventRetryLog> getFailedEventRetryLogs(@PathVariable String appName,@PathVariable String eventId) throws SQLException, UnsupportedEncodingException {
        AppConfig appConfig = appConfiguration.getAppConfigForApp(appName);
        return failedEventRetryLogService.getFailedEventRetryLogStatusList(appConfig,eventId);
    }
}
