package com.zakaria.mobelite.commun;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by zakariaazizi on 12/23/18.
 */

public class Utils {

    public static boolean checkNetworkState(Context mApplication) {
        ConnectivityManager mConnectivityManager = (ConnectivityManager) mApplication.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = mConnectivityManager.getActiveNetworkInfo();
        boolean isConnectedToNetwork = activeNetwork != null && activeNetwork.isConnected();
        return isConnectedToNetwork;
    }

}
