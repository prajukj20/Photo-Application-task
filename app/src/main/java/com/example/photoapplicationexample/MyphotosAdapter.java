package com.example.photoapplicationexample;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MyphotosAdapter extends RecyclerView.Adapter<MyphotosAdapter.Myviewholder> {

    Context context;
    ArrayList<photos> list;
/*
    itemClickListener listenToClick;
*/

    public MyphotosAdapter(Context context, ArrayList<photos> list, itemClickListener listenToClick) {
        this.context = context;
        this.list = list;
//        this.listenToClick = listenToClick;
    }

    @NonNull
    @Override
    public MyphotosAdapter.Myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_list,parent,false);
        MyphotosAdapter.Myviewholder holder = new MyphotosAdapter.Myviewholder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyphotosAdapter.Myviewholder holder, @SuppressLint("RecyclerView") int position) {

        Glide.with(context).load(list.get(position).getFirstPic()).into(holder.Pic);

         holder.Name.setText(list.get(position).getFolderName());
         holder.Size.setText((list.get(position).getNumberOfPics()-1)+"");

        holder.Pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MainActivity activity = (MainActivity) context;
                FragmentTransaction transaction =activity.getSupportFragmentManager().beginTransaction();
                Fragment fragment = new allphotolist_data();
                Bundle bundle = new Bundle();
                bundle.putString("folderPath",list.get(position).getPath());
                bundle.putString("folderName",list.get(position).getFolderName());
                fragment.setArguments(bundle);
                transaction.add(R.id.frame,fragment);
                transaction.commit();
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Myviewholder extends RecyclerView.ViewHolder{
        ImageView Pic;
        TextView Name;
        TextView Size;

        CardView Card;

        public Myviewholder(@NonNull View itemView) {
            super(itemView);
            Pic = itemView.findViewById(R.id.folderPic);
            Name = itemView.findViewById(R.id.folderName);
            Size=itemView.findViewById(R.id.folder_size);
            Card = itemView.findViewById(R.id.folderCard);

        }
    }
}
