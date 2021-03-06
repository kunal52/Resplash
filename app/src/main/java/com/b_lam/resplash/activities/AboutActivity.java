package com.b_lam.resplash.activities;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.b_lam.resplash.R;
import com.b_lam.resplash.Resplash;
import com.b_lam.resplash.util.ThemeUtils;
import com.google.firebase.analytics.FirebaseAnalytics;

import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.toolbar_about) Toolbar mToolbar;
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_about);

        ButterKnife.bind(this);

        Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_material, getTheme());
        upArrow.setColorFilter(ThemeUtils.getThemeAttrColor(this, R.attr.menuIconColor), PorterDuff.Mode.SRC_ATOP);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.about_title));

        LinearLayout [] containers = new LinearLayout[] {
                findViewById(R.id.container_about_unsplash),
                findViewById(R.id.container_about_app),
                findViewById(R.id.container_about_intro),
                findViewById(R.id.container_about_github),
                findViewById(R.id.container_about_privacy_policy),
                findViewById(R.id.container_about_rate),
                findViewById(R.id.container_about_donate),
                findViewById(R.id.container_about_bug),
                findViewById(R.id.container_about_author),
                findViewById(R.id.container_about_website),
                findViewById(R.id.container_about_instagram),
                findViewById(R.id.container_about_library1),
                findViewById(R.id.container_about_library2),
                findViewById(R.id.container_about_library3),
                findViewById(R.id.container_about_library4),
                findViewById(R.id.container_about_library5),
                findViewById(R.id.container_about_library6),
                findViewById(R.id.container_about_library7),
                findViewById(R.id.container_about_library9),
                findViewById(R.id.container_about_library10),
                findViewById(R.id.container_about_library11)};
        for (LinearLayout r : containers) {
            r.setOnClickListener(this);
        }

        PackageManager manager = getApplicationContext().getPackageManager();

        try {
            PackageInfo info = manager.getPackageInfo(getApplicationContext().getPackageName(), 0);
        }catch (PackageManager.NameNotFoundException e){
           return;
        }

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        mFirebaseAnalytics.logEvent(Resplash.FIREBASE_EVENT_VIEW_ABOUT, null);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.container_about_unsplash:
                goToURL("https://unsplash.com/" + Resplash.UNSPLASH_UTM_PARAMETERS);
                break;

            case R.id.container_about_intro:
                startActivity(new Intent(AboutActivity.this, IntroActivity.class));
                break;

            case R.id.container_about_github:
                goToURL("https://github.com/b-lam/Resplash");
                break;

            case R.id.container_about_privacy_policy:
                goToURL("https://b-lam.github.io/projects/resplash/privacy_policy");
                break;

            case R.id.container_about_rate:
                mFirebaseAnalytics.logEvent(Resplash.FIREBASE_EVENT_RATE_FROM_APP, null);
                goToURL("https://play.google.com/store/apps/details?id=com.b_lam.resplash");
                break;

            case R.id.container_about_donate:
                startActivity(new Intent(AboutActivity.this, DonateActivity.class));
                break;

            case R.id.container_about_bug:
                goToURL("https://github.com/b-lam/Resplash/issues");
                break;

            case R.id.container_about_website:
                goToURL("http://b-lam.github.io/");
                break;

            case R.id.container_about_instagram:
                goToURL("https://www.instagram.com/brandon.c.lam/");
                break;

            case R.id.container_about_library1:
                goToURL("https://github.com/square/retrofit");
                break;

            case R.id.container_about_library2:
                goToURL("https://github.com/bumptech/glide");
                break;

            case R.id.container_about_library3:
                goToURL("https://github.com/mikepenz/FastAdapter");
                break;

            case R.id.container_about_library4:
                goToURL("https://github.com/airbnb/lottie-android");
                break;

            case R.id.container_about_library5:
                goToURL("https://github.com/mikepenz/MaterialDrawer");
                break;

            case R.id.container_about_library6:
                goToURL("https://github.com/DavidPacioianu/InkPageIndicator");
                break;

            case R.id.container_about_library7:
                goToURL("https://github.com/JakeWharton/butterknife");
                break;

            case R.id.container_about_library9:
                goToURL("https://github.com/chrisbanes/PhotoView");
                break;

            case R.id.container_about_library10:
                goToURL("https://github.com/Clans/FloatingActionButton");
                break;

            case R.id.container_about_library11:
                goToURL("https://github.com/ocpsoft/prettytime");
                break;
        }
    }

    public void goToURL(String link) {
        Uri uri = Uri.parse(link);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        if (intent.resolveActivity(getPackageManager()) != null)
            startActivity(intent);
        else
            Toast.makeText(this, getString(R.string.error), Toast.LENGTH_SHORT).show();
    }
}
