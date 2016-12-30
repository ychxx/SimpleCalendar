package com.example.cousinalarmclock.Activity;

import com.example.calendaryc.R;
import com.example.calendaryc.R.id;
import com.example.calendaryc.R.layout;
import com.example.calendaryc.R.menu;
import com.yc.calendar.fragment.CalendarFragment;

import android.app.Activity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainActivity extends BaseFragmentActivity {

	private Button mTab1;
	private Button mTab2;
	private Button mTab3;
	private Button mTab4;
	//Fragment的管理器
	private FragmentManager mFragmentManager;
	private FrameLayout mFrameLayout;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		
	}
	private void initView(){
		mFrameLayout = (FrameLayout) findViewById(R.id.main_frame);
		
		mTab1 = (Button) findViewById(R.id.main_tab1);
		mTab2 = (Button) findViewById(R.id.main_tab2);
		mTab3 = (Button) findViewById(R.id.main_tab3);
		mTab4 = (Button) findViewById(R.id.main_tab4);
		
		mTab1.setOnClickListener(mTabListener);
		mTab2.setOnClickListener(mTabListener);
		mTab3.setOnClickListener(mTabListener);
		mTab4.setOnClickListener(mTabListener);
		
		mFragmentManager = getSupportFragmentManager();
		FragmentTransaction transaction = mFragmentManager.beginTransaction();
		CalendarFragment mAlarmFragment = new CalendarFragment();
		transaction.add(R.id.main_frame, mAlarmFragment);
		transaction.commit();
	}
	
	/**
	 * 底部切换按钮的点击事件
	 */
	OnClickListener mTabListener = new OnClickListener() {

		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.main_tab1:
				break;
			case R.id.main_tab2:
				break;
			case R.id.main_tab3:
				break;
			case R.id.main_tab4:
				break;
			}

		}
	};
}
