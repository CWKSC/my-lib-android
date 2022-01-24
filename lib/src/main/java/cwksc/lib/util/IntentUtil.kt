package cwksc.lib.util

import android.app.Application
import android.content.Intent
import android.net.Uri

@Suppress("unused")
object IntentUtil {

    @JvmStatic
    fun phoneCall(application: Application, phoneNumber: String){
        val intent = Intent(Intent.ACTION_CALL).apply {
            data = Uri.parse("tel:$phoneNumber")
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        application.startActivity(intent)
    }

}