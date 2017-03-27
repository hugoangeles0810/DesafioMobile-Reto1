package io.github.hugoangeles0810.desafiomobile2017.reto1.imageloader;

/**
 * Created by hugo on 27/03/17.
 */

public class Injection {

    public static ImageDownloader provideImageDownloader() {
        return new ThreadImageDownloader();
    }
}
