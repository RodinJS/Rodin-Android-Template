package com.rodinapp.rodin;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {

    private WebView webView;
    private Bundle webViewBundle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = (WebView) findViewById(R.id.webView);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());

        webView.setWebViewClient(new WebViewClient(){
            @SuppressWarnings("deprecation")

            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                //todo differentiate into error types (no connection...)

                switch (errorCode)
                {
                    case ERROR_AUTHENTICATION:
                    case ERROR_BAD_URL:
                    case ERROR_CONNECT:
                    case ERROR_FAILED_SSL_HANDSHAKE:
                    case ERROR_FILE:
                    case ERROR_FILE_NOT_FOUND:
                    case ERROR_HOST_LOOKUP:
                        webView.loadUrl("file:///android_asset/404.html");
                        break;
                    default:
                        //do stuff
                }
            }

            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onReceivedError(WebView view, WebResourceRequest req, WebResourceError rerr) {
                onReceivedError(view, rerr.getErrorCode(), rerr.getDescription().toString(), req.getUrl().toString());
            }
        });

        webView.getSettings().setJavaScriptEnabled(true);

        //not sure is we really need this
        if (webViewBundle == null)
            webView.loadUrl(getResources().getString(R.string.url) + "?androidWebview=true");
        else
            webView.restoreState(webViewBundle);

    }

    @Override
    public void onResume() {
        super.onResume();
        //todo resume rodin stuff
        webView.restoreState(webViewBundle);
    }

    @Override
    public void onPause() {
        super.onPause();
        //todo pause rodin stuff
        webView.saveState(webViewBundle);
    }
}
