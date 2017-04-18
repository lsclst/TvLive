package com.rojao.tvlive.network;

import com.rojao.tvlive.entity.Channel;
import com.rojao.tvlive.entity.Recommend;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by lsc on 2017/4/17 0017.
 *
 * @author lsc
 * @desc ${TODO}
 */
public class WebService {

    private static volatile WebService Instance;

    private WebService() {
    }

    public static WebService getInstance() {
        if (Instance == null) {
            synchronized (WebService.class) {
                if (Instance == null) {
                    Instance = new WebService();
                }
            }
        }
        return Instance;
    }

    public List<Channel> getChannelDetail(int type) {

        List<Channel> channels = null;
        switch (type) {
            case 0:
                channels = getAlwaysLook(type);
                break;
            case 1:
                channels = getCCTV(type);
                break;

            case 2:
                channels = getHD(type);
                break;
            case 3:
                channels = getSport(type);
                break;
            case 4:
                channels = getVariety(type);
                break;
            case 5:
                channels = getNews(type);
                break;
            case 6:
                channels = getMovies(type);
                break;
            case 7:
            case 8:
                channels = getCartoon(type);
                break;
        }

        return channels;

    }

    private List<Channel> getCCTV(int type) {
        List<Channel> channels = new ArrayList<>();
        Channel channel = new Channel("CCTV-1综合", "生活提示", type + "1");
        channel.setHasLookBack(true);
        Channel channel1 = new Channel("CCTV-2财经", " 交易时间", type + "2");
        Channel channel2 = new Channel("CCTV-3综艺", " 非常6+1", type + "3");
        Channel channel3 = new Channel("CCTV-4国际", " 走遍中国", type + "4");
        Channel channel4 = new Channel("CCTV-5体育", " 欧冠开场哨", type + "5");
        Channel channel5 = new Channel("CCTV-6电影", "电影 湄公河行动 中国", type + "6");
        Channel channel6 = new Channel("CCTV-7军事", " 军旅人生", type + "7");
        Channel channel7 = new Channel("CCTV-8电视剧", " 冰与火的青春第7集", type + "8");
        Channel channel8 = new Channel("CCTV-9纪录", " 时代:金融风暴", type + "9");
        Channel channel9 = new Channel("CCTV-10新闻", " 新闻直播间", type + "10");
        Channel channel10 = new Channel("CCTV-11戏曲", "名段欣赏", type + "11");
        Channel channel11 = new Channel("CCTV-12法制", " 法律讲堂(文史版)", type + "12");
        Channel channel12 = new Channel("CCTV-13音乐", " 风华国乐", type + "13");


        channels.add(channel);
        channels.add(channel1);
        channels.add(channel2);
        channels.add(channel3);
        channels.add(channel4);
        channels.add(channel5);
        channels.add(channel6);
        channels.add(channel7);
        channels.add(channel8);
        channels.add(channel9);
        channels.add(channel10);
        channels.add(channel11);
        channels.add(channel12);


        return channels;

    }

    private List<Channel> getAlwaysLook(int type) {
        List<Channel> channels = new ArrayList<>();
        Channel channel = new Channel("安徽卫视", "中国梦纪录片展播", type + "1");
        Channel channel1 = new Channel("广东卫视", "上午剧场:欢乐颂(35)", type + "2");
        Channel channel2 = new Channel("深圳卫视", "精品剧场:利箭纵横(38)", type + "3");
        Channel channel3 = new Channel("江苏卫视", "上午剧场:小别离", type + "4");
        Channel channel4 = new Channel("湖南卫视", "偶像独播剧场:璀璨人生(21)", type + "5");
        Channel channel5 = new Channel("浙江卫视", "经典剧场:漂洋过海来看你 30", type + "6");
        Channel channel6 = new Channel("CCTV-5体育", "直播2016/2017赛季NBA季后赛-灰熊-马刺(二)", type + "7");
        Channel channel7 = new Channel("CCTV-6电影", "电影 鸿门宴传奇 中国", type + "8");


        channels.add(channel);
        channels.add(channel1);
        channels.add(channel2);
        channels.add(channel3);
        channels.add(channel4);
        channels.add(channel5);
        channels.add(channel6);
        channels.add(channel7);


        return channels;
    }

