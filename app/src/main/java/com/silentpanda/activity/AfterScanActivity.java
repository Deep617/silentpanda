package com.silentpanda.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.silentpanda.R;
import com.silentpanda.helper.Common;


public class AfterScanActivity extends Activity {
    ImageView backIcon, nextIcon;
    TextView readCode;
    Common common;
    String convertedText = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_scan);
        common = Common.getNewInstance(this);
        backIcon = (ImageView) findViewById(R.id.backIcon);
        nextIcon = (ImageView) findViewById(R.id.nextIcon);
        readCode = (TextView) findViewById(R.id.readCode);

        Typeface bold = Typeface.createFromAsset(this.getAssets(), "fonts/SanFranciscoText-Bold.otf");
        Typeface medium = Typeface.createFromAsset(this.getAssets(), "fonts/SanFranciscoText-Medium.otf");
        Typeface semiBold = Typeface.createFromAsset(this.getAssets(), "fonts/SanFranciscoText-Semibold.otf");
        TextView congratsText = (TextView) findViewById(R.id.congratsText);
        TextView authText = (TextView) findViewById(R.id.authText);
        TextView codeText = (TextView) findViewById(R.id.codeText);
        TextView proceedText = (TextView) findViewById(R.id.proceedText);
        congratsText.setTypeface(bold);
        authText.setTypeface(medium);
        codeText.setTypeface(semiBold);
        readCode.setTypeface(semiBold);
        proceedText.setTypeface(medium);
        try {
            convertedText = new String(Base64.decode(common.getStringValue("barcode"), Base64.NO_WRAP), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        readCode.setText(convertedText);
        nextIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AfterScanActivity.this, SignInActivity.class);
                startActivity(intent);
                finish();
            }
        });
        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
