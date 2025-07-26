package com.saif.cashiapp.transactions.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.saif.cashiapp.koinViewModel
import com.saif.cashiapp.navigation.Screens
import com.saif.cashiapp.transactions.presenter.SendPaymentUiState
import com.saif.cashiapp.transactions.presenter.SendPaymentViewModel
import kotlinx.coroutines.delay

@Composable
fun SendPaymentScreen(
    navController: NavController = rememberNavController()
) {

    val viewModel: SendPaymentViewModel = koinViewModel()
    val state by viewModel.uiState.collectAsState()

    var email by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }
    var currency by remember { mutableStateOf("USD") }

    val currencies = listOf("USD", "EUR")
    LaunchedEffect(state) {
        if (state is SendPaymentUiState.Success || state is SendPaymentUiState.Error) {
            delay(3000)
            viewModel.resetUiState()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Text("Send Payment", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Recipient Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = amount,
            onValueChange = { amount = it },
            label = { Text("Amount") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        var expanded by remember { mutableStateOf(false) }

        Box {
            OutlinedTextField(
                value = currency,
                onValueChange = {},
                label = { Text("Currency") },
                modifier = Modifier.fillMaxWidth(),
                readOnly = true,
                trailingIcon = {
                    Icon(
                        Icons.Default.ArrowDropDown, contentDescription = null,
                        modifier = Modifier.clickable { expanded = true })
                }
            )

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                currencies.forEach {
                    DropdownMenuItem(
                        text = { Text(it) },
                        onClick = {
                            currency = it
                            expanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (state is SendPaymentUiState.Loading) {
            CircularProgressIndicator(
                modifier = Modifier.size(36.dp).align(Alignment.CenterHorizontally),
                color = MaterialTheme.colorScheme.primary
            )
        } else
            Button(
                onClick = {
                    viewModel.sendPayment(email, amount, currency)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Send Payment")
            }
        Spacer(modifier = Modifier.height(48.dp))
        when (state) {
            is SendPaymentUiState.Success -> {
                Text(
                    text = (state as SendPaymentUiState.Success).message,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            is SendPaymentUiState.Error -> {
                Text(
                    text = (state as SendPaymentUiState.Error).message,
                    color = MaterialTheme.colorScheme.error
                )
            }

            else -> {}
        }
        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = { navController.navigate(Screens.TRANSACTIONS_HISTORY.name) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
        ) {
            Text("View Transaction History")
        }

    }
}
