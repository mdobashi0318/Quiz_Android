package com.example.quiz

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_quiz_start.*

class QuizStartActivity : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.activity_quiz_start, container, false)
    }


    override fun onStart() {
        super.onStart()


        val edit_quiz: String = getString(R.string.quiz_edit)
        val start_quiz: String = getString(R.string.quiz_start)


        quizStartButton.text = start_quiz


        quizStartButton.setOnClickListener {

            if (quizStartButton.text == edit_quiz) {
                val intent = Intent(this.context, QuizEdit::class.java)
                startActivity(intent)
            } else {
                val intent = Intent(this.context, QuizScreenActivity::class.java)
                startActivity(intent)
            }
        }
    }



}
