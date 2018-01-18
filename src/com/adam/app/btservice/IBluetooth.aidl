/** 
 * This is java framework service interface definition
 * 
 */

package com.adam.app.btservice;


/**
 * if add new function must update AdapterService@AdapterServiceBinder
 */
interface IBluetooth
{
   
   int getScanMode();
   boolean setScanMode(int mode, int duration);
    
}
