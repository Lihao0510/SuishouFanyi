package com.lihao.suishoufanyi;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import com.lihao.tabviewtest.R;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;

public class MainActivity extends Activity implements OnClickListener {
	
	private LayoutInflater inflator;
	private ViewPager mViewPager;
	private PagerAdapter mAdapter;
	private List<View> mViews = new ArrayList<View>();

	private LinearLayout tabTranslate;
	private LinearLayout tabNote;
	private LinearLayout tabNews;
	private LinearLayout tabJoy;

	private ImageButton tabTranslateButton;
	private ImageButton tabNoteButton;
	private ImageButton tabNewsButton;
	private ImageButton tabJoyButton;
	
	private View tab01;
	private View tab02;
	private View tab03;
	private View tab04;

	private EditText source;
	private EditText result;
	private Button start;
	private Button save;
	private Button clear;
	private MyTask mytask;
	private ClipboardManager myClipboard;
	private ClipData myData;
	private MyListener listener = new MyListener();
	private ListView wordListView;

	private void initContent() {
		myClipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
		source = (EditText) tab01.findViewById(R.id.source);
		result = (EditText) tab01.findViewById(R.id.result);
		start = (Button) tab01.findViewById(R.id.start);
		save = (Button) tab01.findViewById(R.id.save);
		clear = (Button) tab01.findViewById(R.id.clear);
		start.setOnClickListener(listener);
		clear.setOnClickListener(listener);
		save.setOnClickListener(listener);

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		inflator = LayoutInflater.from(this);
		initView();
		initEvents();
		initContent();
		initWordNote();

	}

	private void initWordNote() {
		// TODO Auto-generated method stub 
		List<String> wordList = new ArrayList<String>();
		try {
			wordList = NoteHelper.loadWords(this);
			wordListView = (ListView) tab02.findViewById(R.id.wordList);
			NoteAdapter wordNoteAdapter = new NoteAdapter(this, (ArrayList<String>) wordList);
			wordListView.setAdapter(wordNoteAdapter);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private class MyListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.start:
				Log.d("Lihao", "StartButton Pressde");
				String sourceText = source.getText().toString().trim();
				View cv = getWindow().getDecorView();
				mytask = new MyTask(cv);
				mytask.execute(sourceText);
				break;
			case R.id.clear:
				Log.d("Lihao", "ClearButton Pressde");
				source.setText("");
				result.setText("");
				break;
			case R.id.save:
				String wordFirst = source.getText().toString();
				String wordLast = result.getText().toString();
				if(NoteHelper.isEnglishWord(wordFirst)&&wordLast.length()>0){
					NoteHelper.saveWord(wordFirst + " : " +wordLast, MainActivity.this);
				}
				initWordNote();
			default:
				break;
			}
		}

	}

	@SuppressWarnings("deprecation")
	private void initEvents() {
		// TODO Auto-generated method stub
		tabTranslate.setOnClickListener(this);
		tabNote.setOnClickListener(this);
		tabNews.setOnClickListener(this);
		tabJoy.setOnClickListener(this);

		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				int currentItem = mViewPager.getCurrentItem();
				resetImage();
				switch (currentItem) {
				case 0:
					tabTranslateButton.setImageResource(R.drawable.translate_selected);
					break;
				case 1:
					tabNoteButton.setImageResource(R.drawable.note_selected);
					break;
				case 2:
					tabNewsButton.setImageResource(R.drawable.news_selected);
					break;
				case 3:
					tabJoyButton.setImageResource(R.drawable.joy_selected);
					break;
				default:
					break;
				}
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});
	}

	private void initView() {
		// TODO Auto-generated method stub
		mViewPager = (ViewPager) findViewById(R.id.viewPager);

		tabTranslate = (LinearLayout) findViewById(R.id.tab_translate);
		tabNote = (LinearLayout) findViewById(R.id.tab_note);
		tabNews = (LinearLayout) findViewById(R.id.tab_news);
		tabJoy = (LinearLayout) findViewById(R.id.tab_joy);

		tabTranslateButton = (ImageButton) findViewById(R.id.tab_translate_image);
		tabNoteButton = (ImageButton) findViewById(R.id.tab_note_image);
		tabNewsButton = (ImageButton) findViewById(R.id.tab_news_image);
		tabJoyButton = (ImageButton) findViewById(R.id.tab_joy_image);

		tab01 = inflator.inflate(R.layout.page_01, null);
		tab02 = inflator.inflate(R.layout.page_02, null);
		tab03 = inflator.inflate(R.layout.page_03, null);
		tab04 = inflator.inflate(R.layout.page_04, null);

		mViews.add(tab01);
		mViews.add(tab02);
		mViews.add(tab03);
		mViews.add(tab04);

		mAdapter = new PagerAdapter() {

			@Override
			public void destroyItem(ViewGroup container, int position, Object object) {
				// TODO Auto-generated method stub
				container.removeView(mViews.get(position));
			}

			@Override
			public Object instantiateItem(ViewGroup container, int position) {
				// TODO Auto-generated method stub
				View view = mViews.get(position);
				container.addView(view);
				return view;
			}

			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				// TODO Auto-generated method stub
				return arg0 == arg1;
			}

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return mViews.size();
			}
		};
		mViewPager.setAdapter(mAdapter);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		resetImage();
		switch (v.getId()) {
		case R.id.tab_translate:
			mViewPager.setCurrentItem(0);
			tabTranslateButton.setImageResource(R.drawable.translate_selected);
			break;
		case R.id.tab_note:
			mViewPager.setCurrentItem(1);
			tabNoteButton.setImageResource(R.drawable.note_selected);
			break;
		case R.id.tab_news:
			mViewPager.setCurrentItem(2);
			tabNewsButton.setImageResource(R.drawable.news_selected);
			break;
		case R.id.tab_joy:
			mViewPager.setCurrentItem(3);
			tabJoyButton.setImageResource(R.drawable.joy_selected);
			break;
		default:
			break;
		}
	}

	private void resetImage() {
		// TODO Auto-generated method stub
		tabTranslateButton.setImageResource(R.drawable.translate_normal);
		tabNoteButton.setImageResource(R.drawable.note_normal);
		tabNewsButton.setImageResource(R.drawable.news_normal);
		tabJoyButton.setImageResource(R.drawable.joy_normal);

	}
}
