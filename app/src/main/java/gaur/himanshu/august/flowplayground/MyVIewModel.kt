package gaur.himanshu.august.flowplayground

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MyVIewModel : ViewModel() {


    private val _state = MutableStateFlow<StateController>(StateController.Empty)

    val state: StateFlow<StateController> = _state

    sealed class StateController {

        object Loading : StateController()

        object Empty : StateController()

        data class Error(val message: String) : StateController()

        data class Suceess(val data: String) : StateController()

    }


    fun login(username: String, password: String) = viewModelScope.launch {
        _state.value = StateController.Loading

        delay(3000)

        if (username == "himanshu" && password == "12345") {
            _state.value = StateController.Suceess("authenticate")
        } else {
            _state.value = StateController.Error("wrong credentials")
        }
    }


}