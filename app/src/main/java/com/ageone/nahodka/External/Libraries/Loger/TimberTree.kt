package com.ageone.nahodka.External.Libraries.Loger

import timber.log.Timber

class TimberTree : Timber.DebugTree() {
    override fun createStackElementTag(element: StackTraceElement): String? {
        return "(${element.fileName}:${element.lineNumber})"
    }
}