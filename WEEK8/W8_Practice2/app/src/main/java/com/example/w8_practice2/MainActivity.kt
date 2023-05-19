package com.example.w8_practice2

import android.content.Intent
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.room.Room
import com.example.w8_practice2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var userDB: UserDatabase
    lateinit var saveBtn: Button

    lateinit var userName: EditText
    lateinit var age: EditText
    lateinit var phoneNum: EditText
    private lateinit var userDao: UserDao
    lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        saveBtn = binding.saveBtn

        userName = binding.name
        age = binding.age
        phoneNum = binding.phoneNum
        setContentView(binding.root)

        userDB = Room.databaseBuilder(applicationContext, UserDatabase::class.java, "MyUserDB")
            .fallbackToDestructiveMigration()   //스키마(Database) 버전 변경 가능
            .allowMainThreadQueries()           //Main Thread에서 DB에 IO(입출력)을 가능하게 함
            .build()

        userDao = userDB.userDao() //인터페이스 객체 할당

        saveBtn.setOnClickListener {
            //데이터 삽입
            user = User(userName.text.toString(), age.text.toString(), phoneNum.text.toString()) //User 객체 생성
            userDao.insertUser(user)
            val userList: List<User> = userDao.getAll()

            //데이터 조회
            for (i in 0..userList.size-1){
                Log.d("Test",userList.get(i).toString())
            }

            //intent 객체 생성
            val intent = Intent(this,UserActivity::class.java)
            intent.putExtra("userInfo",user)
            startActivity(intent)
        }

        //데이터 수정
//        val user1 = User()
//        user1.id = 7
//        user1.name = "YunHeeSeul"
//        user1.age = "24"
//        user1.phoneNumber = "01012345678"
//        userDao.updateUser(user1)


        //데이터 삭제
//        val user1 = User()
//        user1.id = 7
//        userDao.deleteUser(user1)

    }
}

