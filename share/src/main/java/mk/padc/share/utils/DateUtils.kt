package mk.padc.share.utils

import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*

class DateUtils
{
    fun getDaysAgo(daysAgo: Int): Date {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, -daysAgo)
        return calendar.time
    }

    fun getCurrentDate() : String{
        val simpleDateFormat = SimpleDateFormat("yyyy.MM.dd")
        return simpleDateFormat.format(Date())
    }

    fun getCurrentHourMin() : String{
        val simpleDateFormat = SimpleDateFormat("HH::mm")
        return simpleDateFormat.format(Date())
    }
}
