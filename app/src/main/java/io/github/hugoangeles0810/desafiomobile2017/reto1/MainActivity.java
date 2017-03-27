package io.github.hugoangeles0810.desafiomobile2017.reto1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import io.github.hugoangeles0810.desafiomobile2017.reto1.imageloader.ImageLoader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView = (ImageView) findViewById(R.id.imageview_main);
        ImageLoader.with(imageView).load("http://www.belatrixsf.com/img/Belatrix.png");
    }
}
