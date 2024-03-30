package com.programmerdatch.webviewdatchapp;


import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {
    ImageView appImage;
    Button errorReflesh;
    TextView errorTxt;
    WebView webView;
    LinearLayout loadErrorLayout;

    SwipeRefreshLayout mySwipeRefreshLayout;

    private static final int MY_REQUEST_CODE = 107;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        mySwipeRefreshLayout = (SwipeRefreshLayout)this.findViewById(R.id.swipeContainer);
        webView = findViewById(R.id.webView);
        appImage = findViewById(R.id.appImage);
        errorReflesh = findViewById(R.id.errorRefleshBtn);
        errorTxt = findViewById(R.id.errorTxt);
        loadErrorLayout = findViewById(R.id.loadErrorLayout);
        webView.getSettings().setJavaScriptEnabled(true);
        WebSettings webSettings = webView.getSettings();
        webSettings.setDomStorageEnabled(true);
        webView.loadUrl("https://google.com/");

        webView.setOverScrollMode(WebView.OVER_SCROLL_NEVER);



        mySwipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        webView.reload();
                    }
                }
        );

        Animation rotate = new RotateAnimation(0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);

        rotate.setDuration(2000);
        rotate.setRepeatCount(Animation.INFINITE);
        rotate.setInterpolator(new LinearInterpolator());
        appImage.startAnimation(rotate);

        webView.setWebViewClient(new WebViewClient()
        {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon)
            {
                super.onPageStarted(view, url, favicon);
                appImage.startAnimation(rotate);
                appImage.setVisibility(View.VISIBLE);

                loadErrorLayout.setVisibility(View.GONE);
            }




            @Override
            public boolean  shouldOverrideUrlLoading(WebView view, String url) {

                if (url != null && url.startsWith("whatsapp://")) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(browserIntent);
                    return true;

                }
                else if (url != null && url.startsWith("https://www.youtube.com")) {
                    Intent ytIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(ytIntent);
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

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback,
                                             FileChooserParams fileChooserParams) {
                HomeActivity.this.filePathCallback = filePathCallback;
                showFileChooser();
                return true;
            }
        });


    }


    private static final int FILE_CHOOSER_REQUEST_CODE = 1;
    private ValueCallback<Uri[]> filePathCallback;

    private void showFileChooser() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");

        Intent chooser = Intent.createChooser(intent, "Choose Image");
        startActivityForResult(chooser, FILE_CHOOSER_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == FILE_CHOOSER_REQUEST_CODE && resultCode == RESULT_OK) {
            filePathCallback.onReceiveValue(WebChromeClient.FileChooserParams.parseResult(resultCode, data));
        } else {
            filePathCallback.onReceiveValue(null);
        }
        filePathCallback = null;
        super.onActivityResult(requestCode, resultCode, data);
    }
    @Override
    public void onBackPressed() {
        BackController backController = new BackController(HomeActivity.this, webView, HomeActivity.this);
    }

}