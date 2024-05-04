package com.baldomeronapoli.mlinvoice.presenter.utils

import kotlin.reflect.KClass

@Target(AnnotationTarget.PROPERTY)
annotation class NotEmptyValidation()

@Target(AnnotationTarget.PROPERTY)
annotation class EmailValidation()

@Target(AnnotationTarget.PROPERTY)
annotation class PasswordValidation()


data class ValidationError(
    val property: String = "",
    val message: String = ""
)

class ValidateState<State : Any>(
    val kClass: KClass<State>
) {
    fun validate(state: State): ValidationError? {
        kClass.members.forEach {
            if (it.annotations.isEmpty())
                return@forEach

            val annotation = it.annotations[0];
            val property = it.name
            val value = it.call(state)

            if (annotation is NotEmptyValidation) {
                if (isEmpty(value)) {
                    return ValidationError(property, "Must fill this field")
                }
            }

            if (annotation is EmailValidation) {
                if (isNotEmail(value)) {
                    return ValidationError(property, "Email Inválido")
                }

            }

            if (annotation is PasswordValidation) {
                if (isNotPassword(value)) {
                    return ValidationError(property, "Not valid password")
                }
            }

        }

        return null
    }

    private fun isEmpty(value: Any?): Boolean {
        return value.toString().isEmpty()
    }

    private fun isNotEmail(value: Any?): Boolean {
        return !Regex("^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})")
            .matches(value.toString())
    }

    private fun isNotPassword(value: Any?): Boolean {
        return !Regex("^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d]{5,}$")
            .matches(value.toString());
    }
}

