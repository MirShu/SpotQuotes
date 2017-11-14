package com.quantpower.spotquotes.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.quantpower.spotquotes.R;

/**
 * Created by ShuLin on 2017/6/3.
 * Email linlin.1016@qq.com
 * Company Shanghai Quantpower Information Technology Co.,Ltd.
 */

public class ServiceActivtiy extends Activity {
    private ImageView imageBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        this.imageBack = (ImageView) findViewById(R.id.image_back);
        this.imageBack.setOnClickListener(v -> finish());
    }
}
