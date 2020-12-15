package mk.padc.share.networks.responses

import com.google.gson.annotations.SerializedName

data class TopicMessageResponse
    (
    @SerializedName("message_id")
    var message_id: String? = "",
    )