package com.ispsolutionsofficial.timersdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.ispsolutionsofficial.timersdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // Variable for Timer which will be initialized later.
    private var countDownTimer: CountDownTimer? = null

    // The duration of timer in milliseconds
    private var timerDuration:Long = 60000

    // pauseOffset = timerDuration - time left
    private var pauseOffset: Long = 0

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tvTimer.text = (timerDuration/1000).toString()

        binding.btnStart.setOnClickListener {
            startTimer(pauseOffset)
        }

        binding.btnPause.setOnClickListener {
            pauseTimer()
        }

        binding.btnReset.setOnClickListener {
            resetTimer()
        }
    }

    private fun resetTimer() {
        if(countDownTimer != null) {
            countDownTimer!!.cancel()
            binding.tvTimer.text = (timerDuration/1000).toString()
            countDownTimer = null
            pauseOffset = 0
        }
    }

    private fun pauseTimer() {
        if(countDownTimer != null) {
            countDownTimer!!.cancel()
        }
    }

    private fun startTimer(pauseOffset: Long) {
        countDownTimer = object : CountDownTimer(timerDuration - pauseOffset,1000) {
            override fun onTick(millisUntilFinished: Long) {
                this@MainActivity.pauseOffset = timerDuration - millisUntilFinished
                binding.tvTimer.text = (millisUntilFinished/1000).toString()
            }

            override fun onFinish() {
                Toast.makeText(this@MainActivity, "Timer is Over", Toast.LENGTH_SHORT).show()
            }
        }.start()
    }
}