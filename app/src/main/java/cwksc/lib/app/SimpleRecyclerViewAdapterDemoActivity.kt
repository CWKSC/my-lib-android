package cwksc.lib.app

import androidx.recyclerview.widget.LinearLayoutManager
import cwksc.lib.app.databinding.ActivitySimpleRecyclerViewAdapterDemoBinding
import cwksc.lib.app.databinding.ActivitySimpleRecyclerViewAdapterDemoItemBinding
import cwksc.lib.base.BindingActivity
import cwksc.lib.framework.simplerecyclerviewadapter.SRVA

class SimpleRecyclerViewAdapterDemoActivity :
    BindingActivity<ActivitySimpleRecyclerViewAdapterDemoBinding>(R.layout.activity_simple_recycler_view_adapter_demo) {

    override fun ActivitySimpleRecyclerViewAdapterDemoBinding.onCreate() {

        val adapter =
            SRVA<ActivitySimpleRecyclerViewAdapterDemoItemBinding, String>(R.layout.activity_simple_recycler_view_adapter_demo_item) {
                {
                    chip.text = itemModel
                }
            }
        adapter.items = mutableListOf("Hello World", "meow")
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(this@SimpleRecyclerViewAdapterDemoActivity)
    }
    
}