package com.saif.cashiapp.transactions.view

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.saif.cashiapp.navigation.Screens

@Composable
fun TransactionScreen() {
    val navController = rememberNavController()
    SendPaymentScreen(
        navController
    )

    Spacer(modifier = Modifier.height(8.dp))
    Button(
        onClick = { navController.navigate(Screens.TRANSACTIONS_HISTORY.name) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text("View Transaction History")
    }
}
