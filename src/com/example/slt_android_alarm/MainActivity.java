package com.example.slt_android_alarm;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends Activity {

	private Button btn_setalarm;
	//闹钟管理器,用于设置闹钟类型的常量 ，通过设置常量，可以得到不同类型的闹钟
	AlarmManager malarmManager;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn_setalarm = (Button)findViewById(R.id.btn_setalarm);
		//通过系统服务，获取AlarmManager对象
		malarmManager = (AlarmManager)getSystemService(Service.ALARM_SERVICE);
		
		btn_setalarm.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//弹出时间设置框，设置闹钟时间
				Calendar currentTime = Calendar.getInstance();
				//TimePickerDialog参数
				//1-上下文；2-主题，默认为0；3-回调函数（设置好好做什么）；4-设置默认小时数；4-设置默认分钟数；5-设置是否为24小时制
				new TimePickerDialog(MainActivity.this, 0, new TimePickerDialog.OnTimeSetListener() {
					
					@Override
					public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
						
						Intent mIntent = new Intent(MainActivity.this,MadiaPlayerActivity.class);//这是即时意图，会立即执行
						PendingIntent pi = PendingIntent.getActivity(MainActivity.this, 0, mIntent, 0);//延迟的意图，延迟执行(被调用)
						//1-让用户设置闹钟的时间
						Calendar c = Calendar.getInstance();
						c.set(Calendar.HOUR, hourOfDay);
						c.set(Calendar.MINUTE, minute);
 						//2-设置闹钟；type:闹钟类型；triggerAtMillis：闹铃时间（毫秒）；operation：闹铃响起后执行什么操作
						//闹铃类型：1-RTC：手机在深度睡眠，闹铃不可用；2-RTC-WAKUP:手机睡眠时，闹铃可用（使用时间是绝对时间:是相对于RTC时间：1970-1-1）
						//3-ELAPSED_REALTIME:手机睡眠时不可用（使用时间是相对时间：是相对于系统启动时间）
						//4-ELAPSED_REALTIME_WAKEUP:手机睡眠时可用
						//5-POWER_OFF_WAKUP:手机关机是可用，闹钟一般用这个（有些ADK不支持）
						malarmManager.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pi);//3-当闹铃时间到了则跳转到MainActivity
						Toast.makeText(MainActivity.this, "闹铃已设置", Toast.LENGTH_LONG).show();
						
						
						
					}
				},currentTime.get(Calendar.HOUR_OF_DAY),currentTime.get(Calendar.MINUTE),false).show();
			}
		});
	}
}
