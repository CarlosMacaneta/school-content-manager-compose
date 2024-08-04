package com.cm.schoolcontentmanager.login

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Login
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cm.schoolcontentmanager.R
import com.cm.schoolcontentmanager.login.states.FieldType
import com.cm.schoolcontentmanager.login.states.LoginAction
import com.cm.schoolcontentmanager.login.states.LoginUiState
import com.cm.schoolcontentmanager.ui.theme.PurpleGray
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LoginUI(
        modifier = modifier,
        uiState = uiState,
        onAction = viewModel::onAction
    )
}

@Composable
private fun LoginUI(
    modifier: Modifier = Modifier,
    uiState: LoginUiState,
    onAction: (LoginAction) -> Unit
) {

    var isPasswordHidden by remember { mutableStateOf(true) }

    Scaffold { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = stringResource(R.string.sign_in),
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .alpha(.65f),
                    text = stringResource(R.string.sign_in_welcome),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.size(32.dp))
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Enter your credentials to continue",
                        maxLines = 1,
                        softWrap = true,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .alpha(.65f),
                    )
                    Spacer(modifier = Modifier.size(16.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(.35f)
                            .height(5.dp)
                            .background(
                                color = PurpleGray,
                                shape = RoundedCornerShape(32.dp)
                            )
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                value = uiState.email,
                onValueChange = {
                    onAction(LoginAction.OnFieldChange(FieldType.EMAIL, it))
                },
                isError = !uiState.isValidEmail,
                enabled = !uiState.isLoading,
                supportingText = {
                    if (!uiState.isValidEmail) {
                        Text(
                            text = stringResource(R.string.invalid_email),
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = stringResource(R.string.email)
                    )
                },
                label = {
                    Text(text = stringResource(R.string.email))
                },
                placeholder = {
                    Text(text = stringResource(R.string.email))
                },
                singleLine = true,
                maxLines = 1,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = uiState.password,
                    onValueChange = {
                        onAction(LoginAction.OnFieldChange(FieldType.PASSWORD, it))
                    },
                    isError = !uiState.isValidPassword,
                    enabled = !uiState.isLoading,
                    supportingText = {
                        if (!uiState.isValidPassword) {
                            Text(
                                text = stringResource(R.string.invalid_password),
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Password,
                            contentDescription = stringResource(R.string.password)
                        )
                    },
                    trailingIcon = {
                        IconButton(onClick = { isPasswordHidden = !isPasswordHidden }) {
                            Icon(
                                imageVector = if (isPasswordHidden) Icons.Default.Visibility
                                else Icons.Default.VisibilityOff,
                                contentDescription = null
                            )
                        }
                    },
                    label = {
                        Text(text = stringResource(R.string.password))
                    },
                    placeholder = {
                        Text(text = stringResource(R.string.password))
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    visualTransformation = if (isPasswordHidden) PasswordVisualTransformation()
                        else VisualTransformation.None
                )

                TextButton(
                    modifier = Modifier
                        .align(Alignment.End)
                        .offset(y = (-15).dp),
                    onClick = { onAction(LoginAction.ForgotPassword) }
                ) {
                    Text(
                        text = stringResource(R.string.forgot_password),
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp)
                    .padding(horizontal = 32.dp),
                shape = RoundedCornerShape(16.dp),
                onClick = { onAction(LoginAction.SignIn) },
                enabled = uiState.isSignInEnabled && !uiState.isLoading,
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.Login,
                    contentDescription = stringResource(R.string.sign_in)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = stringResource(R.string.sign_in))
            }

            AnimatedVisibility (uiState.isLoading) {
                CircularProgressIndicator()
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp, vertical = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically
            ) {
                HorizontalDivider(
                    modifier = Modifier.fillMaxWidth(0.3f),
                    color = Color.LightGray
                )
                Text(text = stringResource(R.string.or_sign_in_with))
                HorizontalDivider(
                    modifier = Modifier.fillMaxWidth(),
                    color = Color.LightGray
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedButton(
                    modifier = Modifier
                        .fillMaxWidth(.5f)
                        .height(54.dp),
                    shape = RoundedCornerShape(16.dp),
                    onClick = { onAction(LoginAction.SignInWithGoogle) },
                    enabled = !uiState.isLoading,
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_google),
                        contentDescription = stringResource(R.string.sign_in_with_google)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = stringResource(R.string.google))
                }

                OutlinedButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp),
                    shape = RoundedCornerShape(16.dp),
                    onClick = { onAction(LoginAction.SignInWithGoogle) },
                    enabled = !uiState.isLoading,
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_microsoft),
                        contentDescription = stringResource(R.string.sign_in_with_microsoft)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = stringResource(R.string.microsoft))
                }
            }
        }
    }
}