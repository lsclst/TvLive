package com.rojao.tvlive.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by lsc on 2017/4/17 0017.
 *
 * @author lsc
 * @desc ${TODO}
 */
public class Recommend {
    @SerializedName("vodPicpath")
    private String thumbnail;
    @SerializedName("vodUrl")
    private String link;
    @SerializedName("vodName")
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
