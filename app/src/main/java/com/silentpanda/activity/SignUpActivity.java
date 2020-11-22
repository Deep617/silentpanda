package com.silentpanda.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.silentpanda.R;
import com.silentpanda.helper.Common;


public class SignUpActivity extends Activity {
    ImageView joinBtn;
    EditText etEmail, etPwd, etReEmail;
    Common common;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        common = Common.getNewInstance(this);
        joinBtn = (ImageView) findViewById(R.id.joinBtn);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etReEmail = (EditText) findViewById(R.id.etReEmail);
        etPwd = (EditText) findViewById(R.id.etPwd);

        Typeface regular = Typeface.createFromAsset(this.getAssets(), "fonts/SanFranciscoText-Regular.otf");
        Typeface medium = Typeface.createFromAsset(this.getAssets(), "fonts/SanFranciscoText-Medium.otf");
        Typeface heavy = Typeface.createFromAsset(this.getAssets(), "fonts/SanFranciscoText-Heavy.otf");
        TextView newMember = (TextView) findViewById(R.id.newMember);
        TextView secondParttext = (TextView) findViewById(R.id.secondParttext);
        TextView textPart1 = (TextView) findViewById(R.id.textPart1);
        TextView thirdPartText = (TextView) findViewById(R.id.thirdPartText);
        newMember.setTypeface(regular);
        secondParttext.setTypeface(heavy);
        thirdPartText.setTypeface(regular);
        textPart1.setTypeface(regular);
        etEmail.setTypeface(medium);
        etPwd.setTypeface(medium);
        etReEmail.setTypeface(medium);

        joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etEmail.getText().toString().isEmpty() || etPwd.getText().toString().isEmpty() || etReEmail.getText().toString().isEmpty()) {
                    common.showShortToast("Please enter all details");
                } else if (!common.isValidEmail(etEmail.getText().toString())) {
                    common.showShortToast("Please enter valid email");
                } else if (!common.isValidEmail(etReEmail.getText().toString())) {
                    common.showShortToast("Please enter valid email");
                } else if (!etReEmail.getText().toString().equalsIgnoreCase(etEmail.getText().toString())) {
                    common.showShortToast("Email didn't match");
                } else {
                    Intent intent = new Intent(SignUpActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}
