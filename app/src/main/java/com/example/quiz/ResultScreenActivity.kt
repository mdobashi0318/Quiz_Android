package com.example.quiz

import android.animation.ObjectAnimator
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_result_screen.*

class ResultScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_screen)


        animateTranslation(resultTextView)


    }



    private fun animateTranslation(textView: TextView) {
        val objectAnimator = ObjectAnimator.ofFloat(textView, "translationY", 300f)
        objectAnimator.duration = 2000
        objectAnimator.start()
    }


}
