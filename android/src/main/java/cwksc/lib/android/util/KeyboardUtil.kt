package cwksc.lib.android.util

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

@Suppress("unused")
object KeyboardUtil {

    @JvmStatic
    fun hideKeyboardFrom(context: Context, view: View) {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

}