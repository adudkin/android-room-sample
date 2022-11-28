package com.example.roomdatabase

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText

class NewItemActivity : AppCompatActivity() {
    private lateinit var editNameView: EditText
    private lateinit var editEnergyValueView: EditText

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_item)
        editNameView = findViewById(R.id.new_product_name)
        editEnergyValueView = findViewById(R.id.new_product_energy_value)

        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editNameView.text) || TextUtils.isEmpty(editEnergyValueView.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                replyIntent.putExtra(EXTRA_NEW_ITEM_NAME, editNameView.text.toString())
                replyIntent.putExtra(EXTRA_NEW_ITEM_VALUE, editEnergyValueView.text.toString().toInt())
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    companion object {
        const val EXTRA_NEW_ITEM_NAME = "new_item_name"
        const val EXTRA_NEW_ITEM_VALUE = "new_item_value"
    }
}