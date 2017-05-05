package com.rojao.tvlive.ad;

import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.rojao.tvlive.R;
import com.rojao.tvlive.weiget.fallingview.FallingView;

/**
 * Created by lsc on 2017/5/3 0003.
 *
 * @author lsc
 * @desc ${TODO}
 */
public class RedPacketDialog extends Dialog implements View.OnClickListener {

    private static final int DISMISS_DIALOG = 0x111;
    private static final java.lang.String URL_RED_PACKET = "http://www.960238.com/redpack/index.html?cardNo=8888777766669991";
    private ImageView mIV_redPacket;
    private WebView mWebView;
    private FallingView mFallingView;
    private Animation mAnimation;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case DISMISS_DIALOG:
                    dismiss();
                    break;
            }
        }
    };

    public RedPacketDialog(Context context) {
        this(context, 0);
    }

    public RedPacketDialog(Context context, int themeResId) {
        super(context, R.style.ad_dialog_style);
        init();
    }

    private void init() {
        mAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.scale_big);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.MATCH_PARENT;
        getWindow().setAttributes(params);
        getWindow().setBackgroundDrawable(new ColorDrawable());
        setContentView(R.layout.red_packet_dialog);

        mIV_redPacket = (ImageView) findViewById(R.id.id_red_packet);
        mFallingView = (FallingView) findViewById(R.id.id_fallingView);
        mIV_redPacket.setOnClickListener(this);
        mWebView = (WebView) findViewById(R.id.id_webView);

        //        webview setting
        mWebView.setBackgroundColor(2);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
    }


    @Override
    public void show() {
        super.show();
        mIV_redPacket.setVisibility(View.VISIBLE);
        flipAnim(mIV_redPacket);
        mIV_redPacket.requestFocus();
        mFallingView.startFalling();
        mHandler.sendEmptyMessageDelayed(DISMISS_DIALOG, 20000);
    }

    private void flipAnim(final View view) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "rotationY",
                0.0F, 45.0f, 0.0f, -45.0f, 0.0f)//
                .setDuration(6000);
        animator.start();
    }

    @Override
    public void onClick(View v) {
        mIV_redPacket.setVisibility(View.GONE);
        mFallingView.stopFalling();
        mHandler.removeMessages(DISMISS_DIALOG);
        mWebView.setVisibility(View.VISIBLE);
        mWebView.loadUrl(URL_RED_PACKET);
        mWebView.startAnimation(mAnimation);
        mWebView.requestFocus();
    }

    @Override
    public void dismiss() {
        super.dismiss();
        mFallingView.stopFalling();
        mWebView.setVisibility(View.GONE);
        mHandler.removeCallbacksAndMessages(null);
    }
}
