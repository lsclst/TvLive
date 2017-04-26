package com.rojao.tvlive.entity;

/**
 * Created by lsc on 2017/4/25 0025.
 *
 * @author lsc
 * @desc ${TODO}
 */
public class BackLookEPG {

    /**
     * assertId : 1493088632885
     * channelId : 9999999920120926001200
     * duration : 3120000
     * endTime : 2017-04-24 01:12
     * programName : 动物世界
     * startTime : 2017-04-24 00:20
     */

    private String assertId;
    private String channelId;
    private String endTime;
    private String programName;
    private String startTime;

    public String getAssertId() {
        return assertId;
    }

    public void setAssertId(String assertId) {
        this.assertId = assertId;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getEndTime() {
        return endTime.split(" ")[1];
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getStartTime() {
        return startTime.split(" ")[1];
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
}
