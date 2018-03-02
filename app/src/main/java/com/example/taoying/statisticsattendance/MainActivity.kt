package com.example.taoying.statisticsattendance

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //setSupportActionBar(toolbar)
        //toolbar.setTitle("主页")

        imgBtn_Originator.setOnClickListener({ startActivity(Intent(Guiding@this,OriginatorActivity::class.java ))})
        imgBtn_Participants.setOnClickListener({ startActivity(Intent(Guiding@this,ParticipantsActivity::class.java ))})
    }
}
