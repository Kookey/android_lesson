package com.example.wxl19.criminalintent;

import android.support.v4.app.Fragment;

/**
 * Created by wxl19 on 2016/6/5.
 */
public class CrimeListActivity extends  SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
       return new CrimeListFragment();
    }
}
