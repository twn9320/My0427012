package com.example.yvtc.my042701;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent it = getIntent();
        String str = it.getStringExtra("msg");
        if(str!=null){
            TextView tv = (TextView)findViewById(R.id.textView);
            tv.setText(str);

        }
        Toast.makeText(MainActivity.this,str,Toast.LENGTH_SHORT).show();
    }
}
