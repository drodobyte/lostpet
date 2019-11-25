package util

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import com.squareup.picasso.Picasso
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import drodobytecom.lostpet.R
import java.text.SimpleDateFormat
import java.util.*
import java.util.Calendar.*

fun ImageView.xLoadPetIcon(url: String) =
    Picasso.get()
        .load(if (url.isBlank()) "_undef_" else url)
        .resize(140, 140)
        .centerCrop()
        .error(R.drawable.ic_alert_error)
        .placeholder(R.drawable.ic_downloading)
        .into(this)

fun ImageView.xLoadPet(url: String) =
    Picasso.get()
        .load(if (url.isBlank()) "_undef_" else url)
        .error(R.drawable.ic_alert_error)
        .placeholder(R.drawable.ic_downloading)
        .into(this)

fun Date.xFormatted(): String = DATE_FORMAT.format(this)

fun Date.xShowDialog(
    fragmentManager: FragmentManager?, onDateSet: (Date) -> Unit
) {
    val now = asCalendar()
    DatePickerDialog.newInstance(
        { _, year, month, day ->
            val date = Triple(year, month, day).asDate()
            time = date.time
            onDateSet(date)
        },
        now.get(YEAR), now.get(MONTH), now.get(DAY_OF_MONTH)
    ).show(fragmentManager!!, "picker")
}

fun TextView.xDate(date: Date) {
    text = date.xFormatted()
}

fun View.xShow() {
    visibility = View.VISIBLE
}

fun View.xHide() {
    visibility = View.INVISIBLE
}

fun View.xGone() {
    visibility = View.GONE
}

fun View.xShow(show: Boolean) {
    if (show) xShow() else xHide()
}

private val DATE_FORMAT = SimpleDateFormat("yyyy/MMM/dd", Locale.getDefault())