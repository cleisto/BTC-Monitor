package com.btcmonitor.app;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Full-screen: remove title bar and status bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        );

        // Create WebView programmatically and set as content view
        webView = new WebView(this);
        setContentView(webView);

        // Dark background to match app (prevents white flash on load)
        webView.setBackgroundColor(Color.parseColor("#020617"));

        WebSettings settings = webView.getSettings();

        // Enable JavaScript
        settings.setJavaScriptEnabled(true);

        // Enable DOM storage (localStorage / sessionStorage)
        settings.setDomStorageEnabled(true);

        // Allow file access from file:// URLs
        settings.setAllowFileAccess(true);

        // Allow universal access from file URLs (needed for fetch() inside file://)
        settings.setAllowUniversalAccessFromFileURLs(true);

        // Allow file access from file URLs
        settings.setAllowFileAccessFromFileURLs(true);

        // Enable mixed content (file:// loading remote resources like CDN scripts)
        // Required for CoinGecko API calls from a file:// origin
        settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);

        // Improve rendering
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        settings.setBuiltInZoomControls(false);
        settings.setDisplayZoomControls(false);

        // Cache mode - use cache when available, fall back to network
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);

        // Keep WebView inside the app (no external browser)
        webView.setWebViewClient(new WebViewClient());

        // Load the HTML file from assets
        webView.loadUrl("file:///android_asset/index.html");
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        webView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        webView.onResume();
    }
}
