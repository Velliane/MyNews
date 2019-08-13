package com.menard.mynews;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Switch;

import androidx.test.annotation.UiThreadTest;
import androidx.test.platform.app.InstrumentationRegistry;

import com.menard.mynews.controller.activities.NotificationActivity;
import com.menard.mynews.utils.Constants;

import org.junit.Before;
import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4ClassRunner.class)
public class NotificationActivityAndroidTest {

//    private SharedPreferences mSharedPreferences;
//    private NotificationActivity mNotificationActivity;
//
//    @Before
//    public void init(){
//        InstrumentationRegistry.getInstrumentation().runOnMainSync(new Runnable() {
//            @Override
//            public void run() {
//                mSharedPreferences = InstrumentationRegistry.getInstrumentation().getTargetContext().getSharedPreferences(Constants.SHARED_PREFERENCE, Context.MODE_PRIVATE);
//                mNotificationActivity = new NotificationActivity();
//            }
//        });
//
//    }
//
//    @UiThreadTest
//    @Test
//    public void ifNotificationsEnabledSetSwitchChecked(){
//        onView(withId(R.id.activity_notification_switch)).check(matches(isChecked()));
//
//        assertTrue(mSharedPreferences.getBoolean(Constants.PREFS_NOTIFICATION, false));
//    }
}
