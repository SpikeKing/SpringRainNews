package me.chunyu.spike.springrainnews.uis.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.chunyu.spike.springrainnews.NewsApplication;
import me.chunyu.spike.springrainnews.R;
import me.chunyu.spike.springrainnews.mvp.models.AvengersCharacter;
import me.chunyu.spike.springrainnews.mvp.presenters.MainPresenter;
import me.chunyu.spike.springrainnews.mvp.views.MainView;
import me.chunyu.spike.springrainnews.uis.adapters.MainListAdapter;

public class MainActivity extends AppCompatActivity implements MainView {

    private static final String TAG = "DEBUG-WCL: " + MainActivity.class.getSimpleName();

    @Inject MainPresenter mMainPresenter;

    @Inject Context mAppContext;

    @Bind(R.id.toolbar) Toolbar mToolbar;
    @Bind(R.id.fab) FloatingActionButton mFab;
    @Bind(R.id.nav_view) NavigationView mNavView;
    @Bind(R.id.drawer_layout) DrawerLayout mDrawerLayout;

    @Bind(R.id.main_rv_list) RecyclerView mRvList; // 列表视图

    private MainListAdapter mListAdapter; // 列表视图的适配器

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUi(); // 初始化Ui
        initList();
        initDi(); // 初始化依赖注入
        initDefault(); // 默认配置
        initPresenter(); // 初始化展示
    }

    @Override protected void onResume() {
        super.onResume();
        mMainPresenter.onResume();
    }

    @Override protected void onStop() {
        super.onStop();
        mMainPresenter.onStop();
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        mMainPresenter.onDestroy();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override public void setListData(List<AvengersCharacter> characters) {
        Toast.makeText(mAppContext, "数量: " + characters.size(), Toast.LENGTH_SHORT).show();
        mListAdapter.setCharacters(characters);
    }

    // 初始化Ui
    private void initUi() {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    // 初始化列表
    private void initList() {
        mRvList.setLayoutManager(new LinearLayoutManager(mAppContext));
        mListAdapter = new MainListAdapter();
        mRvList.setAdapter(mListAdapter);
    }

    // 初始化依赖注入
    private void initDi() {
        NewsApplication.component().inject(this); // 注入
    }

    // 初始化默认配置
    private void initDefault() {
        setSupportActionBar(mToolbar);

        mFab.setOnClickListener(view ->
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
        );

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        mNavView.setNavigationItemSelectedListener(this::onNavigationItemSelected);
    }

    // 初始化展示
    private void initPresenter() {
        mMainPresenter.attachView(this);
        mMainPresenter.onCreate();
    }
}
