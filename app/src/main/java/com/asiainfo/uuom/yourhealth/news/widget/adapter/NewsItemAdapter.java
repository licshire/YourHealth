package com.asiainfo.uuom.yourhealth.news.widget.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.asiainfo.uuom.yourhealth.R;
import com.asiainfo.uuom.yourhealth.api.Constant;
import com.asiainfo.uuom.yourhealth.bean.NewsBean;
import com.asiainfo.uuom.yourhealth.util.ImageLoaderUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by uuom on 16-1-13.
 */
public class NewsItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {

    public static final int VIEW_TYPE_ITEM = 1;
    public static final int VIEW_TYPE_FOOTER = 0;

    private Object[] datas;
    private Context context;

    private OnItemClickListener itemClickListener;
    private FooterViewHolder footerViewHolder;

    public NewsItemAdapter(Context context) {
        this.context = context;
        datas = new Object[0];
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        if (viewType == VIEW_TYPE_ITEM){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news,parent,false);
            if (itemClickListener != null){
                view.setOnClickListener(this);
            }
            viewHolder = new ItemViewHolder(view);
        }else if (viewType == VIEW_TYPE_FOOTER){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.footer,parent,false);
            viewHolder = new FooterViewHolder(view);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder){
            NewsBean news = (NewsBean) datas[position];
            ItemViewHolder vh = (ItemViewHolder) holder;
            if (news.getImg() != null){
                ImageLoaderUtils.display(context, vh.iv_img, Constant.NEWS_IMAGE_PREFIX + news.getImg());
            }
            vh.tv_title.setText(news.getTitle());
            vh.tv_time.setText(news.getTime());
            vh.itemView.setTag(position);
        }else if (holder instanceof FooterViewHolder){
            footerViewHolder = (FooterViewHolder) holder;
        }

    }

    public void setDatas(Object[] datas) {
        this.datas = datas;
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return datas.length==0 ? 0 :datas.length+1 ; //多的一条为footer
    }

    @Override
    public int getItemViewType(int position) {
        int viewType = VIEW_TYPE_ITEM;
        if (datas.length == position){
            viewType =VIEW_TYPE_FOOTER;
        }
        return viewType;
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onItemClick((Integer) view.getTag());
    }

    class ItemViewHolder extends RecyclerView.ViewHolder{

        @Bind(R.id.iv_img)
        ImageView iv_img;
        @Bind(R.id.tv_title)
        TextView tv_title;
        @Bind(R.id.tv_time)
        TextView tv_time;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    class FooterViewHolder extends RecyclerView.ViewHolder{

        @Bind(R.id.more_data_msg)
        TextView more_data_msg;

        public FooterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
}
