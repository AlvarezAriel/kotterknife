package butterknife

import android.view.View
import android.app.Activity
import kotlin.properties.ReadOnlyProperty

public fun <T : View> Activity.listenOnClick(id: Int, callback:View.()->Unit): ReadOnlyProperty<Any, T> = ViewBinding(id)