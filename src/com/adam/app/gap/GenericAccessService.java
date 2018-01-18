/**
 * ===========================================================================
 * Copyright Adam Sample code
 * All Rights Reserved
 * ===========================================================================
 * 
 * File Name: GenericAccessService.java
 * Brief: 
 * 
 * Author: AdamChen
 * Create Date: 2018/1/18
 */

package com.adam.app.gap;

import com.adam.app.btservice.ProfileService;

public class GenericAccessService extends ProfileService {

    @Override
    protected IProfileServiceBinder initBinder() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected boolean start() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    protected boolean stop() {
        // TODO Auto-generated method stub
        return false;
    }

}
