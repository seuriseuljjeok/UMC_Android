package com.example.retrofit2.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

//문자열이 제이슨 형태인지 => Json object면 boolean 반환
//문자열이 {로 시작해서 }로 끝나면 제이슨 형태이니까.
fun String?.isJsonObject():Boolean {
    if(this?.startsWith("{") == true && this.endsWith("}")){
        return true
    }else{
        return false
    }
//    return this?.startsWith("{") == true && this.endsWith("}") //위와 같은 코드
}

//문자열이 제이슨 배열 형태인지
fun String?.isJsonArray() : Boolean {
    if(this?.startsWith("[") == true && this.endsWith("]")){
        return true
    }else{
        return false
    }
}

//에딧 텍스트에 대한 익스텐션
fun EditText.onMyTextChanged(completion: (Editable?) -> Unit){
    this.addTextChangedListener(object: TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable?) {
            completion(s)
        }
    })
}