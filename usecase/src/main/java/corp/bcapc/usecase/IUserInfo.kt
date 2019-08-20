package corp.bcapc.usecase

/**
 *   Created by Carlos Alberto(Beto) Pereira Caldas on 17/08/2019 - 00:37
 */

interface IUserInfo {
    suspend fun getUserInfo(): UserInfo
}