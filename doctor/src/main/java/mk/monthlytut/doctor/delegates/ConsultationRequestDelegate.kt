package mk.monthlytut.doctor.delegates

interface ConsultationRequestDelegate {
    fun onTapNext()
    fun onTapSkip()
    fun onTapPostpone()
    fun onTapAccept()
}