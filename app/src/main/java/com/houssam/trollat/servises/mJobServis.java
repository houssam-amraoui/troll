package com.houssam.trollat.servises;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;

import android.util.Log;
import android.widget.Toast;

import com.houssam.trollat.mFile;

public class mJobServis  extends JobService {
    private static final String TAG = "ExampleJobService";
    private boolean jobCancelled = false;

    @Override
    public boolean onStartJob(JobParameters params) {
        mFile.save("job schiduler",mFile.isconnect(this)+" job servis seule :");

       // doBackgroundWork(params,this);

        jobFinished(params,false);

        return true;
    }

    private void doBackgroundWork(final JobParameters params,  Context context) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    Log.d(TAG, "run: " + i);

                    if (jobCancelled) {
                        break;
                    }

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                Log.d(TAG, "Job finished");
                jobFinished(params, true);
            }
        }).start();
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.d(TAG, "Job cancelled before completion");
        jobCancelled = true;
        return true;
    }

}