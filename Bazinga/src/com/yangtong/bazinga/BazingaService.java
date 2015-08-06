package com.yangtong.bazinga;

import java.util.Timer;
import java.util.TimerTask;

import android.R.integer;
import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

public class BazingaService extends Service {
	
	Timer timer = null;
	Bazinga bazinga;
	public static final int MSG_SHOW_BAZINGA = 0x001;
	private WakeReceiver receiver;

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		Log.i("yangtong","BazingaService onStartCommand");
		IntentFilter filter = new IntentFilter();
		filter.addAction("android.intent.action.SCREEN_ON");
		filter.addAction("me.yangtong.bazinga");
		registerReceiver(receiver, filter);
//		bazinga = new Bazinga(BazingaService.this);
//		
//		
//		timer = new Timer();
//		timer.schedule(new TimerTask() {
//			
//			@Override
//			public void run() {
//				Intent intent = new Intent();
//				intent.setAction("me.yangtong.bazinga");
//				sendBroadcast(intent);
//			}
//		}, 0,8000);
		return super.onStartCommand(intent, flags, startId);
	}
	
	@SuppressLint("HandlerLeak")
	Handler mHandler = new Handler(){
		
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch (msg.what) {
			case MSG_SHOW_BAZINGA:
				bazinga.make();
				break;

			default:
				break;
			}
			super.handleMessage(msg);
		}
		
	};
	
	
}
