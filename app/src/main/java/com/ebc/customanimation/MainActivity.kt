package com.ebc.customanimation

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.motion.widget.MotionLayout
import com.ebc.customanimation.ui.theme.CustomAnimationTheme

class MainActivity : AppCompatActivity() {

    private lateinit var motionLayout: MotionLayout
    private lateinit var gaugeView: CustomGaugeView
    private lateinit var infoText: TextView
    private lateinit var btnReset: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.mainactivity)

        gaugeView = findViewById(R.id.gaugeView)
        //Voy a cargar el XML de transición... el MotionView
        motionLayout = findViewById(R.id.motionLayout)
        infoText = findViewById(R.id.infoText)
        btnReset = findViewById(R.id.btnReset)

        gaugeView.setOnClickListener {
            val randomAngle = (0..180).random().toFloat()
            gaugeView.animateTo(randomAngle)
        }

        gaugeView.setOnLongClickListener {
            if (motionLayout.progress == 0f) motionLayout.transitionToEnd()
            else motionLayout.transitionToStart()

            true
        }

    }
}