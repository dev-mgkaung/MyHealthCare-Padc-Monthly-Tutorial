package mk.padc.share.persistances.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import mk.padc.share.data.vos.SendBy


class SendByConverter {
    @TypeConverter
    fun toString(dataList: SendBy): String {
        return Gson().toJson(dataList)
    }

    @TypeConverter
    fun toList(ListJsonStr: String): SendBy {
        val dataListType = object : TypeToken<SendBy>() {}.type
        return Gson().fromJson(ListJsonStr, dataListType)
    }
}