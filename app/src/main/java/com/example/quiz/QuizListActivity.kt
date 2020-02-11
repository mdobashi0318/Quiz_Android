package com.example.quiz

import android.content.Intent
import android.support.v7.app.AppCompatActivity
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

class QuizListActivity :  Fragment() {

    private lateinit var realm: Realm
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_quiz_list, container, false)
    }


    override fun onStart() {
        super.onStart()


        Realm.init(context)
        realm = Realm.getDefaultInstance()
        val quizModel: RealmResults<QuizModel> = realm.where(QuizModel::class.java).findAll()

        var dataArray = mutableListOf<String>()

        for (quiz in quizModel) {
            dataArray.add(quiz.quizTitle)

        }
        val adapter = ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, dataArray)

        quizArray.adapter = adapter


        quizArray.setOnItemClickListener { parent, view, position, id ->


            val intent: Intent = Intent(this.context, QuizEdit::class.java)
            intent.putExtra("Quiz_Id", quizModel[id.toInt()]?.id?.toInt())
            intent.putExtra("Mode", "Detail")
            startActivity(intent)
        }


        quizArray.setOnItemLongClickListener { parent, view, position, id ->
            val intent: Intent = Intent(this.context, QuizEdit::class.java)
            intent.putExtra("Quiz_Id", quizModel[id.toInt()]?.id?.toInt())
            startActivity(intent)

            return@setOnItemLongClickListener true

        }



        fab.setOnClickListener {
            val intent = Intent(this.context, QuizEdit::class.java)
            startActivity(intent)
        }


    }
}
