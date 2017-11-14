package com.quantpower.spotquotes.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.quantpower.spotquotes.R;
import com.quantpower.spotquotes.utils.UIHelper;

/**
 * Created by ShuLin on 2017/6/3.
 * Email linlin.1016@qq.com
 * Company Shanghai Quantpower Information Technology Co.,Ltd.
 */

public class WealActivtiy extends Activity {
    private WebView wb_invite_process;
    private String the_hang_seng_index_url = "file:///android_asset/bg_weal.png";
    private WebSettings settings;
    private TextView tvParticipation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_weal);
        this.wb_invite_process = (WebView) findViewById(R.id.wb_invite_process);
        this.wb_invite_process.loadUrl(the_hang_seng_index_url);
        ImageView imageBack = (ImageView) findViewById(R.id.image_back);
        imageBack.setOnClickListener(v -> finish());
        this.tvParticipation = (TextView) findViewById(R.id.tv_participation);
        this.tvParticipation.setOnClickListener(v -> UIHelper.toastMessage(WealActivtiy.this, "领取成功"));
        this.settings = wb_invite_process.getSettings();
        this.settings.setUseWideViewPort(true);
        this.settings.setLoadWithOverviewMode(true);
    }
}
