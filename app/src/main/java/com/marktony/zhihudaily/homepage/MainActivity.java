package com.marktony.zhihudaily.homepage;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.marktony.zhihudaily.R;
import com.marktony.zhihudaily.app.App;
import com.marktony.zhihudaily.bookmarks.BookmarksFragment;
import com.marktony.zhihudaily.service.CacheService;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    private MainFragment mainFragment;
    private BookmarksFragment bookmarksFragment;

    private NavigationView navigationView;
    private DrawerLayout drawer;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(App.getThemeResources());
        setContentView(R.layout.activity_main);

        // Theme.setStatusBarColor(this);

        initViews();

        navigationView.setCheckedItem(R.id.nav_home);

        mainFragment = MainFragment.newInstance();
        bookmarksFragment = BookmarksFragment.newInstance();

        showMainFragment();

        startService(new Intent(this, CacheService.class));

    }

    private void initViews() {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                drawer,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

    }

    private void showMainFragment() {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (!mainFragment.isAdded()) {
            fragmentTransaction.add(R.id.layout_fragment, mainFragment, "MainFragment");
        }
        fragmentTransaction.show(mainFragment);
        fragmentTransaction.hide(bookmarksFragment);
        fragmentTransaction.commit();

        toolbar.setTitle(getResources().getString(R.string.app_name));

    }

    private void showBookmarksFragment() {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (!bookmarksFragment.isAdded()) {
            fragmentTransaction.add(R.id.layout_fragment, bookmarksFragment, "BookmarksFragment");
        }
        fragmentTransaction.show(bookmarksFragment);
        fragmentTransaction.hide(mainFragment);
        fragmentTransaction.commit();

        toolbar.setTitle(getResources().getString(R.string.nav_bookmarks));

    }

    /**
     * 改变主题
     */
    /*private void changeTheme(){
        setDrawableCahe();
        setTheme();
        getState();
    }*/

    // 屏幕方向改变时调用的方法，拦截屏幕切换
    // manifest文件中给activity设置了相应的参数
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    /**
     * 获取布局的DrawableCache给ImageView覆盖Fragment
     */
    /*private void setDrawableCahe() {
        //设置false清除缓存
        viewGroup.setDrawingCacheEnabled(false);
        //设置true之后可以获取Bitmap
        viewGroup.setDrawingCacheEnabled(true);
        imageView.setImageBitmap(viewGroup.getDrawingCache());
        imageView.setAlpha(1f);
        imageView.setVisibility(View.VISIBLE);
    }*/

    /**
     * 设置主题
     */
    /*private void setTheme() {
        TypedValue typedValue = new TypedValue();
        getTheme().resolveAttribute(R.attr.my_theme, typedValue, true);
        switch (typedValue.data){

            case Theme.DAY_THEME:
                App.setThemeValue(Theme.NIGHT_THEME);
                setTheme(Theme.RESOURCES_NIGHT_THEME);
                break;
            case Theme.NIGHT_THEME:
                App.setThemeValue(Theme.DAY_THEME);
                setTheme(Theme.RESOURCES_DAY_THEME);
                break;
        }
    }*/

    /**
     * 主题选择的本地存储
     */
    /*private void save() {
        SharedPreferences sharedPreferences = getSharedPreferences("user_settings",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("theme", App.getThemeValue());
        editor.commit();
    }*/

    /**
     * 获取当前fragment状态
     */
   /* public void getState() {
        showMainFragment();
    }*/


    /*private void startAnimation(final View view) {
        ValueAnimator animator = ValueAnimator.ofFloat(1f).setDuration(ANIMATION_TIME);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float n = (float) animation.getAnimatedValue();
                view.setAlpha(1f - n);
            }
        });
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                imageView.setVisibility(View.INVISIBLE);
            }
        });
        animator.start();
    }*/

   /* @Override
    public void viewPagerCreated() {
        // startAnimation(imageView);
    }*/

    @Override
    protected void onDestroy() {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (CacheService.class.getName().equals(service.service.getClassName())) {
                stopService(new Intent(this, CacheService.class));
            }
        }
        super.onDestroy();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_home) {
            showMainFragment();
        } else if (id == R.id.nav_bookmarks) {
            showBookmarksFragment();
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
