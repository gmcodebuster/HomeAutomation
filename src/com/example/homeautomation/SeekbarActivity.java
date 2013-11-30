package com.example.homeautomation;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;

public class SeekbarActivity extends FragmentActivity {
	 private SeekBar volumeControl = null;
	 
	 @Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
	
		 setContentView(R.layout.activity_seekbar);
		 
	        volumeControl = (SeekBar) findViewById(R.id.volume_bar);
	 
	        volumeControl.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
	            int progressChanged = 0;
	 
	            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
	                progressChanged = progress;
	            }
	 
	            public void onStartTrackingTouch(SeekBar seekBar) {
	                // TODO Auto-generated method stub
	            }
	 
	            public void onStopTrackingTouch(SeekBar seekBar) {
	                Toast.makeText(SeekbarActivity.this,"seek bar progress:"+progressChanged,
	                        Toast.LENGTH_SHORT).show();
	            }
	        });
	 }
}
