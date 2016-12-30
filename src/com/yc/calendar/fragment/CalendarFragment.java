package com.yc.calendar.fragment;

import java.util.Calendar;
import com.example.calendaryc.R;
import com.example.cousinalarmclock.Adapter.CalendarAdapter;
import com.yc.calendar.util.CalendarUtil;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.view.animation.Animation.AnimationListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class CalendarFragment extends Fragment {
	/**
	 * 绑定的视图XML
	 */
	private View view;

	/* 日历部分 */
	private GridView gv;
	private Button b;
	private TextView tv;
	CalendarAdapter ga;
	int[] timeLineData = new int[7];
	int[] timeData = new int[42];
	private float gridViewHigh = 0;

	/* 日历部分 */

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		view = inflater.inflate(R.layout.fragment_alarm, null);
		initCalendar(view);
		return view;
	}

	/**
	 * 初始化日历视图
	 * 
	 * @param view
	 */
	private void initCalendar(View view) {
		gv = (GridView) view.findViewById(R.id.ui_calendar_gv);

		CalendarUtil.getLineTimeData(timeLineData);
		CalendarUtil.getTimeData(timeData);

		ga = new CalendarAdapter(CalendarFragment.this.getActivity(), timeData);
		gv.setAdapter(ga);
		gv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Log.i("11111", "aaa" + position);

			}
		});
		// 对单独一个组件进行触摸监听。当返回为true时onTouchEvent就不会触发，false则反之
		gv.setOnTouchListener(gvTouch);

		b = (Button) view.findViewById(R.id.ui_calendar_button);
		b.setOnClickListener(gvOnClick);
		
		tv = (TextView) view.findViewById(R.id.ui_calendar_year);
		tv.setText(CalendarUtil.getNowYearAndMonth());
	}

	float x1 = 0;
	float x2 = 0;
	float y1 = 0;
	float y2 = 0;
	OnTouchListener gvTouch = new OnTouchListener() {

		@Override
		public boolean onTouch(View v, MotionEvent event) {

			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				// 当手指按下的时候
				x1 = event.getX();
				y1 = event.getY();
			}
			if (event.getAction() == MotionEvent.ACTION_UP) {
				// 当手指离开的时候
				x2 = event.getX();
				y2 = event.getY();
				if (y1 - y2 > 50) {
					Toast.makeText(CalendarFragment.this.getActivity(), "向上滑",
							Toast.LENGTH_SHORT).show();
					return true;
				} else if (y2 - y1 > 50) {
					Toast.makeText(CalendarFragment.this.getActivity(), "向下滑",
							Toast.LENGTH_SHORT).show();
					return true;
				} else if (x1 - x2 > 50) {
					Toast.makeText(CalendarFragment.this.getActivity(), "向左滑",
							Toast.LENGTH_SHORT).show();
					return true;
				} else if (x2 - x1 > 50) {
					Toast.makeText(CalendarFragment.this.getActivity(), "向右滑",
							Toast.LENGTH_SHORT).show();
					return true;
				}
			}

			return false;
		}
	};

	/**
	 * 收起/展开日程
	 */
	private OnClickListener gvOnClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			if (gridViewHigh == 0) {
				gridViewHigh = gv.getHeight();
			}

			TranslateAnimation tAnimHide = new TranslateAnimation(0, 0, 0,
					-gridViewHigh);// 横向位移
			tAnimHide.setInterpolator(new AccelerateDecelerateInterpolator());
			tAnimHide.setDuration(1000);
			// 设置动画监听。在动画结束的时刻，隐藏GridView控件
			tAnimHide.setAnimationListener(new AnimationListener() {
				public void onAnimationStart(Animation animation) {
					b.setClickable(false);
				}

				public void onAnimationRepeat(Animation animation) {
				}

				public void onAnimationEnd(Animation animation) {
					if (ga.getIsShow()) {
						ga.setDate(timeLineData);
					} else {
						ga.setDate(timeData);
					}
					ga.notifyDataSetChanged();

					gv.setVisibility(View.GONE);
					b.setClickable(true);
					TranslateAnimation tAnimShow = new TranslateAnimation(0, 0,
							-gridViewHigh, 0);// 横向位移
					tAnimShow
							.setInterpolator(new AccelerateDecelerateInterpolator());
					tAnimShow.setDuration(1000);
					gv.setVisibility(View.INVISIBLE);
					// 设置动画监听。在动画结束的时刻，显示GridView控件
					tAnimShow.setAnimationListener(new AnimationListener() {
						public void onAnimationStart(Animation animation) {
							b.setClickable(false);
							gv.setVisibility(View.VISIBLE);
						}

						public void onAnimationRepeat(Animation animation) {
						}

						public void onAnimationEnd(Animation animation) {
							b.setClickable(true);
						}
					});
					gv.startAnimation(tAnimShow);
				}
			});
			gv.startAnimation(tAnimHide);

		}
	};

}
