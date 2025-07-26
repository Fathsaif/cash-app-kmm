package com.saif.cashiapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.saif.cashiapp.history.view.TransactionsHistoryScreen
import com.saif.cashiapp.transactions.view.SendPaymentScreen

@Composable
fun Navigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screens.TRANSACTIONS.name
    ) {

        composable(Screens.TRANSACTIONS.name) {
            SendPaymentScreen(navController)
        }

        composable(Screens.TRANSACTIONS_HISTORY.name) {
            TransactionsHistoryScreen(navController)
        }
    }
}