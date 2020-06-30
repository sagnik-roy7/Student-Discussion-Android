package com.studentportal.app;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.github.ybq.android.spinkit.style.CubeGrid;


public class MainActivity extends AppCompatActivity {

    private WebView mwv;
    private static boolean userPressedBackAgain ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mwv = (WebView) findViewById(R.id.webView);
        mwv.getSettings().setJavaScriptEnabled(true);
        mwv.getSettings().setLoadWithOverviewMode(true);
        mwv.getSettings().setUseWideViewPort(true);
        mwv.getSettings().setBuiltInZoomControls(true);
        mwv.setWebViewClient(new WebViewClient());
        mwv.loadUrl("http://coscteam9.herokuapp.com/");
        WebSettings webSettings = mwv.getSettings();
        webSettings.setJavaScriptEnabled(true);
        Toast.makeText(MainActivity.this, "Student Discussion Portal", Toast.LENGTH_LONG).show();
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.spin_kit);

        CubeGrid cubeGrid = new CubeGrid();
        progressBar.setIndeterminateDrawable(cubeGrid);
        progressBar.setIndeterminateDrawable(cubeGrid);
        progressBar.setVisibility(View.VISIBLE);

        mwv.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {


                super.onPageStarted(view, url, favicon);

            }

            @Override
            public void onPageFinished(WebView view, String url) {
                progressBar.setVisibility(View.INVISIBLE);
                super.onPageFinished(view, url);
            }
        });
    }
    @Override
    public void onBackPressed() {

        if(mwv.canGoBack()){
            mwv.goBack();
        }
        else{
            if (! userPressedBackAgain ) {
                Toast. makeText ( this, "Back again to exit" , Toast. LENGTH_SHORT ) .show () ;

                userPressedBackAgain = true;
            } else {
                Intent intent = new Intent (Intent. ACTION_MAIN ) ;
                intent.addCategory (Intent. CATEGORY_HOME ) ;
                intent.setFlags (Intent. FLAG_ACTIVITY_NEW_TASK ) ;
                startActivity (intent) ;
            }

            new CountDownTimer( 3000 , 1000 ) {
                @Override
                public void onTick ( long millisUntilFinished) {

                }

                @Override
                public void onFinish () {
                    userPressedBackAgain = false;
                }
            } .start () ;
        }
    }}
