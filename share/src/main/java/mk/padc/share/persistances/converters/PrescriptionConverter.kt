package mk.padc.share.persistances.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import mk.padc.share.data.vos.GeneralQuestionVO
import mk.padc.share.data.vos.PrescriptionVO

class PrescriptionConverter {
    @TypeConverter
    fun toString(dataList: PrescriptionVO): String {
        return Gson().toJson(dataList)
    }

    @TypeConverter
    fun toList(ListJsonStr: String): ArrayList<PrescriptionVO>  {
        val dataListType = object : TypeToken<ArrayList<PrescriptionVO>>() {}.type
        return Gson().fromJson(ListJsonStr, dataListType)
    }
}