package mk.padc.share.persistances.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import mk.padc.share.data.vos.AddressVO

class AddressConverter {
    @TypeConverter
    fun toString(dataList: ArrayList<AddressVO>): String {
        return Gson().toJson(dataList)
    }

    @TypeConverter
    fun toList(ListJsonStr: String): ArrayList<AddressVO> {
        val dataListType = object : TypeToken<ArrayList<AddressVO>>(){}.type
        return Gson().fromJson(ListJsonStr, dataListType)
    }
}