package com.example.quiz

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
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

        delete.setOnSafeClickListener {
            deleteRealm()
        }
    }



    // MARK: Fun ClickListener

    /// 「登録/編集」ボタンのタップアクション
    private fun editButtonClickListener() {
        editButton.setOnSafeClickListener {


            if (titleEditText.text.toString() == "" || titleEditText.text.toString() == null) {
                alert("タイトルが入力されていません") {
                    yesButton { return@yesButton }
                }.show()
                return@setOnSafeClickListener
            }

            if (trueEditText.text.toString() == "" || trueEditText.text.toString() == null) {
                alert("正解が入力されていません") {
                    yesButton { return@yesButton }
                }.show()
                return@setOnSafeClickListener
            }


            if (false1EditText.text.toString() == "" || false1EditText.text.toString() == null) {
                alert("不正解が入力されていません") {
                    yesButton { return@yesButton }
                }.show()
                return@setOnSafeClickListener
            }


            if (false2EditText.text.toString() == "" || false2EditText.text.toString() == null) {
                alert("不正解が入力されていません") {
                    yesButton { return@yesButton }
                }.show()
                return@setOnSafeClickListener
            }


            if (false3EditText.text.toString() == "" || false3EditText.text.toString() == null) {
                alert("不正解が入力されていません") {
                    yesButton { return@yesButton }
                }.show()
                return@setOnSafeClickListener
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
            var quizModel = realm.createObject(QuizModel::class.java, id)
            quizModel.quizTitle = titleEditText.text.toString()
            quizModel.trueAnswer = trueEditText.text.toString()
            quizModel.falseAnswer1 = false1EditText.text.toString()
            quizModel.falseAnswer2 = false2EditText.text.toString()
            quizModel.falseAnswer3 = false3EditText.text.toString()
            quizModel.displayFlag = "0"
        }


        showAlert("クイズを作成しました")
    }




    /// クイズの更新
    private fun updateRealm() {
        realm.executeTransaction {
            val quizModel = realm.where(QuizModel::class.java).equalTo("id", quizId).findFirst()
            quizModel?.quizTitle = titleEditText.text.toString()
            quizModel?.trueAnswer = trueEditText.text.toString()
            quizModel?.falseAnswer1 = false1EditText.text.toString()
            quizModel?.falseAnswer2 = false2EditText.text.toString()
            quizModel?.falseAnswer3 = false3EditText.text.toString()
            quizModel?.displayFlag = "0"
        }

        showAlert("クイズを更新しました")

    }


    /// クイズを削除
    private fun deleteRealm() {
        realm.executeTransaction {
            val quizModel = realm.where(QuizModel::class.java).equalTo("id", quizId).findFirst()
            quizModel?.deleteFromRealm()
        }

        showAlert("クイズを削除しました")
    }




    // MARK: Other

    /// クイズの編集かどうかを判定
    /// 編集であればEditTextにテキストをセットし、ボタンのテキストに「編集」をセットする
    private fun isEditQuiz() {
        quizId = intent.getIntExtra("Quiz_Id", addQuizId)
        delete.hide()

        mode = intent.getStringExtra("Mode")

        if (quizId != addQuizId) {
            realm = Realm.getDefaultInstance()
            val quizModel: QuizModel? = realm.where(QuizModel::class.java).equalTo("id", quizId).findFirst()

            titleEditText.setText(quizModel?.quizTitle.toString())
            trueEditText.setText(quizModel?.trueAnswer.toString())
            false1EditText.setText(quizModel?.falseAnswer1.toString())
            false2EditText.setText(quizModel?.falseAnswer2.toString())
            false3EditText.setText(quizModel?.falseAnswer3.toString())



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
            delete.show()
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
