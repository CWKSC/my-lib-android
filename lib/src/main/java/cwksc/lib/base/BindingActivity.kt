package cwksc.lib.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.viewbinding.ViewBinding

/**
 * Add into build.gradle (Module) inside plugins { }
 *     id 'kotlin-kapt'
 *
 * Add into build.gradle (Module) inside android { }
 * buildFeatures {
 *     dataBinding true
 * }
 */

/**
 * CustomBinding.onCreate() will run before onCreate(savedInstanceState: Bundle?)
 */
@Suppress("unused")
open class BindingActivity<CustomBinding : ViewBinding>(private val layoutId: Int) : AppCompatActivity() {

    @Suppress("MemberVisibilityCanBePrivate")
    lateinit var binding: CustomBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = DataBindingUtil.setContentView(this, layoutId)
        binding.onCreate()
        super.onCreate(savedInstanceState)
    }

    open fun CustomBinding.onCreate() {}

}