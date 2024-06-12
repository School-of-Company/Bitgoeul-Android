package com.msg.certification.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.design_system.component.icon.PlusIcon
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.model.entity.certification.CertificationListEntity
import java.time.LocalDate
import java.util.UUID

@Composable
fun CertificationSection(
    modifier: Modifier = Modifier,
    onPlusClicked: () -> Unit,
    onEditClicked: (id: UUID, title: String, date: LocalDate) -> Unit,
    data: List<CertificationListEntity>
) {
    BitgoeulAndroidTheme { colors, typography ->
        Column(
            modifier = modifier.fillMaxWidth()
        ) {
            Row {
                Text(
                    text = "자격증",
                    style = typography.titleSmall,
                    color = colors.BLACK
                )
                Spacer(modifier = modifier.weight(1f))
                IconButton(
                    modifier = modifier.size(24.dp),
                    content = { PlusIcon() },
                    onClick = onPlusClicked
                )
            }
            Spacer(modifier = modifier.height(24.dp))
            LazyColumn {
                items(data) {
                    CertificationItem(
                        data = it,
                        onEditClicked = onEditClicked
                    )
                    Spacer(modifier = modifier.height(12.dp))
                    HorizontalDivider(
                        modifier = modifier.fillMaxWidth(),
                        thickness = 1.dp,
                        color = colors.G9
                    )
                    Spacer(modifier = modifier.height(12.dp))
                }
            }
            Spacer(modifier = modifier.height(12.dp))
        }
    }
}

@Preview
@Composable
fun CertificationSectionPre() {
    CertificationSection(
        onPlusClicked = {},
        onEditClicked = {_,_,_->},
        data = listOf(
            CertificationListEntity(
                certificationId = UUID.randomUUID(),
                name = "정보처리산업기사",
                acquisitionDate = LocalDate.now()
            )
        )
    )
}