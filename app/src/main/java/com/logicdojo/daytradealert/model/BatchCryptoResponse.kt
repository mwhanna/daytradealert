package com.logicdojo.daytradealert.model

import com.google.gson.annotations.SerializedName

/**
 * Batch Crypto Response object
 */

class BatchCryptoResponse(val data: CryptoResponse)

class CryptResponse(val data: List<CryptoObject>)

class CryptoResponse(
        @SerializedName("1")
        val object1: CryptoObject,
        @SerializedName("2")
        val object2: CryptoObject,
        @SerializedName("3")
        val object3: CryptoObject,
        @SerializedName("4")
        val object4: CryptoObject,
        @SerializedName("5")
        val object5: CryptoObject,
        @SerializedName("6")
        val object6: CryptoObject,
        @SerializedName("7")
        val object7: CryptoObject,
        @SerializedName("8")
        val object8: CryptoObject,
        @SerializedName("9")
        val object9: CryptoObject,
        @SerializedName("10")
        val object10: CryptoObject,
        @SerializedName("11")
        val object11: CryptoObject,
        @SerializedName("12")
        val object12: CryptoObject,
        @SerializedName("13")
        val object13: CryptoObject,
        @SerializedName("14") val
        object14: CryptoObject,
        @SerializedName("15")
        val object15: CryptoObject,
        @SerializedName("16")
        val object16: CryptoObject,
        @SerializedName("17")
        val object17: CryptoObject,
        @SerializedName("18")
        val object18: CryptoObject,
        @SerializedName("19")
        val object19: CryptoObject,
        @SerializedName("20")
        val object20: CryptoObject
)

class CryptoObject(val coinDetails: CoinDetails2)

class CryptoObject2(val coinDetails: CoinDetails3)




