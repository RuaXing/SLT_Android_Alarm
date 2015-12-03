package com.example.slt_android_alarm;

import android.R.menu;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MadiaPlayerActivity extends Activity {
	
	private MediaPlayer mediaplayer;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_madia_player);
		mediaplayer = MediaPlayer.create(this, R.raw.xiayu);
		mediaplayer.setLooping(true);
		
		mediaplayer.start();
		
		new AlertDialog.Builder(MadiaPlayerActivity.this).setTitle("唐长老")
		.setIcon(R.drawable.ic_launcher)
		.setMessage("1+1=3")
		
		.setPositiveButton("去判断", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				//关闭音乐
				//mediaplayer.stop();
				//MadiaPlayerActivity.this.finish();//结束本界面
				new AlertDialog.Builder(MadiaPlayerActivity.this).setTitle("判断")
				.setPositiveButton("错", new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						mediaplayer.stop();
						MadiaPlayerActivity.this.finish();//结束本界面
					}
				}).setNegativeButton("对", new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						mediaplayer.stop();
						MadiaPlayerActivity.this.finish();
						
					}
				}).show();
				
				
				
				
			}
		})
		.setNegativeButton("等哈儿",new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				mediaplayer.stop();
				MadiaPlayerActivity.this.finish();
				
			}
		} )
		.show();
	}
}
