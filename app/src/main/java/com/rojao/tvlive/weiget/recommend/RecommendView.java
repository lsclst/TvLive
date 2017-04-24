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
import com.rojao.tvlive.entity.Recommend;

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


    private TextView mTv_title, mTv_Audience;
    private RecommendListAdapter mAdapter;
    private List<Recommend> mRecommends;

    public String getTv_title() {
        return mTv_title.getText().toString();
    }

    public void setTv_title(String title) {
        mTv_title.setText(title);
    }

    public String getTv_Audience() {
        return mTv_Audience.getText().toString();
    }

    public void setTv_Audience(String audience) {
        mTv_Audience.setText(audience);
    }

    private TvRecyclerView mTvRecyclerView;
    private TvRecyclerView.OnItemListener mItemListener = new TvRecyclerView.OnItemListener() {
        @Override
        public void onItemPreSelected(TvRecyclerView parent, View itemView, int position) {
            itemView.animate().scaleX(1.0f).scaleY(1.0f).setDuration(300).start();
        }

        @Override
        public void onItemSelected(TvRecyclerView parent, View itemView, int position) {
            itemView.animate().scaleX(1.2f).scaleY(1.2f).setDuration(300).start();
        }

        @Override
        public void onReviseFocusFollow(TvRecyclerView parent, View itemView, int position) {
            itemView.animate().scaleX(1.2f).scaleY(1.2f).setDuration(300).start();
        }

        @Override
        public void onItemClick(TvRecyclerView parent, View itemView, int position) {
            if (mOnRecommendItemClickListener != null) {
                mOnRecommendItemClickListener.OnItemClick(mRecommends.get(position).getLink());
            }
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
        mTvRecyclerView = (TvRecyclerView) container.findViewById(R.id.id_recommend_list);
        mTvRecyclerView.setOnItemListener(mItemListener);
        mTvRecyclerView.setSpacingWithMargins(10, 8);

        mAdapter = new RecommendListAdapter();

        mTvRecyclerView.setAdapter(mAdapter);
    }

    public void setDatas(List<Recommend> recommends) {
        if (mAdapter == null) {
            mAdapter = new RecommendListAdapter();
            mTvRecyclerView.setAdapter(mAdapter);
        }
        mRecommends = recommends;
        mAdapter.setDatas(recommends);
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
        }, 400);
    }


    public interface onRecommendItemClickListener {
        void OnItemClick(String linkPath);
    }

    private onRecommendItemClickListener mOnRecommendItemClickListener;

    public void setOnRecommendItemClickListener(onRecommendItemClickListener listener) {
        mOnRecommendItemClickListener = listener;
    }
}
