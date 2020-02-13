package com.example.quiz

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_quiz_start.*

class QuizStartActivity : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.activity_quiz_start, container, false)
    }


    override fun onStart() {
        super.onStart()


        val editQuiz: String = getString(R.string.quiz_edit)
        val startQuiz: String = getString(R.string.quiz_start)


        quizStartButton.text = startQuiz


        quizStartButton.setOnClickListener {

            if (quizStartButton.text == editQuiz) {
                val intent = Intent(this.context, QuizEdit::class.java)
                startActivity(intent)
            } else {
                val intent = Intent(this.context, QuizScreenActivity::class.java)
                startActivity(intent)
            }
        }
    }



}
