package corp.bcapc.testitau.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import corp.bcapc.usecase.ResultApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

/**
 *   Created by Carlos Alberto(Beto) Pereira Caldas on 16/08/2019 - 02:50
 */

open class BaseViewModel(application: Application) : AndroidViewModel(application) {

    private val viewModelJob = SupervisorJob()
    val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val errorLiveData = MutableLiveData<String>()
    val loadLiveData = MutableLiveData<Boolean>()

    fun <T> checkResult( resultApi: ResultApi<T>): T? =
        when (resultApi) {
            is ResultApi.Success -> {
                resultApi.data
            }
            is ResultApi.Fail -> {
                errorLiveData.value = resultApi.error
                null
            }
            is ResultApi.Loading -> {
                loadLiveData.value = resultApi.isLoading
                null
            }
        }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
    }