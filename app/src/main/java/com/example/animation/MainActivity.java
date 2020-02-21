package com.example.animation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageView img;
    private Button btn;
    private LinearInterpolator lin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img = findViewById(R.id.iv);
        btn = findViewById(R.id.btn);

        final Animation rotate = AnimationUtils.loadAnimation(this, R.anim.rotate_anim);

        if (rotate != null) {
            lin = new LinearInterpolator();//设置动画匀速运动
            rotate.setInterpolator(lin);
            img.startAnimation(rotate);
        } else {
            lin = new LinearInterpolator();//设置动画匀速运动
            rotate.setInterpolator(lin);
            img.setAnimation(rotate);
            img.startAnimation(rotate);
        }

        initSleep();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img.startAnimation(rotate);
                initSleep();
            }
        });
    }

    private void initSleep() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(3000);//休眠3秒
                    Looper.prepare();
                    Toast.makeText(MainActivity.this, "扫描成功", Toast.LENGTH_SHORT).show();
                    img.clearAnimation();//清空动画并执行其他操作
                    Looper.loop();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
