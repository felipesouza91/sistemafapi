package com.sistemaf.domain.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.Schedules;

import java.util.concurrent.TimeUnit;

public interface RemoveOldFilesService {

    void removeOldTempFiles();
}
