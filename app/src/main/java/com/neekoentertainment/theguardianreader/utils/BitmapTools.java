package com.neekoentertainment.theguardianreader.utils;

import android.content.Context;
import android.widget.ImageView;

import com.koushikdutta.ion.Ion;
import com.neekoentertainment.theguardianreader.R;

/**
 * Created by Nicolas on 7/30/2016.
 */
public class BitmapTools {

    public static void loadBitmapIntoImageView(Context context, String url, ImageView imageView) {
        Ion.with(context)
                .load(url)
                .withBitmap()
                .placeholder(R.drawable.ic_loop_black)
                .error(R.drawable.ic_broken_image_black)
                .intoImageView(imageView);
    }
}
