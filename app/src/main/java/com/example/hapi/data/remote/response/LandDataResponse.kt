package com.example.hapi.data.remote.response

import com.example.hapi.domain.model.NPK

data class LandDataResponse(
    val water_level:Int,
    val npk:NPK
)
