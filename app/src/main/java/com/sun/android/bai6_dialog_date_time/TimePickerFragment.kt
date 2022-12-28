package com.sun.android.bai6_dialog_date_time

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import java.text.DateFormat
import java.util.Calendar

class TimePickerFragment : DialogFragment(), TimePickerDialog.OnTimeSetListener {
    override fun onCreateDialog(saveInstanceState: Bundle?): Dialog {
        val calendar = Calendar.getInstance()
        val hour: Int = calendar.get(Calendar.HOUR_OF_DAY)
        val minute: Int = calendar.get(Calendar.MINUTE)
        return TimePickerDialog(requireContext(), this, hour, minute, true)
    }

    override fun onTimeSet(timePicker: TimePicker, hourOfDay: Int, minute: Int) {
        (activity as? MainActivity)?.processTimePickerResult(hourOfDay, minute)
    }
}
