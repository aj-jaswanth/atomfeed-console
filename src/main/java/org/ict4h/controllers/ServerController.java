package org.ict4h.controllers;

import org.ict4h.domain.Servers;
import org.ict4h.service.ServerConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ServerController {
    private ServerConfiguration serverConfiguration;

    @Autowired
    public ServerController(ServerConfiguration serverConfiguration) {
        this.serverConfiguration = serverConfiguration;
    }

    @RequestMapping("/config")
    @ResponseBody
    public Servers getServerDetails() {
        return serverConfiguration.getServerDetails();
    }
}


