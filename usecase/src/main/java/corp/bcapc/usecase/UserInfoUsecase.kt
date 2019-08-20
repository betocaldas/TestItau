package corp.bcapc.usecase

import org.koin.core.KoinComponent
import org.koin.core.inject

/**
 *   Created by Carlos Alberto(Beto) Pereira Caldas on 12/08/2019 - 01:08
 */

class UserInfoUsecase: KoinComponent {
    val api by inject<IUserInfo>()

    suspend fun getUserInfo(): ResultApi<UserInfo> =
        try {
            ResultApi.Success(api.getUserInfo())
        } catch (e: Exception) {
            e.printStackTrace()
            ResultApi.Fail(e.localizedMessage)
        }
    }