package com.rojao.tvlive.ad;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.rojao.tvlive.R;
import com.rojao.tvlive.VodActivity;

/**
 * Created by lsc on 2017/5/2 0002.
 *
 * @author lsc
 * @desc ${TODO}
 */
public class AdDialog extends Dialog implements View.OnClickListener {
    private static final int MSG_DISMISS_AD = 0x100;
    private static final String TAG = AdDialog.class.getSimpleName();
    private static final int TYPE_SUBTITLE = 1;
    private static final int TYPE_CAR = 2;
    private static final int TYPE_MOVIE = 3;
    private static final int TYPE_PAY = 4;
    private static final int TYPE_CHC = 5;
    private static final int TYPE_MULTI = 7;

    private final String CAR_AD_URL = "android.resource://" + AdDialog.this.getContext().getPackageName() + "/" + R.raw.car;
    private static final String MOVIE_URL = "http://122.97.219.210/yppl/2016/04/16/JSBYPPL02000009086007601.m3u8";
    private static final String WEBSITE_URL = "http://kp.gzcbn.tv/tvportal/pages/index.html";

    private ImageView mMainAdView;
    private ViewFlipper mViewFlipper;
    private ImageView mAdDetail;
    private TextView mtv_Pay_tips;
    private FrameLayout mSubtitle_layout;
    private AnimationDrawable mPayAnim;
    private int mType;


    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_DISMISS_AD:
                    dismiss();
                    break;
            }
        }
    };

    public AdDialog(Context context) {
        this(context, 0);
    }

    public AdDialog(Context context, int themeResId) {
        super(context, R.style.ad_dialog_style);
        init();
    }


    private void init() {
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.MATCH_PARENT;
        getWindow().setAttributes(params);
        getWindow().setBackgroundDrawable(new ColorDrawable());
        setContentView(R.layout.ad_dialog);
        mMainAdView = (ImageView) findViewById(R.id.id_ad_iv);
        mViewFlipper = (ViewFlipper) findViewById(R.id.id_ad_flipper);
        mtv_Pay_tips = (TextView) findViewById(R.id.id_pay_tips);
        mSubtitle_layout = (FrameLayout) findViewById(R.id.id_subtitle);
        mAdDetail = (ImageView) findViewById(R.id.id_ad_detail);
    }

    @Override
    public void dismiss() {
        super.dismiss();
        if (mPayAnim != null && mPayAnim.isRunning()) {
            mPayAnim.stop();
        }
        mHandler.removeCallbacksAndMessages(null);
    }

    public void showAD(int type) {

        mType = type;
        resetViews();
        setCancelable(true);
        show();
        switch (type) {
            case TYPE_MOVIE:
                showMovieAd();
                mHandler.sendEmptyMessageDelayed(MSG_DISMISS_AD, 20000);
                break;
            case TYPE_CAR:
                showCarAd();
                mHandler.sendEmptyMessageDelayed(MSG_DISMISS_AD, 20000);
                break;
            case TYPE_MULTI:
                showMulti();
                mHandler.sendEmptyMessageDelayed(MSG_DISMISS_AD, 20000);
                break;
            case TYPE_SUBTITLE:
                showSubtitle();
                break;
            case TYPE_PAY:
                showPayTips();
                mHandler.sendEmptyMessageDelayed(MSG_DISMISS_AD, 20000);
                break;
            case TYPE_CHC:
                showCHC();
                setCancelable(false);
                break;
            default:
                break;
        }
    }

    private void showMulti() {
        mViewFlipper.setVisibility(View.VISIBLE);
        mViewFlipper.setDisplayedChild(0);
        mViewFlipper.setOnClickListener(this);
        mViewFlipper.requestFocus();
        mViewFlipper.startFlipping();
    }

    private void showCHC() {

        mAdDetail.setVisibility(View.VISIBLE);
        mAdDetail.setImageResource(R.mipmap.chc_ad);
        if (mCallBack != null) {
            mCallBack.onCHCAdShow(true);
        }
    }

    private void showPayTips() {

        mtv_Pay_tips.setVisibility(View.VISIBLE);
        mtv_Pay_tips.requestFocus();
        mPayAnim = (AnimationDrawable) mtv_Pay_tips.getCompoundDrawables()[0];
        mPayAnim.setBounds(0, 0, mPayAnim.getIntrinsicWidth() / 2, mPayAnim.getIntrinsicHeight() / 2);
        mPayAnim.start();
        mtv_Pay_tips.setOnClickListener(this);
    }

    private void showSubtitle() {
        mSubtitle_layout.setVisibility(View.VISIBLE);
        mSubtitle_layout.requestFocus();
    }

    private void showCarAd() {

        mMainAdView.setVisibility(View.VISIBLE);
        mMainAdView.requestFocus();
        mMainAdView.setImageResource(R.mipmap.car);
        mMainAdView.setOnClickListener(this);
        scaleAndAlpha(mMainAdView);
    }

    private void scaleAndAlpha(View view) {
        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("alpha", 0f,
                1f);
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("scaleX", 0f,
                1f);
        PropertyValuesHolder pvhZ = PropertyValuesHolder.ofFloat("scaleY", 0f,
                1f);
        ObjectAnimator.ofPropertyValuesHolder(view, pvhX, pvhY, pvhZ)
                .setDuration(5000).start();
    }

    private void showMovieAd() {
        mMainAdView.setVisibility(View.VISIBLE);
        mMainAdView.requestFocus();
        mMainAdView.setImageResource(R.mipmap.fishbeauty);
        mMainAdView.setOnClickListener(this);
        flipAnim(mMainAdView);
    }

    private void resetViews() {

        mMainAdView.setOnClickListener(null);
        mViewFlipper.setOnClickListener(null);
        mtv_Pay_tips.setOnClickListener(null);
        mSubtitle_layout.setOnClickListener(null);
        if (mSubtitle_layout.getVisibility() == View.VISIBLE) {
            mSubtitle_layout.setVisibility(View.GONE);
        }
        if (mtv_Pay_tips.getVisibility() == View.VISIBLE) {
            mtv_Pay_tips.setVisibility(View.GONE);
        }
        if (mMainAdView.getVisibility() == View.VISIBLE) {
            mMainAdView.setVisibility(View.GONE);
        }
        if (mAdDetail.getVisibility() == View.VISIBLE) {
            mAdDetail.setVisibility(View.GONE);
        }
        if (mViewFlipper.getVisibility() == View.VISIBLE) {
            mViewFlipper.setVisibility(View.GONE);
        }
    }

    private void flipAnim(final View view) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "rotationY",
                0.0F, 45.0f, 0.0f, -45.0f, 0.0f)//
                .setDuration(6000);
        animator.start();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode != KeyEvent.KEYCODE_DPAD_CENTER) {
            if (mType != TYPE_CHC) {

                dismiss();
            }
        } else if (mType == TYPE_CHC) {
            if (mCallBack != null) {
                mCallBack.onCHCAdShow(false);
                dismiss();
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View v) {
        mHandler.removeMessages(MSG_DISMISS_AD);
        Log.e(TAG, "onClick: " + mType);
        int id = v.getId();
        switch (id) {
            case R.id.id_ad_iv:
                mMainAdView.setVisibility(View.GONE);
                if (mType == TYPE_CAR) {
                    if (mCallBack != null) {

                        mCallBack.onJumpOut(VodActivity.TYPE_CAR, CAR_AD_URL);
                    }
                } else if (mType == TYPE_MOVIE) {
                    if (mCallBack != null) {
                        mCallBack.onJumpOut(VodActivity.TYPE_MOVIE, MOVIE_URL);
                    }
                }
                break;
            case R.id.id_pay_tips:
                if (mPayAnim.isRunning()) {
                    mPayAnim.stop();
                }
                mtv_Pay_tips.setVisibility(View.GONE);
                mAdDetail.setVisibility(View.VISIBLE);
                mAdDetail.setImageResource(R.mipmap.pay_ad);
                break;
            case R.id.id_ad_flipper:
                mViewFlipper.stopFlipping();
                mViewFlipper.setVisibility(View.GONE);
                int pos = mViewFlipper.getDisplayedChild();
                if (pos == 0) {
                    if (mCallBack != null) {
                        mCallBack.onJumpOut(VodActivity.TYPE_WEBSITE, WEBSITE_URL);
                    }
                } else if (pos == 1) {
                    mAdDetail.setVisibility(View.VISIBLE);
                    mAdDetail.setImageResource(R.mipmap.pop2_channel8_detail);
                } else {
                    mAdDetail.setVisibility(View.VISIBLE);
                    mAdDetail.setImageResource(R.mipmap.pop3_channel8_detail);
                }
                break;
            default:
                break;
        }
    }

    public interface OnJumpOutCallBack {
        void onJumpOut(String type, String linkUrl);

        void onCHCAdShow(boolean isNeedStop);
    }

    private OnJumpOutCallBack mCallBack;

    public void setCallBack(OnJumpOutCallBack callBack) {
        this.mCallBack = callBack;
    }
}
