var exec = require('cordova/exec');


exports.openWifiDialog = function(success, error) {
    exec(success, error, "ControlPanel", "wifi", []);
};
exports.openTimeZoneDialog = function(success, error) {
    exec(success, error, "ControlPanel", "timezone", []);
};
