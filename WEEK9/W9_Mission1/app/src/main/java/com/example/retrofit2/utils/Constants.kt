package com.example.retrofit2.utils

object Constants {
    const val TAG : String = "로그"
}

enum class SEARCH_TYPE {
    PHOTO,
    USER
}

enum class RESPONSE_STATE {
    OKAY,
    FAIL
}

object API {
    const val BASE_URL : String = "https://api.unsplash.com/"

    const val CLIENT_ID : String = "0YPsK7vU8LjVO8522vDSdv-eQ7gO6En6qbwflCicnmg"

    const val SEARCH_PHOTOS : String = "search/photos"
    const val SEARCH_USERS : String = "search/users"

}