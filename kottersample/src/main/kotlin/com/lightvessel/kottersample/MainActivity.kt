package com.lightvessel.kottersample

import android.app.Activity
import android.os.Bundle
import android.widget.RelativeLayout
import butterknife.bindView
import android.widget.TextView
import android.widget.Button
import android.widget.ImageView
import butterknife.listenOnClick
import butterknife.ButterActivity
import butterknife.OnClick
import android.view.View
import java.lang.reflect.Method

public class MainActivity : Activity() , ButterActivity {

    val text   : TextView  by bindView(R.id.text)
    val button : Button    by bindView(R.id.button)
    val image  : ImageView by bindView(R.id.image)

    override public fun onCreate(savedInstanceState: Bundle?) {
        super<Activity>.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        initButter()

        text.setText("KotterKnife Demo")
        button.setText("Tap to display image")

    }

    OnClick(R.id.button) fun showImage(btnShowImage:Button){
        image.setImageResource(R.drawable.logo)
    }
}
