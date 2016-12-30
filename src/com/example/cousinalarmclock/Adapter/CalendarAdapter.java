package com.example.cousinalarmclock.Adapter;

import java.util.Calendar;

import com.example.calendaryc.R;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CalendarAdapter extends BaseAdapter {

	private Context context;
	private int[] num;
	private Calendar nowCalendar;
	private int nowDay;
	private int nowDayIndex;

	public CalendarAdapter(Context context, int[] num) {
		this.context = context;
		this.num = num;
		this.nowCalendar = Calendar.getInstance();
		this.nowDay = nowCalendar.get(Calendar.DAY_OF_MONTH);
		setNowDayIndex();
	}

	public boolean getIsShow(){
		if(num.length <8){
			return false;
		}else{
			return true;
		}
	}
	 
	public void setDate(int[] num) {
		this.num = num;
		setNowDayIndex();
	}

	private void setNowDayIndex() {
		if (num.length < 8) {
			this.nowDayIndex = nowCalendar.get(Calendar.WEEK_OF_MONTH) - 1;
		} else {
			this.nowDayIndex = this.nowDay
					+ nowCalendar.get(Calendar.WEEK_OF_MONTH) - 2;
		}

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return num.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return num[position];
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(
					R.layout.ui_calendar_item, null);
			holder.mText = (TextView) convertView
					.findViewById(R.id.ui_calendar_item_tx);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.mText.setText("" + num[position]);
		if (num.length > 7) {
			if (position == nowDayIndex) {
				holder.mText.setTextColor(context.getResources().getColor(
						R.color.common_text_red_color));
				holder.mText.setBackgroundResource(R.drawable.circle2);
			} else if (position < num[position]
					|| (num[position] < 8 && position > 28)) {
				holder.mText.setTextColor(context.getResources().getColor(
						R.color.calendar_text_color_gay));
				holder.mText.setBackgroundResource(Color.TRANSPARENT);
			} else {
				holder.mText.setTextColor(context.getResources().getColor(
						R.color.calendar_text_color_white));
				holder.mText.setBackgroundResource(Color.TRANSPARENT);
			}
		} else {
			if (position == nowDayIndex) {
				holder.mText.setTextColor(context.getResources().getColor(
						R.color.common_text_red_color));
				holder.mText.setBackgroundResource(R.drawable.circle2);
			} else if (position < nowDayIndex && num[position] > nowDay) {
				holder.mText.setTextColor(context.getResources().getColor(
						R.color.calendar_text_color_gay));
				holder.mText.setBackgroundResource(Color.TRANSPARENT);
			} else if (position > nowDayIndex && num[position] < nowDay) {
				holder.mText.setTextColor(context.getResources().getColor(
						R.color.calendar_text_color_gay));
				holder.mText.setBackgroundResource(Color.TRANSPARENT);
			} else {
				holder.mText.setTextColor(context.getResources().getColor(
						R.color.calendar_text_color_white));
				holder.mText.setBackgroundResource(Color.TRANSPARENT);
			}
		}

		return convertView;
	}

	public static class ViewHolder {

		TextView mText;
	}
}
