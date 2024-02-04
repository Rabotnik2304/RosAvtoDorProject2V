package com.example.rosavtodorproject2.ui.view.ChatsWindow

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.rosavtodorproject2.R

class CurrentUserNameEditingDialogFragment(private val currentUserOldName:String?) : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater;

            builder.setView(
                inflater.inflate(
                    R.layout.current_user_name_editing_dialog_fragment,
                    null
                )
            )
                .setPositiveButton(R.string.dialog_window_positive_button_text)
                { _, _ ->
                    Toast.makeText(
                        requireContext(),
                        "Ваше имя было изменено(Неть)",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                .setNegativeButton(R.string.dialog_window_negative_button_text)
                { _, _ ->
                    dialog?.cancel()
                }


            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}