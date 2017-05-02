package com.mralonso.android.presentation.activities;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

public abstract class PortraitBaseActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
    }
}
