package com.example.weather

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Land : AppCompatActivity() {
    lateinit var b1:Button
    lateinit var b2:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_land)
        b1=findViewById(R.id.a1)
        b2=findViewById(R.id.a2)
        b1.setOnClickListener{
            val i= Intent(this,CitySplash::class.java)
            startActivity(i)
            finish()
        }
        b2.setOnClickListener{
            val i= Intent(this,SplashScreen::class.java)
            startActivity(i)
            finish()
        }
    }
}