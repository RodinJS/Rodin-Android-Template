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

        webView.setWebViewClient(new WebViewClient());

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
