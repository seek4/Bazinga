package com.yangtong.bazinga;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener{
	
	Bazinga bazinga;
	private Button switchButton;
	private Button testButton;
	private Button modeButton;
	
	private Intent testIntent;
	
	String[] modeTitles;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.activity_main);
		
		bazinga = Bazinga.getInstance(MainActivity.this);
		initData();
		initView();
	}
	
	private void initData(){
		modeTitles = getResources().getStringArray(R.array.str_mode);
	}

	private void initView(){
		switchButton = (Button)this.findViewById(R.id.button_switch);
		testButton = (Button)this.findViewById(R.id.button_test);
		modeButton = (Button)this.findViewById(R.id.button_mode);
		
		initSwitchView();
		initModeView();
		
		switchButton.setOnClickListener(this);
		testButton.setOnClickListener(this);
		modeButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.button_switch:
			if(bazinga.getSwitch()){
				switchButton.setText("关");
				bazinga.setSwitch(false);
			}else {
				switchButton.setText("开");
				bazinga.setSwitch(true);
			}
			break;
		case R.id.button_test:
			bazinga.make();
//			if(testIntent==null){
//				testIntent = new Intent();
//				testIntent.setAction("me.yangtong.bazinga");
//			}
//			sendBroadcast(testIntent);
			break;
		case R.id.button_mode:
			if(bazinga.getMode()>=0&&bazinga.getMode()<2){
				bazinga.setMode(bazinga.getMode()+1);
			}else {
				bazinga.setMode(0);
			}
			initModeView();
			break;
		default:
			break;
		}
	}

	private void initSwitchView(){
		switchButton.setText(bazinga.getSwitch()?"开":"关");
	}
	
	private void initModeView(){		
		if(bazinga.getMode()<modeTitles.length){
			modeButton.setText(modeTitles[bazinga.getMode()]);
		}	
	}

	
}
