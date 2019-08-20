package corp.bcapc.remote

import corp.bcapc.data.ConfigApi
import corp.bcapc.usecase.IUserInfo
import corp.bcapc.usecase.UserInfo
import org.koin.core.KoinComponent
import org.koin.core.inject

/**
 *   Created by Carlos Alberto(Beto) Pereira Caldas on 15/08/2019 - 19:59
 */

class UserInfoApi: KoinComponent, IUserInfo {

//    private val serverUrl = ServerUrl("https://randomjson-project.firebaseio.com/")//getKoin().bind<ServerUrl, String>()
//    private val isDebugging = IsDebugging(true)//getKoin().bind<IsDebugging, Boolean>()

    val configApi: ConfigApi by inject()

    override suspend fun getUserInfo(): UserInfo =
        retrofitController.configService(configApi.isDebugging, configApi.serverUrl).getUserInfo()
}