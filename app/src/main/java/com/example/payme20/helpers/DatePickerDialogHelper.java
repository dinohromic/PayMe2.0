package com.example.payme20.helpers;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import java.util.Calendar;

/**
 * Responsible for functionality used with a DatePickerDialog
 */
public class DatePickerDialogHelper {

    private final int year;
    private final int month;
    private final int day;

    public DatePickerDialogHelper(){
        Calendar calendar = Calendar.getInstance();
        this.year = calendar.get(Calendar.YEAR);
        this.month = calendar.get(Calendar.MONTH);
        this.day = calendar.get(Calendar.DAY_OF_MONTH);
    }

    public DatePickerDialog initiateDatePickerDialog(DatePickerDialog.OnDateSetListener dataSetListener, Context context){
        int style = AlertDialog.THEME_HOLO_LIGHT;
        DatePickerDialog datePickerDialog = new DatePickerDialog(context, style, dataSetListener, this.year, this.month, this.day);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        return datePickerDialog;
    }

    public String getCurrentDate() {
        int month = this.month +1;
        return makeDateString(this.day, month, this.year);
    }

    public String makeDateString(int day, int month, int year) {
        return year + "-" + month + "-" + day;
    }

}
