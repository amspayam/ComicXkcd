package io.shortcut.android.xkcd.comics.uikit.base

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import io.shortcut.android.xkcd.comics.uikit.R
import io.shortcut.android.xkcd.comics.uikit.base.viewmodel.BaseViewModel
import io.shortcut.android.xkcd.comics.uikit.base.viewmodel.MessageMaster
import io.shortcut.android.xkcd.comics.uikit.base.viewmodel.MessageTypeEnum
import io.shortcut.android.xkcd.comics.uikit.components.snackbar.SnackBarComponent
import io.shortcut.android.xkcd.comics.uikit.components.snackbar.StateEnums
import io.shortcut.android.xkcd.comics.uikit.extension.permitive.dpToPx
import io.shortcut.android.xkcd.comics.uikit.utils.DeviceScreenUtils

abstract class BaseRoundedBottomSheetDialogFragment<VM : BaseViewModel> :
    BottomSheetDialogFragment() {

    open val viewModel: VM? = null

    override fun getTheme(): Int = R.style.BottomSheetComponent

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext(), theme)
        dialog.setOnShowListener {

            val bottomSheetDialog = it as BottomSheetDialog
            val parentLayout =
                bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            parentLayout?.let { it ->
                val behaviour = BottomSheetBehavior.from(it)
                setupFullHeight(it)
                behaviour.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }

        dialog.behavior.apply {
            state = BottomSheetBehavior.STATE_EXPANDED
            isDraggable = false
        }

        return dialog
    }

    private fun setupFullHeight(bottomSheet: View) {
        val layoutParams = bottomSheet.layoutParams
        layoutParams.height =
            DeviceScreenUtils.height(requireContext()) - 100.dpToPx(requireContext())
        bottomSheet.layoutParams = layoutParams
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        setupToolbar()
        setupBackStackEntry()
        setupObserveData()
    }

    abstract fun setupViews()

    open fun setupToolbar() {}

    open fun setupBackStackEntry() {}

    open fun setupObserveData() {}

    open fun showMessage(message: MessageMaster) {
        message.text = message.message ?: message.messageResourceId?.let {
            getString(it)
        } ?: "Error"
        when (message.type) {
            MessageTypeEnum.TOAST -> {
                Toast.makeText(
                    requireContext(),
                    message.text,
                    Toast.LENGTH_LONG
                ).show()
            }
            MessageTypeEnum.SNACK_BAR -> {
                SnackBarComponent(view, message.text, StateEnums.ERROR)
            }
        }
    }

}