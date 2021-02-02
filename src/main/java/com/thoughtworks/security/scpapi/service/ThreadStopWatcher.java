package com.thoughtworks.security.scpapi.service;

public interface ThreadStopWatcher {
    void beforeRun();

    void afterRun();

    void onSuccess();

    void onFailed();
}
