/**
 * ===========================================================================
 * Copyright Adam Sample code
 * All Rights Reserved
 * ===========================================================================
 * 
 * File Name: Utils.java
 * Brief: 
 * 
 * Author: AdamChen
 * Create Date: 2018/1/18
 */

package com.adam.app.util;

import android.content.Context;
import android.content.ContextWrapper;
import android.os.ParcelUuid;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public final class Utils {

    private static final int MICROS_PER_UNIT = 625;

    static final int BD_ADDR_LEN = 6; // bytes

    static final int BD_UUID_LEN = 16; // bytes

    private static final boolean ISLOG = true;

    private static final String TAG = "BTDemo";

    public static void print(Object obj, String str) {
        if (ISLOG) {
            Log.i(TAG, obj.getClass().getSimpleName() + " " + str);
        }
    }
    
    public static void print(String str) {
        if (ISLOG) {
            Log.i(TAG, str);
        }
    }
    
    public static String getAddressStringFromByte(byte[] address) {

        if (address == null || address.length != BD_ADDR_LEN) {
            return null;
        }

        return String.format("%02X:%02X:%02X:%02X:%02X:%02X", address[0],
                address[1], address[2], address[3], address[4], address[5]);
    }

    // public static byte[] getByteAddress(BluetoothDevice device) {
    // return getBytesFromAddress(device.getAddress());
    // }

    public static byte[] getBytesFromAddress(String address) {

        int i, j = 0;
        byte[] output = new byte[BD_ADDR_LEN];

        for (i = 0; i < address.length(); i++) {
            if (address.charAt(i) != ':') {
                output[j] = (byte) Integer.parseInt(
                        address.substring(i, i + 2), BD_UUID_LEN);
                j++;
                i++;
            }
        }

        return output;
    }

    public static int byteArrayToInt(byte[] valueBuf) {

        return byteArrayToInt(valueBuf, 0);
    }

    public static short byteArrayToShort(byte[] valueBuf) {

        ByteBuffer converter = ByteBuffer.wrap(valueBuf);
        converter.order(ByteOrder.nativeOrder());
        return converter.getShort();
    }

    public static int byteArrayToInt(byte[] valueBuf, int offset) {

        ByteBuffer converter = ByteBuffer.wrap(valueBuf);
        converter.order(ByteOrder.nativeOrder());
        return converter.getInt(offset);
    }

    public static byte[] intToByteArray(int value) {

        ByteBuffer converter = ByteBuffer.allocate(4);
        converter.order(ByteOrder.nativeOrder());
        converter.putInt(value);
        return converter.array();
    }

    public static byte[] uuidToByteArray(ParcelUuid pUuid) {

        int length = BD_UUID_LEN;
        ByteBuffer converter = ByteBuffer.allocate(length);
        converter.order(ByteOrder.BIG_ENDIAN);
        long msb, lsb;
        UUID uuid = pUuid.getUuid();
        msb = uuid.getMostSignificantBits();
        lsb = uuid.getLeastSignificantBits();
        converter.putLong(msb);
        converter.putLong(8, lsb);
        return converter.array();
    }

    public static byte[] uuidsToByteArray(ParcelUuid[] uuids) {

        int length = uuids.length * BD_UUID_LEN;
        ByteBuffer converter = ByteBuffer.allocate(length);
        converter.order(ByteOrder.BIG_ENDIAN);
        UUID uuid;
        long msb, lsb;
        for (int i = 0; i < uuids.length; i++) {
            uuid = uuids[i].getUuid();
            msb = uuid.getMostSignificantBits();
            lsb = uuid.getLeastSignificantBits();
            converter.putLong(i * BD_UUID_LEN, msb);
            converter.putLong(i * BD_UUID_LEN + 8, lsb);
        }
        return converter.array();
    }

    public static ParcelUuid[] byteArrayToUuid(byte[] val) {

        int numUuids = val.length / BD_UUID_LEN;
        ParcelUuid[] puuids = new ParcelUuid[numUuids];
        int offset = 0;

        ByteBuffer converter = ByteBuffer.wrap(val);
        converter.order(ByteOrder.BIG_ENDIAN);

        for (int i = 0; i < numUuids; i++) {
            puuids[i] = new ParcelUuid(new UUID(converter.getLong(offset),
                    converter.getLong(offset + 8)));
            offset += BD_UUID_LEN;
        }
        return puuids;
    }

    public static String debugGetAdapterStateString(int state) {

        // switch (state) {
        // case BluetoothAdapter.STATE_OFF:
        // return "STATE_OFF";
        // case BluetoothAdapter.STATE_ON:
        // return "STATE_ON";
        // case BluetoothAdapter.STATE_TURNING_ON:
        // return "STATE_TURNING_ON";
        // case BluetoothAdapter.STATE_TURNING_OFF:
        // return "STATE_TURNING_OFF";
        // default:
        // return "UNKNOWN";
        // }
        return "UNKNOWN";
    }

    public static void copyStream(InputStream is, OutputStream os,
            int bufferSize) throws IOException {

        if (is != null && os != null) {
            byte[] buffer = new byte[bufferSize];
            int bytesRead = 0;
            while ((bytesRead = is.read(buffer)) >= 0) {
                os.write(buffer, 0, bytesRead);
            }
        }
    }

    public static void safeCloseStream(InputStream is) {

        if (is != null) {
            try {
                is.close();
            } catch (Throwable t) {
                Log.d(TAG, "Error closing stream", t);
            }
        }
    }

    public static void safeCloseStream(OutputStream os) {

        if (os != null) {
            try {
                os.close();
            } catch (Throwable t) {
                Log.d(TAG, "Error closing stream", t);
            }
        }
    }

    public static boolean checkCaller() {

        boolean ok = false;
        // // Get the caller's user id then clear the calling identity
        // // which will be restored in the finally clause.
        // int callingUser = UserHandle.getCallingUserId();
        // int callingUid = Binder.getCallingUid();
        // long ident = Binder.clearCallingIdentity();
        //
        // try {
        // // With calling identity cleared the current user is the foreground
        // user.
        // int foregroundUser = ActivityManager.getCurrentUser();
        // ok = (foregroundUser == callingUser);
        // if (!ok) {
        // // Always allow SystemUI/System access.
        // int systemUiUid = ActivityThread.getPackageManager().getPackageUid(
        // "com.android.systemui", UserHandle.USER_OWNER);
        // ok = (systemUiUid == callingUid) || (Process.SYSTEM_UID ==
        // callingUid);
        // }
        // } catch (Exception ex) {
        // Log.e(TAG, "checkIfCallerIsSelfOrForegroundUser: Exception ex=" +
        // ex);
        // ok = false;
        // } finally {
        // Binder.restoreCallingIdentity(ident);
        // }
        return ok;
    }

    public static boolean checkCallerAllowManagedProfiles(Context mContext) {

        if (mContext == null) {
            return checkCaller();
        }
        boolean ok = false;
        // // Get the caller's user id and if it's a managed profile, get it's
        // parents
        // // id, then clear the calling identity
        // // which will be restored in the finally clause.
        // int callingUser = UserHandle.getCallingUserId();
        // int callingUid = Binder.getCallingUid();
        // long ident = Binder.clearCallingIdentity();
        // try {
        // UserManager um = (UserManager)
        // mContext.getSystemService(Context.USER_SERVICE);
        // UserInfo ui = um.getProfileParent(callingUser);
        // int parentUser = (ui != null) ? ui.id : UserHandle.USER_NULL;
        // // With calling identity cleared the current user is the foreground
        // user.
        // int foregroundUser = ActivityManager.getCurrentUser();
        // ok = (foregroundUser == callingUser) ||
        // (foregroundUser == parentUser);
        // if (!ok) {
        // // Always allow SystemUI/System access.
        // int systemUiUid = ActivityThread.getPackageManager().getPackageUid(
        // "com.android.systemui", UserHandle.USER_OWNER);
        // ok = (systemUiUid == callingUid) || (Process.SYSTEM_UID ==
        // callingUid);
        // }
        // } catch (Exception ex) {
        // Log.e(TAG, "checkCallerAllowManagedProfiles: Exception ex=" + ex);
        // ok = false;
        // } finally {
        // Binder.restoreCallingIdentity(ident);
        // }
        return ok;
    }

    /**
     * Enforce the context has android.Manifest.permission.BLUETOOTH_ADMIN
     * permission. A {@link SecurityException} would be thrown if neither the
     * calling process or the application does not have BLUETOOTH_ADMIN
     * permission.
     * 
     * @param context
     *            Context for the permission check.
     */
    public static void enforceAdminPermission(ContextWrapper context) {

        context.enforceCallingOrSelfPermission(
                android.Manifest.permission.BLUETOOTH_ADMIN,
                "Need BLUETOOTH_ADMIN permission");
    }

    /**
     * Converts {@code millisecond} to unit. Each unit is 0.625 millisecond.
     */
    public static int millsToUnit(int milliseconds) {

        return (int) (TimeUnit.MILLISECONDS.toMicros(milliseconds) / MICROS_PER_UNIT);
    }
}