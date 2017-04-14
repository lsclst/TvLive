package com.rojao.tvlive;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.rojao.tvlive.weiget.backlook.BackLookView;
import com.rojao.tvlive.weiget.channel.ChannelAdapter;
import com.rojao.tvlive.weiget.channel.ChannelView;
import com.rojao.tvlive.weiget.recommend.RecommendDialog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ChannelView.OnChannelItemClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String KEY_TYPE = "type";
    private static final String Key_VIDEOPATH = "videoPath";
    private List<String> typeList;
    private List<String> detailList;
    private BackLookView mBackLookView;
    private ChannelView mChannelView;
    private ImageView mADIamgeView;
    private LinearLayout mTipsView;
    private String mVideoPath;
    private String mType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mType = getIntent().getStringExtra(KEY_TYPE);
        mVideoPath = getIntent().getStringExtra(Key_VIDEOPATH);
        typeList = getTypes();
        detailList = getDetailList();
        final ChannelAdapter adapter = new ChannelAdapter();
        adapter.setChannelTypes(typeList);
        mChannelView = (ChannelView) findViewById(R.id.id_channel_view);
        mBackLookView = (BackLookView) findViewById(R.id.id_backLook_view);
        mTipsView = (LinearLayout) findViewById(R.id.id_channel_list_tip);
        mADIamgeView = (ImageView) findViewById(R.id.id_iv_ad);
        mChannelView.setChannelAdapter(adapter);
        mChannelView.setOnChannelItemClickListener(this);
        mChannelView.attachBackLookView(mBackLookView);
        mBackLookView.attachChannelView(mChannelView);
    }

    private List<String> getTypes() {

        return new ArrayList<String>(Arrays.asList("常看", "高清", "标清", "付费", "少儿", "体育", "新闻"
                , "电影", "卡通"

        ));
    }

    private List<String> getDetailList() {

        return new ArrayList<String>(Arrays.asList("节目详情1", "节目详情2", "节目详情3", "节目详情4",
                "节目详情5", "节目详情6",
                "节目详情7", "节目详情8",
                "节目详情7", "节目详情8",
                "节目详情9", "节目详情10",
                "节目详情11", "节目详情12",
                "节目详情13", "节目详情14",
                "节目详情15", "节目详情16"
        ));
    }

    @Override
    public void onChannelItemClick(int firstPos, int secondPos) {

        Log.e(TAG, "curitem: " + typeList.get(firstPos) + " : " + detailList.get(secondPos));
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.e(TAG, "keycode: " + keyCode);
        if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT) {
            if (mChannelView != null) {
                if (mChannelView.getVisibility() == View.GONE && mBackLookView.getVisibility() == View.GONE) {

                    mChannelView.show();
                }

            }
        } else if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mBackLookView.getVisibility() == View.VISIBLE || mChannelView.getVisibility() == View.VISIBLE) {
                hideAllView();
                return true;
            }
        }else if (keyCode == KeyEvent.KEYCODE_DPAD_UP){
            new RecommendDialog(this).show(getWindow().getDecorView());
        }
        return super.onKeyDown(keyCode, event);
    }

    public void hideAllView() {
        if (mBackLookView != null && mBackLookView.getVisibility() == View.VISIBLE) {
            mBackLookView.setVisibility(View.GONE);
        }
        if (mChannelView != null ) {
            mChannelView.hide();
        }
        if (mTipsView != null && mTipsView.getVisibility() == View.VISIBLE) {
            mTipsView.setVisibility(View.GONE);
        }
    }
}
