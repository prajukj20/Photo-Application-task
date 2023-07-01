package com.example.photoapplicationexample;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclelist;
    ArrayList<photos> list;
    TextView empty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclelist = findViewById(R.id.recyclerview);
        empty = findViewById(R.id.empty);

        list = new ArrayList<>();

        ArrayList<photos> images = getPicturePaths();

        if (images.isEmpty()) {
            empty.setVisibility(View.VISIBLE);
        } else {
            MyphotosAdapter Adpter = new MyphotosAdapter(MainActivity.this, images,null);
            recyclelist.setLayoutManager(new GridLayoutManager(MainActivity.this, 3));
            recyclelist.setVisibility(View.VISIBLE);
            recyclelist.setAdapter(Adpter);

        }


        if (checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},
                    102);

            // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
            // app-defined int constant


        }

    }



    private ArrayList<photos> getPicturePaths() {
        ArrayList<photos> picFolders = new ArrayList<>();
        ArrayList<String> picPaths = new ArrayList<>();
        Uri allImagesuri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {MediaStore.Images.ImageColumns.DATA, MediaStore.Images.Media.DISPLAY_NAME,
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME, MediaStore.Images.Media.BUCKET_ID};
        Cursor cursor = this.getContentResolver().query(allImagesuri, projection, null, null, null);
        try {
            if (cursor != null) {
                cursor.moveToFirst();
            }
            do {
                photos images = new photos("path","FolderName",1,"firstPic");
                String name = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME));
                String folder = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME));
                String datapath = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA));

                String pathfolder = datapath.substring(0, datapath.lastIndexOf(folder + "/"));
                pathfolder = pathfolder + folder + "/";
                if (!picPaths.contains(pathfolder)) {
                    picPaths.add(pathfolder);

                    images.setPath(pathfolder);
                    images.setFolderName(folder);
                    images.setFirstPic(datapath);
                    images.addpics();
                    picFolders.add(images);
                } else {
                    for (int i = 0; i < picFolders.size(); i++) {
                        if (picFolders.get(i).getPath().equals(pathfolder)) {
                            picFolders.get(i).setFirstPic(datapath);
                            picFolders.get(i).addpics();
                        }
                    }
                }
            } while (cursor.moveToNext());
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < picFolders.size(); i++) {

            Log.e("TAG", picFolders.get(i).getFolderName() + " and path = " + picFolders.get(i).getPath() + " " + picFolders.get(i).getNumberOfPics() );

        }
        return picFolders;
    }

    @Override
    public void onBackPressed() {

        for (Fragment fragment4 : getSupportFragmentManager().getFragments()) {
            if (fragment4 instanceof photoslist) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.remove(fragment4);
                transaction.commit();
                return;
            }



        }

        for (Fragment fragment3 : getSupportFragmentManager().getFragments()) {
            if (fragment3 instanceof allphotolist_data) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.remove(fragment3);
                transaction.commit();
                return;
            }

        }

        android.app.AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Do you want to exit ?");
        builder.setTitle("Alert !");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();






    }




    }





