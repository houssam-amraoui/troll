package com.houssam.trollat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.content.Context;
import android.graphics.Bitmap.Config;
import android.graphics.BlurMaskFilter.Blur;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;

import com.houssam.trollat.adapters.CommentAdp;
import com.houssam.trollat.model.Comment;
import com.houssam.trollat.model.ControlerOfReplay;
import com.houssam.trollat.model.Replay;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ArrayList<Comment> list=new ArrayList<>();
    ArrayList<Replay> reply=new ArrayList<>();
    RecyclerView.LayoutManager ll;
    RecyclerView recyclerView;
    CommentAdp adp;
    public EditText editText;
    Button button;
    public ControlerOfReplay controler;
    public ImageView cancelReplay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comment_list);
        initdata();

        controler =new ControlerOfReplay();
        controler.barere=findViewById(R.id.barere);
        controler.textbarere=findViewById(R.id.textbarere);
        cancelReplay=findViewById(R.id.imgcancel);
        cancelReplay.setOnClickListener(this);
        ll=new LinearLayoutManager(this);

        adp=new CommentAdp(this,list);

        recyclerView=findViewById(R.id.recycler);
        editText=findViewById(R.id.input_msg);
        button=findViewById(R.id.send_msg);
        button.setOnClickListener(this);


        recyclerView.setLayoutManager(ll);
        recyclerView.setAdapter(adp);
        recyclerView.scrollToPosition(adp.getItemCount()-1);

    }


    private static Bitmap rotateImage(Bitmap img, int degree) {
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        Bitmap rotatedImg = Bitmap.createBitmap(img, 0, 0, img.getWidth(), img.getHeight(), matrix, true);
        img.recycle();
        return rotatedImg;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imgcancel:
                controler.barere.setVisibility(View.GONE);
                controler.position=-1;
                break;
            case R.id.send_msg:
                if (controler.position==-1){
                    list.add(new Comment("https://image.com","houssam amraoui",editText.getText().toString(),new Date(),8,new ArrayList<Replay>()));
                    adp.notifyDataSetChanged();
                    editText.clearFocus();
                    editText.setText("");
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);//imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
                    imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                    recyclerView.scrollToPosition(list.size()-1);
                }else {

                    list.get(controler.position).getReplays().add(new Replay("https://image.com","houssam amraoui",editText.getText().toString(),new Date(),8));
                   // adp.notifyItemChanged(controler.position);
                    //adp.notifyItemInserted(controler.position);
                    editText.clearFocus();
                    editText.setText("");
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);//imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
                    imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                    recyclerView.scrollToPosition(list.size()-1);
                    int ii=adp.bodih.get(controler.position).getAdapterPosition();


                    controler.barere.setVisibility(View.GONE);
                    controler.position=-1;
                }
                break;
        }

    }

    class TouchView extends View{
        Bitmap bgr;
        Bitmap overlayDefault;
        Bitmap overlay;
        Paint pTouch;
        int X = -100;
        int Y = -100;
        Canvas c2;

        public TouchView(Context context) {
            super(context);

            bgr = BitmapFactory.decodeResource(getResources(),R.drawable.ttt);
            overlayDefault = BitmapFactory.decodeResource(getResources(),R.drawable.hugh);
            overlay = BitmapFactory.decodeResource(getResources(),R.drawable.hugh).copy(Config.ARGB_8888, true);
            c2 = new Canvas(overlay);

            pTouch = new Paint(Paint.ANTI_ALIAS_FLAG);
            pTouch.setXfermode(new PorterDuffXfermode(Mode.SRC_OUT));
            pTouch.setColor(Color.TRANSPARENT);
            pTouch.setMaskFilter(new BlurMaskFilter(15, Blur.NORMAL));


        }

        @Override
        public boolean onTouchEvent(MotionEvent ev) {

            switch (ev.getAction()) {

                case MotionEvent.ACTION_DOWN: {

                    X = (int) ev.getX();
                    Y = (int) ev.getY();
                    invalidate();

                    break;
                }

                case MotionEvent.ACTION_MOVE: {

                    X = (int) ev.getX();
                    Y = (int) ev.getY();
                    invalidate();
                    break;

                }

                case MotionEvent.ACTION_UP:

                    break;

            }
            return true;
        }


        @Override
        public void onDraw(Canvas canvas){
            super.onDraw(canvas);

            //draw background
            canvas.drawBitmap(bgr, 0, 0, null);
            //copy the default overlay into temporary overlay and punch a hole in it
            c2.drawBitmap(overlayDefault, 0, 0, null); //exclude this line to show all as you draw
            c2.drawCircle(X, Y, 80, pTouch);
            //draw the overlay over the background
            canvas.drawBitmap(overlay, 0, 0, null);

        }


    }


    public void initdata(){
        reply.add(new Replay("https://image.com","houssam amraoui","hello im sory about that this is replay",new Date(),8));
        reply.add(new Replay("https://image.com","houssam amraoui","hello im sory about that this is replay",new Date(),8));
        reply.add(new Replay("https://image.com","houssam amraoui","hello im sory about that this is replay",new Date(),8));

        list.add(new Comment("https://image.com","houssam amraoui","hello im sory about that this is comment",new Date(),4,reply));
        list.add(new Comment("https://image.com","houssam amraoui","hey im sory about that this is comment",new Date(),4,reply));
        list.add(new Comment("https://image.com","houssam amraoui","hello this is comment",new Date(),10,reply));
        list.add(new Comment("https://image.com","houssam amraoui","hey this is comment",new Date(),4,reply));
        list.add(new Comment("https://image.com","houssam amraoui","hi im sory about that this is comment",new Date(),4,reply));
        list.add(new Comment("https://image.com","houssam amraoui","hello im sory about that this is comment",new Date(),4,reply));

    }
}
