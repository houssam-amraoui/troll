package com.houssam.trollat.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.VectorDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.houssam.trollat.R;

public class myTimage extends FrameLayout {
    View view;
    ImageView imageView;
    int dfimage;

    public myTimage(@NonNull Context context) {
        super(context);
        iniview();
    }




    public myTimage(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        iniview();
    }
    public myTimage(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        iniview();
    }

    private void iniview(){

        view=new View(getContext());
         imageView =new ImageView(getContext());
         view.setBackgroundResource(R.drawable.mysi);
         addView(imageView);
         addView(view);

    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        LayoutParams params =new LayoutParams(getWidth(),getHeight(), Gravity.CENTER);
        imageView.setLayoutParams(params);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);

    }

    public void setImageView(Bitmap bm) {
        this.imageView.setImageBitmap(bm);

    }
    public void setForgraond(int rs){
        view.setBackgroundResource(rs);
    }


}
