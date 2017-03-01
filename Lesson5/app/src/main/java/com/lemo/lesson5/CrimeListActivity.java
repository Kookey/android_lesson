package com.lemo.lesson5;

import android.support.v4.app.Fragment;

/**
 * Created by wxl19 on 2017/2/27.
 */

public class CrimeListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
