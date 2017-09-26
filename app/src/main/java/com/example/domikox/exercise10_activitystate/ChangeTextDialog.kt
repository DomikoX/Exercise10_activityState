package com.example.domikox.exercise10_activitystate

import android.app.DialogFragment
import android.widget.Toast
import android.content.DialogInterface
import android.os.Bundle
import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.widget.EditText


/**
 * Created by DomikoX on 26-Sep-17.
 */
class ChangeTextDialog : DialogFragment() {

    interface ChangeTextListener {
        fun onNewTextCreated(newText: String)
    }

    private var mListener: ChangeTextListener? = null

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        try {
            mListener = activity as ChangeTextListener
        } catch (e: ClassCastException) {
            throw ClassCastException(activity.toString() + getString(R.string.listener_implement_error))
        }

    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder = AlertDialog.Builder(activity)
        val inflater = activity.layoutInflater
        val dialogView = inflater.inflate(R.layout.change_text_dialog, null)
        builder.setView(dialogView)
        val et: EditText = dialogView.findViewById<EditText>(R.id.item_name)


        builder.setTitle(R.string.dialog_title)
                .setPositiveButton(R.string.dialog_yes, DialogInterface.OnClickListener { _, _ ->
                    val name = et.text.toString()
                    if (name == null || name == "") return@OnClickListener
                    mListener?.onNewTextCreated(name)
                })
                .setNegativeButton(R.string.dialog_cancel, { _, _ -> Toast.makeText(activity, R.string.cancel, Toast.LENGTH_SHORT).show() })


        return builder.create()
    }
}