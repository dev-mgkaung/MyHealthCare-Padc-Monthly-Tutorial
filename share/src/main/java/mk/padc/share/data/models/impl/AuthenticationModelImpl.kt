package mk.padc.share.data.models.impl

import mk.padc.share.data.models.AuthenticationModel
import mk.padc.share.networks.auth.AuthManager
import mk.padc.share.networks.auth.FirebaseAuthManager

object AuthenticationModelImpl : AuthenticationModel {

    override var mAuthManager: AuthManager = FirebaseAuthManager

    override fun login(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mAuthManager.login(email, password, onSuccess, onFailure)
    }

    override fun register(
        username: String,
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mAuthManager.register(username, email, password, onSuccess, onFailure)
    }

    override fun updateProfile(photoUrl: String, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        mAuthManager.updateProfile(photoUrl, onSuccess, onFailure)
    }

}