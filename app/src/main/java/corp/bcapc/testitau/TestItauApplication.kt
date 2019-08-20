package corp.bcapc.testitau

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

/**
 *   Created by Carlos Alberto(Beto) Pereira Caldas on 12/08/2019 - 01:30
 */

class TestItauApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        setupTimber()
        startKoin {
            // use AndroidLogger as Koin Logger - default Level.INFO
            androidLogger()
            // Android context
            androidContext(this@TestItauApplication)
            // modules
            modules(appModule)
            // declare properties from given map
//            properties( /* properties map */)

            // load properties from assets/koin.properties file
            androidFileProperties()
        }
    }

    private fun setupTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}