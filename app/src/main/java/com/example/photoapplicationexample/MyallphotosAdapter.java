package com.example.photoapplicationexample;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.ArrayList;

public class MyallphotosAdapter extends  RecyclerView.Adapter<MyallphotosAdapter.Myviewholder>{
    Context context;
    ArrayList <pictureFacer>list1;

    public MyallphotosAdapter(ArrayList<pictureFacer> list, Context context) {
        this.list1 = list;
        this.context= context;
    }


    @NonNull
    @Override
    public MyallphotosAdapter.Myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.picture_browser,parent,false);
        MyallphotosAdapter.Myviewholder holder = new MyallphotosAdapter.Myviewholder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyallphotosAdapter.Myviewholder holder, @SuppressLint("RecyclerView") int position) {
        File file = new File(list1.get(position).getPicturePath());
        Glide.with(context).load(file).into(holder.picture);
//        holder.picture.setImageURI(Uri.fromFile(file));
        holder.picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MainActivity activity = (MainActivity) context;
                FragmentTransaction transaction =activity.getSupportFragmentManager().beginTransaction();
                Fragment fragment = new photoslist();
                Bundle bundle = new Bundle();
                bundle.putString("imagePath",list1.get(position).getPicturePath());
                fragment.setArguments(bundle);
                transaction.add(R.id.frame,fragment);
                transaction.commit();

            }
        });



    }

    @Override
    public int getItemCount() {
        return list1.size();
    }

    public class Myviewholder extends RecyclerView.ViewHolder{

        ImageView picture;
        public Myviewholder(@NonNull View itemView) {
            super(itemView);

            picture = itemView.findViewById(R.id.image);



        }
    }
}
