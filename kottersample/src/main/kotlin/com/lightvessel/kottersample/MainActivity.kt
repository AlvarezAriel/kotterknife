package com.lightvessel.kottersample

import android.app.Activity
import android.os.Bundle
import android.widget.RelativeLayout
import butterknife.bindView
import android.widget.TextView
import android.widget.Button
import android.widget.ImageView
import butterknife.ButterActivity
import butterknife.OnClick
import android.view.View
import java.lang.reflect.Method
import butterknife.oneWayBinding
import butterknife.bindTo
import android.widget.EditText

public class MainActivity : Activity() , ButterActivity {

    val text       : TextView  by bindView(R.id.text)
    val button     : Button    by bindView(R.id.button)
    val image      : ImageView by bindView(R.id.image)
    var someText : String by oneWayBinding(R.id.text)

    val textInput  : EditText by bindTo(R.id.textInput){
        someText = "KotterKnife Demo: $it"
    }

    override public fun onCreate(savedInstanceState: Bundle?) {
        super<Activity>.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        initButter()

        textInput.setHint("Enter some text...")
        button.setText("Tap to display image")

    }

    OnClick(R.id.button) fun showImage(btnShowImage:Button){
        image.setImageResource(R.drawable.logo)
    }
}
