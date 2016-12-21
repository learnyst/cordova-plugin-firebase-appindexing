var exec = require('cordova/exec');

function AppIndexing() {}

AppIndexing.prototype.initialize = function(success, error, baseUri) {
  exec(success, error, "AppIndexing", "initializeIndexing", [baseUri]);
};

AppIndexing.prototype.startView = function(success, error, title, webPath) {
  exec(success, error, "AppIndexing", "startView",  [title, webPath]);
};

AppIndexing.prototype.endView = function(success, error, title, webPath) {
  exec(success, error, "AppIndexing", "endView", [title, webPath]);
};

module.exports = new AppIndexing();
