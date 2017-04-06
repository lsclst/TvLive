package com.rojao.tvlive.weiget.channel;

import java.util.List;

/**
 * Created by lsc on 2017/3/27 0027.
 *
 * @author lsc
 * @desc ${TODO}
 */
public class ChannelAdapter {

    private ChannelTypeAdapter mChannelTypeAdapter;
    private ChannelDetailAdapter mChannelDetailAdapter;

    public ChannelAdapter() {
        mChannelTypeAdapter = new ChannelTypeAdapter();
        mChannelDetailAdapter = new ChannelDetailAdapter();
    }

    public ChannelTypeAdapter getChannelTypeAdapter() {
        return mChannelTypeAdapter;
    }

    public ChannelDetailAdapter getChannelDetailAdapter() {
        return mChannelDetailAdapter;
    }


    public void setChannelTypes(List<String> types) {
        if (mChannelTypeAdapter != null) {
            mChannelTypeAdapter.setTypes(types);
        }
    }

    public void setChannelDetails(List<String> details) {
        if (mChannelDetailAdapter != null) {
            mChannelDetailAdapter.setDetails(details);
        }
    }


    public void notifyDatasetChange() {
        if (mChannelTypeAdapter != null) {
            mChannelTypeAdapter.notifyDataSetChanged();
        }
        if (mChannelDetailAdapter != null) {
            mChannelDetailAdapter.notifyDataSetChanged();
        }
    }


}
