package cwksc.lib.framework.simplerecyclerviewadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

@Suppress("unused")
data class SimpleRecyclerViewAdapter<CustomDataBinding : ViewDataBinding, Model>(
    private val layoutId: Int,
    val onBindView: OnBindViewScope<CustomDataBinding, Model>.() -> Unit = { },
) : RecyclerView.Adapter<SimpleViewHolder<CustomDataBinding>>() {

    var models: List<Model> = listOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): SimpleViewHolder<CustomDataBinding> {
        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        val binding = DataBindingUtil.bind<CustomDataBinding>(view)
        return SimpleViewHolder(view, binding!!)
    }

    data class OnBindViewScope<CustomDataBinding : ViewDataBinding, Model>(
        val holder: SimpleViewHolder<CustomDataBinding>,
        val binding: CustomDataBinding,
        val itemView: View,
        val model: Model,
        val position: Int,
    )

    override fun onBindViewHolder(holder: SimpleViewHolder<CustomDataBinding>, position: Int) =
        OnBindViewScope(holder,
            holder.binding,
            holder.itemView,
            models[position],
            position
        ).onBindView()

    override fun getItemCount(): Int = models.size

}