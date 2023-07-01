package com.example.photoapplicationexample;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PicHolder extends RecyclerView.ViewHolder{
    ImageView picture;
    public PicHolder(@NonNull View itemView) {

        super(itemView);
        picture = itemView.findViewById(R.id.image1);

    }
}
