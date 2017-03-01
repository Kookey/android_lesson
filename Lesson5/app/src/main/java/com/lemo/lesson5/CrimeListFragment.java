package com.lemo.lesson5;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.lemo.model.Crime;
import com.lemo.model.CrimeLab;

import java.util.List;

/**
 * Created by wxl19 on 2017/2/27.
 */

public class CrimeListFragment extends ListFragment {

    private List<Crime> mCrimes;

    private static final String TAG = "CrimeListFragment";

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // Crime crime = (Crime) getListAdapter().getItem(position);
        Crime crime = ((CrimeAdapter) getListAdapter()).getItem(position);
//        Log.i(TAG, "onListItemClick: "+crime.getTitle());
//        Intent intent = new Intent(getActivity(), CrimeActivity.class);
        Intent intent = new Intent(getActivity(), CrimePagerActivity.class);
        intent.putExtra(CrimeFragment.EXTRA_CRIME_ID,crime.getId());
        startActivity(intent);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.crimes_title);
        mCrimes = CrimeLab.get(getActivity()).getCrimes();

//        ArrayAdapter adapter = new ArrayAdapter<Crime>(getActivity(),android.R.layout.simple_list_item_1,mCrimes);
        CrimeAdapter adapter = new CrimeAdapter(mCrimes);
        setListAdapter(adapter);
    }

    private class CrimeAdapter extends ArrayAdapter<Crime> {

        public CrimeAdapter(List<Crime> crimes) {
            super(getActivity(), 0, crimes);
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item_crime, null);
            }
            Crime crime = getItem(position);
            TextView titleTextView = (TextView) convertView.findViewById(R.id.crime_list_item_title);
            TextView dateTextView = (TextView) convertView.findViewById(R.id.crime_list_item_date);
            CheckBox solvedCheckBox = (CheckBox) convertView.findViewById(R.id.crime_list_item_solved);
            titleTextView.setText(crime.getTitle());
            dateTextView.setText(crime.getDateStr());
            solvedCheckBox.setChecked(crime.isSolved());
            return convertView;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        ((CrimeAdapter)getListAdapter()).notifyDataSetChanged();
    }
}
