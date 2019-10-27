package com.example.quiz

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import io.realm.Realm
import io.realm.RealmResults
import kotlinx.android.synthetic.main.activity_quiz_edit.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.yesButton

class QuizEdit : AppCompatActivity() {


    private lateinit var realm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_edit)

        Realm.init(this)
        realm = Realm.getDefaultInstance()


        editButton.setOnClickListener {


            addRealm()


            alert("クイズを作成しました") {
                yesButton {
                    finish()
                }

            }.show()


        }
    }




    /// クイズを新規追加
    private fun addRealm() {
        realm.executeTransaction {
            var id: Long = realm.where(QuizModel::class.java).count()
            var quziModel = realm.createObject(QuizModel::class.java, id)
            quziModel.quizTitle = titleEditText.text.toString()
            quziModel.trueAnswer = trueEditText.text.toString()
            quziModel.falseAnswer1 = false1EditText.text.toString()
            quziModel.falseAnswer2 = false2EditText.text.toString()
            quziModel.falseAnswer3 = false3EditText.text.toString()
            quziModel.displayFlag = "0"

        }
    }
}
