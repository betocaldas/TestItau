package corp.bcapc.testitau.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import corp.bcapc.usecase.UserInfo
import corp.bcapc.usecase.UserInfoUsecase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.KoinComponent
import org.koin.core.inject

/**
 *   Created by Carlos Alberto(Beto) Pereira Caldas on 15/08/2019 - 18:30
 */

class UserInfoViewModel(application: Application) : BaseViewModel(application), KoinComponent {

    private val usecase : UserInfoUsecase by inject()

    val userInfoLiveData = MutableLiveData<UserInfo>()

    fun getUserInfo() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                usecase.getUserInfo()
            }.run {
                checkResult(this)
            }?.let {
                uiScope.launch {
                    userInfoLiveData.value = it
                }
            }
        }
    }
}