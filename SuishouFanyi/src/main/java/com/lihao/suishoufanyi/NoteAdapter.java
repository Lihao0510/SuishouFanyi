package com.lihao.suishoufanyi;

import java.util.ArrayList;
import java.util.List;

import com.lihao.tabviewtest.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class NoteAdapter extends BaseAdapter {
	private LayoutInflater mInflater;
	List<String> wordList;

	public NoteAdapter(Context context, ArrayList<String> list) {
		mInflater = LayoutInflater.from(context);
		wordList = list;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return wordList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return wordList.get(position);
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
		if(convertView == null){
			viewHolder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.word_list, null);
			viewHolder.wordText = (TextView) convertView.findViewById(R.id.wordItem);
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.wordText.setText(wordList.get(position));
		return convertView;
	}
	
	class ViewHolder{
		public TextView wordText;
	}

}
