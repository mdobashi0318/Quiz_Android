package com.example.quiz

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_quiz_screen.*

class QuizScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_screen)


        answer1.setOnClickListener {
            val intent = Intent(this.applicationContext, ResultScreenActivity::class.java)
            startActivity(intent)
        }

        setAnswerText()

    }




    /// ボタンに回答をセットする
    private fun setAnswerText() {

        val buttons = mutableListOf(answer1, answer2, answer3, answer4)

        val answerList = mutableListOf("問題1", "問題2", "問題3", "問題4")

        for (i in 0 until buttons.count()) {

            val num = (0 until answerList.count()).shuffled().first()
            buttons[i].text = answerList[num]

            answerList.removeAt(num)
        }


    }
}
