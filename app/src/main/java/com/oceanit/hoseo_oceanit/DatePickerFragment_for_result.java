package com.oceanit.hoseo_oceanit;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import android.view.View;
import android.widget.DatePicker;

import java.util.Calendar;

public class DatePickerFragment_for_result extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    View v;

    public DatePickerFragment_for_result() {

    }

    @SuppressLint("ValidFragment")
    public DatePickerFragment_for_result(View v) {
        this.v = v;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);              // MONTH : 0~11
        int day = c.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Result_edit result_edit = (Result_edit) getActivity();
        if (v.getId() == R.id.announcement_date_txt) {
            if (month + 1 < 10) {
                if (dayOfMonth < 10) {
                    result_edit.announcement_date_txt.setText(year + "-" + "0" + (month + 1) + "-" + "0" + dayOfMonth);
                } else {
                    result_edit.announcement_date_txt.setText(year + "-" + "0" + (month + 1) + "-" + dayOfMonth);
                }
            } else {
                if (dayOfMonth < 10) {
                    result_edit.announcement_date_txt.setText(year + "-" + (month + 1) + "-" + "0" + dayOfMonth);
                } else {
                    result_edit.announcement_date_txt.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                }
            }

        }

    }
}