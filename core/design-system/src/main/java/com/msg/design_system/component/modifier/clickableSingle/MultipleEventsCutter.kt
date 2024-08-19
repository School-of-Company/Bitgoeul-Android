package com.msg.design_system.component.modifier.clickableSingle

internal interface MultipleEventsCutter {
    fun processEvent(event: () -> Unit)

    companion object
}

internal fun MultipleEventsCutter.Companion.get(): MultipleEventsCutter = MultipleEventsCutterImpl()

private class MultipleEventsCutterImpl : MultipleEventsCutter {
    private var lastEventTimeMs: Long = 0

    override fun processEvent(event: () -> Unit) {
        val now = System.currentTimeMillis()
        if (now - lastEventTimeMs >= 400L) {
            event.invoke()
            lastEventTimeMs = now
        }
    }
}