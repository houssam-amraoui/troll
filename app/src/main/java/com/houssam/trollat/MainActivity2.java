package com.houssam.trollat;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.firebase.jobdispatcher.Constraint;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.RetryStrategy;
import com.firebase.jobdispatcher.Trigger;
import com.houssam.trollat.broadcast.NetworkChangeReceiver;
import com.houssam.trollat.servises.mJobServis;
import com.houssam.trollat.servises.mJobServis1;

public class MainActivity2 extends AppCompatActivity {
        String TAG= "mtag";
        FirebaseJobDispatcher dispatcher ;
        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testservis);

        dispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(this));
        //Log.d("hahaha","oncreate");






    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    public void scheduleJob(View v) {
        Toast.makeText(this, "hhhhhhh", Toast.LENGTH_SHORT).show();
        //resiver
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        filter.addAction(Intent.ACTION_BOOT_COMPLETED);
        filter.setPriority(100);
        this.registerReceiver(new NetworkChangeReceiver(), filter);


//job schiduler seul
        ComponentName name = new ComponentName(this, mJobServis.class);
        JobInfo info = new JobInfo.Builder(123, name)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                .setPersisted(true)
                .setPeriodic(15*60 * 1000).build();

        JobScheduler scheduler =    (JobScheduler)getSystemService(JOB_SCHEDULER_SERVICE);
        int response = scheduler.schedule(info);

        startjob();
    }


    public void cancelJob(View v) {
       /* JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        scheduler.cancel(123);
        Log.d(TAG, "Job cancelled");*/
        dispatcher.cancelAll();
    }


    void startjob(){
// job dispatcher

        FirebaseJobDispatcher  dispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(this));

        Job myJob = dispatcher.newJobBuilder()

                .setService(mJobServis1.class)
                .setLifetime(Lifetime.FOREVER)
                .setRecurring(true)
                .setTag("my-unique-tag")
                .setTrigger(Trigger.executionWindow(0, 6*60))
                .setRetryStrategy(RetryStrategy.DEFAULT_EXPONENTIAL)
                .setReplaceCurrent(false)
                .setConstraints(Constraint.ON_ANY_NETWORK)
                .build();

        //dispatcher.mustSchedule(myJob);
        dispatcher.schedule(myJob);
    }
   /* void sssss()
    {
        ComponentName componentName = new ComponentName(this, mJobServis.class);
        JobInfo info = new JobInfo.Builder(123, componentName)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                .setPersisted(true)
                .setPeriodic(15*60 * 1000)
                .build();

        JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        int resultCode = scheduler.schedule(info);
        if (resultCode == JobScheduler.RESULT_SUCCESS) {
            Log.d(TAG, "Job scheduled");
        } else {
            Log.d(TAG, "Job scheduling failed");
        }
    }*/




}
