package mk.padc.share.persistances.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import mk.padc.share.data.vos.DoctorVO

class DoctorConverter {
    @TypeConverter
    fun toString(dataList: DoctorVO): String {
        return Gson().toJson(dataList)
    }

    @TypeConverter
    fun toList(ListJsonStr: String): DoctorVO {
        val dataListType = object : TypeToken<DoctorVO>() {}.type
        return Gson().fromJson(ListJsonStr, dataListType)
    }
}