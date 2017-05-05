package com.rojao.tvlive;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.VideoView;

import com.rojao.tvlive.ad.AdDialog;
import com.rojao.tvlive.ad.RecPopDialog;
import com.rojao.tvlive.ad.RedPacketDialog;
import com.rojao.tvlive.network.WebService;
import com.rojao.tvlive.weiget.ChannelDialog;
import com.rojao.tvlive.weiget.RecommendDialog;
import com.rojao.tvlive.weiget.channel.ChannelView;
import com.rojao.tvlive.weiget.loadingview.SlackLoadingView;
import com.rojao.tvlive.weiget.recommend.RecommendView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String KEY_TYPE = "type";
    public static final String Key_VIDEOPATH = "videoPath";
    private static final String TYPE_LIVE = "live";
    private static final String TYPE_VOD = "vod";
    //    private static final String DEFAULT_PATH = "http://221.6.85.155/live/hnws_800/hnws_800.m3u8";
    private static final String DEFAULT_PATH = "/storage/emulated/0/ts/beijing.ts";
    private static final int[] sCHANNELBAR_TITLE_IMGS = new int[]{
            R.mipmap.changetv1, R.mipmap.changetv2, R.mipmap.changetv3,
            R.mipmap.changetv4, R.mipmap.changetv5, R.mipmap.changetv6,
            R.mipmap.changetv7, R.mipmap.changetv8
    };
    private static final int[] sCHANNELBAR_AD_IMGS = new int[]{
            R.mipmap.changetv1ad, R.mipmap.changetv2ad, R.mipmap.changetv3ad,
            R.mipmap.changetv4ad, R.mipmap.changetv5ad, R.mipmap.changetv6ad,
            R.mipmap.changetv7ad, R.mipmap.changetv8ad
    };


    private Animation mPopUpAnim, mPopDownAnim;
    private String mVideoPath;
    private String mType = TYPE_LIVE;
    private VideoView mVideoPlayer;
    private SlackLoadingView mLoadingView;
    private ChannelDialog mChannelDialog;
    private RecommendDialog mRecommendDialog;
    private Handler mHandler = new Handler();
    private boolean mIsCanChange = true;
    private Runnable mChangeChannelRunnable = new Runnable() {
        @Override
        public void run() {
            mIsCanChange = true;
        }
    };
    private int mCurChannel;
    private AdDialog mAdDialog;
    private RecPopDialog mRecPopDialog;
    private RedPacketDialog mRedPacketDialog;
    private LinearLayout mInfoBar;
    private ImageView mInfoTitle, mInfoAd;
    private boolean isChannel3Click, isChannel4Click;
    private AdDialog.OnJumpOutCallBack mCallBack = new AdDialog.OnJumpOutCallBack() {
        @Override
        public void onJumpOut(String type, String linkUrl) {
            if (type.equals("car")) {
                isChannel3Click = !isChannel3Click;
            }
            if (type.equals("movie")) {
                isChannel4Click = !isChannel4Click;
            }
            VodActivity.activityStart(MainActivity.this, type, linkUrl);
        }

        @Override
        public void onCHCAdShow(boolean isNeedStop) {

            if (isNeedStop) {
                mVideoPlayer.pause();
            } else {
                mVideoPlayer.start();
            }
        }
    };
    private RecPopDialog.onDialogItemClickListener mOnDialogItemClickListener = new RecPopDialog.onDialogItemClickListener() {
        @Override
        public void onItemClick(int curChannel, String type, String linkPath) {
            if (curChannel == 2) {
                isChannel3Click = !isChannel3Click;
            } else if (curChannel == 3) {
                isChannel4Click = !isChannel4Click;
            }
            VodActivity.activityStart(MainActivity.this, type, linkPath);
        }
    };
    private Runnable mADRunnable = new Runnable() {
        @Override
        public void run() {

            hideInfoBar();
            if (mChannelDialog != null && mChannelDialog.isShowing()) {
                mChannelDialog.dismiss();
            }
            if (mRecommendDialog != null && mRecommendDialog.isShowing()) {
                mRecommendDialog.dismiss();
            }
            if (mCurChannel != 0 && mCurChannel != 6) {
                if (mAdDialog == null) {
                    mAdDialog = new AdDialog(MainActivity.this);
                    mAdDialog.setCallBack(mCallBack);
                }
                if (mAdDialog.isShowing()) {
                    mAdDialog.dismiss();
                }
                Log.e(TAG, "run: isChannel3Click = " + isChannel3Click + " isChannel4Click= " + isChannel4Click + " mCurChannel=" + mCurChannel);
                if ((isChannel4Click &&mCurChannel == 3) || (isChannel3Click && mCurChannel == 2)) {
                    if (mRecPopDialog == null) {
                        mRecPopDialog = new RecPopDialog(MainActivity.this);
                        mRecPopDialog.setOnDialogItemClickListner(mOnDialogItemClickListener);
                    }
                    mRecPopDialog.showAD(mCurChannel);
                } else {
                    mAdDialog.showAD(mCurChannel);
                }
            } else if (mCurChannel == 6) {
                if (mRedPacketDialog == null) {
                    mRedPacketDialog = new RedPacketDialog(MainActivity.this);
                }
                if (mRedPacketDialog.isShowing()) {
                    mRedPacketDialog.dismiss();
                }

                mRedPacketDialog.show();

            }

        }
    };

    private ChannelView.OnChannelItemClickListener mOnChannelItemClickListener = new ChannelView.OnChannelItemClickListener() {
        @Override
        public void onChannelItemClick(int pos, String channelNo, final String linkPath) {
            mChannelDialog.dismiss();
            startLoading();
            mVideoPlayer.stopPlayback();
            if (!TextUtils.isEmpty(linkPath)) {

                mVideoPlayer.setVideoPath(linkPath);
                mVideoPlayer.start();
            }
            mCurChannel = pos;
            mHandler.removeCallbacks(mADRunnable);
            mHandler.postDelayed(mADRunnable, 5000);
        }

        @Override
        public void faile() {
            mChannelDialog.dismiss();
        }

    };

    private RecommendView.onRecommendItemClickListener mOnRecommendItemClickListener = new RecommendView.onRecommendItemClickListener() {
        @Override
        public void OnItemClick(final String linkPath) {
            Log.e(TAG, "OnItemClick: " + linkPath);
            mRecommendDialog.dismiss();

            VodActivity.activityStart(MainActivity.this, TYPE_VOD, linkPath);
        }
    };
    private long mLastTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mInfoAd = (ImageView) findViewById(R.id.id_info_bar_ad);
        mInfoBar = (LinearLayout) findViewById(R.id.id_info_bar);
        mInfoTitle = (ImageView) findViewById(R.id.id_info_bar_title);
        mVideoPlayer = (VideoView) findViewById(R.id.id_videoPlayer);
        mLoadingView = (SlackLoadingView) findViewById(R.id.id_loadingView);
        mType = getIntent().getStringExtra(KEY_TYPE);
        mVideoPath = getIntent().getStringExtra(Key_VIDEOPATH);
        Log.e(TAG, "onCreate: " + mType);
        if (mType == null) {
            mType = TYPE_LIVE;
        }
        if (TextUtils.isEmpty(mVideoPath)) {
            mVideoPath = DEFAULT_PATH;
        }
        startLoading();

    }

    private void initVideo() {
        if (TYPE_LIVE.equals(mType)) {

            mVideoPlayer.setVideoURI(Uri.parse(WebService.getInstance().getChannelDetail(0).get(mCurChannel).getLinkPath()));
            showInfoBar(mCurChannel);
        } else if (TYPE_VOD.equals(mType)) {
            mVideoPlayer.setVideoURI(Uri.parse(mVideoPath));
        }
        mVideoPlayer.start();
        mVideoPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });

        mVideoPlayer.setOnInfoListener(
                new MediaPlayer.OnInfoListener() {
                    @Override
                    public boolean onInfo(MediaPlayer mp, int what, int extra) {
                        switch (what) {
                            case MediaPlayer.MEDIA_INFO_BUFFERING_START:
                                startLoading();
                                break;
                            case MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START:
                            case MediaPlayer.MEDIA_INFO_BUFFERING_END:
                                stopLoading();
                                break;
                        }
                        return false;
                    }
                });

        mHandler.postDelayed(mADRunnable, 5000);
    }


    @Override
    protected void onResume() {
        super.onResume();
        initVideo();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mVideoPlayer.stopPlayback();
        if (mAdDialog != null && mAdDialog.isShowing()) {
            mAdDialog.dismiss();
            mAdDialog = null;
        }
        if (mRedPacketDialog != null && mRedPacketDialog.isShowing()) {
            mRedPacketDialog.dismiss();
            mAdDialog = null;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        String path;
        if (TYPE_LIVE.equals(mType) && keyCode == KeyEvent.KEYCODE_DPAD_LEFT) {
            hideInfoBar();
            if (mChannelDialog == null) {
                mChannelDialog = new ChannelDialog(this, mOnChannelItemClickListener);
            }
            if (!mChannelDialog.isShowing()) {
                mChannelDialog.show(getWindow().getDecorView());
            }
            mHandler.removeCallbacks(mADRunnable);
            return super.onKeyDown(keyCode, event);
        }
        if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT && mCurChannel == 0) {
            hideInfoBar();
            if (mRecommendDialog == null) {
                mRecommendDialog = new RecommendDialog(this, mOnRecommendItemClickListener);
            }
            if (!mRecommendDialog.isShowing()) {
                mRecommendDialog.show(getWindow().getDecorView());
            }
            mHandler.removeCallbacks(mADRunnable);
            return super.onKeyDown(keyCode, event);
        }
        if (TYPE_LIVE.equals(mType) && keyCode != KeyEvent.KEYCODE_BACK) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_DPAD_DOWN:
                    if (mIsCanChange) {
                        mIsCanChange = false;
                        mCurChannel++;
                        if (mCurChannel >= WebService.getInstance().getChannelDetail(0).size()) {
                            mCurChannel = 0;
                        }
                        mHandler.removeCallbacks(mChangeChannelRunnable);
                        mHandler.postDelayed(mChangeChannelRunnable, 200);
                    }
                    break;
                case KeyEvent.KEYCODE_DPAD_UP:
                    if (mIsCanChange) {
                        mIsCanChange = false;
                        mCurChannel--;
                        if (mCurChannel <= 0) {
                            mCurChannel = 7;
                        }
                        mHandler.removeCallbacks(mChangeChannelRunnable);
                        mHandler.postDelayed(mChangeChannelRunnable, 200);
                    }
                    break;
                case KeyEvent.KEYCODE_1:
                    mCurChannel = 0;
                    break;
                case KeyEvent.KEYCODE_2:
                    mCurChannel = 1;
                    break;
                case KeyEvent.KEYCODE_3:
                    mCurChannel = 2;
                    break;
                case KeyEvent.KEYCODE_4:
                    mCurChannel = 3;
                    break;
                case KeyEvent.KEYCODE_5:
                    mCurChannel = 4;
                    break;
                case KeyEvent.KEYCODE_6:
                    mCurChannel = 5;
                    break;
                case KeyEvent.KEYCODE_7:
                    mCurChannel = 6;
                    break;
                case KeyEvent.KEYCODE_8:
                    mCurChannel = 7;
                    break;
                default:
                    return super.onKeyDown(keyCode, event);
            }

            path = WebService.getInstance().getChannelDetail(0).get(mCurChannel).getLinkPath();
            if (!TextUtils.isEmpty(path)) {
                mVideoPlayer.setVideoPath(path);
            }
            mVideoPlayer.start();
            startLoading();
            mHandler.removeCallbacks(mADRunnable);
            mHandler.postDelayed(mADRunnable, 5000);
            showInfoBar(mCurChannel);
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

    private void showInfoBar(int curChannel) {
        if (mPopUpAnim == null) {
            mPopUpAnim = AnimationUtils.loadAnimation(this, R.anim.pop_up_anim);
        }
        mInfoBar.setVisibility(View.VISIBLE);
        mInfoTitle.setImageResource(sCHANNELBAR_TITLE_IMGS[curChannel]);
        mInfoAd.setImageResource(sCHANNELBAR_AD_IMGS[curChannel]);
        mInfoBar.startAnimation(mPopUpAnim);
    }

    private void hideInfoBar() {
        if (mPopDownAnim == null) {
            mPopDownAnim = AnimationUtils.loadAnimation(this, R.anim.pop_down_anim);
        } else {
            mPopDownAnim.cancel();
        }

        if (mInfoBar.getVisibility() == View.VISIBLE) {
            mInfoBar.startAnimation(mPopDownAnim);
            mInfoBar.setVisibility(View.GONE);
        }
    }
}
