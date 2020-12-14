package mk.padc.share.networks.responses

import com.google.gson.annotations.SerializedName

data class DataVO(
        @SerializedName("name")
        var name: String? = "",
        var dob: String? = "",
        var id : String? = ""
)