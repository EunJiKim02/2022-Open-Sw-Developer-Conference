package com.gaebalsebal.Find_trashbin;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder>
    {
    private List<Mypost> list;
    private OnItemClickListener listener;
    private String postid;

    //생성자
    MyRecyclerAdapter(List<Mypost> list) {

        this.list = list;
    }

    public interface OnItemClickListener{
        void onItemClicked(View v, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private ImageView image;
        private TextView contentusername;



        MyViewHolder(View itemView) {
            super(itemView);
            //image src 설정
            title = itemView.findViewById(R.id.title);
        }

        public TextView getTitle() { return title;}
        public ImageView getImage() {
            return image;
        }
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view, parent, false);


        MyRecyclerAdapter.MyViewHolder viewHolder = new MyViewHolder(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener != null)
                {
                    listener.onItemClicked(view, viewHolder.getBindingAdapterPosition());
                }
                //postid = list.get(viewHolder.getBindingAdapterPosition()).postid;
                System.out.println(list.get(viewHolder.getBindingAdapterPosition()).content);
                //Intent intent = new Intent(CommunityActivity.this, PostActivity.class);
                //intent.putExtra("postdata", postid);
            }
        });
        return viewHolder;
    }



    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //System.out.println(list.get(position).title);
        holder.getTitle().setText(list.get(position).title);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}

