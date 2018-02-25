package com.amazonaws.mobile.samples.mynotes;


import android.accounts.AccountAuthenticatorResponse;
import android.accounts.AccountManager;
import android.accounts.NetworkErrorException;
import android.app.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.amazonaws.mobile.auth.ui.SignInUI;
import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobile.client.AWSStartupHandler;
import com.amazonaws.mobile.client.AWSStartupResult;

/**
 * Created by Genius on 24.02.2018.
 */

public class AuthenticatorActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authenticator);

        AWSMobileClient.getInstance().initialize(this, new AWSStartupHandler() {
            @Override
            public void onComplete(AWSStartupResult awsStartupResult) {
                SignInUI signin = (SignInUI) AWSMobileClient.getInstance().getClient(AuthenticatorActivity.this, SignInUI.class);
                signin.login(AuthenticatorActivity.this, NoteListActivity.class).execute();
            }
        }).execute();
    }

//    http://blog.udinic.com/2013/04/24/write-your-own-android-authenticator/
//    @Override
//    public Bundle addAccount(AccountAuthenticatorResponse response, String accountType, String authTokenType, String[] requiredFeatures, Bundle options) throws NetworkErrorException {
//        final Intent intent = new Intent(mContext, AuthenticatorActivity.class);
//        intent.putExtra(AuthenticatorActivity.ARG_ACCOUNT_TYPE, accountType);
//        intent.putExtra(AuthenticatorActivity.ARG_AUTH_TYPE, authTokenType);
//        intent.putExtra(AuthenticatorActivity.ARG_IS_ADDING_NEW_ACCOUNT, true);
//        intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response);
//        final Bundle bundle = new Bundle();
//        bundle.putParcelable(AccountManager.KEY_INTENT, intent);
//        return bundle;
//    }
//
//
//    @Override
//    public Bundle getAuthToken(AccountAuthenticatorResponse response, Account account, String authTokenType, Bundle options) throws NetworkErrorException {
//
//        // Extract the username and password from the Account Manager, and ask
//        // the server for an appropriate AuthToken.
//        final AccountManager am = AccountManager.get(mContext);
//
//        String authToken = am.peekAuthToken(account, authTokenType);
//
//        // Lets give another try to authenticate the user
//        if (TextUtils.isEmpty(authToken)) {
//            final String password = am.getPassword(account);
//            if (password != null) {
//                authToken = sServerAuthenticate.userSignIn(account.name, password, authTokenType);
//            }
//        }
//
//        // If we get an authToken - we return it
//        if (!TextUtils.isEmpty(authToken)) {
//            final Bundle result = new Bundle();
//            result.putString(AccountManager.KEY_ACCOUNT_NAME, account.name);
//            result.putString(AccountManager.KEY_ACCOUNT_TYPE, account.type);
//            result.putString(AccountManager.KEY_AUTHTOKEN, authToken);
//            return result;
//        }
//
//        // If we get here, then we couldn't access the user's password - so we
//        // need to re-prompt them for their credentials. We do that by creating
//        // an intent to display our AuthenticatorActivity.
//        final Intent intent = new Intent(mContext, AuthenticatorActivity.class);
//        intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response);
//        intent.putExtra(AuthenticatorActivity.ARG_ACCOUNT_TYPE, account.type);
//        intent.putExtra(AuthenticatorActivity.ARG_AUTH_TYPE, authTokenType);
//        final Bundle bundle = new Bundle();
//        bundle.putParcelable(AccountManager.KEY_INTENT, intent);
//        return bundle;
//    }
}