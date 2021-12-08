package io.shortcut.android.xkcd.comics.uikit.components.snackbar

import android.graphics.Color
import android.util.TypedValue
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import io.shortcut.android.xkcd.comics.uikit.R

class SnackBarComponent(
    view: View?,
    message: String?,
    state: StateEnums?
) {

    companion object {
        const val VIEW_PARENT = android.R.id.content
    }

    init {
        if (view != null && message?.isNotBlank() == true && state != null) {
            // Show bottom snackBar
            val snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG)
            val snackbarView = snackbar.view

            // Change textView
            val textView =
                snackbarView.findViewById<View>(com.google.android.material.R.id.snackbar_text) as TextView
            textView.setTextColor(Color.WHITE)
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f)
            textView.layoutDirection = View.LAYOUT_DIRECTION_RTL
            textView.textAlignment = View.TEXT_ALIGNMENT_CENTER

            // Change background color
            when (state) {
                StateEnums.SUCCESS -> snackbar.view
                    .setBackgroundColor(ContextCompat.getColor(view.context, R.color.green))
                StateEnums.WARNING -> snackbar.view
                    .setBackgroundColor(ContextCompat.getColor(view.context, R.color.orange))
                StateEnums.ERROR -> snackbar.view
                    .setBackgroundColor(ContextCompat.getColor(view.context, R.color.red))
            }

            // Show the snackbar
            snackbar.show()
        }
    }
}