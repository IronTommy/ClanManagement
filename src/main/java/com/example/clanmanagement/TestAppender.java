package com.example.clanmanagement;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;

import java.util.ArrayList;
import java.util.List;

public class TestAppender extends AppenderBase<ILoggingEvent> {

    private List<ILoggingEvent> loggingEvents = new ArrayList<>();

    @Override
    protected void append(ILoggingEvent eventObject) {
        loggingEvents.add(eventObject);
    }

    public List<ILoggingEvent> getLoggingEvents() {
        return loggingEvents;
    }

    public void clearLoggingEvents() {
        loggingEvents.clear();
    }
}
