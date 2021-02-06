package com.example.clicker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var tv_time: TextView
    lateinit var tv_clicks: TextView

    lateinit var b_start: Button
    lateinit var b_click: Button

    val totalTime = 10
    var currentTime = totalTime
    var currentClicks = 0

    lateinit var timer: CountDownTimer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv_time = findViewById(R.id.tv_time)
        tv_clicks = findViewById(R.id.tv_clicks)

        b_start = findViewById(R.id.b_start)
        b_click = findViewById(R.id.b_click)

        b_click.isEnabled = false

        b_start.setOnClickListener {
            currentTime = totalTime
            currentClicks = 0

            b_start.isEnabled = false
            b_click.isEnabled = true

            timer.start()
        }

        b_click.setOnClickListener {
            currentClicks++
            tv_clicks.text = "Clicks: $currentClicks"
        }

        timer = object : CountDownTimer((totalTime.toLong() * 1000), 1000) {
            override fun onTick(p0: Long) {
                currentTime--
                val time = currentTime + 1
                tv_time.text = "Time: $time"
            }

            override fun onFinish() {
                tv_time.text = "Time: 0"

                b_start.isEnabled = true
                b_click.isEnabled = false

                if(currentClicks <= 10) {
                    tv_clicks.text = "uhm... you can do better than $currentClicks clicks"

                } else if (currentClicks <= 30){
                    tv_clicks.text = "$currentClicks clicks"

                } else if (currentClicks <= 60){
                    tv_clicks.text = "Nice! $currentClicks clicks!"

                } else if (currentClicks <= 70){
                    tv_clicks.text = "Your getting good at this! $currentClicks clicks."

                } else if (currentClicks <= 100){
                    tv_clicks.text = "$currentClicks That's a lot of clicks!"

                } else {
                    tv_clicks.text = "$currentClicks clicks. How, just how."
                }
            }

        }
    }
}
