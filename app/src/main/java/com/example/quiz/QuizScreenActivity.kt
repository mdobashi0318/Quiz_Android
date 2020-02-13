package com.example.quiz

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_quiz_screen.*

class QuizScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_screen)

        answer1.setOnClickListener {
            val intent = Intent(this.applicationContext, ResultScreenActivity::class.java)
            startActivity(intent)
        }
    }
}
