# cordova-plugin-firebase-appindexing

## Installation
cordova plugin add https://github.com/shankar-mahesh/cordova-plugin-firebase-appindexing

## Usage
##1 . IMPORTANT Ensure you have deeplinking in your application. If not kindly use https://github.com/nordnet/cordova-universal-links-plugin

##2
	i.   Create a firebase account 
	ii.  Add your android app to firebase account
	iii. Download google-services.json from firebase website and save in your cordova-app base folder

##3 Setup Appindexing

Initialise : 
```js
AppIndexing.initialize(function success(success){ /* success code */ }, function failure(failure){ /*failure code*/ }, 'http://example.com'); 
```

Start View :
```js
AppIndexing.startView(function success(success){ /* success code */ }, function failure(failure){ /*failure code*/ }, 'title of the view ', 'website_path');
```
do not give full URL only path

End View :
```js
AppIndexing.endView(function success(success){ /* success code */ }, function failure(failure){ /*failure code*/ }, 'title of the view ', 'website_path');
```
do not give full URL only path


# If cordova-plugin-firebase installed. Kindly comment <hook src="scripts/after_prepare.js" type="after_prepare" /> from plugin.xml file 
