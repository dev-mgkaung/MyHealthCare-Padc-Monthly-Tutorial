package mk.padc.share.utils

import java.text.DateFormat
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
        val calendar = Calendar.getInstance()
        var currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.time)
        return currentDate
    }

    fun getCurrentDateTime() : String{
        return SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(Date())
    }

    fun getCurrentHourMinAMPM() : String{
        return SimpleDateFormat("hh:mm a").format(Date())
    }
}
