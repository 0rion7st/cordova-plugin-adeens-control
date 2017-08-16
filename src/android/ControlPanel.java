package com.adeens;

import android.util.Log;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.provider.Settings;
import android.content.Intent;
import android.content.Context;
import 	android.content.ComponentName;
/**
 * This class echoes a string called from JavaScript.
 */
public class ControlPanel extends CordovaPlugin {

    private static final String TAG = "ControlPlugin";

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        Context context=this.cordova.getActivity().getApplicationContext();
        if (action.equals("wifi")) {
            this.launchActivity(context,"com.android.tv.settings",".connectivity.NetworkActivity",callbackContext);
            return true;
        }
        else if (action.equals("timezone")) {
            this.launchActivity(context,"com.android.tv.settings",".system.DateTimeActivity",callbackContext);
            return true;
        }
        else if (action.equals("display")) {
            this.launchActivity(context,"com.android.tv.settings",".device.display.DisplayActivity",callbackContext);
            return true;
        }
        else if (action.equals("appinfo")) {
            try {
                 Intent intent = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                 intent.setData(Uri.parse( "package:com.xwalk.adscreen"));
                 context.startActivity(intent);
                callbackContext.success(new String("Open app info..."));
                return true;
            } catch (Exception ex) {
                  Log.i(TAG, "Could not open app info dialog", ex);
                  callbackContext.error(new String("Open app info failed."));
             }
        }
        return false;
    }

    private void launchActivity(Context context, String packageName, String activityName, CallbackContext callbackContext)
    {
        try {
              Intent i = new Intent();
              i.setComponent(new ComponentName(packageName, new String(packageName+activityName)));
              i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
              context.startActivity(i);
              callbackContext.success(new String("Open "+packageName+activityName+"..."));
        } catch (Exception ex) {
              Log.i(TAG, "Could not open "+activityName+" dialog", ex);
              callbackContext.error(new String("Open "+packageName+activityName+" failed."));
        }
    }
}
