package com.example.ayushgupta.monu

import android.annotation.SuppressLint
import android.app.Fragment
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import java.io.File
import java.io.FileOutputStream

@SuppressLint("ValidFragment")
class BillFrag : Fragment {
    var et1: TextView? = null
    var string: String? = null
    var cost: Double? = null
    var phone: String? = null

    constructor(string: String, cost: Double, phone: String) {
        this.string = string
        this.cost = cost
        this.phone = phone
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater!!.inflate(R.layout.bill_frag, container, false)
        et1 = view.findViewById(R.id.billprint)
        val bill = "$string \n Total cost: Rs. $cost \n Customer contact: +91 $phone"
        et1?.text = bill
        val btn = view.findViewById<Button>(R.id.btn2)
        btn.setOnClickListener {
            createBill(phone!!)
        }
        return view
    }

    private fun createBill(phone: String) {
        try {
            var path = Environment.getExternalStorageDirectory().toString() + "/Bill/"
            val v: View = activity.window.decorView.rootView
            v.isDrawingCacheEnabled = true
            val bitmap = Bitmap.createBitmap(v.drawingCache)
            v.isDrawingCacheEnabled = false
            var file = File(path)
            if (!file.exists()) {
                if (file.mkdir()) {
                    path += "$phone.jpg"
                } else {
                    Toast.makeText(activity, "Error in creating folder", Toast.LENGTH_LONG).show()
                    return
                }
            }
            path += "$phone.jpg"
            file = File(path)
            val fileOutputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream)
            fileOutputStream.flush()
            fileOutputStream.close()
            Toast.makeText(activity, "Saved in $path", Toast.LENGTH_LONG).show()
        } catch (e: Throwable) {
            e.printStackTrace()
        }
    }
}