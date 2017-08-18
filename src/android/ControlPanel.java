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
import android.net.Uri;
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
        else if (action.equals("openLauncher")) {
            this.launchActivity(context,"eu.chainfire.tv.sideloadlauncher",".MainActivity",callbackContext);
            return true;
        }
        else if (action.equals("display")) {
            this.launchActivity(context,"com.android.tv.settings",".device.display.DisplayActivity",callbackContext);
            return true;
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
