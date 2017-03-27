package io.github.hugoangeles0810.desafiomobile2017.reto1.imageloader;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.widget.ImageView;

/**
 * Created by hugo on 27/03/17.
 */

public class ImageLoader {

    private ImageView mImageView;

    private ImageLoader(@NonNull  ImageView imageView) {
        if (imageView == null) {
            throw new NullPointerException("imageView cannot be null");
        }
        mImageView = imageView;
    }

    public static ImageLoader with(ImageView imageView) {
        return new ImageLoader(imageView);
    }

    public  void load(String url) {
        Injection.provideImageDownloader().download(url, new ImageDownloader.OnDownloadImage() {
            @Override
            public void onComplete(final Bitmap bitmap) {
                mImageView.post(new Runnable() {
                    @Override
                    public void run() {
                        mImageView.setImageBitmap(bitmap);
                    }
                });
            }

            @Override
            public void onError(Throwable e) {
                // TO DO: Handle error
            }
        });
    }

}
