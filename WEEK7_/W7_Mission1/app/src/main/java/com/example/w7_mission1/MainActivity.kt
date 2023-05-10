package com.example.w7_mission1

import android.media.SoundPool
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.text.Editable
import android.text.TextWatcher
import com.example.w7_mission1.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var soundPool: SoundPool
    private var soundID: Int? = null

    //상태 변수 설정
    val START = 50
    val TIMEOVER = 100

    //total : 총 남은 시간 | ing : 프로그램 진행 여부
    private var total = 0

    //사용자가 입력한 시간(문자열)을 가져와서 숫자형으로 변환하여 total에 저장
    var getTime = 0
    var ing = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //SoundPool 객체 생성 => 최대 음악 파일 개수 설정
        soundPool = SoundPool.Builder().setMaxStreams(2).build()
        //재생하고자 하는 음악 준비 => 각 파라미터 : 현재 화면의 제어권자, 음악 파일, 우선 순위
        soundID = soundPool.load(this, R.raw.dovelove, 1)


        binding.startBtn.setOnClickListener {
            total = Integer.parseInt(binding.timerView.text.toString())
            start() }
        binding.pauseBtn.setOnClickListener { pause() }
        binding.resetBtn.setOnClickListener { reset() }
    }


    private val handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            when(msg.what){
                START -> {
                    binding.timerView.setText(msg.arg1.toString())
                }
            }
        }
    }

//    fun formatTime(time: Int): String {
//        var minute = String.format("%02d",time/60) //두 자리수 분으로 바꿔주기
//        var second = String.format("%02d",time%60) //두 자리수 초로 바꿔주기
//        return "$minute : $second"
//    }

    fun start() {
        //sub thread
        thread(start = true){
            ing = true //진행 상태
            while (ing){
                Thread.sleep(1000)
                if (!ing) break //진행 상태가 아니라면 반복문 빠져나오기
                total -= 1 //1초씩 감소
                val msg = Message()
                msg.what = START
                msg.arg1 = total
                handler.sendMessage(msg) //handler에 값 세팅한 msg 보내기

                if (total <= 0) {//시간이 다 됐다면 반복문 빠져나오기
                    ing = false

                    //음악 재생
                    //=> 각 파라미터 : 준비한 soundID, 왼쪽 볼륨 float 0.0(작은 소리)~1.0(큰소리),오른쪽 볼륨 float 0.0(작은 소리)~1.0(큰소리), 우선 순위 int, 반복 횟수 int -1:무한 반복/0:반복 안함, 재생 속도 float 0.5~2.0
                    soundPool.play(soundID!!, 1f, 1f, 0, -1, 1f)
                }
            }
        }
    }

    fun pause(){
        ing = false
    }

    fun reset(){
        ing = false
        total = 0
        binding.timerView.setText("0")
        soundPool.stop(soundID!!)
    }
}