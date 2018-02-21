package com.logicdojo.daytradealert.service

import com.logicdojo.daytradealert.model.CryptoObject2
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Service for calling Crypto coin API
 */
interface CryptoService {
    @GET("markets")
    fun getCurrentCryptos(): Observable<List<CryptoObject2>>
}