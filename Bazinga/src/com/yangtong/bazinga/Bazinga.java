package com.yangtong.bazinga;

import java.util.Random;

import android.R.integer;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class Bazinga {
	private Context mContext;
	private LayoutInflater mInflater;
	private View bazingaView = null;
	private ImageView imageBazinga = null;
	private TypedArray array;
	private int[] bkgIds;
	
	public Bazinga(Context context){
		this.mContext = context;
		mInflater = LayoutInflater.from(mContext);
		//mContext.getResources().get
		array = mContext.getResources().obtainTypedArray(R.array.bazinga_bkgs);
		bkgIds = new int[array.length()];
		for(int i=0;i<array.length();i++){
			bkgIds[i] = array.getResourceId(i, R.drawable.bkg_bazinga_1);
			Log.i("yangtong","id>>"+bkgIds[i]);
		}
		array.recycle();
	}
	
	public void makeBazinga(){
		makeBazingaView();
		makeBazingaVoice();
	}
	
	private void makeBazingaView(){
		Toast toast = new Toast(mContext);
		if(bazingaView==null){
			bazingaView = mInflater.inflate(R.layout.bazinga, null);
			imageBazinga = (ImageView)bazingaView.findViewById(R.id.view_bazinga);
		}
		if(bkgIds!=null&&bkgIds.length>1){	// set random bkg
			Random random = new Random();
			imageBazinga.setBackgroundResource(bkgIds[random.nextInt(bkgIds.length)]);
		}
		toast.setView(bazingaView);
		toast.setDuration(Toast.LENGTH_LONG);
		toast.show();
	}
	
	private void makeBazingaVoice(){
		
	}
}
