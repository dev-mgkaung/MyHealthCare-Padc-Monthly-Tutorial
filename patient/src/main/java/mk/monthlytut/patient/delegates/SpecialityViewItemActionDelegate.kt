package mk.monthlytut.patient.delegates

import mk.padc.share.data.vos.SpecialitiesVO

interface SpecialityViewItemActionDelegate {
    fun onTapSpeciality(specialitiesVO: SpecialitiesVO)
}