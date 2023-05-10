package com.example.timer

import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.LayoutInflater
import androidx.core.content.ContextCompat
import com.example.timer.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    var ing = false //타이머 실행 여부
    val START_TIME = 0 //진행 상태
    var total = 0 //전체 시간

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startBtn.setOnClickListener{
            start()
            //버튼 클릭 시 색상 변경
            binding.startBtn.setBackgroundColor(applicationContext.resources.getColor(R.color.press))
            binding.pauseBtn.setBackgroundColor(applicationContext.resources.getColor(R.color.unpress))
            binding.resetBtn.setBackgroundColor(applicationContext.resources.getColor(R.color.unpress))
        }
        binding.pauseBtn.setOnClickListener{
            pause()
            binding.startBtn.setBackgroundColor(applicationContext.resources.getColor(R.color.unpress))
            binding.pauseBtn.setBackgroundColor(applicationContext.resources.getColor(R.color.press))
            binding.resetBtn.setBackgroundColor(applicationContext.resources.getColor(R.color.unpress))
        }
        binding.resetBtn.setOnClickListener{
            reset()
            binding.startBtn.setBackgroundColor(applicationContext.resources.getColor(R.color.unpress))
            binding.pauseBtn.setBackgroundColor(applicationContext.resources.getColor(R.color.unpress))
            binding.resetBtn.setBackgroundColor(applicationContext.resources.getColor(R.color.press))
        }

    }

    //handler 객체 생성
    val handler = object: Handler(){
        override fun handleMessage(msg: Message) {
            when(msg.what){
                START_TIME -> {//메시지로 받아온 타이머 상태가 실행 중인 경우
                    binding.timeView.text = timeFormat(msg.arg1) //msg로부터 받아온 변수(total 시간) 값을 꺼내서 @@ : @@ 형식으로 바꿔주고 timeView에 값 설정
                }
            }
        }
    }

    fun start() {
        //sub thread
        thread(start = true) {
            while (true){
                ing = true //타이머 실행
                Thread.sleep(1000) //밀리세컨드(1/1000초)마다 실행되므로 1초가 될 때까지 thread를 정지시키는 역할
                if (!ing) break; //타이머가 실행되는 상태가 아니라면 반복문 빠져나오기
                total += 1 //1초씩 증가
                val msg = Message()
                msg.what = START_TIME //msg에 상태 저장
                msg.arg1 = total  //msg에 변수 저장
                handler.sendMessage(msg)  //저장된 msg 핸들러에 전달
            }
        }


    }

    fun timeFormat(time: Int): String { //총 시간을 @@ : @@ 형식으로 바꿔주기
        val minute = String.format("%02d",time/60) //두 자리수 분으로 바꿔주기
        val second = String.format("%02d",time%60) //두 자리수 초로 바꿔주기
        return "$minute : $second"
    }

    fun pause() {
        ing = false //타이머 실행 정지
    }

    fun reset(){
        ing = false //타이머 실행 정지
        total = 0
        binding.timeView.text = "00 : 00" //초기화된 시간 값을 timeView에 세팅
    }
}