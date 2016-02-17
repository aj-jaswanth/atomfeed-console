package org.ict4h.controller;

import org.ict4h.Application;
import org.ict4h.domain.AppConfig;
import org.ict4h.domain.AppConfigs;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.web.bind.annotation.RequestMethod;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class AppControllerTest {

    @Autowired
    private AppController appController;


    @Test
    public void shouldRetrieveConfig() {
        AppConfigs appDetails = appController.getAppDetails();
        assertThat(appDetails.size(), is((equalTo(2))));
        AppConfig appConfig = appDetails.get(0);
        assertThat(appConfig.getAppname(), is(equalTo("newTestApp")));
    }


}