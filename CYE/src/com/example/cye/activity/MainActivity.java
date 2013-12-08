package com.example.cye.activity;

import java.util.ArrayList;
import java.util.List;

import com.example.cye.R;
import com.example.cye.database.DatabaseOperation;
import com.example.cye.util.FomattingString;
import com.example.cye.util.ItemInfo;
import com.example.cye.util.MyAdapter;

import android.os.Bundle;
import android.app.Activity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity implements OnItemClickListener, OnClickListener {
	//Them chu thich ne
	private EditText mEditSearch;
	private ListView mListTitle;
	private MyAdapter mMyAdapter;
	private TextView mTextSearch;
	private List<ItemInfo> mTitle;
	private WebView mWebContent;

	DatabaseOperation dbOperation;
	private boolean mAtHome = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.activity_main);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title_bar_custom);
		dbOperation = new DatabaseOperation(getApplicationContext());
//		dbOperation.addDatabase();
		mEditSearch = (EditText) findViewById(R.id.edit_search);
		mListTitle = (ListView) findViewById(R.id.list_title);
		mWebContent = (WebView) findViewById(R.id.web_content);
		mTextSearch = (TextView) findViewById(R.id.text_search);
		mTitle = new ArrayList<ItemInfo>();
		mMyAdapter = new MyAdapter(this, mTitle);
		mListTitle.setAdapter(mMyAdapter);
		 dbOperation.getAllTitle(mTitle);
		mMyAdapter.notifyDataSetChanged();
		mListTitle.setOnItemClickListener(this);
		mTextSearch.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		mTitle.clear();
		dbOperation.searchKeyword(mEditSearch.getText().toString().trim(), mTitle);
		mMyAdapter.notifyDataSetChanged();
	}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int positon, long id) {
		ItemInfo itemInfo = (ItemInfo) mTitle.get(positon);
		String data = FomattingString.format(dbOperation.getContent(itemInfo.getId()));
		mWebContent.loadDataWithBaseURL(null, data, "text/html", "utf-8", null);
		setView(false);
	}

	private void setView(boolean value) {
		if (value) {
			mListTitle.setVisibility(View.VISIBLE);
			mEditSearch.setVisibility(View.VISIBLE);
			mTextSearch.setVisibility(View.VISIBLE);
			mWebContent.setVisibility(View.VISIBLE);
			mEditSearch.requestFocus();
			mAtHome = true;
		} else {
			mListTitle.setVisibility(View.GONE);
			mEditSearch.setVisibility(View.GONE);
			mTextSearch.setVisibility(View.GONE);
			mWebContent.setVisibility(View.VISIBLE);
			mAtHome = false;
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			if (mAtHome == false) {
				setView(true);
				return true;
			}

		}
		return super.onKeyDown(keyCode, event);
	}

}
