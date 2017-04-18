package com.rojao.tvlive.weiget.channel;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.widget.DefaultItemAnimator;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import com.owen.tvrecyclerview.widget.TvRecyclerView;
import com.rojao.tvlive.R;
import com.rojao.tvlive.network.WebService;
import com.rojao.tvlive.weiget.backlook.BackLookView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lsc on 2017/3/27 0027.
 *
 * @author lsc
 * @desc ${TODO}
 */
public class ChannelView extends LinearLayout implements TvRecyclerView.OnInBorderKeyEventListener {
    private static final int KEY_FIRST = 0;
    private static final int KEY_SECOND = 1;
    private Handler mHandler = new Handler();
    private static final String TAG = ChannelView.class.getSimpleName();

    private onDismissPopUpwindowListener mOnDismissPopUpwindowListener;
    public interface onDismissPopUpwindowListener {
        void dismissPopWindow();
    }

    public void setOnDismissPopUpwindowListener(onDismissPopUpwindowListener listener){
        mOnDismissPopUpwindowListener = listener;
    }
    @Override
    public boolean onInBorderKeyEvent(int direction, int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_DPAD_RIGHT) {
            ChannelDetailAdapter.DetailHolder holder = (ChannelDetailAdapter.DetailHolder) mChannelDetailView.findViewHolderForAdapterPosition(mChannelDetailView.getSelectedPosition());
            Drawable[] drawables = holder.tv_detail.getCompoundDrawables();
            if (drawables[2] != null){
                showBackLookView();
            }

        }
        return false;
    }

    public void hide() {
        if (getVisibility() == VISIBLE){
            startAnimation(mAnimSlideOut);
        }
    }

    public interface OnChannelItemClickListener {
        void onChannelItemClick(int firstPos, int secondPos);
    }

    public void setOnChannelItemClickListener(OnChannelItemClickListener listener) {
        mListener = listener;
    }

    private OnChannelItemClickListener mListener;
    private Map<Integer, Integer> mPositionMap = new HashMap<>();
    private ChannelAdapter mChannelAdapter;
    private TvRecyclerView mChannelTypesView, mChannelDetailView;
    private BackLookView mBackLookView;
    private Runnable mUpdateDetailRunnable;
    private Animation mAnimSlideIn;
    private Animation mAnimSlideOut;
    private TvRecyclerView.OnItemListener mChannelTypeListener = new TvRecyclerView.OnItemListener() {
        @Override
        public void onItemPreSelected(TvRecyclerView parent, View itemView, int position) {
            itemView.animate().scaleX(1.0f).scaleY(1.0f).setDuration(300).start();
        }

        @Override
        public void onItemSelected(TvRecyclerView parent, View itemView, final int position) {
            itemView.animate().scaleX(1.1f).scaleY(1.1f).setDuration(300).start();
            mPositionMap.put(KEY_FIRST, position);
            //防止快速滑动时detail切换太频繁
            if (mUpdateDetailRunnable != null) {
                mHandler.removeCallbacks(mUpdateDetailRunnable);
            }
            mUpdateDetailRunnable = new Runnable() {
                @Override
                public void run() {
                    int type = mChannelTypesView.getSelectedPosition();
                    mChannelAdapter.getChannelDetailAdapter().setDetails(WebService.getInstance().getChannelDetail(type));
                }
            };

            mHandler.postDelayed(mUpdateDetailRunnable, 200);

        }

        @Override
        public void onReviseFocusFollow(TvRecyclerView parent, View itemView, int position) {

        }

        @Override
        public void onItemClick(TvRecyclerView parent, View itemView, int position) {

        }
    };
    private TvRecyclerView.OnItemListener mChannelDetailListener = new TvRecyclerView.OnItemListener() {
        @Override
        public void onItemPreSelected(TvRecyclerView parent, View itemView, int position) {
            itemView.animate().scaleX(1.0f).scaleY(1.0f).setDuration(300).start();
        }

        @Override
        public void onItemSelected(TvRecyclerView parent, View itemView, int position) {
            itemView.animate().scaleX(1.1f).scaleY(1.1f).setDuration(300).start();
        }

        @Override
        public void onReviseFocusFollow(TvRecyclerView parent, View itemView, int position) {

        }

        @Override
        public void onItemClick(TvRecyclerView parent, View itemView, int position) {
            mPositionMap.put(KEY_SECOND, position);

            if (mListener != null) {
                mListener.onChannelItemClick(mPositionMap.get(KEY_FIRST), mPositionMap.get(KEY_SECOND));
            }
        }
    };

    public ChannelView(Context context) {
        this(context, null);
    }

    public ChannelView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ChannelView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        LinearLayout container = (LinearLayout) inflater.inflate(R.layout.channels, this, true);

        mChannelTypesView = (TvRecyclerView) container.findViewById(R.id.id_Channel_type);
        mChannelDetailView = (TvRecyclerView) container.findViewById(R.id.id_Channel_type_detail);

        mChannelTypesView.setOnItemListener(mChannelTypeListener);
        mChannelDetailView.setOnItemListener(mChannelDetailListener);

        mChannelTypesView.setItemAnimator(new DefaultItemAnimator());
        mChannelDetailView.setItemAnimator(new DefaultItemAnimator());
        if (mChannelAdapter != null) {
            mChannelTypesView.setAdapter(mChannelAdapter.getChannelTypeAdapter());
            mChannelDetailView.setAdapter(mChannelAdapter.getChannelDetailAdapter());
        }

        mAnimSlideIn = AnimationUtils.loadAnimation(getContext(), android.R.anim.slide_in_left);
        mAnimSlideOut = AnimationUtils.loadAnimation(getContext(), R.anim.slide_out_anim);
        mAnimSlideOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                setVisibility(GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        mChannelDetailView.setInterceptKeyEvent(true);
        mChannelDetailView.setOnInBorderKeyEventListener(this);
    }

    public void setChannelAdapter(ChannelAdapter adapter) {
        mChannelAdapter = adapter;
        mChannelDetailView.setAdapter(mChannelAdapter.getChannelDetailAdapter());
        mChannelTypesView.setAdapter(mChannelAdapter.getChannelTypeAdapter());
        mChannelAdapter.notifyDatasetChange();
        Log.e("setChannelAdapter", "setChannelAdapter: " + mChannelTypesView);
    }


    public void showBackLookView() {

        if (mBackLookView != null) {

            if (mBackLookView.getVisibility() == GONE) {
                mBackLookView.setVisibility(VISIBLE);
            }
            mBackLookView.startAnimation(mAnimSlideIn);

            setVisibility(GONE);
            Log.e(TAG, "showBackLookView: ");
            mBackLookView.requestFocus();

        }
    }

    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);

        if (visibility == VISIBLE) {
            startAnimation(mAnimSlideIn);
            final int detailOldSelectedPosition = mChannelDetailView.getOldSelectedPosition();

            postDelayed(new Runnable() {
                @Override
                public void run() {
                    mChannelDetailView.requestFocus();
                    mChannelDetailView.setSelection(detailOldSelectedPosition);

                }
            }, 250);
        }

    }


    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (KeyEvent.ACTION_DOWN == event.getAction() && event.getKeyCode() == KeyEvent.KEYCODE_BACK){
            if (mBackLookView!= null &&mBackLookView.getVisibility() == GONE && mOnDismissPopUpwindowListener != null){
                mOnDismissPopUpwindowListener.dismissPopWindow();

            }
        }
        return super.dispatchKeyEvent(event);
    }

    public void selectLastPos(){
        postDelayed(new Runnable() {
            @Override
            public void run() {
                mChannelDetailView.requestDefaultFocus();
            }
        }, 250);
    }
    public void attachBackLookView(BackLookView backLookView) {
        this.mBackLookView = backLookView;
    }

    /////////////////////////////////test

    private List<String> getDetails(String type) {

        return new ArrayList<String>(Arrays.asList(type + ": 详情1", type + ": 详情2", type + ": 详情3", type + ": 详情4",
                type + ": 详情5", type + ": 详情6",
                type + ": 详情7", type + ": 详情8",
                type + ": 详情9", type + ": 详情10",
                type + ": 详情11", type + ": 详情12",
                type + ": 详情13", type + ": 详情14",
                type + ": 详情15", type + ": 详情16"
        ));
    }
}
