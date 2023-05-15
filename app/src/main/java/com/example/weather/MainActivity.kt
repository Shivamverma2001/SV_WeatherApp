package com.example.weather

import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import java.lang.Math.ceil
import java.lang.Math.min

class MainActivity : ComponentActivity() {
    lateinit var city:TextView
    lateinit var coordinates:TextView
    lateinit var weather:TextView
    lateinit var temp:TextView
    lateinit var max_temp:TextView
    lateinit var min_temp:TextView
    lateinit var pressure:TextView
    lateinit var humidity:TextView
    lateinit var wind:TextView
    lateinit var degree:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        city=findViewById(R.id.city)
        coordinates=findViewById(R.id.coordinates)
        weather=findViewById(R.id.weather)
        temp=findViewById(R.id.temp)
        max_temp=findViewById(R.id.max_temp)
        min_temp =findViewById(R.id.min_temp)
        pressure=findViewById(R.id.pressure)
        wind=findViewById(R.id.wind)
        humidity=findViewById(R.id.humidity)
        degree=findViewById(R.id.degree)
        val lat=intent.getStringExtra("lat")
        var long=intent.getStringExtra("long")

        window.statusBarColor= Color.parseColor("#1383C3")
        getJsonData(lat,long)
    }



    private fun getJsonData(lat:String?,long:String?)
    {
        val API_KEY="337d7ac9737b1ef4b4faf57281898449"
        val queue = Volley.newRequestQueue(this)
        val url ="https://api.openweathermap.org/data/2.5/weather?lat=${lat}&lon=${long}&appid=${API_KEY}"
        val jsonRequest = JsonObjectRequest(
            Request.Method.GET, url,null,
            Response.Listener { response ->
                setValues(response)
            },
            Response.ErrorListener { Toast.makeText(this,"ERROR",Toast.LENGTH_LONG).show() })


        queue.add(jsonRequest)
    }

    private fun setValues(response: JSONObject){
        city.text=response.getString("name")
        var lat = response.getJSONObject("coord").getString("lat")
        var long=response.getJSONObject("coord").getString("lon")
        coordinates.text="${lat} , ${long}"
        weather.text=response.getJSONArray("weather").getJSONObject(0).getString("main")
        var tempr=response.getJSONObject("main").getString("temp")
        tempr=((((tempr).toFloat()-273.15)).toInt()).toString()
        temp.text="${tempr}°C"


        var mintemp=response.getJSONObject("main").getString("temp_min")
        mintemp=((((mintemp).toFloat()-273.15)).toInt()).toString()
        min_temp.text="Min : "+mintemp+"°C"
        var maxtemp=response.getJSONObject("main").getString("temp_max")
        maxtemp=((ceil((maxtemp).toFloat()-273.15)).toInt()).toString()
        max_temp.text="Max : "+maxtemp+"°C"

        pressure.text=response.getJSONObject("main").getString("pressure")
        humidity.text=response.getJSONObject("main").getString("humidity")+"%"
        wind.text=response.getJSONObject("wind").getString("speed")
        degree.text="Degree : "+response.getJSONObject("wind").getString("deg")+"°"
    }
}