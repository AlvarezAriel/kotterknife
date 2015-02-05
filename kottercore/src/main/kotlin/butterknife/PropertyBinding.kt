package butterknife

import android.view.View
import android.app.Activity
import kotlin.properties.ReadOnlyProperty
import kotlin.properties.ReadWriteProperty
import android.widget.TextView
import android.widget.EditText
import android.text.TextWatcher
import android.text.Editable

public fun Activity.oneWayBinding(id: Int): OneWayBinding = OneWayBinding(id)
public fun Activity.bindTo(id: Int, callback:(String)->Unit): ReadOnlyProperty<Any, EditText> = OnChangeBinding(id,callback)


class OneWayBinding(val id:Int) : ReadWriteProperty<Any, String> {
    private val lazy = Lazy<String>()

    override fun set(thisRef: Any, desc: PropertyMetadata, value: String) {
        textView(thisRef)?.setText(value)
    }

    override fun get(thisRef: Any, desc: PropertyMetadata): String = lazy.get {
        textView(thisRef)?.getText().toString()
    }

    private fun textView(thisRef: Any): TextView? {
        return findView(thisRef, this.id)
    }
}

private class OnChangeBinding(val id: Int, val callback:(String)->Unit) : ReadOnlyProperty<Any, EditText> {
    private val lazy = Lazy<EditText>()

    override fun get(thisRef: Any, desc: PropertyMetadata): EditText = lazy.get {
        val view = findView<EditText>(thisRef, id)
        view?: throw IllegalStateException("View ID $id for '${desc.name}' not found.")
        view.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                callback.invoke(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
        view
    }
}