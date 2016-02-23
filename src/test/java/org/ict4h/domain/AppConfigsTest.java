package org.ict4h.domain;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;
import static org.mockito.Matchers.isNull;

public class AppConfigsTest {

    @Test
    public void shouldRetrieveAppConfigForAName() {
        AppConfigs appConfigs = new AppConfigs();
        AppConfig app1 = new AppConfig("app1", null, null, null);
        appConfigs.add(app1);
        AppConfig app2 = new AppConfig("app2", null, null, null);
        appConfigs.add(app2);
        AppConfig app3 = new AppConfig("app3", null, null, null);
        appConfigs.add(app3);

        assertThat(appConfigs.getForApp("app1"), is(equalTo(app1)));
        assertNull(appConfigs.getForApp("app5"));
    }

    @Test
    public void shouldNotFailIfNullPassedIn() {
        AppConfigs appConfigs = new AppConfigs();
        appConfigs.add(new AppConfig("app1", null, null, null));
        appConfigs.getForApp("newTestApp");
    }

}