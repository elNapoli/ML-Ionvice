package com.baldomeronapoli.mlinvoice.presenter.ui.states

import com.baldomeronapoli.mlinvoice.presenter.utils.EmailValidation
import com.baldomeronapoli.mlinvoice.presenter.utils.ValidationError

data class Credential(
    @property:EmailValidation()
    val email: String,
    val password: String,
    val passwordVisibility: Boolean,
    val error: ValidationError? = null
)