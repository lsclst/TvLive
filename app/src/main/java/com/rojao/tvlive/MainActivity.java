package com.rojao.tvlive;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

import com.rojao.tvlive.weiget.ChannelDialog;
import com.rojao.tvlive.weiget.RecommendDialog;
import com.rojao.tvlive.weiget.channel.ChannelView;
import com.rojao.tvlive.weiget.recommend.RecommendView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String KEY_TYPE = "type";
    private static final String Key_VIDEOPATH = "videoPath";
    private static final String TYPE_LIVE = "live";
    private static final String TYPE_VOD = "vod";
//        private static final String DEFAULT_PATH = "http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f20.mp4";
//    private static final String DEFAULT_PATH = "http://10.10.1.233:9001/hb/hb/20170410105021.mp4";
    private static final String DEFAULT_PATH = "http://221.6.85.155/live/hnws_800/hnws_800.m3u8";

    private String mVideoPath;
    private String mType;
    private ImageView mADIamgeView;
    private VideoView mVideoPlayer;
    private ChannelDialog mChannelDialog;
    private RecommendDialog mRecommendDialog;


    private ChannelView.OnChannelItemClickListener mOnChannelItemClickListener = new ChannelView.OnChannelItemClickListener() {
        @Override
        public void onChannelItemClick(int firstPos, int secondPos) {
            Log.e(TAG, "first pos = " + firstPos + "second pos = " + secondPos);
        }
    };

    private RecommendView.onRecommendItemClickListener mOnRecommendItemClickListener = new RecommendView.onRecommendItemClickListener() {
        @Override
        public void OnItemClick(final String linkPath) {
            Log.e(TAG, "OnItemClick: " + linkPath);
            mRecommendDialog.dismiss();
            mVideoPlayer.stopPlayback();
            mVideoPlayer.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mVideoPlayer.setVideoPath(linkPath);
                    mVideoPlayer.start();
                }
            },200);

//            mVideoPlayer.setUp("http://10.10.1.233:9001/hb/hb/20170410105021.mp4",false,null,"");
//            mVideoPlayer.startPlayLogic();


        }
    };
    private long mLastTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mType = getIntent().getStringExtra(KEY_TYPE) == null ? TYPE_LIVE : mType;
        mVideoPath = getIntent().getStringExtra(Key_VIDEOPATH);
        mADIamgeView = (ImageView) findViewById(R.id.id_iv_ad);
        mVideoPlayer = (VideoView) findViewById(R.id.id_videoPlayer);
        if (mType.equals(TYPE_LIVE)) {
            mVideoPath = DEFAULT_PATH;
        }
        initVideo();
    }

    private void initVideo() {
        mVideoPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
//                mVideoPlayer.setVideoPath(mVideoPath);
//                mVideoPlayer.start();
            }
        });

        mVideoPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {

            }
        });

        mVideoPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {

                return false;
            }
        });

        mVideoPlayer.setVideoPath(DEFAULT_PATH);
        mVideoPlayer.start();

//        //开启自动旋转
//        mVideoPlayer.setRotateViewAuto(false);
//
//        //全屏首先横屏
//        mVideoPlayer.setLockLand(true);
//
//        //是否需要全屏动画效果
//        mVideoPlayer.setShowFullAnimation(false);
//        //不需要非wifi状态提示
//        mVideoPlayer.setNeedShowWifiTip(false);
//
//        //防止错位
//        mVideoPlayer.setPlayTag(TAG);
//
//        mVideoPlayer.setUp(DEFAULT_PATH,false,null,"");
//        mVideoPlayer.startPlayLogic();
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.e(TAG, "keycode: " + keyCode);
        if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT && TYPE_LIVE.equals(mType)) {
            if (mChannelDialog == null) {
                mChannelDialog = new ChannelDialog(this, mOnChannelItemClickListener);
            }
            if (!mChannelDialog.isShowing()) {
                mChannelDialog.show(getWindow().getDecorView());
            }
        } else if (keyCode == KeyEvent.KEYCODE_DPAD_UP) {
            if (mRecommendDialog == null) {
                mRecommendDialog = new RecommendDialog(this, mOnRecommendItemClickListener);
            }
            if (!mRecommendDialog.isShowing()) {
                mRecommendDialog.show(getWindow().getDecorView());
            }
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - mLastTime > 2000) {
            mLastTime = System.currentTimeMillis();
            Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
        } else {
            finish();
        }
    }
}
