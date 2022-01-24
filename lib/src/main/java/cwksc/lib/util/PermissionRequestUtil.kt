@file:JvmName("PermissionRequestUtil")

package cwksc.lib.util

import androidx.activity.result.ActivityResultCaller
import androidx.activity.result.contract.ActivityResultContracts.RequestMultiplePermissions
import androidx.activity.result.contract.ActivityResultContracts.RequestPermission

@Suppress("unused")
object PermissionRequestUtil {

    /**
     * For request permission one time only.
     *
     * Example:
     * PermissionRequestUtil.request(this, Manifest.permission.CAMERA)
     *
     * if you want to request multiple permissions,
     * please use request(activity: ActivityResultCaller, vararg permissions: String, callback: (map: Map<String, Boolean>) -> Unit = { },
     */
    @JvmStatic
    fun request(
        activity: ActivityResultCaller,
        permission: String,
        callback: (name: String, result: Boolean) -> Unit = { _, _ -> },
    ) {
        activity.registerForActivityResult(RequestPermission()) {
            callback(permission, it)
        }.launch(permission)
    }

    /**
     * For request permission multiple time.
     *
     * Example:
     * PermissionRequestUtil.request(this,
     *     Manifest.permission.CAMERA,
     *     Manifest.permission.RECORD_AUDIO,
     *     Manifest.permission.READ_EXTERNAL_STORAGE,
     *     Manifest.permission.WRITE_EXTERNAL_STORAGE
     * )
     */
    @JvmStatic
    fun request(
        activity: ActivityResultCaller,
        vararg permissions: String,
        callback: (map: Map<String, Boolean>) -> Unit = { },
    ) {
        activity.registerForActivityResult(RequestMultiplePermissions()) {
            callback(it)
        }.launch(permissions)
    }

}