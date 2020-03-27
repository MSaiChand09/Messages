package com.example.messages;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText _txtphone ,_txtusername ;
    EditText _txtmail ;
    Button btn_button1,btn_button2 ;
    private static final int PERMISSION_SEND_SMS = 1;
    public static final String fromMailId = "YOUR EMAIL ID";
    public static final String password = "YOUR PASSWORD";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _txtphone = (EditText)findViewById(R.id.txtphone);
        _txtmail = (EditText)findViewById(R.id.txtmail);
        _txtusername =(EditText)findViewById(R.id.username);
        btn_button1 = (Button) findViewById(R.id.button);
        btn_button2= (Button) findViewById(R.id.button2);
        btn_button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED)
                {
                    ActivityCompat.requestPermissions(MainActivity.this ,new String[]{ Manifest.permission.SEND_SMS},0);
                }
                sendMessage();
                Log.e("finished","sending message......");
                //sendEmail();
            }
        });
        btn_button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendEmail();
                Log.e("finished","sending message......");
            }
        });
    }
    private void sendMessage(){
        String phone =_txtphone.getText().toString();
        SmsManager mysmsManager = SmsManager.getDefault();
        mysmsManager.sendTextMessage(phone,null," Hii dude !! You Got this message from Android studio",null,null);
        Toast.makeText(MainActivity.this,"Message sent",Toast.LENGTH_SHORT).show();
    }
    private void sendEmail() {
        /*Intent mailintent = new Intent(Intent.ACTION_SEND);
        mailintent.putExtra(Intent.EXTRA_EMAIL ,email);
        mailintent.putExtra(Intent.EXTRA_SUBJECT,"Registered Message");
        mailintent.putExtra(Intent.EXTRA_TEXT,"Your mail id has been succesfully registered to mobile mahithi ..... Happy Going !!!");
        mailintent.setType("message/rfc822");
        startActivity(Intent.createChooser(mailintent,"Registered !!"));*/

        String email=_txtmail.getText().toString();
        ToSendMail toSendMail =  new ToSendMail(email,fromMailId,password );
        toSendMail.execute();
        Toast.makeText(this, "Email sent ",Toast.LENGTH_LONG).show();

    }
}
