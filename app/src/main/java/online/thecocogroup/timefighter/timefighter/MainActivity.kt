package online.thecocogroup.timefighter.timefighter

import android.content.IntentSender
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.view.*




class MainActivity : AppCompatActivity() {

    internal lateinit var tapMeButton: Button
    internal lateinit var gameScoreTextView: TextView
    internal lateinit var timeLeftTextView : TextView
    internal var score = 0
    internal var gameStarted = false
    internal lateinit var countDownTimer : CountDownTimer
    internal val initialCountDown: Long = 30000
    internal val countDownInterval: Long = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tapMeButton = findViewById<Button>(R.id.tap_me_button)
        gameScoreTextView = findViewById<TextView>(R.id.game_score_textview)
        timeLeftTextView = findViewById<TextView>(R.id.time_left_textview)
        gameScoreTextView.text = getString(R.string.your_score, score.toString())
        resetGame()

        tapMeButton.setOnClickListener{
            view ->
            incrementScore()
        }

    }
    private fun resetGame(){
        score = 0
        gameScoreTextView.text = getString(R.string.your_score, score.toString())
        val initialTimeLeft = initialCountDown / 1000
        timeLeftTextView.text = getString(R.string.time_left, initialTimeLeft.toString())

        countDownTimer = object: CountDownTimer(initialCountDown, countDownInterval){
            override fun onTick(millisUntilFinished: Long) {
                val timeLeft = millisUntilFinished / 1000
                timeLeftTextView.text = getString(R.string.time_left, timeLeft.toString())

            }

            override fun onFinish() {
                endGame()

            }

        }

        gameStarted = false



    }
    private fun endGame() {
        Toast.makeText(this, getString(R.string.game_over_message,score.toString()),Toast.LENGTH_SHORT).show()
        resetGame()

    }

    private fun incrementScore() {
        if (!gameStarted)
        {
            countDownTimer.start()
            gameStarted = true
        }
        score = score + 1
        val newScore = getString(R.string.your_score, score.toString())
        gameScoreTextView.text = newScore


    }
}

