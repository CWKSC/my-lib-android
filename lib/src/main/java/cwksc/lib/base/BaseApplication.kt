package cwksc.lib.base

import android.app.Activity
import android.app.Application
import android.os.Bundle

@Suppress("unused")
open class BaseApplication : Application(), Application.ActivityLifecycleCallbacks {

    override fun onCreate() {
        super.onCreate()

        registerActivityLifecycleCallbacks(this)
    }

    var logOnActivity: (className: String, message: String) -> Unit = { _, _ -> }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        logOnActivity(activity::class.simpleName!!, " Created")
    }

    override fun onActivityStarted(activity: Activity) {
        logOnActivity(activity::class.simpleName!!, " Started")
    }

    override fun onActivityResumed(activity: Activity) {
        logOnActivity(activity::class.simpleName!!, " Resumed")
    }

    override fun onActivityPaused(activity: Activity) {
        logOnActivity(activity::class.simpleName!!, " Paused")
    }

    override fun onActivityStopped(activity: Activity) {
        logOnActivity(activity::class.simpleName!!, " Stopped")
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
        logOnActivity(activity::class.simpleName!!, " SaveInstanceState")
    }

    override fun onActivityDestroyed(activity: Activity) {
        logOnActivity(activity::class.simpleName!!, " Destroyed")
    }

}