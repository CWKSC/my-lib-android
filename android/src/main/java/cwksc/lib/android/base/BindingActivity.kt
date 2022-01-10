package cwksc.lib.android.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.viewbinding.ViewBinding

/**
 * Add into build.gradle (Module) in root level but not in top
 * apply plugin: 'kotlin-kapt'
 *
 * Add into build.gradle (Module) inside android {}
 * buildFeatures {
 *     dataBinding true
 * }
 */
open class BindingActivity<CustomBinding : ViewBinding>(private val layoutId: Int) : AppCompatActivity() {

    lateinit var binding: CustomBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutId)
        binding.onCreate()
    }

    open fun CustomBinding.onCreate() {}

}