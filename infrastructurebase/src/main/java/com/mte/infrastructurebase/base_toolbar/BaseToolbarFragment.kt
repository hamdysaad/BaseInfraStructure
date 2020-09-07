package base.shgardi.basestructure.App_base.toolbar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.mte.infrastructurebase.R
import com.mte.infrastructurebase.base.BaseFragment
import com.mte.infrastructurebase.databinding.BaseActivityToolbarBinding


abstract class BaseToolbarFragment<T : ViewDataBinding> : BaseFragment<T>() {


    private var basToolbarBinding: BaseActivityToolbarBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle? ): View? {

        basToolbarBinding = inflateLayout(R.layout.base_activity_toolbar)?.let {
            DataBindingUtil.bind<BaseActivityToolbarBinding>(it)?.let { baseBinding ->

                createToolbarView()?.let { toolbarView -> baseBinding.toolbarContainer.addView(toolbarView) }

                createContentView(inflater , container , savedInstanceState)?.let{view ->
                    baseBinding.toolbarContent.addView(view)
                }

                baseBinding
            }
        }

        return basToolbarBinding?.root
    }

    open fun createContentView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return  super.onCreateView(inflater, container, savedInstanceState)
    }


    abstract fun createToolbarView(): View?


}