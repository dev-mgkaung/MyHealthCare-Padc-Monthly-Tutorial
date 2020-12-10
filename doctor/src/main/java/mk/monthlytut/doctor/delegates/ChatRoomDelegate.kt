package mk.monthlytut.doctor.delegates

interface ChatRoomDelegate {
  fun onTapSendTextMessage()
  fun onTapAttachImage()
  fun onTapQuestionTemplate()
  fun onTapPrescription()
  fun onTapDoctorComment()
}