@file:Suppress("unused")

package cwksc.lib.android.framework.generalrecyclerviewadapter

import androidx.viewbinding.ViewBinding
import kotlin.reflect.KClass


class ItemConfig<CustomBinding : Any, Model : Any>(
    var resourceId: Int,
    var modelType: KClass<Model>,
    var bindingType: KClass<CustomBinding>,
    var onBindView: (Any, Any, Boolean, Int) -> Unit = { _, _, _, _ -> },
) {

    companion object {

        @JvmStatic
        inline fun <reified CustomBinding : ViewBinding, reified Model : Any> create(
            resourceId: Int,
            noinline onBindView: CustomBinding.(Model, Boolean, Int) -> Unit = { _, _, _ -> },
        ): ItemConfig<CustomBinding, Model> =
            ItemConfig(resourceId, Model::class, CustomBinding::class) { it1, it2, it3, position ->
                onBindView(it1 as CustomBinding, it2 as Model, it3, position)
            }

    }

    inline fun <reified CustomBinding : ViewBinding, reified Model : Any> setBinding(
        crossinline onBindView: CustomBinding.(Model, Boolean, Int) -> Unit = { _, _, _ -> },
    ) {
        this.onBindView = { binding, model, selected, position ->
            onBindView(binding as CustomBinding, model as Model, selected, position)
        }
    }

}



