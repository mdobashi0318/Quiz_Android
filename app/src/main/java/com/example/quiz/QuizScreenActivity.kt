package com.example.quiz

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import io.realm.Realm
import io.realm.RealmResults
import kotlinx.android.synthetic.main.activity_quiz_screen.*

class QuizScreenActivity : AppCompatActivity() {

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
                val intent = Intent(this.applicationContext, ResultScreenActivity::class.java)
                startActivity(intent)
            }
        }

    }





    /// viewにセットするためテキストを取得する
    private fun getQuiz(): MutableList<String?> {
        Realm.init(applicationContext)
        val realm = Realm.getDefaultInstance()
        val quizModel: RealmResults<QuizModel> = realm.where(QuizModel::class.java).findAll()


        return mutableListOf(
            quizModel[0]?.quizTitle,
            quizModel[0]?.trueAnswer,
            quizModel[0]?.falseAnswer1,
            quizModel[0]?.falseAnswer2,
            quizModel[0]?.falseAnswer3
        )

    }
}
