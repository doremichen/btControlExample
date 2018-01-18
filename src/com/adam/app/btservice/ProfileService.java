/**
 * ===========================================================================
 * Copyright Adam Sample code
 * All Rights Reserved
 * ===========================================================================
 * 
 * File Name: ProfileService.java
 * Brief: 
 * 
 * Author: AdamChen
 * Create Date: 2018/1/18
 */

package com.adam.app.btservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public abstract class ProfileService extends Service {

    public static interface IProfileServiceBinder extends IBinder {
        public boolean cleanup();
    }

    protected IProfileServiceBinder mBinder;

    protected abstract IProfileServiceBinder initBinder();

    protected abstract boolean start();

    protected abstract boolean stop();

    protected boolean cleanup() {
        return true;
    }

    protected ProfileService() {

    }

    @Override
    protected void finalize() throws Throwable {
        // TODO Auto-generated method stub
        super.finalize();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return mBinder;
    }

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // TODO Auto-generated method stub
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        // TODO Auto-generated method stub
        return super.onUnbind(intent);
    }

}
