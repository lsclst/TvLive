package com.rojao.tvlive.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by lsc on 2017/4/17 0017.
 *
 * @author lsc
 * @desc ${TODO}
 */
public class Channel {
    @SerializedName("channelCode")
    private String channelNo;
    private String curChannel;
    @SerializedName("channelName")
    private String channelTitle;
    private boolean hasLookBack;
    @SerializedName("playUrl")
    private String linkPath;

    public String getLinkPath() {
        return linkPath;
    }

    public void setLinkPath(String linkPath) {
        this.linkPath = linkPath;
    }

    public Channel(String channelTitle, String curChannel, String channelNo) {
        this.channelTitle = channelTitle;
        this.curChannel = curChannel;
        this.channelNo = channelNo;
    }

    public Channel() {
    }

    public boolean isHasLookBack() {
        return hasLookBack;
    }

    public void setHasLookBack(boolean hasLookBack) {
        this.hasLookBack = hasLookBack;
    }

    public String getChannelNo() {
        return channelNo;
    }

    public void setChannelNo(String channelNo) {
        this.channelNo = channelNo;
    }

    public String getCurChannel() {
        return curChannel;
    }

    public void setCurChannel(String curChannel) {
        this.curChannel = curChannel;
    }

    public String getChannelTitle() {
        return channelTitle;
    }

    public void setChannelTitle(String channelTitle) {
        this.channelTitle = channelTitle;
    }
}
