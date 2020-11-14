package com.example.comegci428a21016

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.ActionBar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.menu_layout.*

class MainActivity2 : AppCompatActivity() {

    private var newNum = 0
    private var numList = arrayListOf<Int>()
    private var counterI = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val actionBar = supportActionBar
        actionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        actionBar.setCustomView(R.layout.menu_layout)
        floatTV.text = "Lucky Draw Result"

        imageView22.setImageResource(R.drawable.bg_transparent)

        newNum = intent.getIntExtra("newNum",0)
        rand5nums()

        textView22.setText("$newNum")
        textView24.setText("${numList[0]}, ${numList[1]}, ${numList[2]}, ${numList[3]}, ${numList[4]}")

        if(numList.contains(newNum)){
            Log.d("test","Win")
            textView23.setText("You Win!!!")
            imageView21.setImageResource(R.drawable.ic_star)
        } else {
            Log.d("test","Lose")
            textView23.setText("You Lose!!!")
            imageView21.setImageResource(R.drawable.ic_mail)
        }

        button21.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }
    }

    private fun rand5nums(){
        val num = kotlin.random.Random.nextInt(20, 40)
        if(counterI>4) {
            return
        } else {
            if(!numList.contains(num)) {
                numList.add(num)
                counterI++
                rand5nums()
            } else {
                rand5nums()
            }
        }
    }

}