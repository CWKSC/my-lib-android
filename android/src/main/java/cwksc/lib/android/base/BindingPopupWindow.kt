package cwksc.lib.android.base

import android.content.Context
import android.view.LayoutInflater
import android.widget.PopupWindow
import androidx.databinding.DataBindingUtil
import androidx.viewbinding.ViewBinding

@Suppress("unused")
open class BindingPopupWindow<CustomBinding : ViewBinding>(
    private var context: Context, private var layoutId: Int,
) : PopupWindow() {

    lateinit var binding: CustomBinding

    init {
        onCreate()
    }

    fun onCreate() {
        beforeCreate()
        val contentView = LayoutInflater.from(context).inflate(layoutId, null, false)
        setContentView(contentView)
        binding = DataBindingUtil.bind(contentView)!!
        binding.onCreate()
    }

    open fun beforeCreate() {}
    open fun CustomBinding.onCreate() {}

}