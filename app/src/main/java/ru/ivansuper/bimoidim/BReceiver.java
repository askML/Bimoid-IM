package ru.ivansuper.bimoidim;

import ru.ivansuper.bservice.BimoidService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Message;
import android.util.Log;

public class BReceiver extends BroadcastReceiver {
	private BimoidService service;
	public BReceiver(BimoidService service){
		this.service = service;
	}
	@Override
	public void onReceive(Context arg0, Intent arg1) {
    	if(arg1.getAction().contains("RINGER_MODE_CHANGED")){
    		AudioManager am = (AudioManager)service.getSystemService(Context.AUDIO_SERVICE);
    		switch(am.getRingerMode()){
    		case 0x1:
    			Media.ring_mode = 1;
    			break;
    		case 0x2:
    			Media.ring_mode = 0;
    			break;
    		}
    	}else if(arg1.getAction().contains("CONNECTIVITY_CHANGE")){
			ConnectivityManager cm = (ConnectivityManager)service.getSystemService(Context.CONNECTIVITY_SERVICE);
    		if(cm == null) return;
    		int flags = 0;
    		NetworkInfo n = cm.getActiveNetworkInfo();
    		if(n == null) return;
    		if(n.isConnected()){
    			flags = 0x1;
    			service.svcHdl.sendMessage(Message.obtain(service.svcHdl, 0x1, flags, 0x0));
    		}else{
    			if(n.getType() == 0x0) flags = 0x2;
    			if(n.getType() == 0x1) flags = 0x3;
    			service.svcHdl.sendMessage(Message.obtain(service.svcHdl, 0x1, flags, 0x0));
    		}
    	}else if(arg1.getAction().contains("SCREEN_OFF")){
    		service.handleScreenTurnedOff();
    	}else if(arg1.getAction().contains("SCREEN_ON")){
    		service.handleScreenTurnedOn();
    	}
	}
}
