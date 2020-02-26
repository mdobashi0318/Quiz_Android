package com.example.quiz

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import io.realm.Realm
import io.realm.RealmResults
import kotlinx.android.synthetic.main.activity_quiz_list.*
import kotlinx.android.synthetic.main.activity_quiz_start.*

class QuizStartActivity : Fragment() {


    private lateinit var realm: Realm



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.activity_quiz_start, container, false)
    }


    override fun onStart() {
        super.onStart()

        val editQuiz: String = getString(R.string.quiz_edit)
        val startQuiz: String = getString(R.string.quiz_start)

        Realm.init(context)
        realm = Realm.getDefaultInstance()
        val quizModel: RealmResults<QuizModel> = realm.where(QuizModel::class.java).findAll()


        if (quizModel.count() > 0) {
            quizStartButton.text = startQuiz
        } else {
            quizStartButton.text = editQuiz
        }


        quizStartButton.setOnSafeClickListener {

            if (quizStartButton.text == editQuiz) {
                val intent = Intent(this.context, QuizEdit::class.java)
                startActivity(intent)
            } else {
                val intent = Intent(this.context, QuizScreenActivity::class.java)
                startActivity(intent)
            }
        }



        historyButton.setOnSafeClickListener {
            val intent = Intent(this.context, HistoryActivity::class.java)
            startActivity(intent)
        }

    }



}
