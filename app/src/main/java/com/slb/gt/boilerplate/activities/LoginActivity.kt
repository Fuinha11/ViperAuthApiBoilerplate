package com.slb.gt.boilerplate.activities

import com.slb.gt.boilerplate.R
import com.slb.gt.boilerplate.contracts.LoginContracts
import com.slb.gt.boilerplate.presenter.LoginPresenter
import com.slb.gt.boilerplate.ui.EnterKeyListener
import kotlinx.android.synthetic.main.activity_login.*
import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.Click
import org.androidannotations.annotations.EActivity


@EActivity(R.layout.activity_login)
open class LoginActivity : BaseActivity<
        LoginContracts.Presenter>(), LoginContracts.View {

    override fun initiatePresenter(): LoginContracts.Presenter {
        return LoginPresenter(this, this)
    }


    @AfterViews
    override fun initComponents(){
        emailTF.setOnKeyListener(EnterKeyListener {passwordTF.requestFocus()})
        passwordTF.setOnKeyListener(EnterKeyListener {loginWithEmail()})
    }

    @Click(R.id.loginBtn)
    open fun loginWithEmail(){
        presenter.login(emailTF.text.toString(), passwordTF.text.toString())
    }

    @Click(R.id.registerBtn)
    open fun register(){
        presenter.register(emailTF.text.toString(), passwordTF.text.toString())
    }
}
