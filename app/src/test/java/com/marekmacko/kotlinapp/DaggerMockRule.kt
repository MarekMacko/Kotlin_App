package com.marekmacko.kotlinapp

import it.cosenonjaviste.daggermock.DaggerMock
import org.robolectric.RuntimeEnvironment


fun getDaggerMockRule() = DaggerMock.rule<AppComponent>(AppModule(getApp())) {
    set { getApp().component = it }
}

private fun getApp() = RuntimeEnvironment.application as App
