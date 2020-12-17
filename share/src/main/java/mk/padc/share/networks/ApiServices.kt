package mk.padc.share.networks

import io.reactivex.Observable
import mk.padc.share.networks.responses.NotiResponse
import mk.padc.share.networks.responses.NotificationVO
import mk.padc.share.utils.APIKEY
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {
    @Headers(
        "Content-Type:application/json",
        "Authorization:$APIKEY"
    )
    @POST("fcm/send")
    fun sendFcm(@Body notificationVO: NotificationVO) : Observable<NotiResponse>


}