package mk.padc.share.persistances.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import mk.padc.share.data.vos.QuestionAnswerVO

class GeneralQuestionConverter {
    @TypeConverter
    fun toString(dataList: ArrayList<QuestionAnswerVO>): String {
        return Gson().toJson(dataList)
    }

    @TypeConverter
    fun toList(ListJsonStr: String): ArrayList<QuestionAnswerVO> {
        val dataListType = object : TypeToken<ArrayList<QuestionAnswerVO>>(){}.type
        return Gson().fromJson(ListJsonStr, dataListType)
    }
}