package com.developer.nguyenngocbaothy.android_ptit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EmailActivity extends AppCompatActivity {

    EditText edtSubject, edtMessage;
    Button btnSending;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);

        edtSubject = (EditText) findViewById(R.id.edtSubject);
        edtMessage = (EditText) findViewById(R.id.edtMessage);
        btnSending = (Button) findViewById(R.id.btnSend);

        btnSending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String toEmail = "sharefood@gmail.com";
                String subject = edtSubject.getText().toString().trim();
                String message = edtMessage.getText().toString().trim();

                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[] { toEmail });
                email.putExtra(Intent.EXTRA_SUBJECT, subject);
                email.putExtra(Intent.EXTRA_TEXT, message);

                email.setType("message/rfc822");
                startActivity(Intent.createChooser(email, "Choose app to send mail"));
            }
        });
    }
}
