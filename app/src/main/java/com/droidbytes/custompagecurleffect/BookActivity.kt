package com.droidbytes.custompagecurleffect

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.MotionEvent
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AccelerateInterpolator
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.droidbytes.custompagecurleffect.databinding.ActivityBookBinding

class BookActivity : AppCompatActivity() {
    lateinit var binding: ActivityBookBinding
    lateinit var imageList : ArrayList<Int>
    var imageCount : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityBookBinding.inflate(layoutInflater)
        setContentView(binding.root)

        imageList=ArrayList()

        imageList.add(R.drawable.page1)
        imageList.add(R.drawable.page2)

        var right_to_left_anim=AnimationUtils.loadAnimation(this@BookActivity,R.anim.right_to_left).apply {
            duration=700
            interpolator=AccelerateDecelerateInterpolator()
        }

        var left_to_right_anim=AnimationUtils.loadAnimation(this@BookActivity,R.anim.left_to_right).apply {
            duration=700
            interpolator=AccelerateDecelerateInterpolator()
        }

        binding.rootLayout.setOnTouchListener(object : View.OnTouchListener {
            private val SWIPE_THRESHOLD = 100
            private var downX = 0f
            private var upX = 0f

            override fun onTouch(view: View, event: MotionEvent): Boolean {
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        downX = event.x
                        return true
                    }

                    MotionEvent.ACTION_UP -> {
                        upX = event.x
                        val deltaX = downX - upX
                        if (deltaX > SWIPE_THRESHOLD) {
                            // Swipe from right to left detected
                            right_to_left_anim.setAnimationListener(object :
                                Animation.AnimationListener {
                                override fun onAnimationStart(animation: Animation) {
                                    var handler = Handler()
                                    handler.postDelayed({
                                        if(imageCount==imageList.size-1){
                                            imageCount=-1
                                        }
                                        imageCount++
                                        binding.imageView.setImageResource(imageList[imageCount])
                                    },100)
                                }

                                override fun onAnimationEnd(p0: Animation?) {
                                }

                                override fun onAnimationRepeat(p0: Animation?) {
                                }

                            })
                            binding.circle2.startAnimation(right_to_left_anim)
                        }
                        else if(-deltaX > SWIPE_THRESHOLD){
                            left_to_right_anim.setAnimationListener(object : Animation.AnimationListener{
                                override fun onAnimationStart(p0: Animation?) {
                                    var handler=Handler()
                                    handler.postDelayed({
                                        if(imageCount==0){
                                            imageCount=imageList.size
                                        }
                                        imageCount--
                                        binding.imageView.setImageResource(imageList[imageCount])
                                    },100)
                                }

                                override fun onAnimationEnd(p0: Animation?) {
                                }

                                override fun onAnimationRepeat(p0: Animation?) {
                                }

                            })
                            binding.circle1.startAnimation(left_to_right_anim)
                        }
                        return true
                    }
                }
                return false
            }

        })


    }
}