package io.github.hugoangeles0810.desafiomobile2017.reto1.imageloader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by hugo on 27/03/17.
 */

public class ThreadImageDownloader implements ImageDownloader {

    public static final String TAG = ThreadImageDownloader.class.getSimpleName();

    public static final int BUFFER_SIZE = 4096;

    @Override
    public void download(final String imageUrl, final OnDownloadImage onDownloadImage) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                InputStream is = null;
                ByteArrayOutputStream out = null;
                try {
                    connection = (HttpURLConnection) new URL(imageUrl).openConnection();
                    connection.connect();
                    int length = connection.getContentLength();
                    if (length <= 0) {
                        connection.disconnect();
                        onDownloadImage.onError(new FileNotFoundException("no image file found"));
                        return;
                    }

                    is = new BufferedInputStream(connection.getInputStream(), BUFFER_SIZE);
                    out = new ByteArrayOutputStream();

                    byte bytes[] = new byte[BUFFER_SIZE];
                    int count;
                    while ((count = is.read(bytes)) != -1) {
                        out.write(bytes, 0, count);
                    }
                    final Bitmap bitmap = BitmapFactory.decodeByteArray(out.toByteArray(), 0, out.size());
                    onDownloadImage.onComplete(bitmap);
                } catch (Throwable e) {
                    Log.e(TAG, "Error", e);
                    onDownloadImage.onError(e);
                } finally {
                    try {
                        if (connection != null) connection.disconnect();
                        if (out != null) {
                            out.flush();
                            out.close();
                        }
                        if (is != null) {
                            is.close();
                        }
                    } catch (IOException e) {
                        Log.e(TAG, "Error", e);
                    }
                }
            }
        }).start();
    }
}
