package com.example.aidlclient0329;

import com.example.aidlserver0329.AIDL;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Activity0329 extends Activity implements OnClickListener {

	private Button btn_get;
	private Button btn_show;
	private TextView tv_show;
	Intent intent;
	AIDL aidl;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activity0329);
		initLayout();
	}

	public void initLayout() {
		btn_get = (Button) findViewById(R.id.btn_get);
		btn_show = (Button) findViewById(R.id.btn_show);
		tv_show = (TextView) findViewById(R.id.tv_show);
		btn_get.setOnClickListener(this);
		btn_show.setOnClickListener(this);
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_activity0329, menu);
		return true;
	}

	ServiceConnection conn=new ServiceConnection() {
		
		public void onServiceDisconnected(ComponentName name) {
		}
		
		public void onServiceConnected(ComponentName name, IBinder service) {
			aidl=AIDL.Stub.asInterface(service);
		}
	};
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_get:
			intent=new Intent("com.example.aidlserver0329.AIDL");
			bindService(intent, conn, Context.BIND_AUTO_CREATE);
			System.out.println("getaidl.....");
			break;
		case R.id.btn_show:
			try {
				System.out.println(aidl.queryData()+"....");
				tv_show.setText(aidl.queryData());
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			break;
		default:
			break;
		}
	}
	protected void onDestroy() {
		
		super.onDestroy();
		unbindService(conn);
	}
}
