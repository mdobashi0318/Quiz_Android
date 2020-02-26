package com.example.quiz

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import io.realm.Realm
import io.realm.RealmResults
import kotlinx.android.synthetic.main.activity_history.*
import kotlinx.android.synthetic.main.activity_quiz_list.*

class HistoryActivity : AppCompatActivity() {

    private lateinit var realm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        Realm.init(this)
        realm = Realm.getDefaultInstance()
        val historyModel: RealmResults<HistoryModel> = realm.where(HistoryModel::class.java).findAll()

        var dataArray = mutableListOf<String>()

        for (history in historyModel) {
            dataArray.add(history.quizTrueCount)

        }
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dataArray)

        historyList.adapter = adapter
    }
}