    private List<Channel> getHD(int type) {
        List<Channel> channels = new ArrayList<>();
        Channel channel = new Channel("安徽卫视", "中国梦纪录片展播", type + "1");
        Channel channel1 = new Channel("广东卫视", "上午剧场:欢乐颂(35)", type + "2");
        Channel channel2 = new Channel("深圳卫视", "精品剧场:利箭纵横(38)", type + "3");
        Channel channel3 = new Channel("江苏卫视", "上午剧场:小别离", type + "4");
        Channel channel4 = new Channel("湖南卫视", "偶像独播剧场:璀璨人生(21)", type + "5");
        Channel channel5 = new Channel("浙江卫视", "经典剧场:漂洋过海来看你 30", type + "6");
        Channel channel6 = new Channel("CCTV-5体育", "直播2016/2017赛季NBA季后赛-灰熊-马刺(二)", type + "7");
        Channel channel7 = new Channel("CCTV-6电影", "电影 鸿门宴传奇 中国", type + "8");


        channels.add(channel);
        channels.add(channel1);
        channels.add(channel2);
        channels.add(channel3);
        channels.add(channel4);
        channels.add(channel5);
        channels.add(channel6);
        channels.add(channel7);


        return channels;
    }

    private List<Channel> getSport(int type) {
        List<Channel> channels = new ArrayList<>();
        Channel channel = new Channel("广东体育", "足球世界", type + "1");
        Channel channel1 = new Channel("上海五星体育", "欧冠总决赛", type + "2");
        Channel channel2 = new Channel("北京体育", "CBA总决赛 广东VS新疆", type + "3");
        Channel channel6 = new Channel("CCTV-5体育", "直播2016/2017赛季NBA季后赛-灰熊-马刺(二)", type + "7");


        channels.add(channel);
        channels.add(channel1);
        channels.add(channel2);
        channels.add(channel6);


        return channels;
    }

    private List<Channel> getVariety(int type) {
        List<Channel> channels = new ArrayList<>();
        Channel channel = new Channel("凤凰卫视", "鲁豫有约", type + "1");
        Channel channel1 = new Channel("广东卫视", "炫风车手", type + "2");
        Channel channel2 = new Channel("天津卫视", "爱情保卫战", type + "3");
        Channel channel3 = new Channel("江苏卫视", "我们相爱吧", type + "4");
        Channel channel4 = new Channel("湖南卫视", "向往的生活", type + "5");
        Channel channel5 = new Channel("浙江卫视", "跑男来了", type + "6");
        Channel channel6 = new Channel("CCTV-1综合", "走进科学", type + "7");
        Channel channel7 = new Channel("东方卫视", "极速挑战", type + "8");


        channels.add(channel);
        channels.add(channel1);
        channels.add(channel2);
        channels.add(channel3);
        channels.add(channel4);
        channels.add(channel5);
        channels.add(channel6);
        channels.add(channel7);
        return channels;

    }

    private List<Channel> getCartoon(int type) {
        List<Channel> channels = new ArrayList<>();
        Channel channel = new Channel("金鹰卡通", " 大头儿子小头爸爸", type + "1");
        Channel channel1 = new Channel("CCTV-8少儿", "哆啦A梦", type + "2");
        Channel channel2 = new Channel("江苏卡通", "巴拉拉小魔仙", type + "3");
        Channel channel3 = new Channel("东方少儿", "四驱兄弟", type + "4");
        Channel channel4 = new Channel("凤凰卡通", "熊出没", type + "5");
        Channel channel5 = new Channel("天津卡通", " 喜洋洋与灰太狼", type + "6");
        Channel channel6 = new Channel("北京卡通", "变形金刚", type + "7");
        Channel channel7 = new Channel("深圳少儿", "小猪佩奇", type + "8");
        Channel channel8 = new Channel("广州少儿", "海绵宝宝", type + "9");
        Channel channel9 = new Channel("广东卡通", "火影忍者", type + "10");
        Channel channel10 = new Channel("广东少儿", "龙珠", type + "11");
        Channel channel11 = new Channel("珠江少儿", "蜡笔小新", type + "12");


        channels.add(channel);
        channels.add(channel1);
        channels.add(channel2);
        channels.add(channel3);
        channels.add(channel4);
        channels.add(channel5);
        channels.add(channel6);
        channels.add(channel7);
        channels.add(channel8);
        channels.add(channel9);
        channels.add(channel10);
        channels.add(channel11);


        return channels;
    }


