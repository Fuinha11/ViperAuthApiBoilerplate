package com.slb.gt.boilerplate.activities

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.TextView
import com.slb.gt.boilerplate.R
import com.slb.gt.boilerplate.contracts.BaseContracts
import com.slb.gt.boilerplate.utils.extensions.hideKeyboard
import com.slb.gt.boilerplate.utils.extensions.snackbar
import com.slb.gt.boilerplate.utils.extensions.toast
import org.androidannotations.annotations.AfterInject
import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.EActivity
import org.androidannotations.annotations.UiThread
import java.lang.Exception


@EActivity
abstract class BaseActivity<
        P : BaseContracts.Presenter>
    : AppCompatActivity(),
        BaseContracts.View {

    lateinit var presenter : P

    private lateinit var loadingDialog : View

    private lateinit var loadingMessage: TextView

    @UiThread
    override fun showLoading(message: String) {
        this.dismissKeyboard()
        if (!::loadingDialog.isInitialized) {
            val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val view = inflater.inflate(R.layout.progress_dialog, this.findViewById(android.R.id.content) as ViewGroup)
            loadingMessage = view.findViewById(R.id.progressBarHolder_message) as TextView
            loadingDialog = view.findViewById(R.id.progressBarHolder)
        }
        loadingMessage.text = message
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        loadingDialog.visibility = View.VISIBLE
    }

    @UiThread
    override fun hideLoading() {
        if (::loadingDialog.isInitialized) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
            loadingDialog.visibility = View.GONE
        }
    }

    override fun showSnackbar(message: String, actionName: String, fn: () -> Unit, length: Int) =
            this.snackbar(message, actionName, fn, length)

    override fun showToast(message: String, length: Int) =
            this.toast(message)

    override fun dismissKeyboard() =
            this.hideKeyboard()

    @AfterInject
    open fun validatePresenter() {
        if (!::presenter.isInitialized)
            throw Exception("Presenter no initialized for Activity: $this")
    }

    @AfterViews
    abstract fun initComponents()
}