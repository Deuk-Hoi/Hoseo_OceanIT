package com.oceanit.hoseo_oceanit;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    View v;

    public DatePickerFragment ()
    {
    }

    @SuppressLint("ValidFragment")
    public DatePickerFragment(View v) // 생성자로부터 클릭하는 ID값을 받아오기위한 작업
    {
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
        Log.e("view",view.toString());
        ResearchField_edit researchField_edit = (ResearchField_edit) getActivity();  //적용할 액티비티 내용을 불러옴
        if(v.getId()==R.id.start_time) {
            if (month + 1 < 10) {
                if (dayOfMonth < 10) {
                    researchField_edit.start_time.setText(year + "-" + "0" + (month + 1) + "-" + "0" + dayOfMonth);
                } else {
                    researchField_edit.start_time.setText(year + "-" + "0" + (month + 1) + "-" + dayOfMonth);
                }
            } else {
                if (dayOfMonth < 10) {
                    researchField_edit.start_time.setText(year + "-" + (month + 1) + "-" + "0" + dayOfMonth);
                } else {
                    researchField_edit.start_time.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                }

            }
        }
        else if(v.getId()==R.id.end_time)
        {
            if (month + 1 < 10) {
                if (dayOfMonth < 10) {
                    researchField_edit.end_time.setText(year + "-" + "0" + (month + 1) + "-" + "0" + dayOfMonth);
                } else {
                    researchField_edit.end_time.setText(year + "-" + "0" + (month + 1) + "-" + dayOfMonth);
                }
            } else {
                if (dayOfMonth < 10) {
                    researchField_edit.end_time.setText(year + "-" + (month + 1) + "-" + "0" + dayOfMonth);
                } else {
                    researchField_edit.end_time.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                }

            }
        }

    }
}
