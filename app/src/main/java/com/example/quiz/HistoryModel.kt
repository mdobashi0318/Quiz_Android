package com.example.quiz

import io.realm.RealmObject

open class HistoryModel: RealmObject() {

    var quizTrueCount: String = ""
    var date:String = ""
}