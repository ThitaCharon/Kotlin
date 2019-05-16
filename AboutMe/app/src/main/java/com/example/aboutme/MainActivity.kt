package com.example.aboutme


import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.aboutme.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var  binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
//        val binding : PlainActivityBinding =
//            DataBindingUtil.setContentView(this, R.layout.plain_activity)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.btn_done).setOnClickListener {
            addNickName(it)
        }
    }

    private fun addNickName(view : View){
        val editText : EditText = findViewById(R.id.editText)
        val nickname : TextView = findViewById(R.id.nickname_text)

        nickname.text = editText.text
        nickname.visibility = View.VISIBLE
        editText.visibility = View.GONE
        // Hide the keyboard.
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}
