package com.example.quiz

import android.content.Context
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

        quizStartButton.setOnClickListener {
            Toast.makeText(context, "クイズがまだ作成されていません", Toast.LENGTH_LONG).show()

        }
    }

}
