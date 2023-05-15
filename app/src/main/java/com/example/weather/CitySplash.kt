package com.example.weather

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class CitySplash : AppCompatActivity() {
    lateinit var citylat: EditText
    lateinit var citylon:EditText
    lateinit var submit:Button
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city_splash)
        citylat=findViewById(R.id.lat)
        citylon=findViewById(R.id.lon)
        submit=findViewById(R.id.submit)
        submit.setOnClickListener{
            val intent= Intent(this,City::class.java)
            intent.putExtra("lat",citylat.text.toString())
            intent.putExtra("lon",citylon.text.toString())
            startActivity(intent)
        }

    }
}