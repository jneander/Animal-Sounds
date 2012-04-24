package com.jneander.animalsounds;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        
    }
    
    public void goToZoo(View v) {
    	Intent zooIntent = new Intent(this, ZooActivity.class);
    	this.startActivity( zooIntent );
    }
}