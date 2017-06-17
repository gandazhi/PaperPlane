package com.marktony.zhihudaily.refactor.details;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
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
import android.text.Html;
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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.marktony.zhihudaily.R;
import com.marktony.zhihudaily.refactor.customtabs.CustomTabsHelper;
import com.marktony.zhihudaily.refactor.data.ContentType;
import com.marktony.zhihudaily.refactor.data.DoubanMomentContent;
import com.marktony.zhihudaily.refactor.data.DoubanMomentThumbs;
import com.marktony.zhihudaily.refactor.data.GuokrHandpickContent;
import com.marktony.zhihudaily.refactor.data.ZhihuDailyContent;
import com.marktony.zhihudaily.refactor.util.InfoConstants;

import java.util.List;

import static android.content.Context.CLIPBOARD_SERVICE;

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

    public static int REQUEST_SHARE = 0;
    public static int REQUEST_COPY_LINK = 1;
    public static int REQUEST_OPEN_WITH_BROWSER = 2;

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
        } else if (mType == ContentType.TYPE_DOUBAN_MOMENT){
            mPresenter.loadDoubanContent(mId);
        } else {
            mPresenter.loadGuokrHandpickContent(mId);
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
            AppCompatTextView share = view.findViewById(R.id.text_view_share);

            if (mFavorite) {
                favorite.setText(R.string.unfavorite);
            }

            // add to bookmarks or delete from bookmarks
            favorite.setOnClickListener(v -> {
                dialog.dismiss();
                mPresenter.favorite(mType, !mFavorite);
                mFavorite = !mFavorite;
            });

            // copy the article's link to clipboard
            copyLink.setOnClickListener(v -> {
                mPresenter.getLink(mType, REQUEST_COPY_LINK, mId);
                dialog.dismiss();
            });

            // open the link in system browser
            openWithBrowser.setOnClickListener(v -> {
                mPresenter.getLink(mType, REQUEST_OPEN_WITH_BROWSER, mId);
                dialog.dismiss();
            });

            // getLink the content as text
            share.setOnClickListener(v -> {
                mPresenter.getLink(mType, REQUEST_SHARE, mId);
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
        Toast.makeText(getContext(), stringRes, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean isActive() {
        return isAdded() && isResumed();
    }

    @Override
    public void showZhihuDailyContent(@NonNull ZhihuDailyContent content) {
        mFavorite = content.isFavorite();

        if (content.getBody() != null) {
            String result = content.getBody();
            result = result.replace("<div class=\"img-place-holder\">", "");
            result = result.replace("<div class=\"headline\">", "");

            String css = "<link rel=\"stylesheet\" href=\"file:///android_asset/zhihu_daily.css\" type=\"text/css\">";

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
    public void showDoubanMomentContent(@NonNull DoubanMomentContent content, @Nullable List<DoubanMomentThumbs> list) {
        mFavorite = content.isFavorite();

        String css;
        String body = content.getContent();
        if (mIsNightMode) {
            css = "<link rel=\"stylesheet\" href=\"file:///android_asset/douban_dark.css\" type=\"text/css\">";
        } else {
            css = "<link rel=\"stylesheet\" href=\"file:///android_asset/douban_light.css\" type=\"text/css\">";
        }
        if (list != null && !list.isEmpty()) {
            setCover(list.get(0).getMedium().getUrl());
            for (DoubanMomentThumbs t : list) {
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
        mFavorite = content.getResult().isFavorite();

        setCover(content.getResult().getImageInfo().getUrl());

        String body = content.getResult().getContent();

        String css;

        if (mIsNightMode) {
            css = "<div class=\"article\" id=\"contentMain\" style=\"background-color:#212b30\">";
        } else {
            css = "<div class=\"article\" id=\"contentMain\">";
        }

        String result = "<!DOCTYPE html>\n"
                + "<html lang=\"ZH-CN\" xmlns=\"http://www.w3.org/1999/xhtml\">\n"
                + "<head>\n<meta charset=\"utf-8\" />\n"
                + "\n<link rel=\"stylesheet\" href=\"file:///android_asset/guokr_master.css\" />\n"
                + css
                + "<script src=\"file:///android_asset/guokr.base.js\"></script>\n"
                + "<script src=\"file:///android_asset/guokr.articleInline.js\"></script>"
                + "<script>\n"
                + "var ukey = null;\n"
                + "</script>\n"
                + "\n</head>\n<div class=\"content\" id=\"articleContent\"><body>\n"
                + body
                + "\n</div></body>\n</html>";
        mWebView.loadDataWithBaseURL("x-data://base", result,"text/html","utf-8",null);
    }

    @Override
    public void share(@Nullable String link) {
        try {
            Intent shareIntent = new Intent().setAction(Intent.ACTION_SEND).setType("text/plain");
            String shareText = "" + mTitle + " " + link;
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareText);
            startActivity(Intent.createChooser(shareIntent, getString(R.string.share_to)));
        } catch (android.content.ActivityNotFoundException ex) {
            showMessage(R.string.something_wrong);
        }
    }

    @Override
    public void copyLink(@Nullable String link) {
        if (link != null) {
            ClipboardManager manager = (ClipboardManager) getContext().getSystemService(CLIPBOARD_SERVICE);
            ClipData clipData = ClipData.newPlainText("text", Html.fromHtml(link).toString());
            manager.setPrimaryClip(clipData);
            showMessage(R.string.copied_to_clipboard);
        } else {
            showMessage(R.string.something_wrong);
        }
    }

    @Override
    public void openWithBrowser(@Nullable String link) {
        if (link != null) {
            CustomTabsHelper.openUrl(getContext(), link);
        } else {
            showMessage(R.string.something_wrong);
        }
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

}
