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


public class SignInActivity extends Activity {
    TextView joinBtn, forgotPwd;
    ImageView signInBtn;
    EditText etEmail, etPwd;
    Common common;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        common = Common.getNewInstance(this);
        joinBtn = (TextView) findViewById(R.id.joinBtn);
        signInBtn = (ImageView) findViewById(R.id.signInBtn);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPwd = (EditText) findViewById(R.id.etPwd);


        Typeface regular = Typeface.createFromAsset(this.getAssets(), "fonts/SanFranciscoText-Regular.otf");
        Typeface medium = Typeface.createFromAsset(this.getAssets(), "fonts/SanFranciscoText-Medium.otf");
        Typeface heavy = Typeface.createFromAsset(this.getAssets(), "fonts/SanFranciscoText-Heavy.otf");
        TextView memberSignIn = (TextView) findViewById(R.id.memberSignIn);
        forgotPwd = (TextView) findViewById(R.id.forgotPwd);
        TextView textPart1 = (TextView) findViewById(R.id.textPart1);
        memberSignIn.setTypeface(regular);
        forgotPwd.setTypeface(medium);
        textPart1.setTypeface(medium);
        joinBtn.setTypeface(heavy);
        etEmail.setTypeface(medium);
        etPwd.setTypeface(medium);

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etEmail.getText().toString().isEmpty() || etPwd.getText().toString().isEmpty()) {
                    common.showShortToast("Please enter details");
                } else if (!common.isValidEmail(etEmail.getText().toString())) {
                    common.showShortToast("Please enter valid email");
                } else {
                    //common.showSpinner(SignInActivity.this);
                    Intent intent = new Intent(SignInActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

    }
}
