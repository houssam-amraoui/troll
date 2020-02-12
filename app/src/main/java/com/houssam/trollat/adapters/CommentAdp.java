package com.houssam.trollat.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.houssam.trollat.MainActivity;
import com.houssam.trollat.R;
import com.houssam.trollat.model.Comment;
import java.util.ArrayList;
import java.util.List;

import com.houssam.trollat.model.Replay;
import com.houssam.trollat.view.myTimage;



public class CommentAdp extends RecyclerView.Adapter<CommentAdp.bodiholder> implements Filterable {

        private ArrayList<Comment> posts ;
        private static int vn=0;
        private Context context;

        public ArrayList<bodiholder> bodih=new ArrayList<bodiholder>();


        public   CommentAdp(Context context, ArrayList<Comment> posts){
            this.context=context;
            this.posts=posts;
            //dateFormat =new  SimpleDateFormat("EEEE d MMM yyyy", new Locale("ar","ma"));
        }
        public void setlist(ArrayList<Comment> postse){
            this.posts=postse;
            notifyDataSetChanged();
        }
        @Override
        public bodiholder onCreateViewHolder( ViewGroup parent, int viewType) {
            bodiholder bodi=new bodiholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_item,parent,false));
            if (!bodih.contains(bodi))
            bodih.add(bodi);
            return bodi;
        }

    @Override
    public void onBindViewHolder(@NonNull bodiholder holder, int position, @NonNull List<Object> payloads) {
            ArrayList<Object> ss = new  ArrayList<Object>(payloads);
            int i= ss.size();
        super.onBindViewHolder(holder, position, payloads);
    }

    @Override
    public void onViewAttachedToWindow(@NonNull bodiholder holder) {
        super.onViewAttachedToWindow(holder);
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull bodiholder holder) {
        super.onViewDetachedFromWindow(holder);
    }

    @Override
        public void onBindViewHolder(final bodiholder holder, int position){
            holder.name.setText(posts.get(position).getName());
            holder.body.setText(posts.get(position).getBody());
            //holder.date.setText(posts.get(position).getDate());
            holder.like.setText(context.getString(R.string.like,posts.get(position).getNblike()));
            holder.voirrepo.setText(context.getString(R.string.voirrepo,posts.get(position).getReplays().size()));

            holder.like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    posts.get(holder.getAdapterPosition()).setNblike(posts.get(holder.getAdapterPosition()).getNblike()+1);
                    notifyDataSetChanged();
                }
            });
            holder.repo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // focuss text fild
                    ((MainActivity) context).editText.requestFocus();
                     InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
                    // imm.hideSoftInputFromWindow(search.getWindowToken(), 0);

                    ((MainActivity) context).controler.position =holder.getAdapterPosition();

                    ((MainActivity) context).controler.barere.setVisibility(View.VISIBLE);
                   // ((MainActivity) context).editText.setText(context.getString(R.string.replayto,holder.name.getText().toString()));
                    ((MainActivity) context).controler.textbarere.setText(context.getString(R.string.replayto,holder.name.getText().toString()));

                }
            });
            holder.voirrepo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(holder.recycler.getAdapter()==null){
                    RecyclerView.LayoutManager rr=new LinearLayoutManager(context);
                    holder.recycler.setLayoutManager(rr);
                    ReplayAdp adp = new ReplayAdp(context,posts.get(holder.getAdapterPosition()).getReplays(),holder.getAdapterPosition());
                    holder.recycler.setAdapter(adp);
                    }

                }
            });

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                int position=holder.getAdapterPosition();
                @Override
                public void onClick(View view) {

                }
            });


        }
   /* public void removeAt(int position) {
        mDataset.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mDataSet.size());
    }*/


        @Override
        public int getItemCount() {
            return posts.size();
        }


      public class  bodiholder extends RecyclerView.ViewHolder{
            TextView name,body,date,like,repo,voirrepo;
            myTimage timage;
            RecyclerView recycler;


            bodiholder(View itemView) {
                super(itemView);
                name=itemView.findViewById(R.id.text1);
                body=itemView.findViewById(R.id.text2);
                date=itemView.findViewById(R.id.text3);
                like=itemView.findViewById(R.id.text4);
                repo=itemView.findViewById(R.id.text5);
                voirrepo=itemView.findViewById(R.id.text6);
                timage=itemView.findViewById(R.id.image1);
                recycler =itemView.findViewById(R.id.recy);

            }
        }


        @Override
        public Filter getFilter() {
            return new Filter() {
                //  seach in bagraond and call publish resulr
                @Override
                protected FilterResults performFiltering(CharSequence constraint) {
                    return null;
                }

                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {

                }
            };
        }
    }

