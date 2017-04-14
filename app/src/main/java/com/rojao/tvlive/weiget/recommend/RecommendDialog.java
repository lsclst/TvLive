package com.rojao.tvlive.weiget.recommend;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.rojao.tvlive.R;
import com.rojao.tvlive.util.DisPlayUtil;

/**
 * Created by lsc on 2017/4/14 0014.
 *
 * @author lsc
 * @desc ${TODO}
 */
public class RecommendDialog {
    private PopupWindow mPopupWindow;
    private RecommendView mRecommendView;

    public RecommendDialog(Context context) {
        mRecommendView = new RecommendView(context);

        mPopupWindow = new PopupWindow(context);
        mPopupWindow.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        mPopupWindow.setHeight(DisPlayUtil.dpToPx(context, 250));
        mPopupWindow.setContentView(mRecommendView);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.setOutsideTouchable(false);
        mPopupWindow.setAnimationStyle(R.style.recommend_dialog_anim_style);


    }

    public void show(View parent) {
        if (mPopupWindow != null && !mPopupWindow.isShowing()) {
            mPopupWindow.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
            mRecommendView.selectCenter();
        }
    }

}
