package com.example.rosavtodorproject2.ui.view.chatsFragment

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.example.rosavtodorproject2.R
import com.example.rosavtodorproject2.databinding.CurrentUserNameEditingDialogFragmentBinding

class CurrentUserNameEditingDialogFragment(
    private val currentUserOldName: String?,
    private val targetFragment: CurrentUserNameChangedListener
) : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder = AlertDialog.Builder(requireActivity())
        val inflater = requireActivity().layoutInflater;

        val binding = CurrentUserNameEditingDialogFragmentBinding.inflate(inflater)

        binding.currentUserOldName.text = currentUserOldName

        builder.setView(
            binding.root
        )
            .setPositiveButton(R.string.dialog_window_positive_button_text)
            { _, _ ->
                targetFragment.onCurrentUserNameChanged(
                    dialog?.findViewById<EditText>(R.id.current_user_new_name)?.text.toString()
                )
            }
            .setNegativeButton(R.string.dialog_window_negative_button_text)
            { _, _ ->
                dialog?.cancel()
            }

        return builder.create()
    }
}