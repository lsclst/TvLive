package com.rojao.tvlive.weiget.channel;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rojao.tvlive.R;
import com.rojao.tvlive.entity.Channel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lsc on 2017/3/27 0027.
 *
 * @author lsc
 * @desc ${TODO}
 */
public class ChannelDetailAdapter extends RecyclerView.Adapter<ChannelDetailAdapter.DetailHolder> {


    private List<Channel> mChannelDetails = new ArrayList<>();


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
        Channel channel = mChannelDetails.get(position);
        if (channel.isHasLookBack()) {
            holder.tv_detail.setCompoundDrawablesWithIntrinsicBounds(null, null, ContextCompat.getDrawable(holder.itemView.getContext(), R.mipmap.has_look_back), null);
        }
        holder.tv_detail.setText(channel.getChannelTitle());
        holder.tv_curChannel.setText(channel.getCurChannel());
        holder.tv_channelNo.setText(channel.getChannelNo());

    }

    @Override
    public int getItemCount() {
        return mChannelDetails.size();
    }

    public void setDetails(List<Channel> details) {
        mChannelDetails.clear();
        mChannelDetails = details;
        //        notifyItemRangeInserted(0, mChannelDetails.size() );  cause a bug
        notifyDataSetChanged();
    }

    public List<Channel> getChannelDetails() {
        return mChannelDetails;
    }

    static class DetailHolder extends RecyclerView.ViewHolder {

        TextView tv_detail;
        TextView tv_curChannel;
        TextView tv_channelNo;

        public DetailHolder(View itemView) {
            super(itemView);
            tv_detail = (TextView) itemView.findViewById(R.id.id_detail);
            tv_curChannel = (TextView) itemView.findViewById(R.id.id_curChannel);
            tv_channelNo = (TextView) itemView.findViewById(R.id.id_channel_no);
        }
    }
}
