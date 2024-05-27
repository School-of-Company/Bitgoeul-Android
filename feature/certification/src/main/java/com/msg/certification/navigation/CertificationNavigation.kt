package com.msg.certification.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.msg.certification.AddCertificationScreenRoute
import com.msg.certification.CertificationScreenRoute
import java.util.UUID

const val certificationRoute = "certification_route"
const val addCertificationRoute = "add_certification_route"

fun NavController.navigateToCertificationPage(navOptions: NavOptions? = null, clubId: Long, studentId: String) {
    this.navigate("$certificationRoute/$clubId/$studentId", navOptions)
}

fun NavGraphBuilder.certificationScreen(onHumanClicked: () -> Unit, onEditClicked: () -> Unit) {
    composable(
        route = "certificationRoute/{clubId}/{studentId}",
        arguments = listOf(
            navArgument("clubId") {
                type = NavType.LongType
            },
            navArgument("studentId") {
                type = NavType.StringType
            }
        )
    ) {
        CertificationScreenRoute(
            onHumanIconClicked = onHumanClicked,
            onEditClicked = onEditClicked
        )
    }
}

fun NavController.navigateToAddCertificationPage(navOptions: NavOptions? = null) {
    this.navigate(addCertificationRoute, navOptions)
}

fun NavGraphBuilder.addCertificationScreen(onBackClicked: () -> Unit) {
    composable(route = addCertificationRoute) {
        AddCertificationScreenRoute(
            onBackClicked = onBackClicked,
            onAddClicked = onBackClicked
        )
    }
}