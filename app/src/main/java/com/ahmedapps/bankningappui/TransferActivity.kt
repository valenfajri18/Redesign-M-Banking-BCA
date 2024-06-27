package com.ahmedapps.bankningappui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ahmedapps.bankningappui.ui.theme.BankningAppUITheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class TransferActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BankningAppUITheme {
                // A surface container using the 'background' color from the theme

                SetBarColor(color = MaterialTheme.colorScheme.background)
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    HomeScreen2 { index ->
                        navigateToTransferActivity(index)
                    }

                }
            }
        }
    }

    @Composable
    private fun SetBarColor(color: Color) {
        val systemUiController = rememberSystemUiController()
        SideEffect {
            systemUiController.setSystemBarsColor(
                color = color
            )
        }
    }

    private fun navigateToTransferActivity(index: Int) {
        val intent = Intent(this, TransferDetail::class.java)
        intent.putExtra("INDEX", index)
        startActivity(intent)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun HomeScreen2(onFinanceItemClick: (Int) -> Unit = {}) {
    Scaffold(
        bottomBar = {
            BottomNavigationBar()
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()) // Mengaktifkan scrolling vertical
                .padding(padding)
        ) {
            TransferSection()
            CardsSectionTransfer()
            SubmitSection(onFinanceItemClick)
            TransferActivitySections()
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransferActivitySections() {
    val nama = remember { mutableStateOf("") }
    val rekeningTujuan = remember { mutableStateOf("") }
    val nominal = remember { mutableStateOf("") }
    val keterangan = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = nama.value,
            onValueChange = { nama.value = it },
            label = { Text("Nama") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = rekeningTujuan.value,
            onValueChange = { rekeningTujuan.value = it },
            label = { Text("Rekening Tujuan") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = nominal.value,
            onValueChange = { nominal.value = it },
            label = { Text("Nominal") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = keterangan.value,
            onValueChange = { keterangan.value = it },
            label = { Text("Keterangan") },
            modifier = Modifier.fillMaxWidth()
        )
    }
}