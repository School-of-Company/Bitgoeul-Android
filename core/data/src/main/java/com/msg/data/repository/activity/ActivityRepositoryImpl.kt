package com.msg.data.repository.activity

import com.msg.network.datasource.activity.ActivityDataSource
import javax.inject.Inject

class ActivityRepositoryImpl @Inject constructor(
    activityDataSource: ActivityDataSource
) : ActivityRepository {
}