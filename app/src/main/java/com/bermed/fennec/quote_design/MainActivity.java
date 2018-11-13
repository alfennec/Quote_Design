package com.bermed.fennec.quote_design;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.io.IOException;
import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;
import jp.wasabeef.blurry.Blurry;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView iv = (ImageView) findViewById(R.id.imageView);

        CircleImageView cv = (CircleImageView) findViewById(R.id.profile_image);





        try {
            // get input stream
            InputStream ims = getAssets().open("authors/Bill Gates.jpg");
            // load image as Drawable
            Drawable d = Drawable.createFromStream(ims, null);
            // set image to ImageView
            iv.setImageDrawable(d);


            cv.setImageDrawable(d);

            final int semiTransparentGrey = Color.argb(80, 255, 0, 0);

            cv.setColorFilter(semiTransparentGrey, PorterDuff.Mode.SRC_ATOP);
        }
        catch(IOException ex) {
            return;
        }


        Thread thread = new Thread() {
            @Override
            public void run() {
                boolean a = true;
                try {
                    while(a) {
                        sleep(500);
                        Blurry.with(MainActivity.this)
                                .radius(25)
                                .sampling(1)
                                .color(Color.argb(150, 0, 0, 0))
                                .async()
                                .capture(findViewById(R.id.imageView))
                                .into((ImageView) findViewById(R.id.imageView));

                        a = false;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        thread.start();


        findViewById(R.id.button).setOnClickListener(new View.OnClickListener()
        {
            @Override public void onClick(View v)
            {

                //long startMs = System.currentTimeMillis();


            }
        });


    }
}
