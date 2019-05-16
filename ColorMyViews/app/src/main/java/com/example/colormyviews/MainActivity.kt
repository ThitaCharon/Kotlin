package com.example.colormyviews

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setListeners()
    }

    fun setListeners(){
        val clickableView : List<View> = listOf(box1,box2,box3,box4,box5,constraint_layout,
                                            red_button,yellow_button,blue_button)
        for(item in clickableView){
            item.setOnClickListener { makeColored(it) }
        }
    }
    fun makeColored(view: View) {
        when (view.id) {

            // Boxes using Color class colors for background
            R.id.box1 -> view.setBackgroundColor(Color.DKGRAY)
            R.id.box2-> view.setBackgroundResource(android.R.color.holo_orange_light)

            // Boxes using Android color resources for background
            R.id.box3 -> view.setBackgroundResource(android.R.color.holo_green_light)
            R.id.box4 -> view.setBackgroundResource(android.R.color.holo_green_dark)
            R.id.box5 -> view.setBackgroundResource(android.R.color.holo_green_light)
            // Button is clicked
            R.id.red_button-> box3.setBackgroundResource(android.R.color.holo_red_dark)
            R.id.yellow_button -> box4.setBackgroundResource(android.R.color.holo_orange_dark)
            R.id.blue_button -> box5.setBackgroundResource(android.R.color.holo_blue_dark)

            else -> view.setBackgroundColor(Color.LTGRAY)
        }
    }
}
