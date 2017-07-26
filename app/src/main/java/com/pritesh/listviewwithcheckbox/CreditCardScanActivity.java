package com.pritesh.listviewwithcheckbox;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import io.card.payment.CardIOActivity;
import io.card.payment.CardType;
import io.card.payment.CreditCard;

import static io.card.payment.CardIOActivity.getCapturedCardImage;

//https://github.com/pritamworld/card.io-Android-SDK
public class CreditCardScanActivity extends AppCompatActivity
{
    final static int MY_SCAN_REQUEST_CODE = 100;
    TextView txtCCDetails;
    ImageView imgCC;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_card_scan);

        txtCCDetails = (TextView)findViewById(R.id.txtCCDetails);
        imgCC = (ImageView)findViewById(R.id.imgCC);
    }

    public void onScanPress(View v) {
        Intent scanIntent = new Intent(this, CardIOActivity.class);

        // customize these values to suit your needs.
        scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_EXPIRY, true); // default: false
        scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_CVV, true); // default: false
        scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_POSTAL_CODE, true); // default: false
        scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_CARDHOLDER_NAME,true);
        scanIntent.putExtra(CardIOActivity.EXTRA_SCAN_INSTRUCTIONS,"Center you Card to Scan");
        scanIntent.putExtra(CardIOActivity.EXTRA_RETURN_CARD_IMAGE,true);
        // MY_SCAN_REQUEST_CODE is arbitrary and is only used within this activity.
        startActivityForResult(scanIntent, MY_SCAN_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == MY_SCAN_REQUEST_CODE) {
            String resultDisplayStr;
            if (data != null && data.hasExtra(CardIOActivity.EXTRA_SCAN_RESULT)) {
                CreditCard scanResult = data.getParcelableExtra(CardIOActivity.EXTRA_SCAN_RESULT);

                // Never log a raw card number. Avoid displaying it, but if necessary use getFormattedCardNumber()
                resultDisplayStr = "Card Number: " + scanResult.getRedactedCardNumber() + "\n";

                // Do something with the raw number, e.g.:
                // myService.setCardNumber( scanResult.cardNumber );

                if (scanResult.isExpiryValid()) {
                    resultDisplayStr += "Expiration Date: " + scanResult.expiryMonth + "/" + scanResult.expiryYear + "\n";
                }

                if (scanResult.cvv != null) {
                    // Never log or display a CVV
                    resultDisplayStr += "CVV has " + scanResult.cvv.length() + " digits.\n";
                }

                if (scanResult.postalCode != null) {
                    resultDisplayStr += "Postal Code: " + scanResult.postalCode + "\n";
                }

                if (scanResult.cardholderName != null) {
                    resultDisplayStr += "Name: " + scanResult.cardholderName + "\n";
                }

                if (scanResult.cardNumber != null) {
                    resultDisplayStr += "Card Number : " + scanResult.cardNumber + "\n";
                }

                if (scanResult.getCardType() != null) {
                    CardType cardType = scanResult.getCardType();
                    resultDisplayStr += "Card Type : " + cardType.name + "\n";
                    imgCC.setImageBitmap(cardType.imageBitmap(getApplicationContext()));
                }

                if (getCapturedCardImage(data) != null) {
                    Bitmap cardImage = CardIOActivity.getCapturedCardImage(data);
                     imgCC.setImageBitmap(cardImage);
                }
            }
            else {
                resultDisplayStr = "Scan was canceled.";
            }

            txtCCDetails.setText(resultDisplayStr);
            // do something with resultDisplayStr, maybe display it in a textView
            // resultTextView.setText(resultDisplayStr);
        }
        // else handle other activity results
    }
}
