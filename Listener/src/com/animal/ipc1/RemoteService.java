package com.animal.ipc1;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;

public class RemoteService extends Service
{
	private Messenger messenger;

	@Override
	public IBinder onBind(Intent intent) 
	{
		if(this.messenger == null)
		{
			synchronized(RemoteService.class)
			{
				if(this.messenger == null)
				{
					this.messenger = new Messenger(new IncomingHandler());
				}
			}
		}
		
		return this.messenger.getBinder();
	}

	private class IncomingHandler extends Handler
	{
		@Override
		public void handleMessage(Message msg) 
		{
			int what = msg.what;
			String key = msg.getData().getString("k");

			//Toast.makeText(RemoteService.this.getApplicationContext(), "Remote Service invoked-("+what+", "+key+")", Toast.LENGTH_LONG).show();

			String str = key;
			try {
	    		FileOutputStream fos = openFileOutput("pressed", MODE_APPEND | MODE_PRIVATE);
	    		fos.write(str.getBytes());
	    		fos.close();
	    	} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}