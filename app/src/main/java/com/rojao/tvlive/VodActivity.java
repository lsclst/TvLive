package com.rojao.tvlive;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.rojao.tvlive.ijkplayer.media.IjkVideoView;
import com.rojao.tvlive.weiget.loadingview.SlackLoadingView;

import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class VodActivity extends AppCompatActivity {

    private static final String TAG = VodActivity.class.getSimpleName();
    private IjkVideoView mVideoPlayer;
    private SlackLoadingView mLoadingView;
    private String mVideoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mVideoPath = getIntent().getStringExtra(MainActivity.Key_VIDEOPATH);
        setContentView(R.layout.activity_vod);
        mVideoPlayer = (IjkVideoView) findViewById(R.id.id_videoPlayer);
        mLoadingView = (SlackLoadingView) findViewById(R.id.id_loadingView);


    }

    @Override
    protected void onResume() {
        super.onResume();
        initVideo();
        startLoading();
    }

    private void initVideo() {
        if (TextUtils.isEmpty(mVideoPath)) {
            new AlertDialog.Builder(VodActivity.this)
                    .setMessage("节目暂时不能播放")
                    .setPositiveButton(R.string.VideoView_error_button,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    VodActivity.this.finish();
                                }
                            })
                    .setCancelable(false)
                    .show();
        }
        IjkMediaPlayer.loadLibrariesOnce(null);
        IjkMediaPlayer.native_profileBegin("libijkplayer.so");
        mVideoPlayer.setVideoURI(Uri.parse(mVideoPath));
        mVideoPlayer.setOnPreparedListener(new IMediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(IMediaPlayer mp) {
                mVideoPlayer.start();
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


    }

    @Override
    protected void onPause() {
        super.onPause();
        if (!mVideoPlayer.isBackgroundPlayEnabled()) {
            mVideoPlayer.stopPlayback();
            mVideoPlayer.release(true);
            mVideoPlayer.stopBackgroundPlay();
        }
        IjkMediaPlayer.native_profileEnd();
    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    public static void activityStart(Context context, String videoPath) {
        Intent intent = new Intent(context, VodActivity.class);
        intent.putExtra(MainActivity.Key_VIDEOPATH, videoPath);
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
