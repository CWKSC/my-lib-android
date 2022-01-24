@file:Suppress("unused")

package cwksc.lib.framework.simplerecyclerviewadapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

typealias SRVA<CustomDataBinding, ItemModel> = SimpleRecyclerViewAdapter<CustomDataBinding, ItemModel>

@Suppress("MemberVisibilityCanBePrivate")
data class SimpleRecyclerViewAdapter<CustomDataBinding : ViewDataBinding, ItemModel>(
    private val layoutId: Int,
    val onBindView: OnBindViewScope<CustomDataBinding, ItemModel>.() -> CustomDataBinding.() -> Unit = { { } },
) : RecyclerView.Adapter<SimpleViewHolder<CustomDataBinding>>() {

    var items: MutableList<ItemModel> = mutableListOf()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    fun addItem(item: ItemModel, position: Int) {
        items.add(position, item)
        notifyItemInserted(position)
    }

    fun removeItem(item: ItemModel) {
        val position: Int = items.indexOf(item)
        items.removeAt(position)
        notifyItemRemoved(position)
    }

    fun changeItem(position: Int, changeItemFunc: (ItemModel) -> ItemModel) {
        items[position] = changeItemFunc(items[position])
        notifyItemChanged(position)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): SimpleViewHolder<CustomDataBinding> {
        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        val binding = DataBindingUtil.bind<CustomDataBinding>(view)
        return SimpleViewHolder(view, binding!!)
    }

    data class OnBindViewScope<CustomDataBinding : ViewDataBinding, ItemModel>(
        val holder: SimpleViewHolder<CustomDataBinding>,
        val binding: CustomDataBinding,
        val itemView: View,
        val itemModel: ItemModel,
        val position: Int,
    )

    override fun onBindViewHolder(holder: SimpleViewHolder<CustomDataBinding>, position: Int) =
        OnBindViewScope(
            holder,
            holder.binding,
            holder.itemView,
            items[position],
            position
        ).onBindView()(holder.binding)

    override fun getItemCount(): Int = items.size

}