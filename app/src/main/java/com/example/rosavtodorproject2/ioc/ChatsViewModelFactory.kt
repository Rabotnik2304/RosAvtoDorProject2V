package ioc

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.RosAvtoDorApp.ui.stateHolder.ChatsFragmentViewModel
import com.example.rosavtodorproject2.domain.useCases.UserWithLastMessageUseCase
import javax.inject.Inject

class ChatsViewModelFactory @Inject constructor(
    val userWithLastMessageUseCase: UserWithLastMessageUseCase
) : ViewModelProvider.Factory{

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ChatsFragmentViewModel(
            userWithLastMessageUseCase=userWithLastMessageUseCase
        ) as T
    }
}