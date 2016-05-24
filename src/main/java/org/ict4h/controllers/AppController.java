package org.ict4h.controllers;

import org.ict4h.domain.configuration.AppConfig;
import org.ict4h.domain.configuration.AppConfigs;
import org.ict4h.service.AppConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AppController {

    private AppConfiguration appConfiguration;

    @Autowired
    public AppController(AppConfiguration appConfiguration) {this.appConfiguration = appConfiguration;}

    @RequestMapping("/apps")
    @ResponseBody
    public AppConfigs getAppDetails() {return appConfiguration.getAppConfigs();}
}
