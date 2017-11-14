package com.quantpower.spotquotes.ui.activity;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.andview.refreshview.XRefreshView;
import com.quantpower.spotquotes.R;
import com.quantpower.spotquotes.adapter.RecyclerAdapter;
import com.quantpower.spotquotes.adapter.RecyclerViewHolder;
import com.quantpower.spotquotes.utils.UIHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ShuLin on 2017/6/3.
 * Email linlin.1016@qq.com
 * Company Shanghai Quantpower Information Technology Co.,Ltd.
 */

public class SchoolActivity extends Activity {
    private RecyclerView recyclerView;
    private RecyclerAdapter myOrderReycAdapter;
    private List<String> myOrderReycList;
    private XRefreshView xrefreshview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school);
        this.xrefreshview = (XRefreshView) findViewById(R.id.xrefreshview);
        this.recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        ImageView imageBack = (ImageView) findViewById(R.id.image_back);
        imageBack.setOnClickListener(v -> finish());
        this.bindRecycleView();
        this.xRefreshView();
    }


    private void bindRecycleView() {
        this.getData();
        this.myOrderReycAdapter = new RecyclerAdapter<String>(SchoolActivity.this, myOrderReycList,
                R.layout.item_school) {
            @Override
            public void convert(RecyclerViewHolder helper, String item, int position) {
                TextView tvState = helper.getView(R.id.tv_name);
                RelativeLayout rlItemBg = helper.getView(R.id.rl_item_bg);
                TextView tvProductLabel = helper.getView(R.id.tv_product_label);
                if (position == 0) {
                    tvState.setText("恒生指数");
                    tvProductLabel.setText("产品一");
                    rlItemBg.setBackgroundResource(R.mipmap.bg_hang_seng_index);
                } else if (position == 1) {
                    tvState.setText("原油期货");
                    tvProductLabel.setText("产品二");
                    rlItemBg.setBackgroundResource(R.mipmap.bg_crude_index);
                } else if (position == 2) {
                    tvState.setText("黄金期货");
                    tvProductLabel.setText("产品三");
                    rlItemBg.setBackgroundResource(R.mipmap.bg_gold_index);
                } else if (position == 3) {
                    tvState.setText("白银期货");
                    tvProductLabel.setText("产品四");
                    rlItemBg.setBackgroundResource(R.mipmap.bg_silver_index);
                }
            }
        };

        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setLayoutManager(new GridLayoutManager(SchoolActivity.this, 1,
                LinearLayoutManager.VERTICAL, false));
        this.recyclerView.setAdapter(this.myOrderReycAdapter);
        this.myOrderReycAdapter.setOnItemClickListener((parent, position) -> {
            Bundle bundle = new Bundle();
            int tag = Integer.valueOf(myOrderReycList.get(position));
            bundle.putInt(ProductIntroductionActivity.ORDER_TAG, tag);
            UIHelper.startActivity(SchoolActivity.this, ProductIntroductionActivity.class, bundle);
        });

    }

    private void getData() {
        myOrderReycList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            myOrderReycList.add("" + i);
        }

    }

    /**
     * 刷新机制
     */
    private void xRefreshView() {
        this.xrefreshview.setAutoRefresh(true);
        this.xrefreshview.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(() -> xrefreshview.stopRefresh(), 1000);
            }

            @Override
            public void onLoadMore(boolean isSlience) {

            }
        });
    }
}
