package cwksc.lib.android.framework.simplerecyclerviewadapter

import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

data class SimpleViewHolder<CustomDataBinding : ViewDataBinding>(
    private val view: View,
    var binding: CustomDataBinding,
) : RecyclerView.ViewHolder(view)