package com.burakustun.techchallange

import com.burakustun.techchallange.login.LoginViewModel
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`



/**
 * Created by burakustun on 2019-09-20
 */
class LoginViewModelUnitTest {

    lateinit var loginViewModel: LoginViewModel

    @Before
    fun init(){
        loginViewModel = mock(LoginViewModel::class.java)
        `when`(loginViewModel.loginUser("kariyer","2019ADev")).thenReturn(true)
        `when`(loginViewModel.loginUser("deneme","deneme")).thenReturn(false)
    }

    @Test
    fun should_login_when_credentials_true(){
        val result = loginViewModel.loginUser("kariyer","2019ADev")
        assertTrue(result)
    }

    @Test
    fun should_login_fail_when_credentials_false(){
        val result = loginViewModel.loginUser("deneme","deneme")
        assertFalse(result)
    }
}