package butterknife

import android.os.Bundle
import android.app.Activity
import android.view.View
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Retention
import java.lang.reflect.Method

/**
 * This should probably be moved to a Delegate, otherwise we will need a trait
 * for each Fragment, Dialog, etc
 */
public trait ButterActivity : Activity {

    /**
     * Since super class is not accessible from trait, interception is not possible
     * and you'll need to call this manually, otherwise it won't know when to inject the callbacks
     */
    fun initButter() {
        this.javaClass.getMethods()
            .filter { it.isAnnotationPresent(javaClass<OnClick>())  }
            .forEach {
                val self = it
                findViewById(it.getAnnotation(javaClass<OnClick>()).id)
                    .setOnClickListener(View.OnClickListener(){
                        self.invoke(this, it)
                    })
            }
    }

}

public Retention(RetentionPolicy.RUNTIME) annotation class OnClick(val id:Int)
