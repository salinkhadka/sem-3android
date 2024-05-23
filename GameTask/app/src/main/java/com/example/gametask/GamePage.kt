package com.example.gametask

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.gametask.databinding.ActivityGamePageBinding
import kotlin.random.Random

class GamePage : AppCompatActivity() {

    lateinit var GamePage: ActivityGamePageBinding
    var Score: Int = 0
    var life: Int = 3
    var number1 = Random.nextInt(0, 100)
    var number2 = Random.nextInt(0, 100)
    lateinit var timer: CountDownTimer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        GamePage = ActivityGamePageBinding.inflate(layoutInflater)
        setContentView(GamePage.root)

        GamePage.textView.text = "Score : $Score"
        GamePage.textView2.text = "Life : $life"
        GamePage.button2.isEnabled = false

        val calc: String = intent.getStringExtra("operator").toString()
        setQuestion(calc)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun setQuestion(calc: String) {
        when (calc) {
            "add" -> GamePage.textView4.text = "$number1 + $number2"
            "sub" -> GamePage.textView4.text = "$number1 - $number2"
            "mul" -> GamePage.textView4.text = "$number1 * $number2"
        }
        startTimer()
        GamePage.Check.setOnClickListener {
            checkAnswer(calc)
        }
    }

    private fun checkAnswer(calc: String) {
        GamePage.button2.isEnabled = true
        if (life > 0) {
            val input = GamePage.editTextText.text.toString()
            val checkInput = input.toIntOrNull() ?: 0
            val ans = when (calc) {
                "add" -> number1 + number2
                "sub" -> number1 - number2
                "mul" -> number1 * number2
                else -> 0
            }

            if (checkInput == ans) {
                Score += 10
                GamePage.textView.text = "Score : $Score"
                GamePage.Check.isEnabled = false
                GamePage.button2.setOnClickListener {
                    nextQuestion(calc)
                }
            } else {
                life--
                GamePage.textView2.text = "Life : $life"
                GamePage.button2.isEnabled = true
                if (life <= 0) {
                    gameOver()
                }
            }
        } else {
            gameOver()
        }
    }

    private fun nextQuestion(calc: String) {
        number1 = Random.nextInt(0, 100)
        number2 = Random.nextInt(0, 100)
        GamePage.editTextText.setText("")
        GamePage.Check.isEnabled = true
        GamePage.button2.isEnabled = false
        setQuestion(calc)
    }

    private fun startTimer() {
        timer = object : CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                GamePage.textView3.text = "Time : ${millisUntilFinished / 1000}"
            }

            override fun onFinish() {
                life--
                GamePage.textView2.text = "Life : $life"
                if (life > 0) {
                    nextQuestion(intent.getStringExtra("operator").toString())
                } else {
                    gameOver()
                }
            }
        }
        timer.start()
    }

    private fun gameOver() {
        GamePage.textView4.text = "Game Over"
        GamePage.Check.isEnabled = false
        timer.cancel()
        GamePage.button2.setOnClickListener {
            val intent = Intent(this@GamePage, GameOver::class.java)
            val message = Score.toString()
            intent.putExtra("Score", message)
            Log.d("message1", message)
            startActivity(intent)
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        timer.cancel()
    }
}
