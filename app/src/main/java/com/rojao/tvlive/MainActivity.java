package com.rojao.tvlive;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;

import com.rojao.tvlive.weiget.channel.ChannelAdapter;
import com.rojao.tvlive.weiget.channel.ChannelView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ChannelView.OnChannelItemClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private List<String> types;
    private List<String> details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        types = getTypes();
        details = getDetails();
        final ChannelAdapter adapter = new ChannelAdapter();
        adapter.setChannelTypes(types);
        ChannelView channelView = (ChannelView) findViewById(R.id.id_channel_view);
        channelView.setChannelAdapter(adapter);
        channelView.setOnChannelItemClickListener(this);
    }

    private List<String> getTypes() {

        return new ArrayList<String>(Arrays.asList("常看", "高清", "标清", "付费", "少儿", "体育", "新闻"
                , "电影", "卡通"

        ));
    }

    private List<String> getDetails() {

        return new ArrayList<String>(Arrays.asList("节目详情1", "节目详情2", "节目详情3", "节目详情4",
                "节目详情5", "节目详情6",
                "节目详情7", "节目详情8",
                "节目详情7", "节目详情8",
                "节目详情9", "节目详情10",
                "节目详情11", "节目详情12",
                "节目详情13", "节目详情14",
                "节目详情15", "节目详情16"
        ));
    }

    @Override
    public void onItemClick(int firstPos, int secondPos) {

        Log.e(TAG, "curitem: " + types.get(firstPos) + " : " + details.get(secondPos));
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }
}
