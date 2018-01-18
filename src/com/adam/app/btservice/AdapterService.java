/**
 * ===========================================================================
 * Copyright Adam Sample code
 * All Rights Reserved
 * ===========================================================================
 * 
 * File Name: AdapterService.java
 * Brief: 
 * 
 * Author: AdamChen
 * Create Date: 2018/1/18
 */

package com.adam.app.btservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import com.adam.app.util.Utils;

import java.lang.ref.SoftReference;

public class AdapterService extends Service {

    private static final String TAG = "AdapterService";

    /**
     * Handlers for incoming service calls
     */
    private IBinder mBinder = new AdapterServiceBinder(this);

    /**
     * The Binder implementation must be declared to be a static class, with the
     * AdapterService instance passed in the constructor. Furthermore, when the
     * AdapterService shuts down, the reference to the AdapterService must be
     * explicitly removed.
     * 
     * Otherwise, a memory leak can occur from repeated starting/stopping the
     * service...Please refer to android.os.Binder for further details on why an
     * inner instance class should be avoided.
     * 
     */
    private static class AdapterServiceBinder extends IBluetooth.Stub {

        private SoftReference<AdapterService> mService;
        
        public AdapterServiceBinder(AdapterService service) {
            this.mService = new SoftReference<AdapterService>(service);
        }

        
        @Override
        public int getScanMode() throws RemoteException {

            Utils.print(this, "getScanMode");

            return 0;
        }

        @Override
        public boolean setScanMode(int mode, int duration)
                throws RemoteException {

            Utils.print(this, "setScanMode");

            return false;
        }

    }

    @Override
    public IBinder onBind(Intent intent) {

        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {

        cleanup();
        return super.onUnbind(intent);
    }

    @Override
    public void onCreate() {

        super.onCreate();
        Utils.print(this, "onCreate");

    }

    void cleanup() {

    }

}
