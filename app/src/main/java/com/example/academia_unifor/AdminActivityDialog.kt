package com.example.academia_unifor

import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ArrayAdapter
import android.widget.PopupWindow
import android.widget.TextView
import androidx.annotation.ArrayRes
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.appcompat.widget.ListPopupWindow
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import com.example.academia_unifor.databinding.DialogAdminActivityBinding

/** Expanded activity and add-activity panels, with presentation-only selectors. */
class AdminActivityDialog : AppCompatDialogFragment() {
    private var viewBinding: DialogAdminActivityBinding? = null
    private var selectorPopup: ListPopupWindow? = null
    private val createMode: Boolean get() = arguments?.getBoolean(ARG_CREATE) == true

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, state: Bundle?): View {
        return DialogAdminActivityBinding.inflate(inflater, container, false).also {
            viewBinding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = viewBinding ?: return
        val preview = AdminActivityPreview.entries.firstOrNull {
            it.name == arguments?.getString(ARG_PREVIEW)
        } ?: AdminActivityPreview.FUNCTIONAL
        binding.apply {
            heading.setText(if (createMode) R.string.admin_add_activity_heading else R.string.admin_next_activity)
            title.apply {
                isFocusable = createMode
                isFocusableInTouchMode = createMode
                isCursorVisible = createMode
                if (createMode) {
                    setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.admin_ic_edit, 0)
                } else {
                    setText(preview.title)
                }
            }
            time.setText(if (createMode) R.string.admin_time else preview.time)
            stats.students.setText(if (createMode) R.string.admin_placeholder else preview.students)
            stats.duration.setText(if (createMode) R.string.admin_placeholder else R.string.admin_one_hour)
            stats.room.setText(if (createMode) R.string.admin_placeholder else preview.room)
            teacher.setText(preview.teacher)
            teacher.isVisible = !createMode
            teacherSelector.setText(if (createMode) R.string.admin_add_teacher else R.string.admin_substitute_teacher)
            capacity.setText(preview.capacity)
            capacity.isVisible = !createMode
            capacitySelector.isVisible = createMode
            cancel.setText(if (createMode) R.string.admin_cancel else R.string.admin_cancel_activity)
            primaryAction.setText(if (createMode) R.string.admin_add_activity else R.string.admin_collapse_activity)
            // Buttons only transition back to the feed, without changing its sample data.
            cancel.setOnClickListener { dismiss() }
            primaryAction.setOnClickListener { dismiss() }
            teacherSelector.setOnClickListener {
                showSelector(teacherSelector, R.array.admin_teachers)
            }
            statusSelector.setOnClickListener {
                showSelector(statusSelector, R.array.admin_statuses)
            }
            capacitySelector.setOnClickListener {
                showSelector(capacitySelector, R.array.admin_capacities)
            }
            if (createMode) {
                time.isClickable = true
                time.isFocusable = true
                time.setOnClickListener {
                    showSelector(time, R.array.admin_times, openCalendar = true)
                }
                // The visual time badge retains a minimum 48dp interaction area.
                time.post {
                    val bounds = Rect()
                    time.getHitRect(bounds)
                    val inset = ((dp(48) - bounds.height()) / 2).coerceAtLeast(0)
                    bounds.inset(0, -inset)
                    (time.parent as View).touchDelegate = android.view.TouchDelegate(bounds, time)
                }
            }
            savedInstanceState?.let { state ->
                teacherSelector.text = state.getCharSequence(STATE_TEACHER, teacherSelector.text)
                capacitySelector.text = state.getCharSequence(STATE_CAPACITY, capacitySelector.text)
                statusSelector.text = state.getCharSequence(STATE_STATUS, statusSelector.text)
                time.text = state.getCharSequence(STATE_TIME, time.text)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        val window = dialog?.window ?: return
        val metrics = resources.displayMetrics
        val insets = ViewCompat.getRootWindowInsets(requireActivity().window.decorView)
            ?.getInsets(WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.displayCutout())
        val availableWidth = metrics.widthPixels - (insets?.left ?: 0) - (insets?.right ?: 0)
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        window.setDimAmount(0.6f)
        window.setLayout(minOf(availableWidth - dp(56), dp(480)), ViewGroup.LayoutParams.WRAP_CONTENT)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        window.attributes = window.attributes.apply {
            gravity = if (createMode) Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL else Gravity.CENTER
            if (createMode) y = dp(84)
            windowAnimations = R.style.AdminPanelAnimation
        }
    }

    private fun showSelector(anchor: TextView, @ArrayRes choices: Int, openCalendar: Boolean = false) {
        selectorPopup?.dismiss()
        val options = resources.getStringArray(choices)
        selectorPopup = ListPopupWindow(requireContext()).apply {
            setAdapter(ArrayAdapter(requireContext(), R.layout.admin_popup_item, options))
            anchorView = anchor
            width = maxOf(anchor.width, dp(164))
            isModal = true
            inputMethodMode = PopupWindow.INPUT_METHOD_NOT_NEEDED
            setBackgroundDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.admin_popup_background))
            setOnItemClickListener { _, _, position, _ ->
                anchor.text = options[position]
                dismiss()
                if (openCalendar && childFragmentManager.findFragmentByTag(AdminCalendarDialog.TAG) == null) {
                    AdminCalendarDialog().show(childFragmentManager, AdminCalendarDialog.TAG)
                }
            }
            show()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        viewBinding?.let {
            outState.putCharSequence(STATE_TEACHER, it.teacherSelector.text)
            outState.putCharSequence(STATE_CAPACITY, it.capacitySelector.text)
            outState.putCharSequence(STATE_STATUS, it.statusSelector.text)
            outState.putCharSequence(STATE_TIME, it.time.text)
        }
        super.onSaveInstanceState(outState)
    }

    override fun onDestroyView() {
        selectorPopup?.dismiss()
        selectorPopup = null
        viewBinding = null
        super.onDestroyView()
    }

    private fun dp(value: Int): Int = (value * resources.displayMetrics.density).toInt()

    companion object {
        internal const val TAG = "admin_activity_panel"
        private const val ARG_CREATE = "create"
        private const val ARG_PREVIEW = "preview"
        private const val STATE_TEACHER = "teacher"
        private const val STATE_CAPACITY = "capacity"
        private const val STATE_STATUS = "status"
        private const val STATE_TIME = "time"

        internal fun newInstance(preview: AdminActivityPreview, create: Boolean) = AdminActivityDialog().apply {
            arguments = Bundle().apply {
                putBoolean(ARG_CREATE, create)
                putString(ARG_PREVIEW, preview.name)
            }
        }
    }
}
