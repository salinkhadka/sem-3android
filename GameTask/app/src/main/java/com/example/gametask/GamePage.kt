package com.example.gametask

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.gametask.databinding.ActivityGamePageBinding

import kotlin.random.Random
class GamePage : AppCompatActivity() {

    lateinit var GamePage:ActivityGamePageBinding
    var Score:Int=0;
    var life:Int=3;



    var number1= Random.nextInt(0,100)
    var number2= Random.nextInt(0,100)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        GamePage=ActivityGamePageBinding.inflate(layoutInflater)
        setContentView(GamePage.root)

        GamePage.textView.text="Score : "+Score
        GamePage.textView2.text="Life : "+life
        GamePage.textView3.text="Time : TODO"
        GamePage.button2.isEnabled=false
        var calc:String=intent.getStringExtra("operator").toString()

        if (calc=="add") {

       GamePage.textView4.text = "" + number1 + "   +  " + number2
                GamePage.Check.setOnClickListener {
                    GamePage.button2.isEnabled=true
                    if (life > 0) {


                        var input = GamePage.editTextText.text.toString();
                        var checkInput = input.toIntOrNull() ?: 0;
                        var ans: Int = (number1 + number2)

                        if (checkInput == ans) {
                            Score = Score + 10;
                            GamePage.textView.text = "Score : " + Score
                            GamePage.Check.isEnabled = false

                            GamePage.button2.setOnClickListener {
                                number1 = Random.nextInt(0, 100)
                                number2 = Random.nextInt(0, 100)
                                GamePage.textView4.text = "" + number1 + "   +  " + number2
                                var input = GamePage.editTextText.text.toString();
                                var checkInput = input.toIntOrNull() ?: 0;
                                var ans: Int = (number1 + number2)


                                GamePage.editTextText.setText("")
                                GamePage.Check.isEnabled = true
                                GamePage.button2.isEnabled=false


                            }





                        }
                        else {

                            life = life - 1;
                            GamePage.textView2.text = "Life : " + life
                            GamePage.button2.isEnabled=true
                        }
                    }
                    else    {
                        GamePage.textView4.text = "Game Over"
                        GamePage.Check.isEnabled =false
                        GamePage.button2.setOnClickListener {
                            var intent=Intent(this@GamePage,GameOver::class.java)
                            var message=Score.toString()
                            intent.putExtra("Score",message)
                            Log.d("message1",message)
                            startActivity(intent)
                            finish()
                        }


                    }
                }



        }

        if (calc=="sub") {

            GamePage.textView4.text = "" + number1 + "   -  " + number2
            GamePage.Check.setOnClickListener {
                GamePage.button2.isEnabled=true
                if (life > 0) {


                    var input = GamePage.editTextText.text.toString();
                    var checkInput = input.toIntOrNull() ?: 0;
                    var ans: Int = (number1-number2)

                    if (checkInput == ans) {
                        Score = Score + 10;
                        GamePage.textView.text = "Score : " + Score
                        GamePage.Check.isEnabled = false

                        GamePage.button2.setOnClickListener {
                            number1 = Random.nextInt(0, 100)
                            number2 = Random.nextInt(0, 100)
                            GamePage.textView4.text = "" + number1 + "   -  " + number2
                            var input = GamePage.editTextText.text.toString();
                            var checkInput = input.toIntOrNull() ?: 0;
                            var ans: Int = (number1 - number2)


                            GamePage.editTextText.setText("")
                            GamePage.Check.isEnabled = true
                            GamePage.button2.isEnabled=false


                        }





                    }
                    else {

                        life = life - 1;
                        GamePage.textView2.text = "Life : " + life
                        GamePage.button2.isEnabled=true
                    }
                }
                else    {
                    GamePage.textView4.text = "Game Over"
                    GamePage.Check.isEnabled =false
                    GamePage.button2.setOnClickListener {
                        var intent=Intent(this@GamePage,GameOver::class.java)
                        var message=Score.toString()
                        intent.putExtra("Score",message)
                        Log.d("message1",message)
                        startActivity(intent)
                        finish()
                    }


                }
            }



        }


        if (calc=="mul") {

            GamePage.textView4.text = "" + number1 + "   *  " + number2
            GamePage.Check.setOnClickListener {
                GamePage.button2.isEnabled=true
                if (life > 0) {


                    var input = GamePage.editTextText.text.toString();
                    var checkInput = input.toIntOrNull() ?: 0;
                    var ans: Int = (number1 * number2)

                    if (checkInput == ans) {
                        Score = Score + 10;
                        GamePage.textView.text = "Score : " + Score
                        GamePage.Check.isEnabled = false

                        GamePage.button2.setOnClickListener {
                            number1 = Random.nextInt(0, 100)
                            number2 = Random.nextInt(0, 100)
                            GamePage.textView4.text = "" + number1 + "   *  " + number2
                            var input = GamePage.editTextText.text.toString();
                            var checkInput = input.toIntOrNull() ?: 0;
                            var ans: Int = (number1 * number2)


                            GamePage.editTextText.setText("")
                            GamePage.Check.isEnabled = true
                            GamePage.button2.isEnabled=false


                        }





                    }
                    else {

                        life = life - 1;
                        GamePage.textView2.text = "Life : " + life
                        GamePage.button2.isEnabled=true
                    }
                }
                else    {
                    GamePage.textView4.text = "Game Over"
                    GamePage.Check.isEnabled =false
                    GamePage.button2.setOnClickListener {
                        var intent=Intent(this@GamePage,GameOver::class.java)
                        var message=Score.toString()
                        intent.putExtra("Score",message)
                        Log.d("message1",message)
                        startActivity(intent)
                        finish()
                    }


                }
            }



        }












        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}