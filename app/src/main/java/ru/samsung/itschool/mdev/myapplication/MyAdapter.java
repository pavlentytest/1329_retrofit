package ru.samsung.itschool.mdev.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewRow> {

    private ArrayList<Anekdot> posts;

    public MyAdapter(ArrayList<Anekdot> posts) {
        this.posts = posts;
    }

    @NonNull
    @Override
    public ViewRow onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row,parent,false);
        return new ViewRow(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewRow holder, int position) {
        Anekdot anekdot = posts.get(position);
        holder.post.setText(anekdot.getElementPureHtml());
    }

    @Override
    public int getItemCount() {
        if(posts == null)
            return 0;
        else
            return posts.size();
    }

    static class ViewRow extends RecyclerView.ViewHolder {
        TextView post;
        public ViewRow(@NonNull View itemView) {
            super(itemView);
            post = itemView.findViewById(R.id.textView);
        }
    }
}
