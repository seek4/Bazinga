package com.yangtong.bazinga;

import java.util.Random;
import java.util.Timer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.text.format.Time;
import android.util.Log;
import android.widget.Space;

public class WakeReceiver extends BroadcastReceiver {
	private Context mContext;
	
	private MyHandler myHandler = new MyHandler();
	
	//	为了方便直接用sharedPreferences来保存每天bazinga次数，
	//	以后可以改为数据库保存
	SharedPreferences  sp = null;
	private static final String SP_BAZINGA = "bazinga_count";
	private static final int MSG_MAKE_BAZINGA = 0x01;
	
	@Override
	public void onReceive(Context context, Intent intent) {		
		mContext = context;
		Time time = new Time("GMT+8");
		time.setToNow();
		Log.i("yangtong"," >>"+time.hour);
//		if(time.hour>=0){
			String strToday = ""+time.month+"-"+time.monthDay;
			// Avoid make too much bazinga
			sp = context.getSharedPreferences(SP_BAZINGA, Context.MODE_PRIVATE);
//			if(sp.getInt(strToday, 0)>3){
//				
//			}else {
				SharedPreferences.Editor spEditor = sp.edit();
				int count = sp.getInt(strToday, 0);
				spEditor.putInt(strToday, count+1);
				spEditor.commit();			
				Random random = new Random();
				// Make bazinga after a random time.
				myHandler.sendEmptyMessage(MSG_MAKE_BAZINGA);
				//myHandler.sendEmptyMessageDelayed(MSG_MAKE_BAZINGA, (random.nextInt(5)+5)*1000);
//			}
//		}

	}

	class MyHandler extends Handler{	
		public MyHandler() {
			
		}

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch (msg.what) {
			case MSG_MAKE_BAZINGA:
				Bazinga bazinga = Bazinga.getInstance(mContext);
				bazinga.make();
				break;

			default:
				break;
			}
			super.handleMessage(msg);
		}
		
	}
	
}
