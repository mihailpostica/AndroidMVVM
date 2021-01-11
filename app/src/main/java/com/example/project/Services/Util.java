package com.example.project.Services;
import android.content.Context;
import android.widget.ImageView;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.project.R;

public class Util {

    public static void loadImage(ImageView view, String url, CircularProgressDrawable progressDrawable) {
        RequestOptions options = new RequestOptions()
                .placeholder(progressDrawable)
                .error(R.mipmap.ic_launcher_round);

        Glide.with(view.getContext())
                .setDefaultRequestOptions(options)
                .load(url)
                .into(view);
    }

    public static CircularProgressDrawable getProgressDrawable(Context context) {
        //a circular loading spinner should be shown in the imgView before the actual data image is fetched
        CircularProgressDrawable progressDrawable = new CircularProgressDrawable(context);
        progressDrawable.setStrokeWidth(10f);
        progressDrawable.setCenterRadius(50f);
        progressDrawable.start();
        return progressDrawable;
    }
}

