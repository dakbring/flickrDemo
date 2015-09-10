package com.codepath.apps.restclienttemplate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.codepath.apps.restclienttemplate.preferences.UserPreferences;
import com.codepath.oauth.OAuthLoginActivity;

public class LoginActivity extends OAuthLoginActivity<FlickrClient> implements View.OnClickListener {

    private Button mLoginBtn;
    private Button mGetRequestTokenBtn;
    private Button mGetAccessTokenBtn;
    private TextView mRequestTokenTxt;
    private TextView mAccessTokenTxt;

    private UserPreferences mPreferences = new UserPreferences(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

        mLoginBtn = (Button)findViewById(R.id.btn_login);
        mGetRequestTokenBtn = (Button)findViewById(R.id.btn_request_token);
        mGetAccessTokenBtn = (Button)findViewById(R.id.btn_access_token);
        mRequestTokenTxt = (TextView)findViewById(R.id.txt_request_token);
        mAccessTokenTxt = (TextView)findViewById(R.id.txt_access_token);

        mLoginBtn.setOnClickListener(this);
        mGetRequestTokenBtn.setOnClickListener(this);
        mGetAccessTokenBtn.setOnClickListener(this);
	}

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_login:
                getClient().connect();
                break;
            case R.id.btn_request_token:
                mRequestTokenTxt.setText("Request Token");
                break;
            case R.id.btn_access_token:
                if(getClient().checkAccessToken()!=null){
                    mPreferences.setUserAccessToken(getClient().checkAccessToken().toString());
                    mAccessTokenTxt.setText(mPreferences.getUserAccessToken());
                }else{
                    mAccessTokenTxt.setText("Token null");
                }
                break;
        }
    }

    @Override
    public void onLoginSuccess() {
    	Intent i = new Intent(this, PhotosActivity.class);
    	startActivity(i);
    }

    @Override
    public void onLoginFailure(Exception e) {
        e.printStackTrace();
    }
}
