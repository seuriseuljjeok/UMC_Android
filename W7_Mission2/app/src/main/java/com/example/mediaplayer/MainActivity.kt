package com.example.mediaplayer

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.mediaplayer.databinding.ActivityMainBinding
import kotlin.concurrent.thread


class MainActivity : AppCompatActivity() {

    //상태 변수 설정
    val START = 50

    //재생 시간 변수
    var time = 0

    //바인딩
    private lateinit var binding : ActivityMainBinding
    lateinit var seekBar : SeekBar
    lateinit var mediaPlayer: MediaPlayer
    lateinit var playbtn : Button
    lateinit var pausebtn : Button
    lateinit var stopbtn : Button
    lateinit var timeView : TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        playbtn = binding.playbtn
        pausebtn = binding.pausebtn
        stopbtn = binding.stopbtn
        seekBar = binding.seekBar
        timeView = binding.timeView

        setContentView(binding.root)

        //MediaPlayer 객체 생성
        mediaPlayer = MediaPlayer.create(this,R.raw.hypeboy)
        //음악의 총 길이를 seekbar의 최댓값에 적용
        seekBar.max = mediaPlayer.duration
        // 현재 재생중인 위치를 가져와 시크바에 적용
        seekBar.progress = mediaPlayer.currentPosition


        //MediaPlayer의 객체를 통해 start(),pause() 또는 stop() 메소드를 이용하여 음악을 재생/정지한다.
        playbtn.setOnClickListener {play()}
        pausebtn.setOnClickListener { mediaPlayer.pause() }
        stopbtn.setOnClickListener {reset()}

        //object 키워드 뒤에 구현한 인터페이스 붙여주기
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                // 사용자가 SeekBar를 움직이면 재생위치를 바꿔준다(움직인 곳)
                if (fromUser) mediaPlayer.seekTo(progress)
                //재생 위치 바꿔주고 play()
                play()
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }

    private fun formatTime(progress: Int): String {
        val min = progress / 60000  //두 자리수 분으로 바꿔주기
        val sec = progress % 60000 / 1000   //두 자리수 초로 바꿔주기
        return String.format("%02d:%02d", min, sec)
    }

    private val handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            when(msg.what){
                START -> { timeView.setText(formatTime(msg.arg1)) }
            }
        }
    }

    private fun play(){
        mediaPlayer.start()
        time = mediaPlayer.currentPosition //재생 위치 가져와서 재생 시간으로 설정
        thread(start = true) {
            while (mediaPlayer.isPlaying) { // 음악이 실행 중일 때 계속 돌아가게 함
                Thread.sleep(1000)  // 1초마다 반복하도록
                if (mediaPlayer.currentPosition == mediaPlayer.duration) reset() //노래가 끝나면 리셋
                if (!mediaPlayer.isPlaying) break //일시 정지 하거나, 정지할 경우
                time += 1 //재생 시간 1초 증가
                val msg = Message() //메세지 객체 생성
                msg.what = START //재생 상태 저장
                msg.arg1=time //재생 시간 저장
                handler.sendMessage(msg) //핸들러에 메세지 전달
                seekBar.progress=mediaPlayer.currentPosition //현재 재생 위치를 SeekBar 위치로 설정
            }
        }
    }

    private fun reset(){
        mediaPlayer.stop() //플레이어 정지
        mediaPlayer.reset() //플레이어 리셋
        seekBar.progress=mediaPlayer.currentPosition //SeekBar 리셋
        mediaPlayer = MediaPlayer.create(this,R.raw.hypeboy) //다음 재생 때 플레이어 사용할 수 있도록 생성
        timeView.text="00 : 00"
        time = 0 //재생 시간 리셋
    }

}