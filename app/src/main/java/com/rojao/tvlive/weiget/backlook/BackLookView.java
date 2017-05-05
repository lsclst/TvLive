package com.rojao.tvlive.weiget.backlook;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.owen.tvrecyclerview.widget.TvRecyclerView;
import com.rojao.tvlive.R;
import com.rojao.tvlive.entity.BackLookEPG;
import com.rojao.tvlive.network.WebService;
import com.rojao.tvlive.weiget.channel.ChannelView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * Created by lsc on 2017/4/5 0005.
 *
 * @author lsc
 * @desc ${TODO}
 */
public class BackLookView extends FrameLayout {
    private static final String TAG = BackLookView.class.getSimpleName();
    private TvRecyclerView mBackLookDateList, mBackLookDetailList;
    private boolean isGoBackToChannel;
    private ChannelView mChannelView;
    private Runnable mgetEpgRunnable;
    private List<BackLookEPG> mBackLookEPGs;
    private int dayPos;
    private boolean isFirstTimeIn;
    private BackLookDateAdapter mBackLookDateAdapter;
    private DetailAdapter mDetailAdapter;
    private ChannelView.OnChannelItemClickListener mOnChannelItemClickListener;

    public void setOnChannelItemClickListener(ChannelView.OnChannelItemClickListener listener) {
        mOnChannelItemClickListener = listener;

    }

    private TvRecyclerView.OnItemListener mBackLookDateListener = new TvRecyclerView.OnItemListener() {
        @Override
        public void onItemPreSelected(TvRecyclerView parent, View itemView, int position) {

        }

        @Override
        public void onItemSelected(final TvRecyclerView parent, View itemView, int position) {

            dayPos = position;
            if (mgetEpgRunnable != null) {
                getHandler().removeCallbacks(mgetEpgRunnable);
            } else {
                mgetEpgRunnable = new Runnable() {
                    @Override
                    public void run() {
                        WebService.getInstance().getBackLookEPG(dayPos, "2017-" + mBackLookDateAdapter.getDateBeanList().get(dayPos).getDay(), new WebService.BackLookEPGCallBack() {
                            @Override
                            public void success(int pos, final List<BackLookEPG> backLookEPGs) {
                                getHandler().post(new Runnable() {
                                    @Override
                                    public void run() {
                                        mBackLookEPGs = backLookEPGs;
                                        mDetailAdapter.setDatas(backLookEPGs);
                                    }
                                });
                            }

                            @Override
                            public void faile(IOException e) {

                            }
                        });
                    }
                };
            }
            if (!isFirstTimeIn) {

                postDelayed(mgetEpgRunnable, 200);
            }

        }

        @Override
        public void onReviseFocusFollow(TvRecyclerView parent, View itemView, int position) {

        }

        @Override
        public void onItemClick(TvRecyclerView parent, View itemView, int position) {

        }
    };

