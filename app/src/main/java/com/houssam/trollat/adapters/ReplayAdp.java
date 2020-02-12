package com.houssam.trollat.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.houssam.trollat.MainActivity;
import com.houssam.trollat.R;
import com.houssam.trollat.model.Comment;
import com.houssam.trollat.model.Replay;
import com.houssam.trollat.view.myTimage;

import java.util.ArrayList;

public class ReplayAdp extends RecyclerView.Adapter<ReplayAdp.bodiholder> implements Filterable {

        private ArrayList<Replay> posts ;
        private static int vn=0;
        private Context context;
        public int positionOfComment;

        public ReplayAdp(Context context, ArrayList<Replay> posts,int position){
            this.context=context;
            this.posts=posts;
            this.positionOfComment=position;
            //dateFormat =new  SimpleDateFormat("EEEE d MMM yyyy", new Locale("ar","ma"));
        }


        public void setlist(ArrayList<Replay> postse){
            this.posts=postse;
            notifyDataSetChanged();
        }



    @Override
    public bodiholder onCreateViewHolder( ViewGroup parent, int viewType) {
        return new bodiholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.reply_item,parent,false));
    }

        @Override
        public void onBindViewHolder(final bodiholder holder, int position){
            holder.name.setText(posts.get(position).getName());
            holder.body.setText(posts.get(position).getBody());
            //holder.date.setText(posts.get(position).getDate());
            holder.like.setText(context.getString(R.string.like,posts.get(position).getNblike()));


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
                    ((MainActivity) context).editText.requestFocus();

                    InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
                    // imm.hideSoftInputFromWindow(search.getWindowToken(), 0);
                    ((MainActivity) context).controler.position =positionOfComment;
                    ((MainActivity) context).controler.barere.setVisibility(View.VISIBLE);
                    // ((MainActivity) context).editText.setText(context.getString(R.string.replayto,holder.name.getText().toString()));
                    ((MainActivity) context).controler.textbarere.setText(context.getString(R.string.replayto,holder.name.getText().toString()));

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


        class  bodiholder extends RecyclerView.ViewHolder{
            TextView name,body,date,like,repo;
            myTimage timage;
            bodiholder(View itemView) {
                super(itemView);
                name=itemView.findViewById(R.id.text1);
                body=itemView.findViewById(R.id.text2);
                date=itemView.findViewById(R.id.text3);
                like=itemView.findViewById(R.id.text4);
                repo=itemView.findViewById(R.id.text5);
                timage=itemView.findViewById(R.id.image1);
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


