package com.example.quiz

import android.animation.ObjectAnimator
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_result_screen.*
import java.util.*

class ResultScreenActivity : AppCompatActivity() {


    // Properties

    /// Realmのインスタンス
    private lateinit var realm: Realm


    private lateinit var trueCount: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_screen)

        Realm.init(this)
        realm = Realm.getDefaultInstance()



        trueCount = intent.getIntExtra("trueCount", 0).toString()
        resultTextView.text = trueCount + "門正解しました"
        animateTranslation(resultTextView)

        addRealm()

    }



    private fun animateTranslation(textView: TextView) {
        val objectAnimator = ObjectAnimator.ofFloat(textView, "translationY", 300f)
        objectAnimator.duration = 2000
        objectAnimator.start()

    }



    /// クイズを新規追加
    private fun addRealm() {
        realm.executeTransaction {
            var historyModel = realm.createObject(HistoryModel::class.java)
            historyModel.quizTrueCount = trueCount
            historyModel.date = Date().toString()
        }
    }


}
