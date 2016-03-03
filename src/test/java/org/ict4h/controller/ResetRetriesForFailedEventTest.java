package org.ict4h.controller;

import org.ict4h.AtomfeedConsoleApplication;
import org.ict4h.controllers.ResetRetryEventController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(AtomfeedConsoleApplication.class)
public class ResetRetriesForFailedEventTest {

    @Autowired
    private ResetRetryEventController resetRetryEventController;

    @Test
    public void shouldResetRetries() throws Exception {
        resetRetryEventController.resetRetries("newTestApp","tag:atomfeed.ict4h.org:6e693eb7-059d-4f0b-8e69-7a72e57b5a50");
    }

}
