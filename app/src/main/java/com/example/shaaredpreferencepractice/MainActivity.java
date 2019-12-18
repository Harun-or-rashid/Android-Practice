package com.example.shaaredpreferencepractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button save= (Button) findViewById(R.id.btn_save);
        Button show=(Button) findViewById(R.id.btn_show);
        final EditText edit_name=(EditText) findViewById(R.id.edit_name);
        final EditText edit_pass=(EditText) findViewById(R.id.edit_pass);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=edit_name.getText().toString();
                String pass=edit_pass.getText().toString();
                if (name.equals("")&&pass.equals("")){
                    Toast.makeText(getApplicationContext(),"Please enter your name and password",Toast.LENGTH_SHORT).show();
                }
                else {
                    SharedPreferences sharedPreferences=getSharedPreferences("database", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("userKey",name);
                    editor.putString("passKey",pass);
                    editor.apply();
                    Toast.makeText(getApplicationContext(),"Successfully Saved",Toast.LENGTH_SHORT).show();
                }



            }
        });

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences=getSharedPreferences("database",Context.MODE_PRIVATE);
                if (sharedPreferences.contains("userKey")&&sharedPreferences.contains("passKey")){
                    String name=sharedPreferences.getString("userKey","Not Found");
                    String pass=sharedPreferences.getString("passKey","Not Found");
                     result=(EditText) findViewById(R.id.result);
                    result.setText(name+"\n"+pass);
                }
            }
        });


    }
}
