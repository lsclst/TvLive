package com.rojao.tvlive.ad;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.rojao.tvlive.R;
import com.rojao.tvlive.VodActivity;

/**
 * Created by lsc on 2017/5/5 0005.
 *
 * @author lsc
 * @desc ${TODO}
 */
public class RecPopDialog extends Dialog {
    private static final int[] CAR_IDs = new int[]{
            R.mipmap.a1, R.mipmap.a2, R.mipmap.a3, R.mipmap.a4
    };
    private static final int[] MOVIE_IDs = new int[]{
            R.mipmap.e1, R.mipmap.e2, R.mipmap.e3, R.mipmap.e4
    };

    private ImageView iv1, iv2, iv3, iv4;
    private int curChannel;

    public RecPopDialog(Context context) {
        this(context, 0);
    }

    public RecPopDialog(Context context, int themeResId) {
        super(context, R.style.ad_dialog_style);
        init();
    }

    private void init() {
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.MATCH_PARENT;
        getWindow().setAttributes(params);
        getWindow().setBackgroundDrawable(new ColorDrawable());
        setContentView(R.layout.rec_pop_dialog);
        iv1 = (ImageView) findViewById(R.id.id_ad_1);
        iv2 = (ImageView) findViewById(R.id.id_ad_2);
        iv3 = (ImageView) findViewById(R.id.id_ad_3);
        iv4 = (ImageView) findViewById(R.id.id_ad_4);

    }

    public void showAD(int channel) {
        curChannel = channel;

        if (channel == 2) {
            iv1.setImageResource(CAR_IDs[0]);
            iv2.setImageResource(CAR_IDs[1]);
            iv3.setImageResource(CAR_IDs[2]);
            iv4.setImageResource(CAR_IDs[3]);
            show();
        } else if (channel == 3) {
            iv1.setImageResource(MOVIE_IDs[0]);
            iv2.setImageResource(MOVIE_IDs[1]);
            iv3.setImageResource(MOVIE_IDs[2]);
            iv4.setImageResource(MOVIE_IDs[3]);
            show();
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        switch (keyCode) {
            case KeyEvent.KEYCODE_1:
            case KeyEvent.KEYCODE_2:
            case KeyEvent.KEYCODE_3:
            case KeyEvent.KEYCODE_4:
                if (mOnDialogItemClickListner != null) {
                    mOnDialogItemClickListner.onItemClick(curChannel, curChannel == 2 ? VodActivity.TYPE_CAR : VodActivity.TYPE_MOVIE,
                            curChannel == 2 ? "" : "http://122.97.219.210/yppl/2016/04/16/JSBYPPL02000009086007601.m3u8");
                }
                dismiss();
                break;
            default:
                dismiss();
                return super.onKeyDown(keyCode, event);
        }
        dismiss();
        return super.onKeyDown(keyCode, event);
    }

    public interface onDialogItemClickListener {
        void onItemClick(int curChannel, String type, String linkPath);
    }

    private onDialogItemClickListener mOnDialogItemClickListner;

    public void setOnDialogItemClickListner(onDialogItemClickListener listner) {
        mOnDialogItemClickListner = listner;

    }
}
