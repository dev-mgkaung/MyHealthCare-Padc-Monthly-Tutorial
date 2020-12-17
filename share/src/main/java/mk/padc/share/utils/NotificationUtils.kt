package mk.padc.share.utils

import android.content.Context
import mk.padc.share.R
import mk.padc.share.data.vos.DoctorVO
import mk.padc.share.data.vos.PatientVO
import mk.padc.share.networks.responses.DataVO
import mk.padc.share.networks.responses.NotificationVO


fun prepareNotificationForPatient(context: Context,to:String?, data: PatientVO): NotificationVO {
    val notificationVO = NotificationVO()
    val dataVO = DataVO()
    notificationVO.to = to.toString()
    dataVO.title = context.getString(R.string.noti_title)
    dataVO.body = "${data.name}${context.getString(R.string.noti_body_for_doctor)}"
    dataVO.id = data.id
    notificationVO.data = dataVO
    return notificationVO
}

fun prepareNotificationForDoctor(context:Context, to:String?, data: DoctorVO):NotificationVO{
    val notificationVO = NotificationVO()
    val dataVO = DataVO()
    notificationVO.to = to
    dataVO.title = context.getString(R.string.noti_title)
    dataVO.body = "${data.name}${context.getString(R.string.noti_body_for_doctor)}"
    dataVO.id = data.id
    notificationVO.data = dataVO
    return notificationVO
}
