package com.medibank.shop.common.util

interface Constants {

    companion object {
        const val BASE_URL = "https://newsapi.org/"
        const val HEADLINES = "v2/top-headlines"
        const val SOURCE = "v2/sources"
        const val UPDATE_NEWS_HEADLINES = "1"
        const val ARTICLE_DATA = "data"
    }

    interface HttpReqParamKey {
        companion object {
            const val COUNTRY = "country"
            const val SOURCE = "source"
            const val API_KEY = "apiKey"
            const val API_KEY_VALUE = "ecf41ddc61484f218c33c11bff044387"
        }
    }
}