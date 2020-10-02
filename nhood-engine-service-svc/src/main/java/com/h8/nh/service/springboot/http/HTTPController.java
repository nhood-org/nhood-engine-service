package com.h8.nh.service.springboot.http;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HTTPController {

    @Value("${app.name}")
    private String appName;

    @Value("${app.version}")
    private String appVersion;

    public HTTPController() {
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public String home() {
        return "<!DOCTYPE html><html><body>\n"
                + "<p style=\"font-family:'Lucida Console', monospace; font-size: small;\">"
                + appName + " : " + appVersion
                + "</p>"
                + "</body></html>";
    }
}
