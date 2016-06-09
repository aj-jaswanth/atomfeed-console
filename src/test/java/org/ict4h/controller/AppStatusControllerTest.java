package org.ict4h.controller;

import org.ict4h.AtomfeedConsoleApplication;
import org.ict4h.controllers.AppController;
import org.ict4h.domain.configuration.AppConfig;
import org.ict4h.domain.configuration.AppConfigs;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(AtomfeedConsoleApplication.class)
public class AppStatusControllerTest {

    @Autowired
    private AppController appController;

    @Value("${../../resources/application.yml}")
    @Test
    public void shouldRetrieveConfig() {
        AppConfigs appDetails = appController.getAppDetails();
        assertThat(appDetails.size(), is((equalTo(1))));
        AppConfig appConfig = appDetails.get(0);
        assertThat(appConfig.getAppName(), is(equalTo("testApp")));
    }
}