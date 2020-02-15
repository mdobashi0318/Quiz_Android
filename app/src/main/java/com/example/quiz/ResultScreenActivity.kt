package com.example.quiz

import android.animation.ObjectAnimator
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_result_screen.*

class ResultScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_screen)



        val trueCount = intent.getIntExtra("trueCount", 0).toString()
        resultTextView.text = trueCount + "門正解しました"
        animateTranslation(resultTextView)

    }



    private fun animateTranslation(textView: TextView) {
        val objectAnimator = ObjectAnimator.ofFloat(textView, "translationY", 300f)
        objectAnimator.duration = 2000
        objectAnimator.start()

    }


}
