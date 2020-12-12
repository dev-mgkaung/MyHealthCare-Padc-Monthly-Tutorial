package mk.monthlytut.doctor.delegates

interface ChatRoomDelegate {
  fun onTapSendTextMessage(message : String )
  fun onTapAttachImage()
  fun onTapQuestionTemplate()
  fun onTapPrescription()
  fun onTapDoctorComment()
}