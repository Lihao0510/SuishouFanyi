package com.lihao.suishoufanyi;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import com.lihao.tabviewtest.R;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class NewsAdapter extends BaseAdapter {

    private List<News> mlist;
    private LayoutInflater mInflater;

    public NewsAdapter(Context context, List<News> list) {
        mlist = list;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mlist.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return mlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.news_layout, null);
            viewHolder.newsImageView = (ImageView) convertView.findViewById(R.id.listItem_image);
            viewHolder.newsTitle = (TextView) convertView.findViewById(R.id.listItem_title);
            viewHolder.newsContent = (TextView) convertView.findViewById(R.id.listItem_content);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.newsImageView.setImageResource(R.drawable.ic_launcher);
        viewHolder.newsTitle.setText(mlist.get(position).getNewsTitle());
        viewHolder.newsContent.setText(mlist.get(position).getNewsContent());
        return convertView;
    }

    class ViewHolder {
        public ImageView newsImageView;
        public TextView newsTitle;
        public TextView newsContent;
    }

}