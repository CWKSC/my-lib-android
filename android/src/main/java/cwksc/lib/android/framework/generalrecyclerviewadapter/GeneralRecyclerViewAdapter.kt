package cwksc.lib.android.framework.generalrecyclerviewadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlin.reflect.full.staticFunctions

@Suppress("unused")
class GeneralRecyclerViewAdapter : RecyclerView.Adapter<ViewHolder>() {

    private var selectedPosition: Int = -1
    fun setSelectedPosition(position: Int) {
        selectedPosition = position
    }

    private lateinit var dataSet: List<Any>
    fun setDataSet(dataSet: List<Any>) {
        this.dataSet = dataSet
        notifyDataSetChanged()
    }

    private lateinit var itemConfigList: Array<out ItemConfig<*, *>>
    fun setItemConfig(vararg itemConfig: ItemConfig<*, *>) {
        this.itemConfigList = itemConfig
    }


    override fun getItemViewType(position: Int): Int {
        itemConfigList.forEachIndexed { index, config ->
            if (config.modelType.isInstance(dataSet[position])) return index
        }
        return 0
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val itemConfig = itemConfigList[viewType]
        val createBinding = itemConfig.bindingType.staticFunctions.first { it.name == "bind" }
        val resourceId = itemConfig.resourceId
        val view = LayoutInflater.from(viewGroup.context).inflate(resourceId, viewGroup, false)
        return ViewHolder(view, createBinding.call(view)!!)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val index = getItemViewType(position)
        val onBindView = itemConfigList[index].onBindView
        val model = dataSet[position]
        viewHolder.itemView.setOnClickListener {
            selectedPosition = position
            notifyDataSetChanged()
        }
        onBindView(viewHolder.binding, model, position == selectedPosition, position)
    }

    override fun getItemCount() = dataSet.size

}

