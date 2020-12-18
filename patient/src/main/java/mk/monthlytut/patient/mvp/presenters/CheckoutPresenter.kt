package mk.monthlytut.patient.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import mk.monthlytut.patient.delegates.CheckoutDelegate
import mk.monthlytut.patient.delegates.ShippingAddressDelegate
import mk.monthlytut.patient.mvp.views.CheckOutView
import mk.padc.share.data.vos.DoctorVO
import mk.padc.share.data.vos.PatientVO
import mk.padc.share.data.vos.PrescriptionVO
import mk.padc.share.mvp.presenters.BasePresenter

interface CheckoutPresenter : BasePresenter<CheckOutView> , CheckoutDelegate ,ShippingAddressDelegate{
    fun onUiReadyCheckout( consultationChatId : String ,  owner: LifecycleOwner)
    fun onTapCheckout(prescriotionList : List<PrescriptionVO>,deliveryAddressVO: String,
                      doctorVO: DoctorVO?, patientVO: PatientVO?, total_price : String)
    fun onTapAddShipping(patientVO: PatientVO?)
}