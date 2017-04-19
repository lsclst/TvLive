package com.rojao.tvlive.weiget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

import com.shuyu.gsyvideoplayer.utils.GSYVideoType;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

/**
 * Created by lsc on 2017/4/19 0019.
 *
 * @author lsc
 * @desc ${TODO}
 */
public class VideoPlayerView extends StandardGSYVideoPlayer {
    public VideoPlayerView(Context context, Boolean fullFlag) {
        super(context, fullFlag);
    }

    public VideoPlayerView(Context context) {
        super(context);
    }

    public VideoPlayerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void init(Context context) {
        super.init(context);
        GSYVideoType.setShowType(GSYVideoType.SCREEN_TYPE_16_9);
    }

    @Override
    protected void onClickUiToggle() {
        super.onClickUiToggle();
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void setStateAndUi(int state) {
//        super.setStateAndUi(state);
        hideAllWidget();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return super.onTouch(v, event);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
//        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
//        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
//        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
//
//        if (widthSpecMode == MeasureSpec.EXACTLY && heightSpecMode == MeasureSpec.EXACTLY) {
//            setMeasuredDimension(widthSpecSize, heightSpecSize);
//        }else {
//            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }
}
