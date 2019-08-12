package com.menard.mynews;

import android.app.Application;
import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.menard.mynews.utils.Constants;
import com.menard.mynews.utils.NotififyWorker;

//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//
//import static org.hamcrest.CoreMatchers.equalTo;
//import static org.hamcrest.CoreMatchers.is;
//import static org.junit.Assert.assertThat;
//import static org.mockito.Mockito.when;

public class NotifyWorkerAndroidTest {

//    @Mock
//    WorkerParameters mWorkerParameters;
//    Context mContext;
//    Data mData;
//    NotififyWorker mNotififyWorker;
//
//
//    @Before
//    public void init(){
//        MockitoAnnotations.initMocks(this);
//        mWorkerParameters = Mockito.mock(WorkerParameters.class);
//        mContext = Mockito.mock(Context.class);
//
//        mData = new Data.Builder().putString(Constants.EXTRA_TITLE, Constants.TITLE)
//                .putString(Constants.EXTRA_TEXT,Constants.TEXT)
//                .putInt(Constants.EXTRA_ID, 1)
//                .putString(Constants.EXTRA_KEYWORD, "Asia")
//                .putString(Constants.EXTRA_SECTION, "Travel")
//                .build();
//        when(mWorkerParameters.getInputData()).thenReturn(mData);
//
//        mNotififyWorker = new NotififyWorker(mContext, mWorkerParameters);
//    }
//
//    @Test
//    public void testDoWorkSuccess(){
//        Worker.Result result = mNotififyWorker.doWork();
//
//        assertThat(result, is(equalTo(Worker.Result.success())));
//    }

}
