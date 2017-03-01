package com.lemo.lesson5;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by wxl19 on 2017/2/28.
 */

public class TimePickerFragment extends DialogFragment {

    private static final String TAG = "TimePickerFragment";

    public static final String EXTRA_TIME = "com.lemo.lesson5.time";
    private Date mDate;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mDate = (Date) getArguments().getSerializable(EXTRA_TIME);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mDate);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);

        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_time, null);
//        view.findViewById(R.id.dial)
        TimePicker timePicker = (TimePicker) view.findViewById(R.id.dialog_date_timepicker);
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                mDate = (Date) getArguments().getSerializable(EXTRA_TIME);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(mDate);
                calendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
                calendar.set(Calendar.MINUTE,minute);
                Date time = calendar.getTime();
                getArguments().putSerializable(EXTRA_TIME,time);
            }
        });

        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.time_picker_title)
                .setView(view)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sendResult(Activity.RESULT_OK);
                    }
                })
                .create();
    }

    public static TimePickerFragment newInstance(Date date){
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRA_TIME,date);
        TimePickerFragment fragment = new TimePickerFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    private void sendResult(int resultCode){
        Fragment fragment = getTargetFragment();
        if (fragment == null){
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(EXTRA_TIME,mDate);
        fragment.onActivityResult(getTargetRequestCode(),resultCode,intent);
    }
}
