package com.rojao.tvlive.weiget.recommend;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rojao.tvlive.R;
import com.rojao.tvlive.entity.Recommend;
import com.squareup.picasso.Picasso;

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

    private List<Recommend> mDatas = new ArrayList<>();
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
        Context context = holder.itemView.getContext();
        Picasso.with(context).load(mDatas.get(position).getThumbnail()).into(holder.iv_recommend);
        holder.tv_title.setText(mDatas.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }


    public void setDatas(List<Recommend> datas) {
        mDatas = datas;
        notifyDataSetChanged();
    }


    static class RecommendHolder extends RecyclerView.ViewHolder {

        ImageView iv_recommend;
        TextView tv_title;

        public RecommendHolder(View itemView) {
            super(itemView);
            iv_recommend = (ImageView) itemView.findViewById(R.id.id_recommend_iv);
            tv_title = (TextView) itemView.findViewById(R.id.id_title);
        }
    }
}
