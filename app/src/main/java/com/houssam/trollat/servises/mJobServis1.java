package com.houssam.trollat.servises;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;
import com.houssam.trollat.mFile;

public class mJobServis1 extends JobService {
    @Override
    public boolean onStartJob(@NonNull JobParameters job) {

        mFile.save("job dispatcher seule",mFile.isconnect(this)+" job dispatcher :");

         jobFinished(job,false);
        return true;
    }

    @Override
    public boolean onStopJob(@NonNull JobParameters job) {

        return false;
    }

}
