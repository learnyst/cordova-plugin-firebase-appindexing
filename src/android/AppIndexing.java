package com.learnyst.appindexing;

import android.net.Uri;
import android.util.Log;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaWebView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.firebase.appindexing.Action;
import com.google.firebase.appindexing.FirebaseUserActions;
import com.google.firebase.appindexing.FirebaseAppIndex;
import com.google.firebase.appindexing.Indexable;
import com.google.firebase.appindexing.builders.Actions;

import com.google.android.gms.appindexing.Thing;

public class AppIndexing extends CordovaPlugin {

  private static final String TAG = "Firebase Indexing";
  private static final String VIEW_STARTED = "startView";
  private static final String VIEW_ENDED = "endView";
  private static final String INITIALIZE_INDEXING = "initializeIndexing";
  private static Uri BASE_URL;

  @Override
  public void initialize(CordovaInterface cordova, CordovaWebView webView)
  {
    super.initialize(cordova, webView);
  }

  @Override
  public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException
  {
    try {
      if (INITIALIZE_INDEXING.equals(action)) {
        this.initializeIndexing(args.getString(0));
        callbackContext.success();
        return true;
      } else if (VIEW_STARTED.equals(action)) {
        if(BASE_URL != null){
          this.startView(args.getString(0), args.getString(1));
          callbackContext.success();
        } else {
          callbackContext.error("Please initialize base URI");
          return false;
        }
        return true;
      } else if (VIEW_ENDED.equals(action)) {
        if(BASE_URL != null){
          this.endView(args.getString(0), args.getString(1));
          callbackContext.success();
        } else {
          callbackContext.error("Please initialize base URI");
          return false;
        }
        return true;
      }
    } catch (Exception e){
      Log.i("AppIndexing Exception","AppIndexing ExceptionExceptionExceptionExceptionExceptionException");
    }
    return false;  // Returning false results in a "MethodNotFound" error.
  }

  private void initializeIndexing(String uri){
    BASE_URL = Uri.parse(uri);
  }

  private void startView(String title, String webPath)
  {
    try {
      final String APP_URI = BASE_URL.buildUpon().appendPath(webPath).build().toString();
      final String TITLE = title;
      Log.d(TAG, "App Indexing API: startView Exception "+APP_URI+title+webPath);
      Indexable indexPage = new Indexable.Builder().setName(TITLE).setUrl(APP_URI).build();
      FirebaseAppIndex.getInstance().update(indexPage);
      FirebaseUserActions.getInstance().start(Actions.newView(TITLE, APP_URI));
    } catch(Exception e){
      Log.d(TAG, "App Indexing API: startView Exception ");
    }
  }

  private void endView(String title, String webPath)
  {
    try{
      final String APP_URI = BASE_URL.buildUpon().appendPath(webPath).build().toString();
      final String TITLE = title;
      Log.d(TAG, "App Indexing API: endView Exception "+APP_URI+title+webPath);
      FirebaseUserActions.getInstance().end(Actions.newView(TITLE, APP_URI));
    } catch(Exception e){
      Log.d(TAG, "App Indexing API: endView Exception ");
    }
  }
}