package com.hope.kenny3

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.hope.kenny3.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    var Score = 0
    var imageArry = ArrayList<ImageView>()
    var runnable = Runnable{}
    var handler = Handler(Looper.getMainLooper())

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        imageArry.add(binding.imageView)
        imageArry.add(binding.imageView2)
        imageArry.add(binding.imageView3)
        imageArry.add(binding.imageView4)
        imageArry.add(binding.imageView5)
        imageArry.add(binding.imageView6)
        imageArry.add(binding.imageView7)
        imageArry.add(binding.imageView8)
        imageArry.add(binding.imageView9)

        hideimages()

        object : CountDownTimer(15500,1000){
            override fun onTick(p0: Long) {
                binding.TimeText.text = "Time : ${p0/1000}"
            }

            override fun onFinish() {
                binding.TimeText.text = "Time : 0"
                handler.removeCallbacks(runnable)

                for (image in imageArry) {
                    image.visibility = View.INVISIBLE
                }

                val alert = AlertDialog.Builder(this@MainActivity)
                alert.setTitle("Game Over")
                alert.setMessage("Restart The Game")
                alert.setPositiveButton(
                    "Yes",
                    DialogInterface.OnClickListener { dialogInterface, i ->
                        val intentfromMain = intent
                        finish()
                        startActivity(intentfromMain)
                    })

                alert.setNegativeButton(
                    "No",
                    DialogInterface.OnClickListener { dialog: DialogInterface?, i ->
                        0
                        Toast.makeText(this@MainActivity, "Game Over!", Toast.LENGTH_LONG).show()
                    })
                alert.show()

            }

        }.start()
    }
    fun hideimages (){

        runnable = object : Runnable{
            override fun run() {

                for (image in imageArry){
                    image.visibility = View.INVISIBLE

                }
                val random = Random
                val RandomIndex = random.nextInt(9)
                imageArry[RandomIndex].visibility = View.VISIBLE

                handler.postDelayed(runnable,500)
            }
        }
        handler.post(runnable)

    }
    fun UpScore(view: View){
        Score = Score + 1
        binding.ScoreText.text = "Score  ${Score}"
    }


}