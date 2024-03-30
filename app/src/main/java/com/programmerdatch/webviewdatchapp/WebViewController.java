package com.programmerdatch.webviewdatchapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.Map;

public class WebViewController {
    public WebViewController() {
    }
    public void webViewShouldOverwrite(WebView webView, String[] links, Activity activity)
    {
        webView.setWebViewClient(new WebViewClient()
        {
            @Override
            public boolean  shouldOverrideUrlLoading(WebView view, String url) {
                for(int i=0; i< links.length; i++)
                {
                    if (url != null && url.startsWith(links[i])) {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        activity.startActivity(browserIntent);
                        return true;

                    }
                }

                if (url != null && url.startsWith("whatsapp://")) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    activity.startActivity(browserIntent);
                    return true;

                }
                else if (url != null && url.startsWith("https://www.youtube.com")) {
                    Intent ytIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    activity.startActivity(ytIntent);
                    return true;

                }
                else if (url != null && url.startsWith("https://play.google.com/")) {
                    Intent psIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(psIntent);
                    return true;

                }
                else if (url != null && url.startsWith("https://m.facebook.com")) {
                    Intent fbIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(fbIntent);
                    return true;

                }
                else if (url != null && url.startsWith("https://twitter")) {
                    Intent twitterIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(twitterIntent);
                    return true;

                }
                else if (url != null && url.startsWith("https://www.instagram.com")) {
                    Intent twitterIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(twitterIntent);
                    return true;

                }
                else {
                    return false;
                }
            }


            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                appImage.clearAnimation();
                appImage.setVisibility(View.GONE);
                mySwipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(getApplicationContext().CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                if(networkInfo == null || !networkInfo.isConnected())
                {
                    Toast.makeText(HomeActivity.this, "Network Disconnected!", Toast.LENGTH_LONG).show();
                    errorTxt.setText("Network Disconnected!, Connect again & Reflesh");
                }
                else
                {
                    errorTxt.setText("Page Loading Failed!, Check Network & Reflesh");
                }

                loadErrorLayout.setVisibility(View.VISIBLE);
                errorReflesh.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String currentLink = view.getUrl();
                        webView.loadUrl(currentLink);
                    }
                });
            }

        });
    }
}
