package com.rojao.tvlive.weiget;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.rojao.tvlive.R;
import com.rojao.tvlive.weiget.backlook.BackLookView;
import com.rojao.tvlive.weiget.channel.ChannelAdapter;
import com.rojao.tvlive.weiget.channel.ChannelView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by lsc on 2017/4/17 0017.
 *
 * @author lsc
 * @desc ${TODO}
 */
public class ChannelDialog {

    private final List<String> typeList;
    private PopupWindow mPopupWindow;
    private ChannelView mChannelView;
    private BackLookView mBackLookView;

    public ChannelDialog(Context context, ChannelView.OnChannelItemClickListener listener) {
        View contentView = LayoutInflater.from(context).inflate(R.layout.channel_dialog, null);
        mPopupWindow = new PopupWindow(context);
        mPopupWindow.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        mPopupWindow.setHeight(WindowManager.LayoutParams.MATCH_PARENT);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setBackgroundDrawable(null);
        mPopupWindow.setAnimationStyle(R.style.channel_dialog_anim_style);
        mPopupWindow.setContentView(contentView);
        mChannelView = (ChannelView) contentView.findViewById(R.id.id_channel_view);
        mBackLookView = (BackLookView) contentView.findViewById(R.id.id_backLook_view);
        typeList = getTypes();
        final ChannelAdapter adapter = new ChannelAdapter();
        adapter.setChannelTypes(typeList);
        mChannelView.setChannelAdapter(adapter);
        mChannelView.setOnChannelItemClickListener(listener);
        mBackLookView.setOnChannelItemClickListener(listener);
        mChannelView.attachBackLookView(mBackLookView);
        mBackLookView.attachChannelView(mChannelView);
        mChannelView.setOnDismissPopUpwindowListener(new ChannelView.onDismissPopUpwindowListener() {
            @Override
            public void dismissPopWindow() {
                if (mPopupWindow != null && mPopupWindow.isShowing()) {
                    mPopupWindow.dismiss();
                }
            }
        });
    }

    public void show(View parent) {
        if (mPopupWindow != null && !mPopupWindow.isShowing()) {
            mPopupWindow.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
            mChannelView.selectLastPos();
        }
    }

    public boolean isShowing() {
        boolean isShowing = false;
        if (mPopupWindow != null && mPopupWindow.isShowing()) {
            isShowing = true;
        }

        return isShowing;
    }

    private List<String> getTypes() {

        return new ArrayList<String>(Arrays.asList("常看", "央视", "高清", "体育", "综艺", "新闻"
                , "电影", "卡通", "少儿"
        ));
    }

    public void dismiss() {
        if (mPopupWindow != null && mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
        }
    }
}
