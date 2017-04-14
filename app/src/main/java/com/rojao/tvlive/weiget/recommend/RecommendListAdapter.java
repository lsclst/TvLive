package com.rojao.tvlive.weiget.recommend;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.rojao.tvlive.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by lsc on 2017/3/28 0028.
 *
 * @author lsc
 * @desc ${TODO}
 */
public class RecommendListAdapter extends RecyclerView.Adapter<RecommendListAdapter.RecommendHolder> {

    private List<Integer> mDatas = new ArrayList<>();
    private static final List<Integer> mColorIDs = Arrays.asList(R.color.recommendColor_1,
            R.color.recommendColor_2, R.color.recommendColor_3,
            R.color.recommendColor_4, R.color.recommendColor_5,
            R.color.recommendColor_6, R.color.recommendColor_7,
            R.color.recommendColor_8, R.color.recommendColor_4,
            R.color.recommendColor_5,
            R.color.recommendColor_6, R.color.recommendColor_7,
            R.color.recommendColor_8
    );

    @Override
    public RecommendHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View content = LayoutInflater.from(parent.getContext()).inflate(R.layout.recommend_item, parent, false);

        return new RecommendHolder(content);
    }

    @Override
    public void onBindViewHolder(RecommendHolder holder, int position) {
       holder.iv_recommend.setImageResource(mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }


    public void setDatas(List<Integer> datas) {
        mDatas = datas;
        notifyDataSetChanged();
    }



    static class RecommendHolder extends RecyclerView.ViewHolder {

        ImageView iv_recommend;

        public RecommendHolder(View itemView) {
            super(itemView);
            iv_recommend = (ImageView) itemView.findViewById(R.id.id_recommend_iv);
        }
    }
}
