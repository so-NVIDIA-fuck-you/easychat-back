package org.itheima.easychatback.entity.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("appConfig")
public class AppConfig {


    /**
     * websocket端口
     */
    @Value("${ws.port}")
    private Integer wsPort;

    @Value("e:/webser/web_app/easychat")
    private String projectFolder;

    @Value("${admin.email}")
    private String adminEmails;



    public Integer getWsPort() {
        return wsPort;
    }

    public String getProjectFolder() {
        return projectFolder;
    }

    public String getAdminEmails() {
        return adminEmails;
    }


}
