# CwkscLibAndroid

### `PermissionRequestUtil`

```kotlin
// For request permission one time only.
fun request(
    activity: ActivityResultCaller,
    permission: String,
    callback: (name: String, result: Boolean) -> Unit = { _, _ -> },
)

// For request permission multiple time.
fun request(
    activity: ActivityResultCaller,
    vararg permissions: String,
    callback: (map: Map<String, Boolean>) -> Unit = { },
)
```

#### Example:

```kotlin
PermissionRequestUtil.request(this, Manifest.permission.CAMERA)

PermissionRequestUtil.request(this,
    Manifest.permission.CAMERA,
    Manifest.permission.RECORD_AUDIO,
    Manifest.permission.READ_EXTERNAL_STORAGE,
    Manifest.permission.WRITE_EXTERNAL_STORAGE
)
```

### `SimpleRecyclerViewAdapter`

Use with `RecyclerView`

```kotlin
data class SimpleRecyclerViewAdapter<CustomDataBinding : ViewDataBinding, ItemModel>(
    private val layoutId: Int,
    val onBindView: OnBindViewScope<CustomDataBinding, ItemModel>.() -> CustomDataBinding.() -> Unit = { { } },
)
```

`activity_simple_recycler_view_adapter_demo.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools">

    <data>

    </data>

    <androidx.constraintlayout.widget.ConstraintLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        tools:context=".SimpleRecyclerViewAdapterDemoActivity">

        <androidx.recyclerview.widget.RecyclerView
            android:id="@+id/rv"
            android:layout_width="0dp"
            android:layout_height="0dp"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toTopOf="parent" />

    </androidx.constraintlayout.widget.ConstraintLayout>

</layout>
```

`activity_simple_recycler_view_adapter_demo_item.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android">

    <data>

    </data>

    <com.google.android.material.chip.Chip
        android:id="@+id/chip"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" />

</layout>
```

#### Example:

```kotlin
val adapter = SimpleRecyclerViewAdapter</*CustomBinding*/, /*ItemModel*/>(/*layoutId*/) {{
    // On binding view
}}
adapter.items = /* data */
rv.adapter = adapter
rv.layoutManager = /* LayoutManager */
```

```kotlin
val adapter = SimpleRecyclerViewAdapter<
    ActivitySimpleRecyclerViewAdapterDemoItemBinding, 
    String
>(R.layout.activity_simple_recycler_view_adapter_demo_item) {{
    chip.text = itemModel
}}
adapter.items = mutableListOf("Hello World", "meow")
rv.adapter = adapter
rv.layoutManager = LinearLayoutManager(this@SimpleRecyclerViewAdapterDemoActivity)
```

