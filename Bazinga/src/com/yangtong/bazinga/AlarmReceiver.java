package com.yangtong.bazinga;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Bazinga bazinga = new Bazinga(context);
		bazinga.makeBazinga();
	}

}
