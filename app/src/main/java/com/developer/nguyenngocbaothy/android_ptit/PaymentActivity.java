package com.developer.nguyenngocbaothy.android_ptit;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.craftman.cardform.Card;
import com.craftman.cardform.CardForm;
import com.craftman.cardform.OnPayBtnClickListner;

public class PaymentActivity extends AppCompatActivity {

    CardForm cardForm;
    TextView txtDes;
    Button btnPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        cardForm = (CardForm) findViewById(R.id.cardform);
        txtDes = (TextView) findViewById(R.id.payment_amount); // default
        btnPay = (Button) findViewById(R.id.btn_pay);

        txtDes.setText("$" + "1000");
        btnPay.setText(String.format("Payer %s", txtDes.getText()));

        cardForm.setPayBtnClickListner(new OnPayBtnClickListner() {
            @Override
            public void onClick(Card card) {
                //Toast.makeText(PaymentActivity.this, "Name: " + card.getName() + " | Lats 4 digits: " + card.getLast4(), Toast.LENGTH_LONG).show();

                AlertDialog.Builder alertBuilder  = new AlertDialog.Builder(PaymentActivity.this);
                alertBuilder.setTitle("Confirm before purchase");
                alertBuilder.setMessage(
                        "Card Name: " + card.getName() + "\n" +
                        "Card number: " + card.getNumber() + "\n" +
                        "Card expiry month: " + card.getExpMonth().toString() + "\n" +
                        "Card expiry year: " + card.getExpYear().toString() + "\n" +
                        "Card CVV: " + card.getCVC() + "\n"
                );
                alertBuilder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        Toast.makeText(PaymentActivity.this, "Thank you for purchase", Toast.LENGTH_LONG).show();
                    }
                });
                alertBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                alertBuilder.show();

            }
        });

    }
}
