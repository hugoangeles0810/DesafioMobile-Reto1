package io.github.hugoangeles0810.desafiomobile2017.reto1.imageloader;

import android.graphics.Bitmap;

/**
 * Created by hugo on 27/03/17.
 */

public interface ImageDownloader {

    interface OnDownloadImage {
        void onComplete(Bitmap bitmap);
        void onError(Throwable e);
    }

    void download(String imageUrl, OnDownloadImage onDownloadImage);
}
