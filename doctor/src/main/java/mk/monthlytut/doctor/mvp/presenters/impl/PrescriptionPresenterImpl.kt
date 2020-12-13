package mk.monthlytut.doctor.mvp.presenters.impl

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import mk.monthlytut.doctor.mvp.presenters.PrescriptionPresenter
import mk.monthlytut.doctor.mvp.views.PrescriptionView
import mk.padc.share.data.models.DoctorModel
import mk.padc.share.data.models.impl.DoctorModelImpl
import mk.padc.share.data.vos.MedicineVO
import mk.padc.share.data.vos.PrescriptionVO
import mk.padc.share.mvp.presenters.AbstractBasePresenter


class PrescriptionPresenterImpl : PrescriptionPresenter, AbstractBasePresenter<PrescriptionView>() {

    private val doctorModel: DoctorModel = DoctorModelImpl
    lateinit var mOwner: LifecycleOwner


    override fun onTapFinishConsulation(list: List<PrescriptionVO>) {

    }

    override fun onUiReady(context: Context, owner: LifecycleOwner) {
        mOwner =owner
    }

    override fun onTapSelectMedicine(medicineVO: MedicineVO) {
     mView?.displayRoutinechooseDialog(medicineVO)
    }

    override fun onUiReadyForPrescription(speciality: String) {

        doctorModel.getAllMedicine(speciality, onSuccess = {}, onError = {})

        doctorModel.getAllMedicineFromDB()
                .observe(mOwner, Observer {
                    it?.let{
                        mView?.displayMedicineList(it)
                    }

                })
    }
}

