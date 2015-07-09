package com.example.slidedrawer;

import java.util.ArrayList;
import java.util.List;

import com.example.slidedrawer.leftlist.LeftListAdapter;
import com.example.slidedrawer.leftlist.LeftListModel;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MainActivity extends Activity {

	private ListView drawerListView;
	private ActionBarDrawerToggle mDrawerToggle;
	private String mTitle = "";
	private DrawerLayout drawerLayout;
	private ArrayList<LeftListModel> arrayList;
	private LeftListAdapter leftListadapter;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findView();
		setView();
		setAction();
	}

	private void findView() {
		mTitle = (String) getTitle();
		drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		drawerListView = (ListView) findViewById(R.id.drawer_list);
	}

	private void setView() {
		mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
				R.drawable.ic_drawer, R.string.drawer_open,
				R.string.drawer_close) {

			/** Called when drawer is closed */
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(mTitle);
				invalidateOptionsMenu();

			}

			/** Called when a drawer is opened */
			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle("Select a river");
				invalidateOptionsMenu();
			}

		};
		
		drawerLayout.setDrawerListener(mDrawerToggle);

		arrayList=new ArrayList<LeftListModel>();
		
		arrayList.add(new LeftListModel(R.drawable.ic_btn, "新聞"));
		arrayList.add(new LeftListModel(R.drawable.ic_btn, "訂閱"));
		arrayList.add(new LeftListModel(R.drawable.ic_btn, "圖片"));
		arrayList.add(new LeftListModel(R.drawable.ic_btn, "視頻"));
		arrayList.add(new LeftListModel(R.drawable.ic_btn, "跟帖"));
		arrayList.add(new LeftListModel(R.drawable.ic_btn, "投票"));
		
		leftListadapter = new LeftListAdapter(getBaseContext(), arrayList);
		
		
		drawerListView.setAdapter(leftListadapter);
		
		
		
		getActionBar().setHomeButtonEnabled(true);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		drawerListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
				String[] rivers = getResources().getStringArray(R.array.rivers);
				mTitle = rivers[position];
				LeftFragment rFragment = new LeftFragment();
				Bundle data = new Bundle();
				data.putInt("position", position);
				rFragment.setArguments(data);
				FragmentManager fragmentManager = getFragmentManager();
				FragmentTransaction ft = fragmentManager.beginTransaction();
				ft.replace(R.id.content_frame, rFragment);
				ft.commit();
				drawerLayout.closeDrawer(drawerListView);

			}
		});
	}

	private void setAction() {

	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mDrawerToggle.syncState();
	}

	/** Handling the touch event of app icon */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/** Called whenever we call invalidateOptionsMenu() */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		boolean drawerOpen = drawerLayout.isDrawerOpen(drawerListView);

		menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
