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

        motionLayout.setTransitionListener(object : MotionLayout.TransitionListener {
            override fun onTransitionStarted(
                motionLayout: MotionLayout?,
                startId: Int,
                endId: Int
            ) {

            }

            override fun onTransitionChange(
                motionLayout: MotionLayout?,
                startId: Int,
                endId: Int,
                progress: Float
            ) {

            }

            override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {
                val angle = gaugeView.currentNeedleAngle
                //Supongamos que el velocímetro va de 0 a 120 KM
                //0° - 0KM
                //180° - 120KM
                val speed = (angle /180f * 120).toInt() //Esta regla de tres nos va a convertir los grados a nuestros KM

                infoText.text = "Velocidad: $speed KM/H"
            }

            override fun onTransitionTrigger(
                motionLayout: MotionLayout?,
                triggerId: Int,
                positive: Boolean,
                progress: Float
            ) {

            }
        })

    }
}