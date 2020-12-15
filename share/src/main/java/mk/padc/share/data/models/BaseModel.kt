package mk.padc.share.data.models

import android.content.Context
import mk.padc.share.networks.ApiService
import mk.padc.share.persistances.MyCareDatabase
import mk.padc.share.utils.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


abstract class BaseModel  {

    protected var mApi: ApiService

    protected lateinit var mTheDB: MyCareDatabase

    init {
        val mOkHttpClient = OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build()

        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(mOkHttpClient)
                .build()

        mApi = retrofit.create(ApiService::class.java)
    }


    fun initDatabase(context: Context) {
        mTheDB = MyCareDatabase.getDBInstance(context)
    }

}