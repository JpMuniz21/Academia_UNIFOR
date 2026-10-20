package com.example.academia_unifor

import android.app.Dialog
import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.ViewGroup
import android.widget.CalendarView
import androidx.appcompat.app.AppCompatDialog
import androidx.appcompat.app.AppCompatDialogFragment
import java.util.Calendar
import java.util.Locale

/** Calendar presentation matching the September 2026 sample shown on page 43. */
class AdminCalendarDialog : AppCompatDialogFragment() {
    // Only a framework CalendarView is localized; app resources/locales do not change.
    @SuppressLint("AppBundleLocaleChanges")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val configuration = Configuration(resources.configuration).apply {
            setLocale(Locale.forLanguageTag("pt-BR"))
        }
        val localizedContext = requireContext().createConfigurationContext(configuration)
        val calendarContext = ContextThemeWrapper(localizedContext, R.style.AdminCalendarTheme)
        val referenceDate = Calendar.getInstance().apply { set(2026, Calendar.SEPTEMBER, 29, 12, 0, 0) }
        val calendar = CalendarView(calendarContext).apply {
            contentDescription = getString(R.string.admin_calendar)
            firstDayOfWeek = Calendar.SUNDAY
            date = referenceDate.timeInMillis
            setOnDateChangeListener { _, _, _, _ -> dismiss() }
        }
        // Keep the Activity's window token; the localized context is only for the view.
        return AppCompatDialog(requireActivity(), R.style.AdminCalendarTheme).apply {
            setContentView(calendar, ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT))
        }
    }

    override fun onStart() {
        super.onStart()
        val width = resources.displayMetrics.widthPixels
        val margin = (56 * resources.displayMetrics.density).toInt()
        val maxWidth = (480 * resources.displayMetrics.density).toInt()
        dialog?.window?.setLayout(minOf(width - margin, maxWidth), ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    companion object {
        internal const val TAG = "admin_calendar"
    }
}
