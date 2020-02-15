package com.example.quiz

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.realm.Realm
import io.realm.RealmResults
import kotlinx.android.synthetic.main.activity_quiz_screen.*

class QuizScreenActivity : AppCompatActivity() {

    private var quizNum: Int = 0

    private var trueCount: Int = 0

    private lateinit var  quizModel: RealmResults<QuizModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_screen)

        setQuizText()

    }




    /// ボタンに回答をセットする
    private fun setQuizText() {

        val buttons = mutableListOf(answer1, answer2, answer3, answer4)

        val answers = getQuiz()

        quizText.text = answers[0]


        val answerList = mutableListOf(answers[1], answers[2], answers[3], answers[4])

        for (i in 0 until buttons.count()) {

            val num = (0 until answerList.count()).shuffled().first()
            buttons[i].text = answerList[num]

            answerList.removeAt(num)


            buttons[i].setOnClickListener {

                if (buttons[i].text == quizModel[i]?.trueAnswer) {
                    tapTrueAnswer()
                }

                if (quizNum == 10 || quizNum == quizModel.count() - 1) {
                    val intent = Intent(this.applicationContext, ResultScreenActivity::class.java)
                    intent.putExtra("trueCount", trueCount)
                    startActivity(intent)
                } else {
                    changeQuiz()
                }
            }
        }

    }





    /// viewにセットするためテキストを取得する
    private fun getQuiz(): MutableList<String?> {
        Realm.init(applicationContext)
        val realm = Realm.getDefaultInstance()
        quizModel = realm.where(QuizModel::class.java).findAll()


        return mutableListOf(
            quizModel[quizNum]?.quizTitle,
            quizModel[quizNum]?.trueAnswer,
            quizModel[quizNum]?.falseAnswer1,
            quizModel[quizNum]?.falseAnswer2,
            quizModel[quizNum]?.falseAnswer3
        )

    }



    private fun changeQuiz() {
        quizNum++
        setQuizText()
    }



    /// 正解だったらtrueCountにインクリメントする
    private fun tapTrueAnswer() {
        trueCount++
    }


}
