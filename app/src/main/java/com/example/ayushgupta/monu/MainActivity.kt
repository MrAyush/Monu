package com.example.ayushgupta.monu

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    var cb1:CheckBox? = null
    var cb2:CheckBox? = null
    var cb3:CheckBox? = null
    var cb4:CheckBox? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        cb1 = findViewById(R.id.caps)
        cb2 = findViewById(R.id.belts)
        cb3 = findViewById(R.id.watches)
        cb4 = findViewById(R.id.sung)
        val btn1:ImageView = findViewById(R.id.next)
        btn1.setOnClickListener {
            if (!(cb1?.isChecked!! || cb2?.isChecked!! || cb3?.isChecked!! || cb4?.isChecked!!)){
                Toast.makeText(this@MainActivity,"None of the items is selected",Toast.LENGTH_SHORT).show()
            }else {
                val intent = Intent(this, NextActivity::class.java)
                intent.putExtra("caps",cb1!!.isChecked)
                intent.putExtra("belts",cb2!!.isChecked)
                intent.putExtra("watches",cb3!!.isChecked)
                intent.putExtra("sung",cb4!!.isChecked)
                startActivity(intent)
            }
        }
    }
}
