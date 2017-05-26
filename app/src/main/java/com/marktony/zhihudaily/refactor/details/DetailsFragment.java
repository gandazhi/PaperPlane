package com.marktony.zhihudaily.refactor.details;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.marktony.zhihudaily.R;
import com.marktony.zhihudaily.refactor.customtabs.CustomTabsHelper;
import com.marktony.zhihudaily.refactor.data.ContentType;
import com.marktony.zhihudaily.refactor.data.DoubanMomentContent;
import com.marktony.zhihudaily.refactor.data.DoubanMomentNews;
import com.marktony.zhihudaily.refactor.data.GuokrHandpickContent;
import com.marktony.zhihudaily.refactor.data.ZhihuDailyContent;
import com.marktony.zhihudaily.refactor.util.InfoConstants;

import java.util.List;

/**
 * Created by lizhaotailang on 2017/5/24.
 */

public class DetailsFragment extends Fragment
        implements DetailsContract.View {

    private ImageView mImageView;
    private WebView mWebView;
    private CollapsingToolbarLayout mToolbarLayout;
    private Toolbar toolbar;
    private NestedScrollView mScrollView;

    private DetailsContract.Presenter mPresenter;

    private int mId;
    private ContentType mType;
    private String mTitle;

    private boolean mIsNightMode = false;
    private boolean mFavorite = false;

    public DetailsFragment() {
        // Requires an empty constructor.
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mId = getActivity().getIntent().getIntExtra(DetailsActivity.KEY_ARTICLE_ID, -1);
        mType = (ContentType) getActivity().getIntent().getSerializableExtra(DetailsActivity.KEY_ARTICLE_TYPE);
        mTitle = getActivity().getIntent().getStringExtra(DetailsActivity.KEY_ARTICLE_TITLE);
        mIsNightMode = PreferenceManager.getDefaultSharedPreferences(getContext()).getBoolean(InfoConstants.KEY_NIGHT_MODE, false);
    }

    public static DetailsFragment newInstance() {
        return new DetailsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.re_fragment_details, container, false);

        initViews(view);

        setTitle(mTitle);

        toolbar.setOnClickListener(v -> {
            mScrollView.smoothScrollTo(0, 0);
        });

        setHasOptionsMenu(true);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
        if (mType == ContentType.TYPE_ZHIHU_DAILY) {
            mPresenter.loadZhihuDailyContent(mId);
        } else {
            mPresenter.loadDoubanContent(mId);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_more, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            getActivity().onBackPressed();
        } else if (id == R.id.action_more) {

            final BottomSheetDialog dialog = new BottomSheetDialog(getActivity());

            View view = getActivity().getLayoutInflater().inflate(R.layout.reading_actions_sheet, null);

            AppCompatTextView favorite = view.findViewById(R.id.text_view_favorite);
            AppCompatTextView copyLink = view.findViewById(R.id.text_view_copy_link);
            AppCompatTextView openWithBrowser = view.findViewById(R.id.text_view_open_with_browser);
            AppCompatTextView copyContent = view.findViewById(R.id.text_view_copy_content);
            AppCompatTextView shareAsText = view.findViewById(R.id.text_view_share_as_text);

            if (mFavorite) {
                favorite.setText(R.string.unfavorite);
            }

            // add to bookmarks or delete from bookmarks
            favorite.setOnClickListener(v -> {
                dialog.dismiss();
                mPresenter.favorite(!mFavorite);
            });

            // copy the article's link to clipboard
            copyLink.setOnClickListener(v -> {
                //
                dialog.dismiss();
            });

            // open the link in system browser
            openWithBrowser.setOnClickListener(v -> {
                //
                dialog.dismiss();
            });

            // copy the text content to clipboard
            copyContent.setOnClickListener(v -> {
                //
                dialog.dismiss();
            });

            // shareAsText the content as text
            shareAsText.setOnClickListener(v -> {
                //
                dialog.dismiss();
            });

            dialog.setContentView(view);
            dialog.show();
        }
        return true;
    }

    @Override
    public void setPresenter(DetailsContract.Presenter presenter) {
        if (presenter != null) {
            mPresenter = presenter;
        }
    }

    @Override
    public void initViews(View view) {

        toolbar = view.findViewById(R.id.toolbar);

        DetailsActivity activity = (DetailsActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        activity.getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);

        mImageView = view.findViewById(R.id.image_view);

        //mRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);
        //mRefreshLayout.setColorSchemeResources(R.color.colorAccent);

        mToolbarLayout = view.findViewById(R.id.toolbar_layout);
        mScrollView = view.findViewById(R.id.nested_scroll_view);

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
    public void showMessage(int stringRes) {

    }

    @Override
    public boolean isActive() {
        return isAdded() && isResumed();
    }

    @Override
    public void showZhihuDailyContent(@NonNull ZhihuDailyContent content) {
        mFavorite = content.isFavorited();

        if (content.getBody() != null) {
            String result = content.getBody();
            result = result.replace("<div class=\"img-place-holder\">", "");
            result = result.replace("<div class=\"headline\">", "");

            // 在api中，css的地址是以一个数组的形式给出，这里需要设置
            // in fact,in api,css addresses are given as an array
            // api中还有js的部分，这里不再解析js
            // javascript is included,but here I don't use it
            // 不再选择加载网络css，而是加载本地assets文件夹中的css
            // use the css file from local assets folder,not from network
            String css = "<link rel=\"stylesheet\" href=\"file:///android_asset/zhihu_daily.css\" type=\"text/css\">";


            // 根据主题的不同确定不同的加载内容
            // load content judging by different theme
            String theme = "<body className=\"\" onload=\"onLoaded()\">";
            if (mIsNightMode) {
                theme = "<body className=\"\" onload=\"onLoaded()\" class=\"night\">";
            }

            result = "<!DOCTYPE html>\n"
                    + "<html lang=\"en\" xmlns=\"http://www.w3.org/1999/xhtml\">\n"
                    + "<head>\n"
                    + "\t<meta charset=\"utf-8\" />"
                    + css
                    + "\n</head>\n"
                    + theme
                    + result
                    + "</body></html>";

            mWebView.loadDataWithBaseURL("x-data://base", result,"text/html","utf-8",null);
        } else {
            mWebView.loadDataWithBaseURL("x-data://base", content.getShareUrl(),"text/html","utf-8",null);
        }

        setCover(content.getImage());

    }

    @Override
    public void showDoubanMomentContent(@NonNull DoubanMomentContent content, @Nullable List<DoubanMomentNews.Posts.Thumbs> list) {
        mFavorite = content.isFavorite();

        String css;
        String body = content.getContent();
        if (mIsNightMode) {
            css = "<link rel=\"stylesheet\" href=\"file:///android_asset/douban_dark.css\" type=\"text/css\">";
        } else {
            css = "<link rel=\"stylesheet\" href=\"file:///android_asset/douban_light.css\" type=\"text/css\">";
        }
        if (list != null) {
            setCover(list.get(0).getMedium().getUrl());
            for (DoubanMomentNews.Posts.Thumbs t : list) {
                String old = "<img id=\"" + t.getTagName()+ "\" />";
                String newStr = "<img id=\"" + t.getTagName() + "\" "
                        + "src=\"" + t.getMedium().getUrl() + "\"/>";
                body = body.replace(old, newStr);
            }
        } else {
            setCover(null);
        }
        String result = "<!DOCTYPE html>\n"
                + "<html lang=\"ZH-CN\" xmlns=\"http://www.w3.org/1999/xhtml\">\n"
                + "<head>\n<meta charset=\"utf-8\" />\n"
                + css
                + "\n</head>\n<body>\n"
                + "<div class=\"container bs-docs-container\">\n"
                + "<div class=\"post-container\">\n"
                + body
                + "</div>\n</div>\n</body>\n</html>";

        mWebView.loadDataWithBaseURL("x-data://base", result,"text/html","utf-8",null);
    }

    @Override
    public void showGuokrHandpickContent(@NonNull GuokrHandpickContent content) {
        String result = content.getResult().getContent();
    }


    private void setTitle(@NonNull String title) {
        setCollapsingToolbarLayoutTitle(title);
    }

    private void setCover(@Nullable String url) {
        if (url != null) {
            Glide.with(getContext())
                    .load(url)
                    .asBitmap()
                    .placeholder(R.drawable.placeholder)
                    .centerCrop()
                    .error(R.drawable.placeholder)
                    .into(mImageView);
        } else {
            mImageView.setImageResource(R.drawable.placeholder);
        }
    }

    // to change the title's font size of toolbar layout
    private void setCollapsingToolbarLayoutTitle(String title) {
        mToolbarLayout.setTitle(title);
        mToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
        mToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);
        mToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBarPlus1);
        mToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBarPlus1);
    }

    private void shareAsText() {

    }

}
