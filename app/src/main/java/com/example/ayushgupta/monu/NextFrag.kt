package com.example.ayushgupta.monu

import android.annotation.SuppressLint
import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

@SuppressLint("ValidFragment")
class NextFrag : Fragment {
    var et1: EditText? = null
    var et2: EditText? = null
    var et3: EditText? = null
    var et4: EditText? = null
    var et5: EditText? = null
    var caps: Boolean = false
    var belts: Boolean = false
    var watches: Boolean = false
    var sung: Boolean = false

    constructor(caps: Boolean, belts: Boolean, watches: Boolean, suns: Boolean) {
        this.caps = caps
        this.belts = belts
        this.watches = watches
        this.sung = suns
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val v = inflater!!.inflate(R.layout.next_frag, container, false)
        et1 = v.findViewById(R.id.et1)
        et2 = v.findViewById(R.id.et2)
        et3 = v.findViewById(R.id.et3)
        et4 = v.findViewById(R.id.et4)
        et5 = v.findViewById(R.id.et5)
        var flag: Boolean
        var string: String? = ""
        var cost: Double? = 0.0
        if (caps) {
            et1?.visibility = View.VISIBLE
        }
        if (belts) {
            et2?.visibility = View.VISIBLE
        }
        if (watches) {
            et3?.visibility = View.VISIBLE
        }
        if (sung) {
            et4?.visibility = View.VISIBLE
        }
        val btn1: Button = v.findViewById(R.id.btn1)
        btn1.setOnClickListener {
            flag = false
            if (caps) {
                if (et1?.text.toString() == "") {
                    flag = true
                } else {
                    string += "Cap(s): Rs. ${et1?.text.toString()}\n"
                    cost = cost?.plus(et1?.text.toString().toDouble())
                }
            }
            if (belts) {
                if (et2?.text.toString() == "") {
                    flag = true
                } else {
                    string += "Belt(s): Rs. ${et2?.text.toString()}\n"
                    cost = cost?.plus(et2?.text.toString().toDouble())
                }
            }
            if (watches) {
                if (et3?.text.toString() == "") {
                    flag = true
                } else {
                    string += "Watche(s): Rs. ${et3?.text.toString()}\n"
                    cost = cost?.plus(et3?.text.toString().toDouble())
                }
            }
            if (sung) {
                if (et4?.text.toString() == "") {
                    flag = true
                } else {
                    string += "Sunglasses(s): Rs. ${et4?.text.toString()}\n"
                    cost = cost?.plus(et4?.text.toString().toDouble())
                }
            }
            if (et5?.text.toString() == "")
                flag = true
            if (flag)
                Toast.makeText(activity, "Field is empty", Toast.LENGTH_SHORT).show()
            else {
                Toast.makeText(activity, "$string $cost", Toast.LENGTH_LONG).show()
                val fragment = fragmentManager.beginTransaction()
                fragment.remove(Fragment())
                fragment.replace(R.id.fl1, BillFrag(string!!, cost!!, et5?.text.toString()))
                fragment.addToBackStack("true")
                fragment.commit()
            }
        }
        return v
    }
}
