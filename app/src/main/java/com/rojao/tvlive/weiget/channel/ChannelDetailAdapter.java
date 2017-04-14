package com.rojao.tvlive.weiget.channel;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rojao.tvlive.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lsc on 2017/3/27 0027.
 *
 * @author lsc
 * @desc ${TODO}
 */
public class ChannelDetailAdapter extends RecyclerView.Adapter<ChannelDetailAdapter.DetailHolder> {


    private List<String> mChannelDetails = new ArrayList<>();


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public DetailHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View content = LayoutInflater.from(parent.getContext()).inflate(R.layout.channel_detail_item, parent, false);

        return new DetailHolder(content);
    }

    @Override
    public void onBindViewHolder(DetailHolder holder, int position) {

        holder.tv_detail.setText(mChannelDetails.get(position));
    }

    @Override
    public int getItemCount() {
        return mChannelDetails.size();
    }

    public void setDetails(List<String> details) {
        mChannelDetails.clear();
        mChannelDetails = details;
//        notifyItemRangeInserted(0, mChannelDetails.size() );  cause a bug
        notifyDataSetChanged();
    }

    public List<String> getChannelDetails() {
        return mChannelDetails;
    }

    static class DetailHolder extends RecyclerView.ViewHolder {

        TextView tv_detail;

        public DetailHolder(View itemView) {
            super(itemView);
            tv_detail = (TextView) itemView.findViewById(R.id.id_detail);
        }
    }
}
