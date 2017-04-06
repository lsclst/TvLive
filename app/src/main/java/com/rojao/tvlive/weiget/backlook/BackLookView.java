package com.rojao.tvlive.weiget.backlook;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.owen.tvrecyclerview.widget.TvRecyclerView;
import com.rojao.tvlive.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by lsc on 2017/4/5 0005.
 *
 * @author lsc
 * @desc ${TODO}
 */
public class BackLookView extends FrameLayout {
    private TvRecyclerView mBackLookDateList, mBackLookDetailList;

    private TvRecyclerView.OnItemListener mBackLookDateListener = new TvRecyclerView.OnItemListener() {
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

    private void initView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.backlook, this, true);
        mBackLookDateList = (TvRecyclerView) view.findViewById(R.id.id_backLook_Date);
        mBackLookDetailList = (TvRecyclerView) view.findViewById(R.id.id_backLook_detail);

        mBackLookDateList.setOnItemListener(mBackLookDateListener);
        mBackLookDetailList.setOnItemListener(mBackLookDetailListListener);

        mBackLookDateList.setAdapter(new BackLookDateAdapter());

    }



    private static class BackLookDateAdapter extends RecyclerView.Adapter<BackLookDateAdapter.ViewHolder> {

        private static final String TAG = BackLookDateAdapter.class.getSimpleName();
        private int mParentWidth;
        private List<DateBean> mDateBeanList = new ArrayList<>();
        private static final String[] sWeeks = new String[]{"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};

        public BackLookDateAdapter() {
            Calendar now = Calendar.getInstance();
            for (int i = 0; i < 7; i++) {
                int month = now.get(Calendar.MONTH) + 1;
                int day = now.get(Calendar.DAY_OF_MONTH);
                int week = now.get(Calendar.DAY_OF_WEEK)-1;
                DateBean bean = new DateBean();
                bean.setDay(String.format("%2d -%2d", month, day));
                bean.setWeek(i == 0 ? "今天" : sWeeks[week]);
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
}
