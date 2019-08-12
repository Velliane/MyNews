package com.menard.mynews;

import android.content.Context;
import android.content.SharedPreferences;

import com.menard.mynews.controller.activities.NotificationActivity;
import com.menard.mynews.utils.Constants;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertTrue;

public class NotificationActivityTest {

    SharedPreferences mSharedPreferences;
    Context mContext;
    NotificationActivity mNotificationActivity;

    @Before
    public void init(){
        mSharedPreferences = Mockito.mock(SharedPreferences.class);
        mContext = Mockito.mock(Context.class);
        mNotificationActivity = Mockito.mock(NotificationActivity.class);
        Mockito.when(mContext.getSharedPreferences(Constants.PREFS_NOTIFICATION, Context.MODE_PRIVATE)).thenReturn(mSharedPreferences);
    }

    @Test
    public void ifNotificationAreDisabledSwitchUnchecked(){
        Mockito.when(mSharedPreferences.getBoolean(Constants.PREFS_NOTIFICATION, false)).thenReturn(true);

        assertTrue(mNotificationActivity.mSwitch.isChecked());
    }
}
