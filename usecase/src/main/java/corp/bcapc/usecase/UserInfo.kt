package corp.bcapc.usecase

/**
 *   Created by Carlos Alberto(Beto) Pereira Caldas on 11/08/2019 - 03:51
 */

data class UserInfo(
    val name : String,
    val balance: String,
    val savings: String,
    val contacts: List<String>
) {
    companion object {
        const val TAG = "UserInfo"
    }
}