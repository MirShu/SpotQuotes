package com.quantpower.spotquotes.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.quantpower.spotquotes.R;
import com.quantpower.spotquotes.adapter.RecyclerAdapter;
import com.quantpower.spotquotes.adapter.RecyclerViewHolder;
import com.quantpower.spotquotes.model.MarketViewRollModel;
import com.quantpower.spotquotes.model.MessageResult;
import com.quantpower.spotquotes.ui.activity.NewsActivity;
import com.quantpower.spotquotes.ui.activity.SchoolActivity;
import com.quantpower.spotquotes.ui.activity.ServiceActivtiy;
import com.quantpower.spotquotes.ui.activity.WealActivtiy;
import com.quantpower.spotquotes.utils.Constants;
import com.quantpower.spotquotes.utils.GlideImageLoader;
import com.quantpower.spotquotes.utils.UIHelper;
import com.quantpower.spotquotes.utils.URLS;
import com.youth.banner.Banner;
import com.youth.banner.transformer.FlipHorizontalTransformer;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ShuLin on 2017/6/3.
 * Email linlin.1016@qq.com
 * Company Shanghai Quantpower Information Technology Co.,Ltd.
 */

public class HomeFragment extends Fragment {
    public static HomeFragment newInstance(String s) {
        HomeFragment homeFragment = new HomeFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.ARGS, s);
        homeFragment.setArguments(bundle);
        return homeFragment;
    }

    private RecyclerView recycler_market;
    private RecyclerAdapter<MarketViewRollModel> marketAdapter;
    private List<MarketViewRollModel> listMarket;
    private View rootView;
    private Banner banner;
    private TextView tvSchool;
    private TextView tvWeal;
    private TextView tvNews;
    private TextView tvService;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.rootView = inflater.inflate(R.layout.fragment_home, container, false);
        this.banner = (Banner) rootView.findViewById(R.id.banner);
        this.recycler_market = (RecyclerView) rootView.findViewById(R.id.recycler_market);
        this.tvSchool = (TextView) rootView.findViewById(R.id.tv_school);
        this.tvWeal = (TextView) rootView.findViewById(R.id.tv_weal);
        this.tvNews = (TextView) rootView.findViewById(R.id.tv_news);
        this.tvService = (TextView) rootView.findViewById(R.id.tv_service);
        this.tvSchool.setOnClickListener(v -> UIHelper.startActivity(getActivity(), SchoolActivity.class));
        this.tvWeal.setOnClickListener(v -> UIHelper.startActivity(getActivity(), WealActivtiy.class));
        this.tvNews.setOnClickListener(v -> UIHelper.startActivity(getActivity(), NewsActivity.class));
        this.tvService.setOnClickListener(v -> UIHelper.startActivity(getActivity(), ServiceActivtiy.class));
        this.listMarket = new ArrayList<>();
        this.bannerView();
        this.bindRecycleView();
        return rootView;
    }

    private void bannerView() {
        List<Integer> list = new ArrayList<>();
        list.add(R.mipmap.banner01);
        list.add(R.mipmap.banner02);
        list.add(R.mipmap.banner03);
        list.add(R.mipmap.banner04);
        banner.setImages(list)
                .setBannerAnimation(FlipHorizontalTransformer.class)
                .setImageLoader(new GlideImageLoader())
                .start();
    }

    private void bindRecycleView() {
        /*******************
         * 绑定头条新闻数据
         *******************/
        this.markeData();
        this.marketAdapter = new RecyclerAdapter<MarketViewRollModel>(this.getActivity(), listMarket,
                R.layout.item_market) {
            @Override
            public void convert(RecyclerViewHolder helper, MarketViewRollModel item, int position) {
                TextView tv_name = helper.getView(R.id.tv_market_name);
                TextView tv_price = helper.getView(R.id.tv_market_price);
                TextView tv_float = helper.getView(R.id.tv_market_float);

                helper.setText(R.id.tv_market_name, item.getMarket_name());
                helper.setText(R.id.tv_market_price, item.getMarket_price());
                helper.setText(R.id.tv_market_float, item.getMarket_float());
                int size = listMarket.size();

                if (String.valueOf(listMarket.get(position % size).getMarket_float().charAt(0)).equals("-")) {
                    tv_float.setTextColor(Color.parseColor("#019f53"));
                    tv_price.setTextColor(Color.parseColor("#019f53"));
                } else {
                    tv_float.setTextColor(Color.parseColor("#fd040a"));
                    tv_price.setTextColor(Color.parseColor("#fd040a"));
                }
            }
        };

        this.recycler_market.setHasFixedSize(true);
        this.recycler_market.setLayoutManager(new GridLayoutManager(getActivity(), 1, LinearLayoutManager.HORIZONTAL, false));
        this.recycler_market.setAdapter(this.marketAdapter);
    }


    //获取行情数据
    private void markeData() {
        RequestParams mparams = new RequestParams(URLS.MARKET_NOWMARKET);
        x.http().get(mparams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                listMarket.clear();
                MessageResult message = MessageResult.parse(result);
                List<MarketViewRollModel> marketList = JSON.parseArray(message.getData(),
                        MarketViewRollModel.class);
                listMarket.addAll(marketList);
                marketAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
            }
        });
    }
}
