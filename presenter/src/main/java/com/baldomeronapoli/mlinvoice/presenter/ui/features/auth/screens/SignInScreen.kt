package com.baldomeronapoli.mlinvoice.presenter.ui.features.auth.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.baldomeronapoli.mlinvoice.presenter.R
import com.baldomeronapoli.mlinvoice.presenter.base.composables.MyOutlinedTextField
import com.baldomeronapoli.mlinvoice.presenter.components.CardFeedback
import com.baldomeronapoli.mlinvoice.presenter.components.FeedbackType
import com.baldomeronapoli.mlinvoice.presenter.components.LoadingDialog
import com.baldomeronapoli.mlinvoice.presenter.state.BaseUiState
import com.baldomeronapoli.mlinvoice.presenter.ui.viewmodels.auth.AuthContract
import com.baldomeronapoli.mlinvoice.presenter.utils.AnimateVisibility
import timber.log.Timber

@Composable
fun SignInScreen(
    state: State<AuthContract.State>,
    onIntent: (event: AuthContract.Intent) -> Unit,
) {
    Timber.e(state.value.userState.toString())
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .padding(bottom = 200.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LoadingDialog(state.value.userState is BaseUiState.Loading)
        Text(
            text = stringResource(id = R.string.sign_in_title),
            style = MaterialTheme.typography.headlineLarge
        )

        Text(
            text = stringResource(id = R.string.sign_in_subtitle),
            style = MaterialTheme.typography.titleLarge
        )
        AnimateVisibility(
            visible = state.value.userState.message !== null,
        ) {
            CardFeedback(
                modifier = Modifier.padding(top = 16.dp),
                message = state.value.userState.message ?: "",
                type = if (state.value.userState is BaseUiState.Error) FeedbackType.ERROR else FeedbackType.SUCCESS

            )
        }
        MyOutlinedTextField(
            modifier = Modifier
                .padding(top = 24.dp)
                .fillMaxWidth(),
            value = state.value.credential.email,
            placeholder = stringResource(id = R.string.example_email),
            label = stringResource(id = R.string.email),
            onValueChange = { value ->
                onIntent(AuthContract.Intent.OnUpdateEmail(value))
            },
            error = if (state.value.credential.error?.property == "email") state.value.credential.error else null,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                autoCorrect = true,
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
        )

        MyOutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            singleLine = true,
            value = state.value.credential.password,
            visualTransformation = if (state.value.credential.passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            label = stringResource(id = R.string.password),
            placeholder = stringResource(id = R.string.password),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            onValueChange = { value ->
                onIntent(AuthContract.Intent.OnUpdatePassword(value))
            },
            error = if (state.value.credential.error?.property == "password") state.value.credential.error else null,
            trailingIcon = {
                val image = if (state.value.credential.passwordVisibility)
                    Icons.Filled.Visibility
                else Icons.Filled.VisibilityOff
                IconButton(onClick = { onIntent(AuthContract.Intent.TogglePasswordVisibility) }) {
                    Icon(imageVector = image, contentDescription = null)
                }
            }
        )

        Button(
            onClick = { onIntent(AuthContract.Intent.SignIn) },
            enabled = state.value.credential.error === null
        ) {
            Text(stringResource(id = R.string.sign_in_title))
        }
        Text(
            modifier = Modifier
                .padding(vertical = 16.dp)
                .clickable {
                    onIntent(AuthContract.Intent.GoToSignUp)
                },
            text = stringResource(id = R.string.new_user),
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold
        )


    }

}