package mk.padc.share.data.models

import mk.padc.share.networks.auth.AuthManager


interface AuthenticationModel {

    var mAuthManager: AuthManager

    fun login(email: String, password: String, onSuccess: () -> Unit, onFailure: (String) -> Unit)

    fun register(
        username: String,
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    )


    fun updateProfile(
            photoUrl : String,
            onSuccess: () -> Unit,
            onFailure: (String) -> Unit
    )

}