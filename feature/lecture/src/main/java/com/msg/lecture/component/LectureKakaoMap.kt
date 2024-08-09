package com.msg.lecture.component

import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.kakao.vectormap.KakaoMap
import com.kakao.vectormap.KakaoMapReadyCallback
import com.kakao.vectormap.LatLng
import com.kakao.vectormap.MapLifeCycleCallback
import com.kakao.vectormap.MapView
import com.kakao.vectormap.camera.CameraUpdateFactory
import com.kakao.vectormap.label.LabelOptions
import com.kakao.vectormap.label.LabelStyle
import com.kakao.vectormap.label.LabelStyles
import com.msg.ui.makeToast

@Composable
fun LectureKakaoMap(
    modifier: Modifier = Modifier,
    locationX: Double,
    locationY: Double,
) {
    val context = LocalContext.current
    val mapView = remember { MapView(context) }

    AndroidView(
        modifier = modifier.height(200.dp),
        factory = { context ->
            mapView.apply {
                mapView.start(
                    object : MapLifeCycleCallback() {
                        override fun onMapDestroy() {
                            makeToast(context = context, message = "지도를 불러오는데 실패했습니다.")
                        }

                        override fun onMapError(exception: Exception?) {
                            makeToast(context = context, message = "지도를 불러오는 중 알 수 없는 에러가 발생했습니다.\n onMapError: $exception")
                        }
                    },

                    object : KakaoMapReadyCallback() {
                        override fun onMapReady(kakaoMap: KakaoMap) {
                            val cameraUpdate = CameraUpdateFactory.newCenterPosition(LatLng.from(locationY, locationX))
                            val style = kakaoMap.labelManager?.addLabelStyles(LabelStyles.from(LabelStyle.from(com.msg.design_system.R.drawable.ic_new_bitgoeul)))
                            val options = LabelOptions.from(LatLng.from(locationY, locationX)).setStyles(style)
                            val layer = kakaoMap.labelManager?.layer

                            kakaoMap.moveCamera(cameraUpdate)
                            layer?.addLabel(options)
                        }

                        override fun getPosition(): LatLng {
                            return LatLng.from(locationY, locationX)
                        }
                    },
                )
            }
        },
    )
}

