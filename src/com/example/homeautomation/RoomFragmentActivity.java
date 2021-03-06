package com.example.homeautomation;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.opengl.Visibility;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class RoomFragmentActivity extends FragmentActivity {
	Context ctx;
	CompoundButton cb_l1,cb_l2,cb_l3,cb_l4,cb_l5,cb_l6,cb_l7,cb_l8,cb_fan;
	Button btnReset,btnTurnonall;
	boolean isReset = true;

	boolean isOnAll = true;

	RadioGroup radioGroup;
	RadioButton r_slow,r_medium,r_fast;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		ctx = this;
		setContentView(R.layout.main_homescreen);
		//add internet connection code
		cb_l1 = (CompoundButton) findViewById(R.id.ch_light_01);
		cb_l2 = (CompoundButton) findViewById(R.id.ch_light_02);
		cb_l3 = (CompoundButton) findViewById(R.id.ch_light_03);
		cb_l4 = (CompoundButton) findViewById(R.id.ch_light_04);
		cb_l5 = (CompoundButton) findViewById(R.id.ch_light_05);
		cb_l6 = (CompoundButton) findViewById(R.id.ch_light_06);
		cb_l7 = (CompoundButton) findViewById(R.id.ch_light_07);
		cb_l8 = (CompoundButton) findViewById(R.id.ch_light_08);
		cb_fan = (CompoundButton) findViewById(R.id.ch_fan);
		radioGroup = (RadioGroup) findViewById(R.id.radiogroup);
		
		r_slow = (RadioButton) findViewById(R.id.radiobutton_slow);
		r_medium = (RadioButton) findViewById(R.id.radiobutton_medium);
		r_fast = (RadioButton) findViewById(R.id.radiobutton_fast);
		
		btnReset = (Button) findViewById(R.id.btn_resetall);
	    btnTurnonall = (Button) findViewById(R.id.btn_turnon_all);
	    
	    if(!(cb_fan.isChecked())){
	    	
//	    	r_slow.setEnabled(false);
//			r_medium.setEnabled(false);
//			r_fast.setEnabled(false);
	    	radioGroup.clearCheck();
	    }
		
	    /*int checkedRadioButton = radioGroup.getCheckedRadioButtonId();
		 
		String radioButtonSelected = "";
		 
		switch (checkedRadioButton) {
		  case R.id.radiobutton1 : radioButtonSelected = "radiobutton1";
		                   	              break;
		  case R.id.radiobutton2 : radioButtonSelected = "radiobutton2";
				                      break;
		  case R.id.radiobutton3 : radioButtonSelected = "radiobutton3";
				                      break;
		}*/
	    
//		cb_l1.setOnCheckedChangeListener(new MyHomeOnCheckedChangeListener("1"));
//		cb_l2.setOnCheckedChangeListener(new MyHomeOnCheckedChangeListener("2"));
//		cb_l3.setOnCheckedChangeListener(new MyHomeOnCheckedChangeListener("3"));
//		cb_l4.setOnCheckedChangeListener(new MyHomeOnCheckedChangeListener("4"));
//		cb_l5.setOnCheckedChangeListener(new MyHomeOnCheckedChangeListener("5"));
//		cb_l6.setOnCheckedChangeListener(new MyHomeOnCheckedChangeListener("6"));
//		cb_l7.setOnCheckedChangeListener(new MyHomeOnCheckedChangeListener("7"));
//		cb_l8.setOnCheckedChangeListener(new MyHomeOnCheckedChangeListener("8")); 
	    
	    cb_l1.setOnClickListener(new MyHomeOnClickListener("1",cb_l1));
		cb_l2.setOnClickListener(new MyHomeOnClickListener("2",cb_l2));
		cb_l3.setOnClickListener(new MyHomeOnClickListener("3",cb_l3));
		cb_l4.setOnClickListener(new MyHomeOnClickListener("4",cb_l4));
		cb_l5.setOnClickListener(new MyHomeOnClickListener("5",cb_l5));
		cb_l6.setOnClickListener(new MyHomeOnClickListener("6",cb_l6));
		cb_l7.setOnClickListener(new MyHomeOnClickListener("7",cb_l7));
		cb_l8.setOnClickListener(new MyHomeOnClickListener("8",cb_l8));
		cb_fan.setOnClickListener(new MyHomeOnClickListener("fan", cb_fan));
		
		
		r_slow.setOnClickListener(new OnClickListener() {

			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				System.out.println("Checkbox fan slow>> "+v.isSelected());
//				Toast.makeText(ctx, "text"+isChecked, Toast.LENGTH_LONG).show();
				String url = ctx.getResources().getString(R.string.base_url)+ctx.getResources().getString(R.string.exte_url_fan)+"/1"; 
				System.out.println("URL of Fan slow  >> "+url);					
				
				
				new OnOffAsync().execute(url);
			}
		});
		

		r_medium.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				System.out.println("Checkbox fan medium>> "+v.isSelected());
