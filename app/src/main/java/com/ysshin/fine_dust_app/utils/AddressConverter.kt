package com.ysshin.fine_dust_app.utils

object AddressConverter {
    fun contains(address: String) = convert(address) != ""

    fun convert(address: String): String {
        return when(address) {
            "서울특별시" -> "서울"
            "부산광역시" -> "부산"
            "대구광역시" -> "대구"
            "인천광역시" -> "인천"
            "광주광역시" -> "광주"
            "대전광역시" -> "대전"
            "울산광역시" -> "울산"
            "경기도" -> "경기"
            "강원도" -> "강원"
            "충청북도" -> "충북"
            "충청남도" -> "충남"
            "전라북도" -> "전북"
            "전라남도" -> "전남"
            "경상북도" -> "경북"
            "경상남도" -> "경남"
            "제주특별자치도" -> "제주"
            "세종특별자치도" -> "세종"
            else -> ""
        }
    }
}