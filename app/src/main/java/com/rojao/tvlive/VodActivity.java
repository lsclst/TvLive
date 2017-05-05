package com.rojao.tvlive;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.rojao.tvlive.weiget.loadingview.SlackLoadingView;

import java.util.Locale;

public class VodActivity extends AppCompatActivity {

    private static final String TAG = VodActivity.class.getSimpleName();
    private static final String KEY_LINK_PATH = "path";
    private static final String KEY_TYPE = "type";
    private VideoView mVideoPlayer;
    private SlackLoadingView mLoadingView;
    private TextView mTv_timmer;
    private ImageView mImageView;
    private WebView mWebView;
    private String mLinkPath;
    private String mType;
    private CountDownTimer mCountDownTimer;
    private boolean isFirstPart = true;
    public static final String TYPE_CAR = "car";
    public static final String TYPE_VOD = "vod";
    public static final String TYPE_MOVIE = "movie";
    public static final String TYPE_WEBSITE = "website";

    private String CAR_AD_URL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CAR_AD_URL = "android.resource://" + this.getPackageName() + "/" + R.raw.car;
        mLinkPath = getIntent().getStringExtra(KEY_LINK_PATH);
        mType = getIntent().getStringExtra(KEY_TYPE);
        setContentView(R.layout.activity_vod);
        mVideoPlayer = (VideoView) findViewById(R.id.id_videoPlayer);
        mLoadingView = (SlackLoadingView) findViewById(R.id.id_loadingView);
        mImageView = (ImageView) findViewById(R.id.id_recommend_iv);
        mTv_timmer = (TextView) findViewById(R.id.id_timmer);
        mWebView = (WebView) findViewById(R.id.id_webView);
        mWebView.setBackgroundColor(2);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress >= 99) {
                    stopLoading();
                } else {
                    startLoading();
                }
            }
        });
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        mCountDownTimer = new CountDownTimer(15 * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (mTv_timmer.getVisibility() == View.GONE) {
                    mTv_timmer.setVisibility(View.VISIBLE);
                }
                int remainingTime = (int) (millisUntilFinished / 1000);
                String s = String.format(Locale.getDefault(), "广告剩余%02d 秒", remainingTime);
                SpannableString ss = new SpannableString(s);
                ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(
                        ContextCompat.getColor(VodActivity.this, R.color.recommendColor_5));
                ss.setSpan(foregroundColorSpan, 4, 6, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                mTv_timmer.setText(ss);
            }

            @Override
            public void onFinish() {
                mTv_timmer.setVisibility(View.GONE);
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (TYPE_CAR.equals(mType) && TextUtils.isEmpty(mLinkPath)) {
            mWebView.setVisibility(View.GONE);
            mVideoPlayer.setVisibility(View.GONE);
            mTv_timmer.setVisibility(View.GONE);
            mImageView.setVisibility(View.VISIBLE);
            mImageView.setImageResource(R.mipmap.recommed_ad);
            return;
        }
        if (TYPE_CAR.equals(mType) || TYPE_MOVIE.equals(mType) || TYPE_VOD.equals(mType)) {
            mVideoPlayer.setVisibility(View.VISIBLE);
            mWebView.setVisibility(View.GONE);
            mImageView.setVisibility(View.GONE);
            initVideo();
        } else {
            mVideoPlayer.setVisibility(View.GONE);
            mTv_timmer.setVisibility(View.GONE);
            mImageView.setVisibility(View.GONE);
            mWebView.setVisibility(View.VISIBLE);
            mWebView.loadUrl(mLinkPath);
        }
        startLoading();
    }

    private void initVideo() {

        if (TYPE_CAR.equals(mType) || TYPE_MOVIE.equals(mType)) {

            mVideoPlayer.setVideoURI(Uri.parse(CAR_AD_URL));
        } else if (TYPE_VOD.equals(mType)) {
            mVideoPlayer.setVideoURI(Uri.parse(mLinkPath));
        }
        mVideoPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
                if (isFirstPart && !TYPE_VOD.equals(mType)) {
                    mCountDownTimer.start();
                }
            }
        });
        mVideoPlayer.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                switch (what) {
                    case MediaPlayer.MEDIA_INFO_BUFFERING_START:
                        Log.e(TAG, "onInfo: buffering start");
                        startLoading();
                        break;
                    case MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START:
                    case MediaPlayer.MEDIA_INFO_BUFFERING_END:
                        Log.e(TAG, "onInfo: buffering end");
                        stopLoading();
                        break;
                }
                return false;
            }
        });

        mVideoPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if (TYPE_VOD.equals(mType) || TYPE_MOVIE.equals(mType)) {
                    mVideoPlayer.setVideoURI(Uri.parse(mLinkPath));
                    isFirstPart = false;
                } else {
                    finish();
                }
            }
        });


    }

    @Override
    protected void onPause() {
        super.onPause();
        mVideoPlayer.stopPlayback();
    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    public void onBackPressed() {

        if (mWebView.getVisibility() == View.VISIBLE) {
            if (mWebView.canGoBack()) {
                mWebView.goBack();
            } else {
                finish();
            }
        } else {
            finish();
        }
    }

    public static void activityStart(Context context, String type, String linkPath) {
        Intent intent = new Intent(context, VodActivity.class);
        intent.putExtra(KEY_LINK_PATH, linkPath);
        intent.putExtra(KEY_TYPE, type);
        context.startActivity(intent);
    }

    public void startLoading() {
        if (mLoadingView != null) {

            mLoadingView.setVisibility(View.VISIBLE);
            mLoadingView.start();
        }
    }

    public void stopLoading() {
        if (mLoadingView != null) {
            mLoadingView.reset();
            mLoadingView.setVisibility(View.GONE);
        }
    }
}
