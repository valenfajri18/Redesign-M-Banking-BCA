package com.ahmedapps.bankningappui

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MonetizationOn
import androidx.compose.material.icons.rounded.StarHalf
import androidx.compose.material.icons.rounded.TransferWithinAStation
import androidx.compose.material.icons.rounded.Wallet
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ahmedapps.bankningappui.data.Finance
import com.ahmedapps.bankningappui.ui.theme.BlueStart
import com.ahmedapps.bankningappui.ui.theme.GreenStart
import com.ahmedapps.bankningappui.ui.theme.OrangeStart
import com.ahmedapps.bankningappui.ui.theme.PurpleStart

val financeList = listOf(
    Finance(
        icon = R.drawable.info,
        name = "M-Info",
        color = Color(0xFF1D469D)
    ),

    Finance(
        icon = R.drawable.transfer,
        name = "M-Transfer",
        color = Color(0xFF1D469D)
    ),

    Finance(
        icon = R.drawable.payment_1,
        name = "M-Payment",
        color = Color(0xFF1D469D)
    ),
)

@Preview
@Composable
fun FinanceSection(onFinanceItemClick: (Int) -> Unit = {}) {
    Column {
        Text(
            text = "Features",
            fontSize = 24.sp,
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)

        )

        LazyRow(
            horizontalArrangement = Arrangement.SpaceBetween, // Mengatur jarak antara item
            modifier = Modifier.fillMaxWidth()
        ) {
            items(financeList.size) { index ->
                FinanceItem(index = index, onClick = { onFinanceItemClick(index) })
            }
        }
    }
}

@Composable
fun FinanceItem(
    index: Int,
    onClick: () -> Unit // Tambahkan parameter onClick
) {
    val finance = financeList[index]
    var lastPaddingEnd = 0.dp
    if (index == financeList.size - 1) {
        lastPaddingEnd = 16.dp
    }

    val context = LocalContext.current // Dapatkan context lokal dari Composable

    Column(
        modifier = Modifier.padding(start = 16.dp, end = lastPaddingEnd),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(25.dp))
                .background(MaterialTheme.colorScheme.secondaryContainer)
                .size(width = 100.dp, height = 100.dp) // Atur lebar dan tinggi card
                .clickable {
                    navigateToSecondActivity(context, index)
                }
                .padding(13.dp),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .padding(6.dp)
            ) {
                Image(
                    painter = painterResource(id = finance.icon),
                    contentDescription = finance.name,
                    modifier = Modifier.size(
                        width = 45.dp,
                        height = 45.dp
                    )// Ensure the icon fills the size of the inner box
                )
            }
        }

        Text(
            text = finance.name,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            fontWeight = FontWeight.SemiBold,
            fontSize = 15.sp,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}

private fun navigateToSecondActivity(context: Context, index: Int) {
    when (index) {
        1 -> { // Index for TransferActivity
            val intent = Intent(context, TransferActivity::class.java)
            context.startActivity(intent)
        }
        else -> {
            Toast.makeText(context, "This feature is not available yet", Toast.LENGTH_SHORT).show()
        }
    }
}
