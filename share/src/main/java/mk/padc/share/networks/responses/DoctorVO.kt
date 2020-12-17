package mk.padc.share.networks.responses

import com.google.gson.annotations.SerializedName

data class DataVO(
        @SerializedName("name")
        var title: String? = "",
        var body: String? = "",
        var id : String? = ""
)