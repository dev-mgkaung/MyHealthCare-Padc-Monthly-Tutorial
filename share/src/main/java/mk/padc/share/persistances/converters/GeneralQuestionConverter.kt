package mk.padc.share.persistances.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import mk.padc.share.data.vos.GeneralQuestionVO

class GeneralQuestionConverter {
    @TypeConverter
    fun toString(dataList: GeneralQuestionVO): String {
        return Gson().toJson(dataList)
    }

    @TypeConverter
    fun toList(ListJsonStr: String): ArrayList<GeneralQuestionVO> {
        val dataListType = object : TypeToken<ArrayList<GeneralQuestionVO>>(){}.type
        return Gson().fromJson(ListJsonStr, dataListType)
    }
}