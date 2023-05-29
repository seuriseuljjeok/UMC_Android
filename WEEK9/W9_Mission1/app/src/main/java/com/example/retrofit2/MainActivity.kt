package com.example.retrofit2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ProgressBar
import android.widget.RadioGroup
import android.widget.ScrollView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.example.retrofit2.databinding.ActivityMainBinding
import com.example.retrofit2.databinding.LayoutButtonSearchBinding
import com.example.retrofit2.retrofit.RetrofitInterface
import com.example.retrofit2.retrofit.RetrofitManager
import com.example.retrofit2.utils.Constants.TAG
import com.example.retrofit2.utils.RESPONSE_STATE
import com.example.retrofit2.utils.SEARCH_TYPE
import com.example.retrofit2.utils.onMyTextChanged
import com.google.android.material.button.MaterialButton
import com.google.android.material.radiobutton.MaterialRadioButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout


class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    lateinit var binding2: LayoutButtonSearchBinding
    lateinit var search_term_radio_group : RadioGroup
    lateinit var photo_search_radio_btn : MaterialRadioButton
    lateinit var user_search_radio_btn : MaterialRadioButton
    lateinit var search_term_edit_text : TextInputEditText
    lateinit var search_term_text_layout : TextInputLayout
    lateinit var frame_search_btn : FrameLayout
    lateinit var main_scrollview : ScrollView
    lateinit var btn_search : Button
    lateinit var btn_progress : ProgressBar

    private var currentSearchType: SEARCH_TYPE = SEARCH_TYPE.PHOTO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding2 = LayoutButtonSearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        search_term_radio_group = binding.searchTermRadioGroup
        photo_search_radio_btn = binding.photoSearchRadioBtn
        user_search_radio_btn = binding.userSearchRadioBtn
        search_term_edit_text = binding.searchTermEditText
        search_term_text_layout = binding.searchTermTextLayout
        main_scrollview = binding.mainScrollview
        frame_search_btn = binding2.frameSearchBtn
        btn_search = binding.btnSearch
        btn_progress = binding2.btnProgress


        Log.d(TAG, "MainActivity - onCreate() called")

        //라디오 그룹 가져오기
        search_term_radio_group.setOnCheckedChangeListener {_, checkedId ->
            //swich문
            when(checkedId) {
                photo_search_radio_btn.id -> {
                    Log.d(TAG,"사진검색 버튼 클릭!")
                    search_term_text_layout.hint = "사진검색"
                    search_term_text_layout.startIconDrawable = resources.getDrawable(R.drawable.ic_photo_library_black_24dp, resources.newTheme())
                    this.currentSearchType = SEARCH_TYPE.PHOTO
                }
                user_search_radio_btn.id -> {
                    Log.d(TAG,"사용자검색 버튼 클릭!")
                    search_term_text_layout.hint = "사용자검색"
                    search_term_text_layout.startIconDrawable = resources.getDrawable(R.drawable.ic_person_black_24dp, resources.newTheme())
                    this.currentSearchType = SEARCH_TYPE.USER
                }
            }
            Log.d(TAG,"MainActivity - OnCheckedChange() called / currentSearchType : $currentSearchType")
        }

        //텍스트가 변경이 되었을 때
        search_term_edit_text.onMyTextChanged {
            //입력된 글자가 하나라도 있다면
            if(it.toString().count() > 0){
                //검색 버튼을 보여준다.
                frame_search_btn.visibility = View.VISIBLE
                search_term_text_layout.helperText = " "

                //스크롤뷰를 올린다.
                main_scrollview.scrollTo(0,200)
            } else {
                frame_search_btn.visibility = View.INVISIBLE
                search_term_text_layout.helperText = "검색어를 입력해주세요"
            }

            if (it.toString().count() == 12) {
                Log.d(TAG,"MainActivity - 에러 띄우기")
                Toast.makeText(this, "검색어는 12자까지만 가능합니다.", Toast.LENGTH_SHORT).show()
            }
        }

        //검색 버튼 클릭 시
        btn_search.setOnClickListener{
            Log.d(TAG, "MainActivity - 검색 버튼이 클릭되었음. / currentSearchType : $currentSearchType")
            //검색 api 호출
            RetrofitManager.instance.searchPhotos(searchTerm = search_term_edit_text.toString(), completion = {
                responseState, responseBody ->
                when(responseState){
                    RESPONSE_STATE.OKAY -> {
                        Log.d(TAG, "API 호출 성공 : $responseBody")
                    }
                    RESPONSE_STATE.FAIL -> {
                        Toast.makeText(this,"API 호출 에러입니다.", Toast.LENGTH_SHORT).show()
                        Log.d(TAG, "API 호출 실패 : $responseBody")
                    }
                }
            })
//            this.handleSearchButtonUI()
        }
    } //onCreate

//    private fun handleSearchButtonUI(){
//        btn_progress.visibility = View.VISIBLE
//        btn_search.text = ""
//        Handler().postDelayed({
//            btn_progress.visibility = View.INVISIBLE
//            btn_search.text="검색"
//        }, 1500)
//    }
}