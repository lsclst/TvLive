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
public class ChannelTypeAdapter extends RecyclerView.Adapter<ChannelTypeAdapter.ChannelTypeViewHolder> {

    private List<String> mChannelTypes = new ArrayList<>();


    @Override
    public ChannelTypeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View content = LayoutInflater.from(parent.getContext()).inflate(R.layout.channel_type_item, parent, false);

        return new ChannelTypeViewHolder(content);
    }

    @Override
    public void onBindViewHolder(ChannelTypeViewHolder holder, int position) {
        holder.tv_Channel.setText(mChannelTypes.get(position));
    }


    @Override
    public int getItemCount() {
        return mChannelTypes.size();
    }

    public void setTypes(List<String> types) {
        mChannelTypes.clear();
        mChannelTypes = types;
        notifyItemRangeInserted(0,mChannelTypes.size());
    }

    public List<String> getChannelTypes() {
        return mChannelTypes;
    }

    static class ChannelTypeViewHolder extends RecyclerView.ViewHolder {

        TextView tv_Channel;

        public ChannelTypeViewHolder(View itemView) {
            super(itemView);
            tv_Channel = (TextView) itemView.findViewById(R.id.id_type);
        }
    }
}
