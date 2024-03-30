package com.programmerdatch.webviewdatchapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.webkit.WebView;

public class BackController {
    public BackController(Context context, WebView webView, Activity activity) {
        if (webView.isFocused() && webView.canGoBack())
        {
            webView.goBack();
        }
        else {
            new AlertDialog.Builder(context)
                    .setTitle("EXIT")
                    .setMessage("Are you sure. You want to close this app?")
                    .setPositiveButton
                            ("Yes",
                                new DialogInterface.OnClickListener()
                                {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which)
                                    {
                                        activity.finish();
                                    }
                                }
                            )
                    .setNegativeButton("No", null)
                    .show();
        }
    }
}
