package com.accountassistant;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class AuthenticatorService extends Service {

    private MyAuthenticator authenticator;

    @Override
    public IBinder onBind(Intent intent) {

        if (authenticator == null) {

            authenticator = new MyAuthenticator(this);
        }

        return authenticator.getIBinder();
    }
}
