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
/**
 * This class echoes a string called from JavaScript.
 */
public class ControlPanel extends CordovaPlugin {

    private static final String TAG = "ControlPlugin";

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("wifi")) {
            this.wifi(callbackContext);
            return true;
        }
        else if (action.equals("timezone")) {
            this.timezone(callbackContext);
            return true;
        }
        return false;
    }

    private void timezone(CallbackContext callbackContext) {
        try {
          Context context=this.cordova.getActivity().getApplicationContext();
          Intent setting = new Intent(Settings.ACTION_DATE_SETTINGS);
          setting.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
          context.startActivity(setting);
          callbackContext.success("Open WIFI settings...");
        } catch (Exception ex) {
          Log.i(TAG, "Could not open timezone dialog", ex);
          callbackContext.error("Open timezone failed.");
        }
    }
    private void wifi(CallbackContext callbackContext) {
        try {
         Context context=this.cordova.getActivity().getApplicationContext();
         Intent setting = new Intent(Settings.ACTION_WIFI_SETTINGS);
         setting.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
         context.startActivity(setting);
         callbackContext.success("Open TimeZone settings...");
        } catch (Exception ex) {
          Log.i(TAG, "Could not open wifi dialog", ex);
          callbackContext.error("Open wifi failed.");
        }
    }
}
