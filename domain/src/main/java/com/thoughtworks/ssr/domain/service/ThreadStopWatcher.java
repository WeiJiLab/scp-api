package com.thoughtworks.ssr.domain.service;

public interface ThreadStopWatcher {
    void beforeRun();

    void afterRun();

    void onSuccess();

    void onFailed();
}
