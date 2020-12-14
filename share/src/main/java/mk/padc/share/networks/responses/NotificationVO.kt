package mk.padc.share.networks.responses

import com.google.gson.annotations.SerializedName

data class NotificationVO(
        @SerializedName("topic")
        var topic :String? = "",
        @SerializedName("data")
        var data : DataVO? = null
)
