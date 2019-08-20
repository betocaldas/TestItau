package corp.bcapc.testitau

import corp.bcapc.data.ConfigApi
import corp.bcapc.remote.UserInfoApi
import corp.bcapc.testitau.viewmodel.UserInfoViewModel
import corp.bcapc.usecase.IUserInfo
import corp.bcapc.usecase.UserInfoUsecase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 *   Created by Carlos Alberto(Beto) Pereira Caldas on 12/08/2019 - 01:35
 */

val appModule = module(override = true) {

//    single { IsDebugging(value = BuildConfig.DEBUG) } bind Boolean::class
//    single { ServerUrl(value = BuildConfig.SERVER_URL) } bind String::class
//    factoryBy<IUserInfo, UserInfoApi>()

    single { ConfigApi(BuildConfig.SERVER_URL, BuildConfig.DEBUG) }
    factory<IUserInfo> { UserInfoApi() }
//    factory { UserInfoApi() } bind IUserInfo::class
//    factory { UserInfoApi() }
//    factory {
//        var api: UserInfoApi = get()
//        UserInfoUsecase(api)
//    }

    factory { UserInfoUsecase() }
    viewModel { UserInfoViewModel(get()) }
}