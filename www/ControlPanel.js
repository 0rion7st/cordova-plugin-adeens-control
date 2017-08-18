var exec = require('cordova/exec');


exports.openWifiDialog = function(success, error) {
    exec(success, error, "ControlPanel", "wifi", []);
};
exports.openTimeZoneDialog = function(success, error) {
    exec(success, error, "ControlPanel", "timezone", []);
};
exports.openDisplayDialog = function(success, error) {
    exec(success, error, "ControlPanel", "display", []);
};

exports.openAppInfoDialog = function(success, error) {
    exec(success, error, "ControlPanel", "openMinixLauncher", []);
};
