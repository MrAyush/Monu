package com.example.ayushgupta.monu

import android.app.Fragment
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.Toast

class NextActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_next)
        val caps = intent.getBooleanExtra("caps", false)
        val belts = intent.getBooleanExtra("belts", false)
        val watches = intent.getBooleanExtra("watches", false)
        val sung = intent.getBooleanExtra("sung", false)
        if (getPermission()) {
            val fragment1 = fragmentManager.beginTransaction()
            fragment1.remove(Fragment())
            fragment1.add(R.id.fl1, NextFrag(caps, belts, watches, sung))
            fragment1.commit()
        }
    }

    private fun getPermission(): Boolean {
        val status = ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
        if (status == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE), 100)
        } else {
            return true
        }
        return false
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
            Toast.makeText(this, "Can't take the screenshot, storage access denied!!", Toast.LENGTH_LONG).show()
        }
    }

    fun goBack(view: View) {
        onBackPressed()
    }
}
