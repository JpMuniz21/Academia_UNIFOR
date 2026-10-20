package com.example.academia_unifor

import android.os.Bundle
import android.view.Gravity
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.academia_unifor.databinding.ActivityFeedAdministradorBinding

/** Static administrator feed and navigation between the PDF's presentation states. */
class FeedAdministradorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFeedAdministradorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityFeedAdministradorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        WindowInsetsControllerCompat(window, window.decorView).apply {
            isAppearanceLightStatusBars = false
            isAppearanceLightNavigationBars = false
        }
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { view, insets ->
            val bars = insets.getInsets(
                WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.displayCutout()
            )
            view.setPadding(bars.left, bars.top, bars.right, bars.bottom)
            insets
        }
        ViewCompat.requestApplyInsets(binding.root)

        binding.backButton.setOnClickListener { onBackPressedDispatcher.onBackPressed() }
        binding.nextActivity.viewActivity.setOnClickListener {
            showActivityPanel(AdminActivityPreview.FUNCTIONAL)
        }
        binding.strengthActivity.root.apply {
            contentDescription = getString(R.string.admin_open_activity, getString(R.string.admin_strength))
            setOnClickListener { showActivityPanel(AdminActivityPreview.STRENGTH) }
        }
        binding.yogaActivity.apply {
            icon.setImageResource(R.drawable.admin_ic_yoga)
            title.setText(R.string.admin_yoga)
            time.setText(R.string.admin_time_yoga)
            students.setText(R.string.admin_students_yoga)
            root.contentDescription = getString(R.string.admin_open_activity, getString(R.string.admin_yoga))
            root.setOnClickListener { showActivityPanel(AdminActivityPreview.YOGA) }
        }
        binding.addActivity.setOnClickListener { showActivityPanel(create = true) }
        renderWeek()
    }

    private fun showActivityPanel(
        preview: AdminActivityPreview = AdminActivityPreview.FUNCTIONAL,
        create: Boolean = false
    ) {
        if (supportFragmentManager.findFragmentByTag(AdminActivityDialog.TAG) == null) {
            AdminActivityDialog.newInstance(preview, create)
                .show(supportFragmentManager, AdminActivityDialog.TAG)
        }
    }

    private fun renderWeek() {
        val initials = resources.getStringArray(R.array.admin_week_initials)
        val descriptions = resources.getStringArray(R.array.admin_week_descriptions)
        initials.forEachIndexed { index, initial ->
            val container = LinearLayout(this).apply {
                gravity = Gravity.CENTER
                layoutParams = LinearLayout.LayoutParams(0, dp(48), 1f)
            }
            container.addView(AppCompatTextView(this).apply {
                text = initial
                textSize = 20f
                gravity = Gravity.CENTER
                includeFontPadding = false
                contentDescription = descriptions[index]
                setTextColor(ContextCompat.getColor(context, when (index) {
                    0, 1 -> R.color.admin_primary
                    2 -> R.color.admin_text
                    else -> R.color.admin_outline
                }))
                setBackgroundResource(when (index) {
                    0, 1 -> R.drawable.admin_day_done
                    2 -> R.drawable.admin_day_today
                    else -> R.drawable.admin_day_pending
                })
                layoutParams = LinearLayout.LayoutParams(dp(36), dp(36))
            })
            binding.weekDays.addView(container)
        }
    }

    private fun dp(value: Int): Int = (value * resources.displayMetrics.density).toInt()
}
