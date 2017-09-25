package com.marekmacko.kotlinapp.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

class UtilsTest {

    @Rule
    @JvmField
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Mock
    lateinit var context: Context

    @Mock
    lateinit var connectivityManager: ConnectivityManager

    @Before
    fun setUp() {
        whenever(context.getSystemService(Context.CONNECTIVITY_SERVICE)).thenReturn(connectivityManager)
    }

    @Test
    fun isNetworkAvailableReturnsTrue() {
        val networkInfo = mock<NetworkInfo>()
        whenever(connectivityManager.activeNetworkInfo).thenReturn(networkInfo)
        whenever(networkInfo.isConnected).thenReturn(true)

        assertTrue(Utils.isNetworkAvailable(context))
    }

    @Test
    fun isNetworkAvailableReturnsFalse() {
        val networkInfo = mock<NetworkInfo>()
        whenever(connectivityManager.activeNetworkInfo).thenReturn(networkInfo)
        whenever(networkInfo.isConnected).thenReturn(false)

        assertFalse(Utils.isNetworkAvailable(context))
    }

    @Test
    fun isNetworkAvailableReturnsFalseWhenNetworkInfoIsNull() {
        whenever(connectivityManager.activeNetworkInfo).thenReturn(null)

        assertFalse(Utils.isNetworkAvailable(context))
    }
}
