package org.ict4h.controller;

import org.ict4h.AtomfeedConsoleApplication;
import org.ict4h.atomfeed.client.domain.FailedEventRetryLog;;
import org.ict4h.controllers.FailedEventRetryLogController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(AtomfeedConsoleApplication.class)
public class FailedEventRetryLogControllerTest {

    @Autowired
    private FailedEventRetryLogController failedEventRetryLogController;
    private List<FailedEventRetryLog> failedEventRetryLogList;

    @Test
    public void shouldReturnFailedEvents() throws Exception {

        failedEventRetryLogList=failedEventRetryLogController.getFailedEventRetryLogs("newTestApp","tag:atomfeed.ict4h.org:6e693eb7-059d-4f0b-8e69-7a72e57b5a50");
        System.out.println(failedEventRetryLogList);
    }

}
