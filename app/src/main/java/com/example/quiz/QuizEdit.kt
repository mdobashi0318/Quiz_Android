package com.example.quiz

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.widget.Button
import android.widget.Toast
import io.realm.Realm
import io.realm.RealmResults
import kotlinx.android.synthetic.main.activity_quiz_edit.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.yesButton

class QuizEdit : AppCompatActivity() {


    // Properties

    /// Realmのインスタンス
    private lateinit var realm: Realm

    /// 編集をするクイズのID
    private var quizId: Int? = null

    /// クイズの新規追加時のID
    private val addQuizId: Int = 9999


    private var mode: String? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_edit)

        Realm.init(this)
        realm = Realm.getDefaultInstance()

        isEditQuiz()

        editButtonClickListener()
    }



    // MARK: Fun ClickListener

    /// 「登録/編集」ボタンのタップアクション
    private fun editButtonClickListener() {
        editButton.setOnClickListener {


            if (titleEditText.text.toString() == "" || titleEditText.text.toString() == null) {
                alert("タイトルが入力されていません") {
                    yesButton { return@yesButton }
                }.show()
                return@setOnClickListener
            }

            if (trueEditText.text.toString() == "" || trueEditText.text.toString() == null) {
                alert("正解が入力されていません") {
                    yesButton { return@yesButton }
                }.show()
                return@setOnClickListener
            }


            if (false1EditText.text.toString() == "" || false1EditText.text.toString() == null) {
                alert("不正解が入力されていません") {
                    yesButton { return@yesButton }
                }.show()
                return@setOnClickListener
            }


            if (false2EditText.text.toString() == "" || false2EditText.text.toString() == null) {
                alert("不正解が入力されていません") {
                    yesButton { return@yesButton }
                }.show()
                return@setOnClickListener
            }


            if (false3EditText.text.toString() == "" || false3EditText.text.toString() == null) {
                alert("不正解が入力されていません") {
                    yesButton { return@yesButton }
                }.show()
                return@setOnClickListener
            }

            if (quizId == addQuizId) {
                /// 新規追加
                addRealm()
            } else if (mode == "Detail") {
                /// 何もしない
            } else {
                /// 編集
                updateRealm()
            }

        }
    }



    // MARK: Fun Realm

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


        showAlert("クイズを作成しました")
    }




    /// クイズの更新
    private fun updateRealm() {
        realm.executeTransaction {
            val quziModel = realm.where(QuizModel::class.java).equalTo("id", quizId).findFirst()
            quziModel?.quizTitle = titleEditText.text.toString()
            quziModel?.trueAnswer = trueEditText.text.toString()
            quziModel?.falseAnswer1 = false1EditText.text.toString()
            quziModel?.falseAnswer2 = false2EditText.text.toString()
            quziModel?.falseAnswer3 = false3EditText.text.toString()
            quziModel?.displayFlag = "0"
        }

        showAlert("クイズを更新しました")

    }




    // MARK: Other

    /// クイズの編集かどうかを判定
    /// 編集であればEditTextにテキストをセットし、ボタンのテキストに「編集」をセットする
    private fun isEditQuiz() {
        quizId = intent.getIntExtra("Quiz_Id", addQuizId)

        mode = intent.getStringExtra("Mode")

        if (quizId != addQuizId) {
            realm = Realm.getDefaultInstance()
            val quizModel: RealmResults<QuizModel> = realm.where(QuizModel::class.java).findAll()

            titleEditText.setText(quizModel[quizId!!]?.quizTitle.toString())
            trueEditText.setText(quizModel[quizId!!]?.trueAnswer.toString())
            false1EditText.setText(quizModel[quizId!!]?.falseAnswer1.toString())
            false2EditText.setText(quizModel[quizId!!]?.falseAnswer2.toString())
            false3EditText.setText(quizModel[quizId!!]?.falseAnswer3.toString())


            editButton.text = "更新"
        }


        if (mode == "Detail") {
            realm = Realm.getDefaultInstance()
            val quizModel: RealmResults<QuizModel> = realm.where(QuizModel::class.java).findAll()

            titleEditText.isEnabled = false
            trueEditText.isEnabled = false
            false1EditText.isEnabled = false
            false2EditText.isEnabled = false
            false3EditText.isEnabled = false
            showSwitch.isEnabled = false


            editButton.visibility = Button.INVISIBLE
        }
    }



    /// yesButtonを押した時に画面を閉じるアラート
    private fun showAlert(message: String) {
        alert(message) {
            yesButton {
                finish()
            }

        }.show()
    }

}
