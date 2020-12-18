package mk.monthlytut.patient.mvp.presenters.impl

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import mk.monthlytut.patient.mvp.presenters.CheckoutPresenter
import mk.monthlytut.patient.mvp.views.CheckOutView
import mk.monthlytut.patient.util.SessionManager
import mk.padc.share.data.models.PatientModel
import mk.padc.share.data.models.impl.PatientModelImpl
import mk.padc.share.data.vos.DoctorVO
import mk.padc.share.data.vos.PatientVO
import mk.padc.share.data.vos.PrescriptionVO
import mk.padc.share.mvp.presenters.AbstractBasePresenter


class CheckoutPresenterImpl : CheckoutPresenter, AbstractBasePresenter<CheckOutView>() {

    private val patientModel: PatientModel = PatientModelImpl
    private lateinit var mOwner : LifecycleOwner


    override fun onUiReadyCheckout(consultationChatId: String, owner: LifecycleOwner) {

        patientModel.getPrescription(consultationChatId, onSuccess = {}, onError = {})

        patientModel.getPrescriptionFromDB()
                .observe(owner, Observer {
                    it?.let{
                        mView?.displayPrescription(it)
                    }
                })

        patientModel.getPatientByEmail(SessionManager.patient_email.toString(), onSuccess = {}, onError = {})

        patientModel.getPatientByEmailFromDB(SessionManager.patient_email.toString())
                .observe(owner, Observer {
                    it?.let{
                        mView?.displayShippingAddress(it.address)
                    }
                })
    }

    override fun onTapCheckout(prescriotionList: List<PrescriptionVO>, deliveryAddressVO: String, doctorVO: DoctorVO?, patientVO: PatientVO?, total_price: String) {
        if (doctorVO != null && patientVO != null) {
                patientModel.checkout(prescriotionList,
                        deliveryAddressVO,
                        doctorVO,
                        patientVO,
                        total_price,
                        onSuccess = {}, onFailure = {})
              mView?.displayConfirmDialog(prescriotionList,deliveryAddressVO,total_price)
        }
    }

    override fun onTapAddShipping(patientVO: PatientVO?) {

        patientVO?.let {
            patientModel.addPatientInfo(it,onSuccess = {}, onError = {})
        }

        patientModel.getPatientByEmail(SessionManager.patient_email.toString(), onSuccess = {}, onError = {})

        patientModel.getPatientByEmailFromDB(SessionManager.patient_email.toString())
                .observe(mOwner, Observer {
                    it?.let{
                        mView?.displayShippingAddress(it.address)
                    }
                })
    }


    override fun onUiReady(context: Context, owner: LifecycleOwner) {
        mOwner= owner
    }

    override fun onTapSelected(address: String) {

    }

}