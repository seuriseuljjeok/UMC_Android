package com.example.lifecycle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.lifecycle.databinding.ActivityCheckBinding
import com.example.lifecycle.databinding.ActivityMemoBinding

class Memo : AppCompatActivity() {
    private lateinit var binding: ActivityMemoBinding
    private lateinit var binding2: ActivityCheckBinding
    var result:String = ""  //최종메모를 담아줄 변수

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMemoBinding.inflate(layoutInflater)
        binding2 = ActivityCheckBinding.inflate(layoutInflater)
        var view = binding.root
        setContentView(view)

        val editMemo: EditText = binding.editText //EditText를 받을 변수
        val btnMemo: Button = binding.btn //Button를 받을 변수

        //버튼 비활성화로 초기화
        btnMemo.isEnabled = false
        //TextWatcher => 텍스트 변화를 탐지하는 함수. 탐지하면 호출
        editMemo.addTextChangedListener(object : TextWatcher {
            //텍스트 변화 전 호출
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            //텍스트 변화 시 호출
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                result = editMemo.text.toString() //EditText에 입력된 텍스트 받아와 result 변수에 저장
                btnMemo.isEnabled = result.isNotEmpty() //메모가 입력되었다면 버튼 활성화
            }

            //텍스트 변화 후 호출
            override fun afterTextChanged(p0: Editable?) {}
        })

        btnMemo.setOnClickListener {
            //editText로 입력한 데이터 가져와서 getText에 넣어줌
            binding2.getText.text = result

            //페이지 이동
            //Intent(현재 어플리케이션 정보, 이동하고자 하는 클래스)
            var intent = Intent(this, Check::class.java)

            //intent에 editText 값 담아줌
            //putExtra(key, value) => 값을 담을 때 사용함
            intent.putExtra("memo", result)
            startActivity(intent)
        }
    }
    private var savedMemo:String = ""

    override fun onPause() {
        super.onPause()
        //pause 상황이 발생했을 때 입력했던 데이터를 꺼내와서 savedMemo에 저장
        savedMemo = binding.editText.text.toString()

        //pause 상황 발생 시 메시지 보여줌
        Toast.makeText(this,"PAUSE APP",Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        if(savedMemo.isNotEmpty()) { //저장된 메모가 있는 경우
            result = savedMemo //저장된 메모를 최종메모 변수에 저장
            binding.editText.setText(result) //최종변수를 EditText에 넣어줌
            binding.btn.isEnabled = true //버튼 활성화
        }
    }

    override fun onRestart() {
        super.onRestart()
        //AlertDialog : Dialog의 서브클래스로 여러 버튼을 생성해줌
        val builder = AlertDialog.Builder(this)
        builder.setTitle("작성 중인 메모가 있습니다")
            .setMessage("작성 중인 메모로 돌아가시겠습니까?")
            .setPositiveButton("Yes") {
                _, _ ->
            }
            .setNegativeButton("No") { _, _ -> //No를 선택했을 때 실행해줄 것들
                savedMemo = "" //저장된 메모 초기화
                binding.editText.setText("") //메모 입력창 초기화
                binding.btn.isEnabled = false //버튼 비활성화
            }
            .setCancelable(false) //다이얼로그 닫을 수 없도록
        builder.create().show() //화면에 출력
    }
}