    private TvRecyclerView.OnItemListener mBackLookDetailListListener = new TvRecyclerView.OnItemListener() {
        @Override
        public void onItemPreSelected(TvRecyclerView parent, View itemView, int position) {

        }

        @Override
        public void onItemSelected(TvRecyclerView parent, View itemView, int position) {

        }

        @Override
        public void onReviseFocusFollow(TvRecyclerView parent, View itemView, int position) {

        }

        @Override
        public void onItemClick(TvRecyclerView parent, View itemView, int position) {
            setVisibility(GONE);
            final int pos = position;
            if (mOnChannelItemClickListener != null) {

                BackLookEPG backLookEPG = mBackLookEPGs.get(position);
                WebService.getInstance().getEPGDetail(backLookEPG.getChannelId(), backLookEPG.getAssertId(), new WebService.getEpgDetaiLinkCallBack() {
                    @Override
                    public void success(final String link) {
                        Log.e(TAG, "success: " + link);
                        post(new Runnable() {
                            @Override
                            public void run() {
                                mOnChannelItemClickListener.onChannelItemClick(pos, "backlookNO", link);
                            }
                        });

                    }

                    @Override
                    public void faile() {
                        post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getContext(), "没有详细信息", Toast.LENGTH_LONG).show();
                                mOnChannelItemClickListener.faile();
                            }
                        });
                    }
                });

            }

        }
    };

    public BackLookView(Context context) {
        this(context, null);
    }

    public BackLookView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BackLookView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @Override
    public boolean requestFocus(int direction, Rect previouslyFocusedRect) {
        //
        this.postDelayed(new Runnable() {
            @Override
            public void run() {
                isFirstTimeIn = false;
                mBackLookDateList.setSelection(3);
            }
        }, 250);
        isGoBackToChannel = false;
        return super.requestFocus(direction, previouslyFocusedRect);
    }

    private void initView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.backlook, this, true);
        mBackLookDateList = (TvRecyclerView) view.findViewById(R.id.id_backLook_Date);
        mBackLookDetailList = (TvRecyclerView) view.findViewById(R.id.id_backLook_detail);

        mBackLookDateList.setOnItemListener(mBackLookDateListener);
        mBackLookDetailList.setOnItemListener(mBackLookDetailListListener);

        mBackLookDateList.setAdapter(mBackLookDateAdapter = new BackLookDateAdapter());
        mBackLookDetailList.setAdapter(mDetailAdapter = new DetailAdapter());

    }

    private static class BackLookDateAdapter extends RecyclerView.Adapter<BackLookDateAdapter.ViewHolder> {

        private static final String TAG = BackLookDateAdapter.class.getSimpleName();
        private int mParentWidth;
        private List<DateBean> mDateBeanList = new ArrayList<>();
        private static final String[] sWeeks = new String[]{"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};

        public List<DateBean> getDateBeanList() {
            return mDateBeanList;
        }

        public BackLookDateAdapter() {
            Calendar now = Calendar.getInstance();
            now.set(Calendar.DAY_OF_MONTH, now.get(Calendar.DAY_OF_MONTH) - 3);
            for (int i = 0; i < 7; i++) {
                int month = now.get(Calendar.MONTH) + 1;
                int day = now.get(Calendar.DAY_OF_MONTH);
                int week = now.get(Calendar.DAY_OF_WEEK) - 1;
                DateBean bean = new DateBean();
                bean.setDay(String.format(Locale.getDefault(), "%02d-%02d", month, day));
                bean.setWeek(i == 3 ? "今天" : sWeeks[week]);
                mDateBeanList.add(bean);
                now.set(Calendar.DAY_OF_MONTH, now.get(Calendar.DAY_OF_MONTH) + 1);

            }
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.backlook_date_item, parent, false);
            mParentWidth = parent.getWidth() - parent.getPaddingLeft() - parent.getPaddingRight();

            if (mParentWidth != 0) {
                view.getLayoutParams().width = mParentWidth / 7;
            }
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            DateBean bean = mDateBeanList.get(position);
            holder.tv_week.setText(bean.getWeek());
            holder.tv_day.setText(bean.getDay());
        }

        @Override
        public int getItemCount() {
            return 7;
        }

        static class ViewHolder extends RecyclerView.ViewHolder {

            TextView tv_week;
            TextView tv_day;

            public ViewHolder(View itemView) {
                super(itemView);
                tv_week = (TextView) itemView.findViewById(R.id.id_week);
                tv_day = (TextView) itemView.findViewById(R.id.id_day);
            }
        }

        static class DateBean {
            private String week;
            private String day;

            public String getWeek() {
                return week;
            }

            public void setWeek(String week) {
                this.week = week;
            }

            public String getDay() {
                return day;
            }

            public void setDay(String day) {
                this.day = day;
            }
        }
    }

    private static class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.DetailHolder> {
        List<BackLookEPG> mDatas = new ArrayList<>();

        public void setDatas(List<BackLookEPG> datas) {
            mDatas = datas;
            notifyDataSetChanged();
        }

        @Override
        public DetailHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.back_look_detail_item, parent, false);

            return new DetailHolder(view);
        }

        @Override
        public void onBindViewHolder(DetailHolder holder, int position) {
            BackLookEPG backLookEPG = mDatas.get(position);
            holder.tv_time.setText(String.format(Locale.getDefault(), "%2s - %2s", backLookEPG.getStartTime(), backLookEPG.getEndTime()));
            holder.tv_title.setText(backLookEPG.getProgramName());
        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }

        class DetailHolder extends RecyclerView.ViewHolder {
            TextView tv_title;
            TextView tv_time;

            public DetailHolder(View itemView) {
                super(itemView);
                tv_title = (TextView) itemView.findViewById(R.id.id_backLook_title);
                tv_time = (TextView) itemView.findViewById(R.id.id_time);
            }
        }
    }

    public void attachChannelView(ChannelView channelView) {
        mChannelView = channelView;
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {

        if (event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            if (!isGoBackToChannel) {
                showChannelView();
                Log.e(TAG, "showChannelView: ");

                return true;
            }
            isGoBackToChannel = true;

        }

        return super.dispatchKeyEvent(event);
    }

    public void showChannelView() {
        if (mChannelView != null) {
            if (mChannelView.getVisibility() == GONE) {
                this.setVisibility(GONE);
                mChannelView.setVisibility(VISIBLE);
            }
        }
    }

    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);
        if (visibility == VISIBLE) {

            isFirstTimeIn = true;
        }
    }
}
