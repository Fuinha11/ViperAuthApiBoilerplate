package com.slb.gt.bluesilo.services.api

import com.slb.gt.bluesilo.data.models.Driver
import com.slb.gt.bluesilo.services.api.data.response.WikiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

interface SlbInterface {

    @GET("api.php")
    fun hitCountCheck(@Query("action") action: String,
                      @Query("format") format: String,
                      @Query("list") list: String,
                      @Query("srsearch") srsearch: String):
            Call<WikiResponse.Result>


}