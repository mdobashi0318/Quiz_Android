package com.example.quiz

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class QuizModel: RealmObject() {
    @PrimaryKey
    var id: Long = 0
    var quizTitle: String = ""
    var trueAnswer:String = ""
    var falseAnswer1:String = ""
    var falseAnswer2:String = ""
    var falseAnswer3:String = ""
    var displayFlag:String = ""

}