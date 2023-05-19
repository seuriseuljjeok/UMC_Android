package com.example.database

import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.database.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    lateinit var editText: EditText
    lateinit var editSave: Button
    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor: Editor
    var shared: String = "file"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        editText = binding.editText
        editSave = binding.saveBtn
        setContentView(binding.root)

        //데이터 불러오기
        //데이터를 불러올 때는 데이터를 저장하는 것과는 다르게 Editor를 사용하지 않고 SharedPreferences의 메소드를 직접 호출해서 불러옴
        //만약 해당 키에 데이터가 없으면 defaultValue를 지정해서 해당 값을 기본으로 반환 함.
        sharedPreferences = getSharedPreferences(shared, 0)
        var value = sharedPreferences.getString("key","")
        editText.setText(value)

        editSave.setOnClickListener {
            //데이터 저장
            //데이터를 저장할 때는 입력될 값의 타입에 맞는 Editor의 메소드를 사용해서 저장.
            //마지막에 apply() 메소드를 호출해야만 실제 파일에 반영됨
            sharedPreferences = getSharedPreferences(shared, 0)
            editor = sharedPreferences.edit()
            var value = editText.text.toString()
            editor.putString("key",value)    //키와 값을 저장.
            editor.apply()
        }

    }
}