//				Toast.makeText(ctx, "text"+isChecked, Toast.LENGTH_LONG).show();
				String url = ctx.getResources().getString(R.string.base_url)+ctx.getResources().getString(R.string.exte_url_fan)+"/2"; 
				System.out.println("URL of Fan medium  >> "+url);					
				
				
				new OnOffAsync().execute(url);
			}
		});
		

		r_fast.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				System.out.println("Checkbox fan fast >> "+v.isSelected());
//				Toast.makeText(ctx, "text"+isChecked, Toast.LENGTH_LONG).show();
				String url = ctx.getResources().getString(R.string.base_url)+ctx.getResources().getString(R.string.exte_url_fan)+"/3"; 
				System.out.println("URL of Fan fast  >> "+url);					
				
				
				new OnOffAsync().execute(url);
			}
		});
		
//		cb_fan.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				//enable radiobutton
//				if(cb_fan.isChecked()){
//					System.out.println("Checkbox fan >> "+cb_fan.isChecked());
//					Toast.makeText(ctx, "text"+cb_fan.isChecked(), Toast.LENGTH_LONG).show();
//					
//					
//					r_slow.setEnabled(true);
//					r_medium.setEnabled(true);
//					r_fast.setEnabled(true);
//					
//					
//					String url_fan = ctx.getResources().getString(R.string.base_url)+ctx.getResources().getString(R.string.exte_url_fan)+"/1"; 
//					System.out.println("URL of Device fan  >> "+url_fan);				
//					
//					new OnOffAsync().execute(url_fan);
////					192.168.2.150/arduino/fan/2
//					
//				}else{
//					
//					String url_fan = ctx.getResources().getString(R.string.base_url)+ctx.getResources().getString(R.string.exte_url_fan)+"/1"; 
//					System.out.println("URL of Device fan  >> "+url_fan);				
//					
//					new OnOffAsync().execute(url_fan);
//					
//					r_slow.setEnabled(false);
//					r_medium.setEnabled(false);
//					r_fast.setEnabled(false);
//				}
//				
//				
//			}
//		});
		
		
		
		
		btnReset.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String url;
				// TODO Auto-generated method stub
				
				if(isReset){//if true make reset btn disable
				System.out.println("Button reset >> ");
				btnReset.setEnabled(false);
//				Toast.makeText(ctx, "text"+isChecked, Toast.LENGTH_LONG).show();
				url = ctx.getResources().getString(R.string.base_url)+ctx.getResources().getString(R.string.reset_all); 
				System.out.println("URL of Device Reset >> "+url);
				new OnOffAsync().execute(url);
				}
			}
		});
		
		
		btnTurnonall.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String url;
				System.out.println("Button reset >> ");
				btnReset.setEnabled(false);
