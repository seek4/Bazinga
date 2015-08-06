package com.yangtong.bazinga;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.R.integer;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * 
 * Bazinga 
 * singleton
 * 
 * @author yangtong
 *
 */
public class Bazinga {
	private Context mContext;
	private static int mCurMode = 0;
	private static boolean mCurSwitch = true;
	private static Bazinga mBazinga;
	
	
	/**
	 * SharedPref
	 */
	private SharedPreferences bazingaPref;
	public static final String BAZING_PREF = "bazinga_pref";
	public static final String KEY_MODE = "mode";
	public static final String KEY_SWITCH = "switch";
	
	
	/**
	 * use int but enum benefit store
	 */
	public static final int MODE_BAZINGA = 0;
	public static final int MODE_SCARY   = 1;
	public static final int MODE_HONEY   = 2;

	
	/**
	 * show bazinga
	 */
	private LayoutInflater mInflater;
	private View bazingaView = null;
	private ImageView imageBazinga = null;
	private TypedArray array;
	private int[] bkgIds;

	private static final int MSG_SCARY_NEXT = 0x201;
	
		
	public static synchronized Bazinga getInstance(Context context){
		if(mBazinga==null){
			mBazinga = new Bazinga(context);
		}
		return mBazinga;
	}
	
	private Bazinga(Context context){
		this.mContext = context;
		if(bazingaPref==null)
			bazingaPref = mContext.getSharedPreferences(BAZING_PREF, Context.MODE_PRIVATE);	
		mCurMode = bazingaPref.getInt(KEY_MODE, 0);
		mCurSwitch = bazingaPref.getBoolean(KEY_SWITCH, true);
		mInflater = LayoutInflater.from(mContext);
	}
	
	Handler mHandler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch (msg.what) {
			case MSG_SCARY_NEXT:
				scaryImage.setBackgroundResource(R.drawable.bkg_scary_2);
				scaryToast.setView(scaryView);
				break;

			default:
				break;
			}
			
			super.handleMessage(msg);
		}
		
	};
	
	public void make(){
		if(!mCurSwitch){
			return;
		}
		switch (mCurMode) {
		case 0:
			makeBazingaView();
			makeBazingaSound();
			break;
		case 1:
			makeScaryView();
			makeScarySound();
			break;
		case 2:
			makeHoneyView();
			makeHoneySound();
			break;
		default:
			makeBazingaView();
			makeBazingaSound();
			break;
		}

	}
	
	public void setSwitch(boolean isOn){
		mCurSwitch = isOn;
		SharedPreferences.Editor editor = bazingaPref.edit();
		editor.putBoolean(KEY_SWITCH, isOn);
		editor.commit();
	}
	
	public boolean getSwitch(){
		return mCurSwitch;
	}
	
	public void setMode(int mode){
		mCurMode = mode;
		SharedPreferences.Editor editor = bazingaPref.edit();
		editor.putInt(KEY_MODE, mode);
		editor.commit();
	}
	
	public int getMode(){
		return mCurMode;
	}
	
	private Toast scaryToast;
	private View scaryView;
	private ImageView scaryImage;
	private void makeScaryView(){
		if(scaryToast==null){
			scaryToast = new Toast(mContext);
			scaryToast.setGravity(Gravity.CENTER, 0, 0);
		}
		if(scaryView==null){
			scaryView = mInflater.inflate(R.layout.scary, null);
			scaryImage = (ImageView)scaryView.findViewById(R.id.image_scary);
		}
		scaryToast.setView(scaryView);
		scaryToast.setDuration(Toast.LENGTH_LONG);
		scaryToast.show();
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				mHandler.sendEmptyMessage(MSG_SCARY_NEXT);
			}
		}, 2000);
	}
	
	private void makeScarySound(){
		
	}
	
	
	private Toast honeyToast;
	private View honeyView;
	private ImageView honeyImage;
	private void makeHoneyView(){
		if(honeyToast==null){
			honeyToast = new Toast(mContext);
			honeyToast.setGravity(Gravity.CENTER, 0, 0);
		}
		if(honeyView==null){
			honeyView = mInflater.inflate(R.layout.honey, null);
			honeyImage = (ImageView)honeyView.findViewById(R.id.image_honey);
		}
		honeyToast.setView(honeyView);
		honeyToast.setDuration(Toast.LENGTH_SHORT);
		honeyToast.show();
	}
	
	private void makeHoneySound(){
		
	}
	
	
	private Toast bazingaToast;
	private void makeBazingaView(){
		if(bazingaToast==null){
			bazingaToast = new Toast(mContext);
		}
		if(bazingaView==null){
//			array = mContext.getResources().obtainTypedArray(R.array.bazinga_bkgs);
//			bkgIds = new int[array.length()];
//			for(int i=0;i<array.length();i++){
//				bkgIds[i] = array.getResourceId(i, R.drawable.bkg_bazinga_1);
//			}
//			array.recycle();
			bazingaView = mInflater.inflate(R.layout.bazinga, null);
			imageBazinga = (ImageView)bazingaView.findViewById(R.id.view_bazinga);
		}
//		if(bkgIds!=null&&bkgIds.length>1){	// set random bkg
//			Random random = new Random();
//			imageBazinga.setBackgroundResource(bkgIds[random.nextInt(bkgIds.length)]);
//		}
		bazingaToast.setView(bazingaView);
		bazingaToast.setGravity(Gravity.CENTER, 0, 0);
		bazingaToast.setDuration(Toast.LENGTH_LONG);
		bazingaToast.show();
	}
		
	private void makeBazingaSound(){	
		MediaPlayer player = MediaPlayer.create(mContext,R.raw.bazinga);
		player.start();
	}
	
	
}
