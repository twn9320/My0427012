package com.example.yvtc.my042701;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference myRef,myRef2;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("message");
        myRef2 = database.getReference("phone");
        mAuth = FirebaseAuth.getInstance();
        Intent it = getIntent();
        String str = it.getStringExtra("msg");
        if(str!=null){
            TextView tv = (TextView)findViewById(R.id.textView);
            tv.setText(str);

        }
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                TextView tv2 = (TextView)findViewById(R.id.textView2);
                String value = dataSnapshot.getValue(String.class);
                tv2.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    public void clicksave(View v){
        // Write a message to the database

        EditText ed = (EditText)findViewById(R.id.editText);
        myRef.setValue(ed.getText().toString());
        myRef2.setValue(ed.getText().toString());
    }
    public void clicklogin(View v){
        mAuth.signInWithEmailAndPassword("abc@aa.com","123123").addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(MainActivity.this,"登入成功",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this,"登入失敗",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
