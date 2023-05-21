package com.example.database

import android.content.Intent
import android.content.SharedPreferences
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.ToggleButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.database.databinding.ActivityMainBinding
import com.example.database.databinding.ActivityMemoSavedBinding

class MainActivity : AppCompatActivity() {
    //binding 관련
    private lateinit var binding: ActivityMainBinding
    private lateinit var binding2: ActivityMemoSavedBinding

    //RoomDB 관련
    private lateinit var memoDao: MemoDao
    lateinit var memo: Memo
    lateinit var memoDB: MemoDatabase
    var memoList = mutableListOf<Memo>()


    //activity_memo 관련
    lateinit var writeMemo: EditText
    lateinit var saveBtn: Button
    lateinit var likedBtn: Button


    //activity_memo_saved 관련
    lateinit var savedMemo: TextView
    lateinit var toggleButton: ToggleButton
    lateinit var time: TextView

    //즐겨찾기 관련
    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor
    var shared: String = "file"

    var result:String = ""  //최종 메모를 담아줄 변수
    var string:String = ""  //저장되기 전 메모를 담아줄 변수

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding2 = ActivityMemoSavedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        writeMemo = binding.writeMemo //EditText를 받을 변수
        saveBtn = binding.saveBtn
        likedBtn = binding.likedBtn

        savedMemo = binding2.memo
        toggleButton = binding2.like
        time = binding2.time

        //Room DB 생성
        memoDB = Room.databaseBuilder(applicationContext, MemoDatabase::class.java, "MemoDB")
            .fallbackToDestructiveMigration()   //스키마(Database) 버전 변경 가능
            .allowMainThreadQueries()           //Main Thread에서 DB에 IO(입출력)을 가능하게 함
            .build()

        //Dao 인터페이스 객체 할당
        memoDao = memoDB.memoDao()

        //저장 버튼 비활성화로 초기화
        saveBtn.isEnabled = false
        //TextWatcher => 텍스트 변화를 탐지하는 함수. 탐지하면 호출
        writeMemo.addTextChangedListener(object : TextWatcher {
            //텍스트 변화 전 호출
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            //텍스트 변화 시 호출
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                result = writeMemo.text.toString() //EditText에 입력된 텍스트 받아와 result 변수에 저장
                saveBtn.isEnabled = result.isNotEmpty() //메모가 입력되었다면 버튼 활성화
            }

            //텍스트 변화 후 호출
            override fun afterTextChanged(p0: Editable?) {}
        })
        //recyclerView
        val memoList: MutableList<Memo> = memoDao.getAll()
        binding.recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = Adapter(memoList)


        saveBtn.setOnClickListener {
            if (result.isNotEmpty()) {
                val dateTime = System.currentTimeMillis().toString()

                //데이터 삽입
                memo = Memo(result, toggleButton.isPressed, dateTime) //Memo 객체 생성
                memoDao.insertMemo(memo)
                val memoList: MutableList<Memo> = memoDao.getAll()

                //recyclerView
                binding.recyclerView.layoutManager =
                    LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                binding.recyclerView.setHasFixedSize(true)
                binding.recyclerView.adapter = Adapter(memoList)
            }
        }

        toggleButton.setOnCheckedChangeListener {
                savedMemo,
                isChecked ->
            //데이터 수정
            val dateTime = System.currentTimeMillis().toString()
            memo = Memo(savedMemo.text.toString(), toggleButton.isChecked, dateTime) //Memo 객체 생성
            memoDao.updateMemo(memo)

            //데이터 저장
            //데이터를 저장할 때는 입력될 값의 타입에 맞는 Editor의 메소드를 사용해서 저장.
            //마지막에 apply() 메소드를 호출해야만 실제 파일에 반영됨
            sharedPreferences = getSharedPreferences(shared, 0)
            editor = sharedPreferences.edit() //에디터 객체 생성
            var value = toggleButton.isChecked //즐겨찾기 버튼이 눌렸는지
            editor.putBoolean("key", value) //키와 값을 저장.
            editor.apply()
        }

        likedBtn.setOnClickListener {
            //즐겨찾기 데이터 불러오기
            //데이터를 불러올 때는 데이터를 저장하는 것과는 다르게 Editor를 사용하지 않고 SharedPreferences의 메소드를 직접 호출해서 불러옴
            //만약 해당 키에 데이터가 없으면 defaultValue를 지정해서 해당 값을 기본으로 반환 함.
            sharedPreferences = getSharedPreferences(shared, 0)
            var value = sharedPreferences.getBoolean("key", false)
            toggleButton.isChecked = value //즐겨찾기 버튼 상태 저장

            //페이지 이동
            //Intent 객체 생성 -> Intent(현재 어플리케이션 정보, 이동하고자 하는 클래스)
            val intent = Intent(this, RepoActivity::class.java)
            //intent에 memo 객체를 담아줌 / putExtra(key, value) => 값을 담을 때 사용함
            intent.putExtra("starMemoInfo", memo)
            startActivity(intent)
        }
    }

    override fun onPause() {
        super.onPause()
        //pause 상황이 발생했을 때 입력했던 데이터를 꺼내와서 result 저장
        string = binding.writeMemo.text.toString()

        //pause 상황 발생 시 메시지 보여줌
        Toast.makeText(this,"PAUSE MEMO",Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        if(string.isNotEmpty()) { //저장된 메모가 있는 경우
            result = string //저장된 메모를 최종메모 변수에 저장
            binding.writeMemo.setText(result) //최종변수를 EditText에 넣어줌
            binding.saveBtn.isEnabled = true //버튼 활성화
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
                string = "" //저장된 메모 초기화
                binding.writeMemo.setText("") //메모 입력창 초기화
                binding.saveBtn.isEnabled = false //버튼 비활성화
            }
            .setCancelable(false) //다이얼로그 닫을 수 없도록
        builder.create().show() //화면에 출력
    }
}


