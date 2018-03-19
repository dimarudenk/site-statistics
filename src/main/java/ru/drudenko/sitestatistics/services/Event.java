package ru.drudenko.sitestatistics.services;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Event {
    private String clientId;
    private String pageId;

    public Event() {
    }

    public Event(String clientId, String pageId) {
        this.clientId = clientId;
        this.pageId = pageId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public String getClientId() {
        return clientId;
    }

    public String getPageId() {
        return pageId;
    }
}
