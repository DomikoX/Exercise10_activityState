package com.example.domikox.exercise10_activitystate

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity(), ChangeTextDialog.ChangeTextListener {

    private val TEXTVIEW_STATEKEY: String = "TEXTVIEW_KEY"
    private var textView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.textView = findViewById<TextView>(R.id.textView)

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(TEXTVIEW_STATEKEY)) {
                val text = savedInstanceState.getString(TEXTVIEW_STATEKEY)
                textView?.text = text
            }
        }
    }

    override fun onNewTextCreated(newText: String) {
        this.textView?.text = newText
    }

    fun changeText(view: View) {
        val changeDialog = ChangeTextDialog()
        changeDialog.show(fragmentManager, "change_text")
    }


    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putString(TEXTVIEW_STATEKEY, textView?.text.toString())
        super.onSaveInstanceState(outState)
    }


}
