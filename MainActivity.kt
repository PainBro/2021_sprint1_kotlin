//Setting up packages
package com.example.clicker

//Importing objects from game
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView

//Creating main class
class MainActivity : AppCompatActivity() {
    //setting up textview object variables for future use
    lateinit var tv_time: TextView
    lateinit var tv_clicks: TextView

    //setting up button object variables for future use
    lateinit var b_start: Button
    lateinit var b_click: Button

    //setting up game variables/ values
    val totalTime = 10
    var currentTime = totalTime
    var currentClicks = 0

    //setting up timer for future use
    lateinit var timer: CountDownTimer

    //setting up on create function
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Setting textView variables to be objects from the view
        tv_time = findViewById(R.id.tv_time)
        tv_clicks = findViewById(R.id.tv_clicks)

        ////Setting button variables to be objects from the view
        b_start = findViewById(R.id.b_start)
        b_click = findViewById(R.id.b_click)

        //Disabling the click button until game starts
        b_click.isEnabled = false

        //Setting on click listener for the start button
        b_start.setOnClickListener {
            //Setting up variables for new game
            currentTime = totalTime
            currentClicks = 0

            //re-enabling the click button for the game
            b_start.isEnabled = false
            b_click.isEnabled = true

            //Starting the timer for the game
            timer.start()
        }

        //Setting on click listener for the click button
        b_click.setOnClickListener {
            //adding one to the score and displaying that on screen
            currentClicks++
            tv_clicks.text = "Clicks: $currentClicks"
        }

        //Setting up the timer object to go for 'totalTime' ticks where each tick is 1 second
        timer = object : CountDownTimer((totalTime.toLong() * 1000), 1000) {
            //setting up the on tick function so that each second the timer does stuff
            override fun onTick(p0: Long) {
                //Counts down the time and displays that on screen
                currentTime--
                val time = currentTime + 1
                tv_time.text = "Time: $time"
            }

            //Setting up what happens when the timer ends
            override fun onFinish() {
                //display how much time is left (none)
                tv_time.text = "Time: 0"

                //Disable the click button and re-enable the start button
                b_start.isEnabled = true
                b_click.isEnabled = false

                //Set up a short message for the player depending on their score
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
