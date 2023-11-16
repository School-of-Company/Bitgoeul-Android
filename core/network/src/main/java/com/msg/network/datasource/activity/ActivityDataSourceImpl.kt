package com.msg.network.datasource.activity

import com.msg.network.api.ActivityAPI
import javax.inject.Inject

class ActivityDataSourceImpl @Inject constructor(
    private val activityAPI: ActivityAPI
) : ActivityDataSource {
}