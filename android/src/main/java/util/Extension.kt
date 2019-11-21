package util

import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.FragmentManager
import com.squareup.picasso.Picasso
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnDateSetListener
import drodobytecom.lostpet.R
import java.text.SimpleDateFormat
import java.util.*

fun ImageView.xLoadPetIcon(url: String) =
    if (url.isBlank())
        Picasso.get()
            .load(R.drawable.ic_alert_error)
            .resize(140, 140)
            .centerCrop()
            .into(this)
    else
        Picasso.get()
            .load(url)
            .resize(140, 140)
            .centerCrop()
            .error(R.drawable.ic_alert_error)
            .placeholder(R.drawable.ic_downloading)
            .into(this)

fun ImageView.xLoadPet(url: String) =
    if (url.isBlank())
        Picasso.get()
            .load(R.drawable.ic_alert_error)
            .into(this)
    else
        Picasso.get()
            .load(url)
            .error(R.drawable.ic_alert_error)
            .placeholder(R.drawable.ic_downloading)
            .into(this)

fun Date.xFormatted(): String = DATE_FORMAT.format(this)

fun Date.xFormatted(year: Int, month: Int, day: Int): String = "$year/$month/$day"

fun Date.xShow(fragmentManager: FragmentManager?, date: String, onChange: OnDateSetListener) {
    val now = Calendar.getInstance()
    now.time = DATE_FORMAT.parse(date)!!
    DatePickerDialog.newInstance(
        onChange,
        now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH)
    )
        .show(fragmentManager!!, "picker")
}

fun EditText.xShowDialog(fragmentManager: FragmentManager?) {
    Date().xShow(fragmentManager, text.toString(),
        OnDateSetListener { _, y, m, d ->
            setText(Date().xFormatted(y, m, d))
        })
}

fun EditText.xDate(date: Date) {
    setText(date.xFormatted())
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

private val DATE_FORMAT = SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH)