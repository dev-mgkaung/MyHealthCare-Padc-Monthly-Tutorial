package mk.padc.share.mvp.presenters.impl

import mk.padc.share.mvp.presenters.AbstractBasePresenter
import mk.padc.share.mvp.presenters.BasePresenter
import mk.padc.share.mvp.views.BaseView

abstract class BaseAppPresenterImpl<V : BaseView> : AbstractBasePresenter<V>(), BasePresenter<V>
