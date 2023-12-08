package com.example.main.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.model.ui.ClubInfoData

@Composable
fun ClubInfoCardView(
    modifier: Modifier = Modifier,
    data: ClubInfoData
) {
    BitgoeulAndroidTheme { colors, typography ->  
       Card(
           modifier = modifier
               .fillMaxWidth()
               .padding(horizontal = 16.dp),
           colors = CardDefaults.cardColors(
               containerColor = Color.Transparent,
               contentColor = Color.Transparent
           )
       ) {
           Column {
               Text(
                   text = data.roleName,
                   style = typography.bodyMedium,
                   color = colors.BLACK
               )
               if (data.roleDetailName.isNotEmpty()) {
                   Text(
                       text = data.roleDetailName,
                       style = typography.bodySmall,
                       color = colors.G2
                   )
               }
               Text(
                   text = data.primaryRoleContent,
                   style = typography.bodySmall,
                   color = colors.G2
               )
           }
           Spacer(modifier = modifier.height(16.dp))
           Column {
               data.roleContent.forEach {
                   Text(
                       text = "    •$it",
                       style = typography.labelMedium,
                       color = colors.G1,
                       maxLines = 1,
                       overflow = TextOverflow.Visible
                   )
               }
           }
       }
    }
}

@Composable
fun ClubInfoCardViewList(
    modifier: Modifier,
    data: List<ClubInfoData>
) {
    Column {
        data.forEach { 
            ClubInfoCardView(data = it)
            Spacer(modifier = modifier.height(24.dp))
        }
    }
}