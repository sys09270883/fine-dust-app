package com.ysshin.fine_dust_app.utils

object AddressConverter {
    private val converter = hashMapOf(
        "서울특별시" to "서울",
        "부산광역시" to "부산",
        "대구광역시" to "대구",
        "인천광역시" to "인천",
        "광주광역시" to "광주",
        "대전광역시" to "대전",
        "울산광역시" to "울산",
        "경기도" to "경기",
        "강원도" to "강원",
        "충청북도" to "충북",
        "충청남도" to "충남",
        "전라북도" to "전북",
        "전라남도" to "전남",
        "경상북도" to "경북",
        "경상남도" to "경남",
        "제주특별자치도" to "제주",
        "세종특별자치도" to "세종"
    )

    fun convert(address: String) = converter[address]!!
}