//				Toast.makeText(ctx, "text"+isChecked, Toast.LENGTH_LONG).show();
				url = ctx.getResources().getString(R.string.base_url)+ctx.getResources().getString(R.string.turn_all); 
				System.out.println("URL of Device Reset >> "+url);
				new OnOffAsync().execute(url);
			}
		});
		
//		cb_l1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
//			String url;
//			@Override
//			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//				// TODO Auto-generated method stub
//				if(isChecked){
//					System.out.println("Checkbox 1 >> "+isChecked);
//					Toast.makeText(ctx, "text"+isChecked, Toast.LENGTH_LONG).show();
//					url = ctx.getResources().getString(R.string.base_url)+ctx.getResources().getString(R.string.exte_url)+"1/1"; 
//					System.out.println("URL of Device 1 >> "+url);
//					new OnOffAsync().execute(url);
//					
//				}else{
//					
//					url = ctx.getResources().getString(R.string.base_url)+ctx.getResources().getString(R.string.exte_url)+"1/0";
//					System.out.println("URL of Device 1 >> "+url);
//					new OnOffAsync().execute(url);
//				}
//				
//			}
//		});
		
		
		/*cb_l1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String url;
				if(cb_l1.isChecked()){
					System.out.println("Checkbox 1 >> "+cb_l1.isChecked());
					Toast.makeText(ctx, "text"+cb_l1.isChecked(), Toast.LENGTH_LONG).show();
					url = ctx.getResources().getString(R.string.base_url)+ctx.getResources().getString(R.string.exte_url)+"1/1"; 
					System.out.println("URL of Device 1 >> "+url);
					new OnOffAsync().execute(url);
					
				}else{
					
					url = ctx.getResources().getString(R.string.base_url)+ctx.getResources().getString(R.string.exte_url)+"1/0";
					System.out.println("URL of Device 1 >> "+url);
					new OnOffAsync().execute(url);
				}
			}
		});*/
		
	
	}//End of onCreate()
	
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}
	
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
	}
		
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}	

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		try{
			//not working doesnot set button state
			if(isReset){
				btnReset.setEnabled(true);
			}else{
				btnReset.setEnabled(false);
				
			}
			
			String url = ctx.getResources().getString(R.string.base_url)+ctx.getResources().getString(R.string.statusnow_url);
			new OnOffAsync().execute(url);
		}catch(Exception e){
			
		}
	}	
	
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
	}
	
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onRestoreInstanceState(savedInstanceState);
	}
	
	@Override
	public Object onRetainCustomNonConfigurationInstance() {
		// TODO Auto-generated method stub
		return super.onRetainCustomNonConfigurationInstance();
	}

	private Context getDialogContext() {
		Context context;
		if (getParent() != null)
			context = getParent();
		else
			context = this;
		return context;
	}
	
	class OnOffAsync extends AsyncTask<String, Void, String>{
		ProgressDialog pdialog = new ProgressDialog(getDialogContext());
		String[] response = new String[2];
		String s_msg,s_token,susername,spassword,s_did,s_type;
		String str_device1,str_device2,str_device3,str_device4,str_device5,str_device6,str_device7,str_device8,str_fan,str_curtain;
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
	
			pdialog = ProgressDialog.show(ctx, "", "");
			pdialog.setContentView(R.layout.wheel);

			pdialog.setCancelable(true);
			pdialog.setOnCancelListener(new OnCancelListener() {

				public void onCancel(DialogInterface dialog) {
					// TODO Auto-generated method stub
					cancel(true);
				}
			});
			
		}
		
		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			
			
			
			/*{
			    "device1": 1,
			    "device2": 0,
			    "device3": 0,
			    "device4": 1,
			    "device5": 0,
			    "device6": 0,
			    "device7": 0,
			    "device8": 0,
			    "fan": 0,
			    "curtain": 0
			}*/
			String url = params[0];
			
			response = HttpCall.getDeviceOnOff(url);
			
			System.out.println("378 RoomFragmentActivity >> "+response[0]+" string >> "+response[1]);
			
			if(response[1] != null && !(response[1].equalsIgnoreCase("null")) ){
				
				if(response[0] == "200" || response[0].equalsIgnoreCase("200")){
					
					try {
						JSONObject room_json = new JSONObject(response[1]);
						 str_device1 = room_json.getString("device1");
						 str_device2 = room_json.getString("device2");
						 str_device3 = room_json.getString("device3");
						 str_device4 = room_json.getString("device4");
						 str_device5 = room_json.getString("device5");
						 str_device6 = room_json.getString("device6");
						 str_device7 = room_json.getString("device7");
						 str_device8 = room_json.getString("device8");
						 str_fan = room_json.getString("fan");
//						 str_curtain = room_json.getString("curtain");
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
			
			return null;
		}
		
		
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if (pdialog.isShowing()) {
				pdialog.dismiss();

			}
			
			if(response[1] != null && !(response[1].equalsIgnoreCase("null"))){
				
				if(response[0] == "200" || response[0].equalsIgnoreCase("200")){
					
					System.out.println(" Device 1>> "+str_device1+" Device 2>> "+str_device2+" Device 3>> "+str_device3+" Device 4>> "+str_device4+" Device 5>> "+str_device5+" Device 6>> "+str_device6+" Device 7>> "+str_device7+" Device 8>> "+str_device8+" Device Fan >> "+str_fan+" Device curtain >> "+str_curtain);
					
					
					if(str_device1.equalsIgnoreCase("1") && str_device2.equalsIgnoreCase("1") 
							&& str_device3.equalsIgnoreCase("1") && str_device4.equalsIgnoreCase("1")
							&& str_device5.equalsIgnoreCase("1") && str_device6.equalsIgnoreCase("1")
							&& str_device7.equalsIgnoreCase("1") && str_device8.equalsIgnoreCase("1")
							&& !(str_fan.equalsIgnoreCase("0"))){
						
						isOnAll = false;
					}else{
						isOnAll = true;
					}
					
					if(isOnAll){
						btnTurnonall.setEnabled(true);
					}else{
						btnTurnonall.setEnabled(false);
					}
					
					
					if(str_device1.equalsIgnoreCase("0")){
						cb_l1.setChecked(false);
						
					}else{
						cb_l1.setChecked(true);
						isReset = true;
						btnReset.setEnabled(true);
					}
					
					if(str_device2.equalsIgnoreCase("0")){
						cb_l2.setChecked(false);
					}else{
						cb_l2.setChecked(true);
						isReset = true;
						btnReset.setEnabled(true);
					}
					
					
					if(str_device3.equalsIgnoreCase("0")){
						cb_l3.setChecked(false);
					}else{
						cb_l3.setChecked(true);
						isReset = true;
						btnReset.setEnabled(true);
					}
					
					if(str_device4.equalsIgnoreCase("0")){
						cb_l4.setChecked(false);
					}else{
						cb_l4.setChecked(true);
						isReset = true;
						btnReset.setEnabled(true);
					}
					
					if(str_device5.equalsIgnoreCase("0")){
						cb_l5.setChecked(false);
					}else{
						cb_l5.setChecked(true);
						isReset = true;
						btnReset.setEnabled(true);
					}
					
					if(str_device6.equalsIgnoreCase("0")){
						cb_l6.setChecked(false);
					}else{
						cb_l6.setChecked(true);
						isReset = true;
						btnReset.setEnabled(true);
					}
					if(str_device7.equalsIgnoreCase("0")){
						cb_l7.setChecked(false);
					}else{
						cb_l7.setChecked(true);
						isReset = true;
						btnReset.setEnabled(true);
					}
					if(str_device8.equalsIgnoreCase("0")){
						cb_l8.setChecked(false);
					}else{
						cb_l8.setChecked(true);
						isReset = true;
						btnReset.setEnabled(true);
					}
					
					if(str_device8.equalsIgnoreCase("0")){
						cb_l8.setChecked(false);
					}else{
						cb_l8.setChecked(true);
						isReset = true;
						btnReset.setEnabled(true);
					}
					if(str_fan.equalsIgnoreCase("0")){
						

						Toast.makeText(ctx, "fan is 0 >> "+str_fan, 2000).show();
						cb_fan.setChecked(false);						
						//disable all radio button
//						r_slow.setChecked(false);
//						r_medium.setChecked(false);
//						r_fast.setChecked(false);
						radioGroup.clearCheck();
						
						r_slow.setEnabled(false);
						r_medium.setEnabled(false);
						r_fast.setEnabled(false);
						
						
						
						
					} if(str_fan.equalsIgnoreCase("1")){
						
						Toast.makeText(ctx, "fan is 1 >> "+str_fan, 2000).show();
						
						r_slow.setEnabled(true);
						r_medium.setEnabled(true);
						r_fast.setEnabled(true);
						
						
						r_slow.setChecked(true);
						Toast.makeText(ctx, "fan is 1 set on>> ", 2000).show();
						cb_fan.setChecked(true);
						
						
						
						
						isReset = true;
						
						
						
					} if(str_fan.equalsIgnoreCase("2")){
						
						Toast.makeText(ctx, "fan is 2 >> "+str_fan, 2000).show();
						
						r_slow.setEnabled(true);
						r_medium.setEnabled(true);
						r_fast.setEnabled(true);
						
						cb_fan.setChecked(true);	
						r_medium.setChecked(true);
						
						isReset = true;
					} if (str_fan.equalsIgnoreCase("3")){
						
						Toast.makeText(ctx, "fan is 3 >> "+str_fan, 2000).show();
						r_slow.setEnabled(true);
						r_medium.setEnabled(true);
						r_fast.setEnabled(true);
						
						cb_fan.setChecked(true);	
						r_fast.setChecked(true);
						
						isReset = true;

					}
						//enable all radio button and set radiobutton
						
//						cb_fan.setChecked(true);
//						r_slow.setEnabled(true);
//						r_medium.setEnabled(true);
//						r_fast.setEnabled(true);
//						
//						if(str_fan.equalsIgnoreCase("1")){
//							r_slow.setSelected(true);
//							r_medium.setSelected(false);
//							r_fast.setSelected(false);
//						}
//						
//						if(str_fan.equalsIgnoreCase("2")){
//							r_slow.setSelected(false);
//							r_medium.setSelected(true);
//							r_fast.setSelected(false);
//						}
//						
//						if(str_fan.equalsIgnoreCase("3")){
//							r_slow.setSelected(false);
//							r_medium.setSelected(false);
//							r_fast.setSelected(true);
//						}
						
					
					
				}
			}else{
				
				
				
			}
		}//End of postExecute
	}//End of async class	 
	
	private class MyHomeOnClickListener implements OnClickListener{

		 String dev_id;
		 CompoundButton vi;
	     public MyHomeOnClickListener(String dev_id,CompoundButton v) {
	          this.dev_id = dev_id;
	          this.vi = v;
	     }

		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			String url;
			
			if(dev_id.equalsIgnoreCase("fan")){
				
				
				if(vi.isChecked()){

					
					Toast.makeText(ctx, "is Checked >> "+vi.isChecked(), 2000).show();

					System.out.println("Checkbox fan >> "+vi.isChecked());
//					Toast.makeText(ctx, "text"+isChecked, Toast.LENGTH_LONG).show();
					url = ctx.getResources().getString(R.string.base_url)+ctx.getResources().getString(R.string.exte_url_fan)+"/1"; 
					System.out.println("URL of Device "+dev_id+" >> "+url);				
					

//					r_slow.setEnabled(true);
//					r_medium.setEnabled(true);
//					r_fast.setEnabled(true);
					
//					r_slow.setChecked(true);

					
					new OnOffAsync().execute(url);
					
				}else{

					Toast.makeText(ctx, "is not >> "+vi.isChecked(), 2000).show();
//					r_slow.setChecked(false);
//					r_medium.setChecked(false);
//					r_fast.setChecked(false);
//					
//					r_slow.setEnabled(false);
//					r_medium.setEnabled(false);
//					r_fast.setEnabled(false);
//					radioGroup.clearCheck();

					
					
					
					
					url = ctx.getResources().getString(R.string.base_url)+ctx.getResources().getString(R.string.exte_url_fan)+"/0";
					System.out.println("URL of Device "+dev_id+" >> "+url);
					new OnOffAsync().execute(url);
				}
				
				
			}else 
			
			if(vi.isChecked()){
				System.out.println("Checkbox 1 >> "+vi.isChecked());
//				Toast.makeText(ctx, "text"+isChecked, Toast.LENGTH_LONG).show();
				url = ctx.getResources().getString(R.string.base_url)+ctx.getResources().getString(R.string.exte_url)+dev_id+"/1"; 
				System.out.println("URL of Device "+dev_id+" >> "+url);				
				
				new OnOffAsync().execute(url);
				
			}else{
				
				url = ctx.getResources().getString(R.string.base_url)+ctx.getResources().getString(R.string.exte_url)+dev_id+"/0";
				System.out.println("URL of Device "+dev_id+" >> "+url);
				new OnOffAsync().execute(url);
			}
		}
		
	}
	
	public class MyHomeOnCheckedChangeListener implements OnCheckedChangeListener
	   {

	     String dev_id;
	     public MyHomeOnCheckedChangeListener(String dev_id) {
	          this.dev_id = dev_id;
	     }

		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			String url;
			// TODO Auto-generated method stub
			if(isChecked){
				System.out.println("Checkbox 1 >> "+isChecked);
//				Toast.makeText(ctx, "text"+isChecked, Toast.LENGTH_LONG).show();
				url = ctx.getResources().getString(R.string.base_url)+ctx.getResources().getString(R.string.exte_url)+dev_id+"/1"; 
				System.out.println("URL of Device "+dev_id+" >> "+url);
				new OnOffAsync().execute(url);
				
			}else{
				
				url = ctx.getResources().getString(R.string.base_url)+ctx.getResources().getString(R.string.exte_url)+dev_id+"/0";
				System.out.println("URL of Device "+dev_id+" >> "+url);
				new OnOffAsync().execute(url);
			}
		}

	  }//End of MyHomeOnCheckedChangeListener
}


