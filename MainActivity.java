/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.runette.googlenewsapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import static android.view.KeyEvent.*;

/*
 * MainActivity class that loads MainFragment
 */
public class MainActivity extends Activity {
    /**
     * Called when the activity is first created.
     */

    static final String NEWS_PAGE_URL = "https://news.google.com";
    WebView mainWebView = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_view);
        mainWebView = findViewById(R.id.webview);



        mainWebView.setWebViewClient(new WebViewClient() {
                                       @Override
                                       public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest resourceRequest) {
                                           // do your handling codes here, which url is the requested url
                                           // probably you need to open that url rather than redirect:
                                           view.loadUrl(resourceRequest.getUrl().toString());
                                           return false; // then it is not handled by default action
                                       }

                                   }
        );
        mainWebView.setOnKeyListener (new  WebView.OnKeyListener()    {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // You can get any keyCode or KeyEvent here
                //Toast.makeText(MainActivity.this, String.valueOf(event.getKeyCode()), Toast.LENGTH_SHORT).show();

                Boolean ret = false;
                if (event.getAction() == ACTION_DOWN){
                    switch (keyCode) {
                        case KEYCODE_BACK :
                            loadNewsPage(mainWebView);
                            ret = true;
                            break;
                        case KEYCODE_DPAD_DOWN :
                            Toast.makeText(MainActivity.this, "DOWN KEY", Toast.LENGTH_SHORT).show();
                            ret = true;
                            break;
                        case KEYCODE_DPAD_LEFT :
                            Toast.makeText(MainActivity.this, "LEFT KEY", Toast.LENGTH_SHORT).show();
                            ret = true;
                            break;
                        default:
                            break;
                    }

                }

                return ret;
            }
        });
        WebSettings webSettings = mainWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        loadNewsPage(mainWebView);
    }

    public void loadNewsPage (WebView v) {
        v.loadUrl(NEWS_PAGE_URL);

    }
}
