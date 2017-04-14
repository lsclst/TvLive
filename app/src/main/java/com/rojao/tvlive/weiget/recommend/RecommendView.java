package com.rojao.tvlive.weiget.recommend;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.owen.tvrecyclerview.widget.TvRecyclerView;
import com.rojao.tvlive.R;
import com.rojao.tvlive.weiget.bridge.MainUpView;
import com.rojao.tvlive.weiget.bridge.RecyclerViewBridge;

import java.util.Arrays;
import java.util.List;

/**
 * Created by lsc on 2017/3/28 0028.
 *
 * @author lsc
 * @desc ${TODO}
 */
public class RecommendView extends LinearLayout {
    //test data

    private static final List<Integer> mColorIDs = Arrays.asList(R.color.recommendColor_1,
            R.color.recommendColor_2, R.color.recommendColor_3,
            R.color.recommendColor_4, R.color.recommendColor_5,
            R.color.recommendColor_6, R.color.recommendColor_7,
            R.color.recommendColor_8, R.color.recommendColor_4,
            R.color.recommendColor_5,
            R.color.recommendColor_6, R.color.recommendColor_7,
            R.color.recommendColor_8
    );

    private static final List<Integer> mImgs = Arrays.asList(R.mipmap.recommend1,
            R.mipmap.recommend2, R.mipmap.recommend3,
            R.mipmap.recommend4, R.mipmap.recommend4,
            R.mipmap.recommend5, R.mipmap.recommend1,
            R.mipmap.recommend4, R.mipmap.recommend4);

    private TextView mTv_title, mTv_Audience;
    private TvRecyclerView mTvRecyclerView;
    private RecyclerViewBridge mRecyclerViewBridge;
    private TvRecyclerView.OnItemListener mItemListener = new TvRecyclerView.OnItemListener() {
        @Override
        public void onItemPreSelected(TvRecyclerView parent, View itemView, int position) {

//            itemView.animate().scaleX(1.0f).scaleY(1.0f).setDuration(300).start();

            mRecyclerViewBridge.setUnFocusView(itemView);
        }

        @Override
        public void onItemSelected(TvRecyclerView parent, View itemView, int position) {
//            itemView.animate().scaleX(1.2f).scaleY(1.2f).setDuration(300).start();

            mRecyclerViewBridge.setFocusView(itemView,1.2f);
        }

        @Override
        public void onReviseFocusFollow(TvRecyclerView parent, View itemView, int position) {
            mRecyclerViewBridge.setFocusView(itemView, 1.2f);
        }

        @Override
        public void onItemClick(TvRecyclerView parent, View itemView, int position) {

        }
    };


    public RecommendView(Context context) {
        this(context, null);
    }

    public RecommendView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RecommendView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LinearLayout container = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.recommend_layout, this, true);
        mTv_title = (TextView) container.findViewById(R.id.id_title);
        mTv_Audience = (TextView) container.findViewById(R.id.id_audience);
        MainUpView mainUpView = (MainUpView) findViewById(R.id.id_mainUpView);
        mainUpView.setEffectBridge(new RecyclerViewBridge());
        mRecyclerViewBridge = (RecyclerViewBridge) mainUpView.getEffectBridge();
        mRecyclerViewBridge.setUpRectResource(R.drawable.focus_border);
        mTvRecyclerView = (TvRecyclerView) container.findViewById(R.id.id_recommend_list);
        mTvRecyclerView.setOnItemListener(mItemListener);
        mTvRecyclerView.setSpacingWithMargins(10, 8);

        RecommendListAdapter adapter = new RecommendListAdapter();
        adapter.setDatas(mImgs);
        mTvRecyclerView.setAdapter(adapter);
    }

    public void selectCenter() {
        postDelayed(new Runnable() {
            @Override
            public void run() {
                RecyclerView.Adapter adapter = mTvRecyclerView.getAdapter();
                if (adapter != null && adapter.getItemCount() != 0) {
                    mTvRecyclerView.setSelection(Math.round(adapter.getItemCount() / 2));
                }
            }
        }, 200);
    }


}
