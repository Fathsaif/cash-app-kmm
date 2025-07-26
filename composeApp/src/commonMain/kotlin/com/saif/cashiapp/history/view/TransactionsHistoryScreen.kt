package com.saif.cashiapp.history.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.saif.cashiapp.history.presenter.TransactionHistoryUiState
import com.saif.cashiapp.history.presenter.TransactionsHistoryViewmodel
import com.saif.cashiapp.history.presenter.model.TransactionItem
import com.saif.cashiapp.history.view.skelton.HomeSkeletonScreen
import com.saif.cashiapp.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionsHistoryScreen(
    navController: NavController = rememberNavController()
) {

    val viewmodel: TransactionsHistoryViewmodel = koinViewModel()
    val state by viewmodel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        modifier = Modifier.padding(16.dp),
                        text = "Transaction History"
                    )
                },
                navigationIcon = {
                    Icon(
                        modifier = Modifier.clickable {
                            navController.navigateUp()
                        }
                            .padding(8.dp),
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "back"
                    )
                }
            )
        },
    ) { innerPadding ->
        when (val currentState = state) {
            is TransactionHistoryUiState.Loading -> {
                HomeSkeletonScreen()
            }

            is TransactionHistoryUiState.Success -> {
                val transactions = currentState.transactions
                if (transactions.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "No transactions found",
                        )
                    }
                } else
                    TransactionsList(
                        transactions,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    )
            }

            is TransactionHistoryUiState.Error -> {
                val errorMessage = currentState.message
                Text(
                    text = errorMessage,
                    color = Color.Red,
                    modifier = Modifier
                        .padding(16.dp)
                )
            }
        }
    }
}

@Composable
fun TransactionsList(
    transactions: List<TransactionItem>,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier.padding(16.dp)) {
        items(transactions.size) { index ->
            TransactionItem(transaction = transactions[index])
        }
    }
}

@Composable
fun TransactionItem(
    transaction: TransactionItem,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth()
            .padding(vertical = 12.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text("Recipient: ${transaction.receiverEmail}")
            Text("Amount: ${transaction.amount} ${transaction.currency}")
            Text("Date: ${transaction.date}")
        }
    }
}
