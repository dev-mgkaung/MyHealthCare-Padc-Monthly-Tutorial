package mk.padc.share.persistances.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import mk.padc.share.data.vos.DeliveryRoutineVO


class DeliveryRoutineConverter {
    @TypeConverter
    fun toString(dataList: DeliveryRoutineVO): String {
        return Gson().toJson(dataList)
    }

    @TypeConverter
    fun toList(ListJsonStr: String): DeliveryRoutineVO {
        val dataListType = object : TypeToken<DeliveryRoutineVO>() {}.type
        return Gson().fromJson(ListJsonStr, dataListType)
    }
}