/*List of the Fan Speed Control urls
Fan OFF - 192.168.x.xyz/arduino/fan/0

Fan ON � SPEED : LOW - 192.168.x.xyz/arduino/fan/1

Fan ON � SPEED : MEDIUM - 192.168.x.xyz/arduino/fan/2
{
    "device1": 1,
    "device2": 1,
    "device3": 1,
    "device4": 1,
    "device5": 1,
    "device6": 1,
    "device7": 1,
    "device8": 1,
    "fan": 2,
    "curtain": 0
}

Fan ON � SPEED : HIGH - 192.168.x.xyz/arduino/fan/3


List of the Curtain Control urls
CLOSE THE CURTAINS - 192.168.x.xyz/arduino/curtain/close

OPEN THE CURTAINS - 192.168.x.xyz/arduino/curtain/open

For Reset Operation :
Resets the circuit to OFF state - 192.168.x.xyz/arduino/reset/all
{
    "device1": 0,
    "device2": 0,
    "device3": 0,
    "device4": 0,
    "device5": 0,
    "device6": 0,
    "device7": 0,
    "device8": 0,
    "fan": 0,
    "curtain": 1//close
}

For Turning All Devices ON Operation :
Turns on all the devices - 192.168.x.xyz/arduino/turnon/all
{
    "device1": 1,
    "device2": 1,
    "device3": 1,
    "device4": 1,
    "device5": 1,
    "device6": 1,
    "device7": 1,
    "device8": 1,
    "fan": 1,
    "curtain": 0//open
}


 

*/