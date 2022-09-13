package com.gaebalsebal.Find_trashbin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder> {
    private List<Mypost> list;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private ImageView image;

        MyViewHolder(View itemView) {
            super(itemView);
            //image src 설정
            title = itemView.findViewById(R.id.title);
        }

        public TextView getTitle() {

            return title;
        }

        public ImageView getImage() {
            return image;
        }
    }


    MyRecyclerAdapter(List<Mypost> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        System.out.println(list.get(position).title);
        holder.getTitle().setText(list.get(position).title);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
