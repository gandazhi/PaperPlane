package com.marktony.zhihudaily.refactor.details;

import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.marktony.zhihudaily.R;
import com.marktony.zhihudaily.refactor.customtabs.CustomTabsHelper;
import com.marktony.zhihudaily.refactor.data.ArticleType;
import com.marktony.zhihudaily.refactor.util.InfoConstants;

/**
 * Created by lizhaotailang on 2017/5/24.
 */

public class DetailsFragment extends Fragment
        implements DetailsContract.View {

    private ImageView mImageView;
    private WebView mWebView;
    private CollapsingToolbarLayout mToolbarLayout;
    private SwipeRefreshLayout mRefreshLayout;

    private DetailsContract.Presenter mPresenter;

    private int id;

    public DetailsFragment() {
        // Requires an empty constructor.
    }

    public static DetailsFragment newInstance() {
        return new DetailsFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //id = getActivity().getIntent().getIntExtra()
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.re_fragment_details, container, false);

        initViews(view);

        mRefreshLayout.setOnRefreshListener(() -> {

        });

        return view;
    }

    @Override
    public void setPresenter(DetailsContract.Presenter presenter) {
        if (presenter != null) {
            mPresenter = presenter;
        }
    }

    @Override
    public void initViews(View view) {

        DetailsActivity activity = (DetailsActivity) getActivity();
        activity.setSupportActionBar(view.findViewById(R.id.toolbar));
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mImageView = view.findViewById(R.id.image_view);

        mRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);
        mRefreshLayout.setColorSchemeResources(R.color.colorAccent);

        mToolbarLayout = view.findViewById(R.id.toolbar_layout);
        mWebView = view.findViewById(R.id.web_view);

        mWebView.setScrollbarFadingEnabled(true);
        //能够和js交互
        mWebView.getSettings().setJavaScriptEnabled(true);
        //缩放,设置为不能缩放可以防止页面上出现放大和缩小的图标
        mWebView.getSettings().setBuiltInZoomControls(false);
        //缓存
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        //开启DOM storage API功能
        mWebView.getSettings().setDomStorageEnabled(true);
        //开启application Cache功能
        mWebView.getSettings().setAppCacheEnabled(false);

        // Show the images or not.
        mWebView.getSettings().setBlockNetworkImage(PreferenceManager.getDefaultSharedPreferences(getContext()).getBoolean(InfoConstants.KEY_NO_IMG_MODE, false));

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                CustomTabsHelper.openUrl(getContext(), url);
                return true;
            }

        });
    }

    @Override
    public void setLoadingIndicator(boolean active) {
        mRefreshLayout.post(() -> mRefreshLayout.setRefreshing(active));
    }

    @Override
    public void showMessage(int stringRes) {

    }

    @Override
    public void setTitle(@NonNull String title) {
        setCollapsingToolbarLayoutTitle(title);
    }

    @Override
    public void showResult(@ArticleType.TYPE int type, @NonNull String content, boolean withoutBody) {
        boolean nightMode = PreferenceManager.getDefaultSharedPreferences(getContext()).getBoolean(InfoConstants.KEY_NIGHT_MODE, false);

    }

    @Override
    public void showCover(@Nullable String url) {
        Glide.with(getContext())
                .load(url)
                .asBitmap()
                .placeholder(R.drawable.placeholder)
                .centerCrop()
                .error(R.drawable.placeholder)
                .into(mImageView);
    }

    // to change the title's font size of toolbar layout
    private void setCollapsingToolbarLayoutTitle(String title) {
        mToolbarLayout.setTitle(title);
        mToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
        mToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);
        mToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBarPlus1);
        mToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBarPlus1);
    }
}
