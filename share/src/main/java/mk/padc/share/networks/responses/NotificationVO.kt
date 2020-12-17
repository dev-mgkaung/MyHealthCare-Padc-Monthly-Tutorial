package mk.padc.share.networks.responses

import com.google.gson.annotations.SerializedName

//data class NotificationVO(
//        @SerializedName("to")
//        var to :String? = "",
//        @SerializedName("notification")
//        var notification : Notification? = null
//)
//
//data class Notification(
//        @SerializedName("body")
//        var body :String? = "",
//        @SerializedName("title")
//        var title : String? = null
//)


data class NotificationVO(
        @SerializedName("to")
        var to:String? = "",
        @SerializedName("data")
        var data : DataVO? = null
)


data class RegistrationAddRequest(
        @SerializedName("operation")
        var operation:String? ="",
        @SerializedName("notification_key_name")
        var notification_key_name:String? ="",
        @SerializedName("notification_key")
        var notification_key : String? ="",
        @SerializedName("registration_ids")
        var registration_ids : ArrayList<String> = arrayListOf()
)