package com.mralonso.android.presentation.utils;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class ImageUtils {

    public static void setImage(Context context, String url, ImageView imageView){
        if(imageView==null || url==null) return;

        Picasso.with(context).load(url).into(imageView);
    }
}
