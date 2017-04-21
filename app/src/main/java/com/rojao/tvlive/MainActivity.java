package com.rojao.tvlive;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.rojao.tvlive.ijkplayer.media.IjkVideoView;
import com.rojao.tvlive.weiget.ChannelDialog;
import com.rojao.tvlive.weiget.RecommendDialog;
import com.rojao.tvlive.weiget.channel.ChannelView;
import com.rojao.tvlive.weiget.loadingview.SlackLoadingView;
import com.rojao.tvlive.weiget.recommend.RecommendView;

import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String KEY_TYPE = "type";
    public static final String Key_VIDEOPATH = "videoPath";
    private static final String TYPE_LIVE = "live";
    private static final String TYPE_VOD = "vod";
    //        private static final String DEFAULT_PATH = "http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f20.mp4";
    //    private static final String DEFAULT_PATH = "http://10.10.1.233:9001/hb/hb/20170410105021.mp4";
    private static final String DEFAULT_PATH = "http://221.6.85.155/live/hnws_800/hnws_800.m3u8";

    private String mVideoPath;
    private String mType = TYPE_LIVE;
    private ImageView mADIamgeView;
    private IjkVideoView mVideoPlayer;
    private SlackLoadingView mLoadingView;
    private ChannelDialog mChannelDialog;
    private RecommendDialog mRecommendDialog;


    private ChannelView.OnChannelItemClickListener mOnChannelItemClickListener = new ChannelView.OnChannelItemClickListener() {
        @Override
        public void onChannelItemClick(final String channelNo, final String linkPath) {
            mChannelDialog.dismiss();
            startLoading();
            mVideoPlayer.stopPlayback();
//            mVideoPlayer.release(true);
            if (!TextUtils.isEmpty(linkPath)) {
                if (channelNo.equals("01")) {

                    mVideoPlayer.setVideoPath("http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f20.mp4");
                } else {

                    mVideoPlayer.setVideoPath(linkPath);
                }
                mVideoPlayer.start();
            }
        }

    };

    private RecommendView.onRecommendItemClickListener mOnRecommendItemClickListener = new RecommendView.onRecommendItemClickListener() {
        @Override
        public void OnItemClick(final String linkPath) {
            Log.e(TAG, "OnItemClick: " + linkPath);
            mRecommendDialog.dismiss();

            VodActivity.activityStart(MainActivity.this, linkPath);


        }
    };
    private long mLastTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mADIamgeView = (ImageView) findViewById(R.id.id_iv_ad);
        mVideoPlayer = (IjkVideoView) findViewById(R.id.id_videoPlayer);
        mLoadingView = (SlackLoadingView) findViewById(R.id.id_loadingView);
        mLoadingView.setDuration(500);
        mType = getIntent().getStringExtra(KEY_TYPE);
        mVideoPath = getIntent().getStringExtra(Key_VIDEOPATH);
        Log.e(TAG, "onCreate: " + mType + " " + mVideoPath);
        if (!TYPE_LIVE.equals(mType)) {
            mType = TYPE_LIVE;
        }
        if (TextUtils.isEmpty(mVideoPath)) {
            mVideoPath = DEFAULT_PATH;
        }
        startLoading();
        initVideo();
    }

    private void initVideo() {
        IjkMediaPlayer.loadLibrariesOnce(null);
        IjkMediaPlayer.native_profileBegin("libijkplayer.so");
        mVideoPlayer.setVideoURI(Uri.parse(mVideoPath));
        mVideoPlayer.start();
        mVideoPlayer.setOnPreparedListener(new IMediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(IMediaPlayer mp) {

            }
        });

        mVideoPlayer.setOnInfoListener(new IMediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(IMediaPlayer mp, int what, int extra) {
                switch (what) {
                    case IjkMediaPlayer.MEDIA_INFO_BUFFERING_START:
                        Log.e(TAG, "onInfo: buffering start");
                        startLoading();
                        break;
                    case IjkMediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START:
                    case IjkMediaPlayer.MEDIA_INFO_BUFFERING_END:
                        Log.e(TAG, "onInfo: buffering end");
                        stopLoading();
                        break;
                }
                return false;
            }
        });

        //        mVideoView.setOnCompletionListener(new IMediaPlayer.OnCompletionListener() {
        //            @Override
        //            public void onCompletion(IMediaPlayer mp) {
        //                mLoadingLayout.setVisibility(View.VISIBLE);
        //                mVideoView.stopPlayback();
        //                mVideoView.release(true);
        //                mVideoView.setVideoURI(Uri.parse(mVideoUrl));
        //            }
        //        });

        //        mVideoView.setOnErrorListener(new IMediaPlayer.OnErrorListener() {
        //            @Override
        //            public boolean onError(IMediaPlayer mp, int what, int extra) {
        //                if (mRetryTimes > CONNECTION_TIMES) {
        //                    new AlertDialog.Builder(LiveActivity.this)
        //                            .setMessage("节目暂时不能播放")
        //                            .setPositiveButton(R.string.VideoView_error_button,
        //                                    new DialogInterface.OnClickListener() {
        //                                        public void onClick(DialogInterface dialog, int whichButton) {
        //                                            LiveActivity.this.finish();
        //                                        }
        //                                    })
        //                            .setCancelable(false)
        //                            .show();
        //                } else {
        //                    mVideoView.stopPlayback();
        //                    mVideoView.release(true);
        //                    mVideoView.setVideoURI(Uri.parse(mVideoUrl));
        //                }
        //                return false;
        //            }
        //        });

    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (!mVideoPlayer.isBackgroundPlayEnabled()) {
            mVideoPlayer.stopPlayback();
            mVideoPlayer.release(true);
            mVideoPlayer.stopBackgroundPlay();
        }
        IjkMediaPlayer.native_profileEnd();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.e(TAG, "keycode: " + keyCode + "type = "+mType);
        if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT && TYPE_LIVE.equals(mType)) {
            if (mChannelDialog == null) {
                mChannelDialog = new ChannelDialog(this, mOnChannelItemClickListener);
            }
            if (!mChannelDialog.isShowing()) {
                mChannelDialog.show(getWindow().getDecorView());
            }
        } else if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
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
