@file:Suppress("SpellCheckingInspection")

package cwksc.lib.android.util

import cwksc.lib.android.base.BaseApplication
import timber.log.Timber

/**
 *  implementation 'com.jakewharton.timber:timber:5.0.1'
 */
object TimberUtil {

    const val format = "%-35s %-30s |"

    @JvmStatic
    fun setup() {
        Timber.plant(object : Timber.DebugTree() {
            override fun createStackElementTag(element: StackTraceElement) = with(element) {
                String.format(format, "(${fileName}:${lineNumber})", methodName)
            }
        })
    }

    @JvmStatic
    fun setup(application: BaseApplication) {
        setup()
        application.logOnActivity = { className, message -> Timber.v(className + message) }
    }

}


