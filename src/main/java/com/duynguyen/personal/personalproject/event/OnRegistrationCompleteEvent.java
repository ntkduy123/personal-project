package com.duynguyen.personal.personalproject.event;

import com.duynguyen.personal.personalproject.domain.MyUser;
import org.springframework.context.ApplicationEvent;


public class OnRegistrationCompleteEvent extends ApplicationEvent {

    private String appUrl;
    private MyUser user;

    public OnRegistrationCompleteEvent(
            MyUser user, String appUrl) {
        super(user);

        this.user = user;
        this.appUrl = appUrl;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    public MyUser getUser() {
        return user;
    }

    public void setUser(MyUser user) {
        this.user = user;
    }
}
