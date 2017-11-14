package com.quantpower.spotquotes.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.quantpower.spotquotes.utils.ImageLoaderEx;


/**
 * Created by administrator on 2016/11/18.
 */

public class RecyclerViewHolder extends RecyclerView.ViewHolder {

    private static final String TAG = "ViewHolder";

    private final SparseArray<View> listView;

    private View mConvertView;


    public RecyclerViewHolder(View itemView) {
        super(itemView);
        this.listView = new SparseArray<View>();
        this.mConvertView = itemView;
        itemView.setTag(this);
    }

    public <T extends View> T getView(int viewId) {
        View view = listView.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            listView.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * 为TextView设置字符串
     *
     * @param viewId
     * @param text
     * @return
     */
    public RecyclerViewHolder setText(int viewId, CharSequence text) {
        TextView view = getView(viewId);
        view.setText(text);
        return this;
    }

    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @param drawableId
     * @return
     */
    public RecyclerViewHolder setImageResource(int viewId, int drawableId) {
        ImageView view = getView(viewId);
        view.setImageResource(drawableId);
        return this;
    }

    public RecyclerViewHolder setImageUrl(int viewId, String url) {
        ImageView view = this.getView(viewId);
        ImageLoader.getInstance().displayImage(url, view, ImageLoaderEx.getDisplayImageOptions());
        return this;
    }


}
