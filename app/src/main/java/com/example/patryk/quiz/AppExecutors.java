package com.example.patryk.quiz;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Patryk on 2018-01-04.
 */

@Singleton
public class AppExecutors {
    private final ExecutorService diskIO;
    private final ExecutorService networkIO;

    public AppExecutors(ExecutorService diskIO, ExecutorService networkIO) {
        this.diskIO = diskIO;
        this.networkIO = networkIO;
    }

    @Inject
    public AppExecutors(){
        this(Executors.newSingleThreadExecutor(), Executors.newFixedThreadPool(3));
    }

    public ExecutorService getDiskIO(){
        return diskIO;
    }

    public ExecutorService getNetworkIO(){
        return networkIO;
    }
}
