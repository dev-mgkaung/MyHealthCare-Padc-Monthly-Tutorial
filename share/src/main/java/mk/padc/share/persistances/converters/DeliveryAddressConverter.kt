package mk.padc.share.persistances.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import mk.padc.share.data.vos.DeliveryAddressVO

class DeliveryAddressConverter {
    @TypeConverter
    fun toString(dataList: DeliveryAddressVO): String {
        return Gson().toJson(dataList)
    }

    @TypeConverter
    fun toList(ListJsonStr: String): DeliveryAddressVO {
        val dataListType = object : TypeToken<DeliveryAddressVO>() {}.type
        return Gson().fromJson(ListJsonStr, dataListType)
    }
}