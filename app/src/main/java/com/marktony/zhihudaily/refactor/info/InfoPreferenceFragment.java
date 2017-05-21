package com.marktony.zhihudaily.refactor.info;

import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;

import com.marktony.zhihudaily.R;

/**
 * Created by lizhaotailang on 2017/5/21.
 */

public class InfoPreferenceFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        addPreferencesFromResource(R.xml.info_preference);
    }

}
