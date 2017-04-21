package com.rojao.tvlive.network;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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

    private static final String JSON = "[{\"assetId\":\"JSBYPPL02000008864481901\",\"vodActor\":\"布拉德·皮特|朱莉娅·罗伯茨|詹姆斯·甘多菲尼|J·K·西蒙斯|鲍勃·巴拉班\",\"vodDescription\":\"充满浪漫喜剧色彩的美国版《寻枪》，金发帅哥皮特携手大嘴甜心罗伯茨，配乐带有浓浓的墨西哥情调。小混混杰瑞运气有些背，顶头大哥命令他寻找古董枪，女友则勒令他金盆洗手，一番冥思苦想之后，他决定放手一博。\",\"vodDirector\":\"戈尔·维宾斯基\",\"vodDuration\":\"7380\",\"vodName\":\"危险情人\",\"vodPicpath\":\"http://122.97.219.210/images/thumb/2012/03/30/1001/201203300952001001z2554/201203300952001001z2554_250x344.jpg\",\"vodType\":\"1\",\"vodUrl\":\"http://122.97.219.210/yppl/2015/03/30/JSBYPPL02000008864481901.m3u8\",\"year\":\"2001\"},{\"assetId\":\"JSBYPPL020000291703\",\"vodActor\":\"贾斯汀·丁伯莱克|阿曼达·塞弗里德|奥利维亚·王尔德|约翰尼·盖尔克奇|Shyloh\n" +
            "\t\tOostwald\",\"vodDescription\":\"在不太遥远的未来，人类的极限是25岁，此后自然寿命只有一年。通用货币就是时间，可以此交易延长寿命直至永生；自然，时间清零便是死亡。明星养眼，骨感+黑丝+烟熏，值得一提的是《生活大爆炸》中的莱纳德也有出演。\",\"vodDirector\":\"安德鲁·尼科尔\",\"vodDuration\":\"6420\",\"vodName\":\"时间规划局\",\"vodPicpath\":\"http://122.97.219.210/images/thumb/2012/03/12/1000/201203121408311000z0304/201203121408311000z0304_250x344.jpg\",\"vodType\":\"1\",\"vodUrl\":\"http://122.97.219.210/yppl/JSBYPPL020000291703.m3u8\",\"year\":\"2011\"},{\"assetId\":\"JSBYPPL020000309809\",\"vodActor\":\"卡尔·厄本|奥莉薇·瑟尔比|琳娜·海蒂|Deobia\n" +
            "\t\tOparei|Langley\n" +
            "\t\tKirkwood\",\"vodDescription\":\"翻拍自史泰龙95年的版本。在末世人类聚居超级城市中，外围是突变生物占据的辐射荒漠。这里新的执法者被称为特警判官，对犯罪拥有就地正法的至高权力。影片场面暴力，刺激，令人血脉贲张。\",\"vodDirector\":\"彼得·特拉维斯\",\"vodDuration\":\"5700\",\"vodName\":\"新特警判官\",\"vodPicpath\":\"http://122.97.219.210/images/thumb/2013/01/14/1000/201301141106211000z3f31/201301141106211000z3f31_250x344.jpg\",\"vodType\":\"1\",\"vodUrl\":\"http://122.97.219.210/yppl/JSBYPPL020000309809.m3u8\",\"year\":\"2012\"},{\"assetId\":\"JSBYPPL020000295209\",\"vodActor\":\"伊萨赫·德·班克尔|蒂尔达·斯文顿|盖尔·加西亚·贝纳尔|帕兹·德拉维尔塔|工藤夕贵\",\"vodDescription\":\"神秘兮兮的雇主、策划精密的谋杀，一个沉默的独行杀手，接受到一项神秘的任务，随后启程前往西班牙马德里，按照他们的指引，一步步逼近了他的目标……在这部明星云集的吉姆·贾木许的电影里依然能找到独立制片的影子。\",\"vodDirector\":\"吉姆·贾木许\",\"vodDuration\":\"6360\",\"vodName\":\"控制的极限\",\"vodPicpath\":\"http://122.97.219.210/images/thumb/2012/07/02/1000/201207021233201000z4aa6/201207021233201000z4aa6_250x344.jpg\",\"vodType\":\"1\",\"vodUrl\":\"http://122.97.219.210/yppl/JSBYPPL020000295209.m3u8\",\"year\":\"2009\"},{\"assetId\":\"JSBYPPL02000008879637901\",\"vodActor\":\"马特·狄龙|劳伦斯·菲什伯恩|让·雷诺|阿莫里·诺拉斯科|弗莱德·沃德|米洛·文堤米利亚|斯基特·乌尔里奇|哥伦布·绍特|安德烈？凯尼\",\"vodDescription\":\"好莱坞众银幕硬汉云集，“纯爷们”动作片！突发的意外却让情况彻底地扭转，他们不得不争分夺秒地进行补救，尽自己最大的努力挽回之前的错误，数百万美元突然变成了危险商品……\",\"vodDirector\":\"尼莫洛德·安塔尔\",\"vodDuration\":\"5280\",\"vodName\":\"激战运钞车\",\"vodPicpath\":\"http://122.97.219.210/images/thumb/2012/03/30/1000/201203300951121000z640a/201203300951121000z640a_250x344.jpg\",\"vodType\":\"1\",\"vodUrl\":\"http://122.97.219.210/yppl/2015/03/31/JSBYPPL02000008879637901.m3u8\",\"year\":\"2009\"},{\"assetId\":\"JSBYPPL02000009794055601\",\"vodActor\":\"王翔弘|童鑫|姚雨鑫|吴晴|唐佳成\",\"vodDescription\":\"照进人心灵的照相机。讲述蒙昊在古镇租下一座老宅，但老宅里出现的红衣女鬼惊吓到了蒙昊女友冉洁，慢慢的冉洁感觉到女鬼事件另有蹊跷并开始展开调查，在调查中怪异事件的真相开始浮出水面。\",\"vodDirector\":\"李庆城\",\"vodDuration\":\"5040\",\"vodName\":\"恐怖照相机\",\"vodPicpath\":\"http://122.97.219.210/images/thumb/2016/05/31/1000/201605311614441000z3533/201605311614441000z3533_480x660.jpg\",\"vodType\":\"1\",\"vodUrl\":\"http://122.97.219.210/yppl/2016/05/31/JSBYPPL02000009794055601.m3u8\",\"year\":\"2015\"},{\"assetId\":\"JSBYPPL020000286541\",\"vodActor\":\"Otto\n" +
            "\t\tJespersen|Hans\n" +
            "\t\tMorten\",\"vodDescription\":\"比央视《走近科学》好N倍，这部伪纪录片里的挪威风光秀丽壮观，群山峻岭，高山雪原，让人很享受。2008年的一天，挪威沃达大学的托马斯、乔安娜组成摄制组，前往偏远山区追踪猎熊人，而他们却发现了山中的巨型怪物。\",\"vodDirector\":\"安德烈·欧弗兰多\",\"vodDuration\":\"5940\",\"vodName\":\"追击巨怪\",\"vodPicpath\":\"http://122.97.219.210/images/ROJAO/201610101619531.jpg\",\"vodType\":\"1\",\"vodUrl\":\"http://122.97.219.210/yppl/JSBYPPL020000286541.m3u8\",\"year\":\"2010\"},{\"assetId\":\"JSBYPPL02000008986812601\",\"vodActor\":\"杰森·贝特曼|丽贝卡·豪尔|乔尔·埃哲顿|大卫·丹曼|蒂姆·格里芬|贝茜·菲利普斯\",\"vodDescription\":\"君子报仇永不晚。影片讲述一对夫妻搬了新的住处，但是却意外的收到了一个接一个的来自号称老同学送来的恶作剧礼物，随着时间的推移，他们慢慢发现，这不是恶作剧，而是对他们生命的威胁。\",\"vodDirector\":\"乔尔·埃哲顿\",\"vodDuration\":\"6420\",\"vodName\":\"致命礼物\",\"vodPicpath\":\"http://122.97.219.210/images/thumb/2016/03/30/1000/201603301505331000zd23d/201603301505331000zd23d_480x660.jpg\",\"vodType\":\"1\",\"vodUrl\":\"http://122.97.219.210/yppl/2016/03/30/JSBYPPL02000008986812601.m3u8\",\"year\":\"2015\"},{\"assetId\":\"JSBYPPL020000278031\",\"vodActor\":\"格蕾塔·葛韦格|亚当·布罗迪|安娜蕾·提普顿|梅歌林·艾奇坤沃克|卡莉·麦克勒摩尔\",\"vodDescription\":\"一部关注大学校园男权、女权及性别歧视的影片。主要讲述了两位少女去“防止自杀中心”当志愿者的故事，刻画了少女成长中的焦虑与自省。这部电影集青春、音乐、浪漫等元素于一身。\",\"vodDirector\":\"惠特·斯蒂尔曼\",\"vodDuration\":\"5940\",\"vodName\":\"待解救的少女\",\"vodPicpath\":\"http://122.97.219.210/images/thumb/2012/11/09/1000/201211091006281000z9f9a/201211091006281000z9f9a_250x344.jpg\",\"vodType\":\"1\",\"vodUrl\":\"http://122.97.219.210/yppl/2015/04/08/JSBYPPL020000278031.m3u8\",\"year\":\"2012\"},{\"assetId\":\"JSBYPPL02000009247783201\",\"vodActor\":\"柳承范|高俊熙|柳贤静|梁益俊|金应洙|郑元中|金柱赫\",\"vodDescription\":\"韩式黑色幽默犯罪片。该片是一部极具动感的动作电影，由风格化著称的林常树导演所执导，该片故事围绕几个在交通事故现场发现巨额现金的年轻人展开，柳承范领衔众演员在片中奉献了个性张扬的表演。\",\"vodDirector\":\"林常树\",\"vodDuration\":\"6600\",\"vodName\":\"我的亲密敌人\",\"vodPicpath\":\"http://122.97.219.210/images/pic/2015/08/10/1000/201508101014561000z9213.jpg\",\"vodType\":\"1\",\"vodUrl\":\"http://122.97.219.210/yppl/2015/07/31/JSBYPPL02000009247783201.m3u8\",\"year\":\"2015\"}]\n" +
            "\t";

    private static final String ALL_CHANNEL_JSON = "[\n" +
            "  {\n" +
            "    \"channelCode\": \"9999999920120924001000\",\n" +
            "    \"channelName\": \"CCTV14\",\n" +
            "    \"playUrl\": \"http://221.6.85.155/live/cctv14_800/cctv14_800.m3u8\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"channelCode\": \"9999999920120924001400\",\n" +
            "    \"channelName\": \"CCTV3综艺\",\n" +
            "    \"playUrl\": \"http://221.6.85.155/live/cctv3_800/cctv3_800.m3u8\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"channelCode\": \"9999999920120926002000\",\n" +
            "    \"channelName\": \"CCTV4中文国际\",\n" +
            "    \"playUrl\": \"http://221.6.85.155/live/cctv4_800/cctv4_800.m3u8\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"channelCode\": \"9999999920120920000000\",\n" +
            "    \"channelName\": \"CCTV5体育\",\n" +
            "    \"playUrl\": \"http://221.6.85.155/live/cctv5_800/cctv5_800.m3u8\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"channelCode\": \"9999999920121218006200\",\n" +
            "    \"channelName\": \"CCTV6电影\",\n" +
            "    \"playUrl\": \"http://221.6.85.155/live/cctv6_800/cctv6_800.m3u8\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"channelCode\": \"9999999920121218003800\",\n" +
            "    \"channelName\": \"CCTV8电视剧\",\n" +
            "    \"playUrl\": \"http://221.6.85.155/live/cctv8_800/cctv8_800.m3u8\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"channelCode\": \"9999999920120926001200\",\n" +
            "    \"channelName\": \"CCTV1综合\",\n" +
            "    \"playUrl\": \"http://221.6.85.155/live/cctv1_800/cctv1_800.m3u8\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"channelCode\": \"9999999920120907000600\",\n" +
            "    \"channelName\": \"安徽卫视\",\n" +
            "    \"playUrl\": \"http://221.6.85.155/live/ahws_800/ahws_800.m3u8\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"channelCode\": \"9999999920120907001000\",\n" +
            "    \"channelName\": \"东方卫视\",\n" +
            "    \"playUrl\": \"http://221.6.85.155/live/dfws_800/dfws_800.m3u8\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"channelCode\": \"9999999920120925000000\",\n" +
            "    \"channelName\": \"广东卫视\",\n" +
            "    \"playUrl\": \"http://221.6.85.155/live/gdws_800/gdws_800.m3u8\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"channelCode\": \"9999999920120912001200\",\n" +
            "    \"channelName\": \"湖南卫视\",\n" +
            "    \"playUrl\": \"http://221.6.85.155/live/hnws_800/hnws_800.m3u8\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"channelCode\": \"9999999920120926000200\",\n" +
            "    \"channelName\": \"山东卫视\",\n" +
            "    \"playUrl\": \"http://221.6.85.155/live/sdws_800/sdws_800.m3u8\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"channelCode\": \"9999999920121218000600\",\n" +
            "    \"channelName\": \"天津卫视\",\n" +
            "    \"playUrl\": \"http://221.6.85.155/live/tjws_800/tjws_800.m3u8\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"channelCode\": \"9999999920121218003400\",\n" +
            "    \"channelName\": \"CCTV7\",\n" +
            "    \"playUrl\": \"http://221.6.85.155/live/cctv7_800/cctv7_800.m3u8\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"channelCode\": \"9999999920140411000100\",\n" +
            "    \"channelName\": \"CCTV9记录\",\n" +
            "    \"playUrl\": \"http://221.6.85.155/live/cctv9_800/cctv9_800.m3u8\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"channelCode\": \"888002\",\n" +
            "    \"channelName\": \"CCTV2财经\",\n" +
            "    \"playUrl\": \"http://221.6.85.155/live/cctv2_800/cctv2_800.m3u8\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"channelCode\": \"888010\",\n" +
            "    \"channelName\": \"CCTV10科教\",\n" +
            "    \"playUrl\": \"http://221.6.85.155/live/cctv10_800/cctv10_800.m3u8\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"channelCode\": \"888011\",\n" +
            "    \"channelName\": \"CCTV11戏曲\",\n" +
            "    \"playUrl\": \"http://221.6.85.155/live/cctv11_800/cctv11_800.m3u8\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"channelCode\": \"888012\",\n" +
            "    \"channelName\": \"CCTV12社会与法\",\n" +
            "    \"playUrl\": \"http://221.6.85.155/live/cctv12_800/cctv12_800.m3u8\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"channelCode\": \"888013\",\n" +
            "    \"channelName\": \"CCTV13新闻\",\n" +
            "    \"playUrl\": \"http://221.6.85.155/live/cctv13_800/cctv13_800.m3u8\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"channelCode\": \"888015\",\n" +
            "    \"channelName\": \"CCTV15音乐\",\n" +
            "    \"playUrl\": \"http://221.6.85.155/live/cctv15_800/cctv15_800.m3u8\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"channelCode\": \"888016\",\n" +
            "    \"channelName\": \"江苏卫视\",\n" +
            "    \"playUrl\": \"http://221.6.85.155/live/jsws_800/jsws_800.m3u8\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"channelCode\": \"888017\",\n" +
            "    \"channelName\": \"浙江卫视\",\n" +
            "    \"playUrl\": \"http://221.6.85.155/live/zjws_800/zjws_800.m3u8\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"channelCode\": \"888018\",\n" +
            "    \"channelName\": \"江西卫视\",\n" +
            "    \"playUrl\": \"http://221.6.85.155/live/jxws_800/jxws_800.m3u8\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"channelCode\": \"888019\",\n" +
            "    \"channelName\": \"陕西卫视\",\n" +
            "    \"playUrl\": \"http://221.6.85.155/live/sxws_800/sxws_800.m3u8\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"channelCode\": \"888020\",\n" +
            "    \"channelName\": \"江苏综艺\",\n" +
            "    \"playUrl\": \"http://221.6.85.155/live/jszy_800/jszy_800.m3u8\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"channelCode\": \"888021\",\n" +
            "    \"channelName\": \"苏州新闻综合\",\n" +
            "    \"playUrl\": \"http://221.6.85.155/live/szxwzh_800/szxwzh_800.m3u8\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"channelCode\": \"888022\",\n" +
            "    \"channelName\": \"优漫卡通\",\n" +
            "    \"playUrl\": \"http://221.6.85.155/live/ymkt_800/ymkt_800.m3u8\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"channelCode\": \"3806131\",\n" +
            "    \"channelName\": \"游戏风云\",\n" +
            "    \"playUrl\": \"http://221.6.85.155/live/yxfy_800/yxfy_800.m3u8\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"channelCode\": \"3806151\",\n" +
            "    \"channelName\": \"昆山新闻综合\",\n" +
            "    \"playUrl\": \"http://221.6.85.155/live/ksxwzh_800/ksxwzh_800.m3u8\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"channelCode\": \"3806161\",\n" +
            "    \"channelName\": \"昆山社会生活\",\n" +
            "    \"playUrl\": \"http://221.6.85.155/live/ksshsh_800/ksshsh_800.m3u8\"\n" +
            "  }\n" +
            "]";

    private static volatile WebService Instance;
    private List<Channel> allChannels;
    private Gson mGson = new Gson();

    private WebService() {
        allChannels = mGson.fromJson(ALL_CHANNEL_JSON, new TypeToken<List<Channel>>() {
        }.getType());
    }

    public List<Channel> getAllChannels() {
        return allChannels;
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
        channel.setLinkPath("http://221.6.85.155/live/cctv1_800/cctv1_800.m3u8");
        Channel channel1 = new Channel("CCTV-2财经", " 交易时间", type + "2");
        channel1.setLinkPath("http://221.6.85.155/live/cctv2_800/cctv2_800.m3u8");
        Channel channel2 = new Channel("CCTV-3综艺", " 非常6+1", type + "3");
        channel2.setLinkPath("http://221.6.85.155/live/cctv3_800/cctv3_800.m3u8");
        Channel channel3 = new Channel("CCTV-4国际", " 走遍中国", type + "4");
        channel3.setLinkPath("http://221.6.85.155/live/cctv4_800/cctv4_800.m3u8");
        Channel channel4 = new Channel("CCTV-5体育", " 欧冠开场哨", type + "5");
        channel4.setLinkPath("http://221.6.85.155/live/cctv5_800/cctv5_800.m3u8");
        Channel channel5 = new Channel("CCTV-6电影", "电影 湄公河行动 中国", type + "6");
        channel5.setLinkPath("http://221.6.85.155/live/cctv6_800/cctv6_800.m3u8");
        Channel channel6 = new Channel("CCTV-7军事", " 军旅人生", type + "7");
        channel6.setLinkPath("http://221.6.85.155/live/cctv7_800/cctv7_800.m3u8");
        Channel channel7 = new Channel("CCTV-8电视剧", " 冰与火的青春第7集", type + "8");
        channel7.setLinkPath("http://221.6.85.155/live/cctv8_800/cctv8_800.m3u8");
        Channel channel8 = new Channel("CCTV-9纪录", " 时代:金融风暴", type + "9");
        channel8.setLinkPath("http://221.6.85.155/live/cctv9_800/cctv9_800.m3u8");
        Channel channel9 = new Channel("CCTV-10科教", "人与自然", type + "10");
        channel9.setLinkPath("http://221.6.85.155/live/cctv10_800/cctv10_800.m3u8");
        Channel channel10 = new Channel("CCTV-11戏曲", "名段欣赏", type + "11");
        channel10.setLinkPath("http://221.6.85.155/live/cctv11_800/cctv11_800.m3u8");
        Channel channel11 = new Channel("CCTV-12法制", " 法律讲堂(文史版)", type + "12");
        channel11.setLinkPath("http://221.6.85.155/live/cctv12_800/cctv12_800.m3u8");
        Channel channel12 = new Channel("CCTV-13新闻", " 新闻直播间", type + "13");
        channel12.setLinkPath("http://221.6.85.155/live/cctv13_800/cctv13_800.m3u8");
        Channel channel13 = new Channel("CCTV-14少儿", "猫和老鼠", type + "13");
        channel12.setLinkPath("http://221.6.85.155/live/cctv14_800/cctv14_800.m3u8");
        Channel channel14 = new Channel("CCTV-15音乐", " 风华国乐", type + "13");
        channel12.setLinkPath("http://221.6.85.155/live/cctv15_800/cctv15_800.m3u8");

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
        channels.add(channel13);
        channels.add(channel14);

        return channels;

    }

    private List<Channel> getAlwaysLook(int type) {
        List<Channel> channels = new ArrayList<>();
        Channel channel = new Channel("安徽卫视", "中国梦纪录片展播", type + "1");
        channel.setLinkPath("http://221.6.85.155/live/ahws_800/ahws_800.m3u8");
        Channel channel1 = new Channel("广东卫视", "上午剧场:欢乐颂(35)", type + "2");
        channel1.setLinkPath("http://221.6.85.155/live/gdws_800/gdws_800.m3u8");
        Channel channel2 = new Channel("东方卫视", "精品剧场:利箭纵横(38)", type + "3");
        channel2.setLinkPath("http://221.6.85.155/live/dfws_800/dfws_800.m3u8");
        Channel channel3 = new Channel("江苏卫视", "上午剧场:小别离", type + "4");
        channel3.setLinkPath("http://221.6.85.155/live/jsws_800/jsws_800.m3u8");
        Channel channel4 = new Channel("湖南卫视", "偶像独播剧场:璀璨人生(21)", type + "5");
        channel4.setLinkPath("http://221.6.85.155/live/hnws_800/hnws_800.m3u8");
        Channel channel5 = new Channel("浙江卫视", "经典剧场:漂洋过海来看你 30", type + "6");
        channel5.setLinkPath("http://221.6.85.155/live/zjws_800/zjws_800.m3u8");
        Channel channel6 = new Channel("CCTV-5体育", "直播2016/2017赛季NBA季后赛-灰熊-马刺(二)", type + "7");
        channel6.setLinkPath("http://221.6.85.155/live/cctv5_800/cctv5_800.m3u8");
        Channel channel7 = new Channel("CCTV-6电影", "电影 鸿门宴传奇 中国", type + "8");
        channel7.setLinkPath("http://221.6.85.155/live/cctv6_800/cctv6_800.m3u8");

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
        channel.setLinkPath("http://221.6.85.155/live/ahws_800/ahws_800.m3u8");
        Channel channel1 = new Channel("广东卫视", "上午剧场:欢乐颂(35)", type + "2");
        channel1.setLinkPath("http://221.6.85.155/live/gdws_800/gdws_800.m3u8");
        Channel channel2 = new Channel("东方卫视", "精品剧场:利箭纵横(38)", type + "3");
        channel2.setLinkPath("http://221.6.85.155/live/dfws_800/dfws_800.m3u8");
        Channel channel3 = new Channel("江苏卫视", "上午剧场:小别离", type + "4");
        channel3.setLinkPath("http://221.6.85.155/live/jsws_800/jsws_800.m3u8");
        Channel channel4 = new Channel("湖南卫视", "偶像独播剧场:璀璨人生(21)", type + "5");
        channel3.setLinkPath("http://221.6.85.155/live/jsws_800/jsws_800.m3u8");
        Channel channel5 = new Channel("浙江卫视", "经典剧场:漂洋过海来看你 30", type + "6");
        channel5.setLinkPath("http://221.6.85.155/live/zjws_800/zjws_800.m3u8");
        Channel channel6 = new Channel("CCTV-5体育", "直播2016/2017赛季NBA季后赛-灰熊-马刺(二)", type + "7");
        channel6.setLinkPath("http://221.6.85.155/live/cctv5_800/cctv5_800.m3u8");
        Channel channel7 = new Channel("CCTV-6电影", "电影 鸿门宴传奇 中国", type + "8");
        channel7.setLinkPath("http://221.6.85.155/live/cctv6_800/cctv6_800.m3u8");


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
        List<Recommend> recommends = mGson.fromJson(JSON, new TypeToken<List<Recommend>>() {
        }.getType());
        Collections.shuffle(recommends);
        return recommends;

    }
}