    private List<Channel> getNews(int type) {
        List<Channel> channels = new ArrayList<>();
        Channel channel = new Channel("CCTV-1综合", " 新闻直播间", type + "1");
        Channel channel1 = new Channel("CCTV-2财经", "  环球视线", type + "2");
        Channel channel2 = new Channel("江苏卫视", " 焦点访谈", type + "3");
        Channel channel3 = new Channel("东方卫视", " 东方时空", type + "4");
        Channel channel4 = new Channel("凤凰卫视", "  朝闻天下", type + "5");
        Channel channel5 = new Channel("天津卫视", " 共同关注", type + "6");
        Channel channel6 = new Channel("北京卫视", " 新闻1+1", type + "7");
        Channel channel7 = new Channel("深圳卫视", "新闻30分", type + "8");
        Channel channel8 = new Channel("广州卫视", "午间新闻", type + "9");
        Channel channel9 = new Channel("广东卫视", "新闻大件事", type + "10");
        Channel channel10 = new Channel("广东新闻频道", "今日关注", type + "11");
        Channel channel11 = new Channel("珠江卫视", "630新闻", type + "12");


        channels.add(channel);
        channels.add(channel1);
        channels.add(channel2);
        channels.add(channel3);
        channels.add(channel4);
        channels.add(channel5);
        channels.add(channel6);
        channels.add(channel7);
        channels.add(channel8);
        channels.add(channel9);
        channels.add(channel10);
        channels.add(channel11);


        return channels;
    }

    private List<Channel> getMovies(int type) {
        List<Channel> channels = new ArrayList<>();
        Channel channel = new Channel("CCTV-6电影", "忠犬八公", type + "1");
        Channel channel1 = new Channel("北京电影", "放牛班的故事", type + "2");
        Channel channel2 = new Channel("江苏电影", " 肖申克的救赎", type + "3");
        Channel channel3 = new Channel("东方电影", " 大话西游", type + "4");
        Channel channel4 = new Channel("凤凰电影", "  星球大战", type + "5");
        Channel channel5 = new Channel("天津电影", " 僵尸世界大战", type + "6");
        Channel channel6 = new Channel("北京电影", " 西游伏妖篇", type + "7");
        Channel channel7 = new Channel("深圳电影", "功夫瑜伽", type + "8");
        Channel channel8 = new Channel("广州电影", "微微一笑很倾城", type + "9");
        Channel channel9 = new Channel("广东电影", "速度与激情7 英语", type + "10");
        Channel channel10 = new Channel("高清电影", "变形金刚4", type + "11");
        Channel channel11 = new Channel("珠江电影", "黄飞鸿之铁公鸡", type + "12");


        channels.add(channel);
        channels.add(channel1);
        channels.add(channel2);
        channels.add(channel3);
        channels.add(channel4);
        channels.add(channel5);
        channels.add(channel6);
        channels.add(channel7);
        channels.add(channel8);
        channels.add(channel9);
        channels.add(channel10);
        channels.add(channel11);


        return channels;
    }

    public List<Recommend> getRecommends(int num) {
        List<Recommend> recommends = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            Recommend recommend = new Recommend();
            recommend.setLink("http://xxxxxx.com");
            recommend.setThumbnail("http://xxxxx.com");
            recommend.setTitle("星球大战");
            recommends.add(recommend);
        }
        Collections.shuffle(recommends);
        return recommends;

    }
}
