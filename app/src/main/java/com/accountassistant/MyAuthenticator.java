package com.accountassistant;

import android.accounts.*;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class MyAuthenticator extends AbstractAccountAuthenticator {

    private final Context context;

    public MyAuthenticator(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public Bundle addAccount(
            AccountAuthenticatorResponse response,
            String accountType,
            String authTokenType,
            String[] requiredFeatures,
            Bundle options
    ) {
        Bundle bundle = new Bundle();

        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response);
        intent.putExtra("mode", "add_account");

        bundle.putParcelable(AccountManager.KEY_INTENT, intent);
        return bundle;
    }

    @Override public Bundle editProperties(AccountAuthenticatorResponse response, String accountType) { return null; }
    @Override public Bundle confirmCredentials(AccountAuthenticatorResponse response, Account account, Bundle options) { return null; }
    @Override public Bundle getAuthToken(AccountAuthenticatorResponse response, Account account, String authTokenType, Bundle options) { return null; }
    @Override public String getAuthTokenLabel(String authTokenType) { return null; }
    @Override public Bundle updateCredentials(AccountAuthenticatorResponse response, Account account, String authTokenType, Bundle options) { return null; }
    @Override public Bundle hasFeatures(AccountAuthenticatorResponse response, Account account, String[] features) { return null; }
}