package com.example.swipemvvmkoin.util

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

object Extensions {


    /*
    * The below two functions are for safely collect flow and make flow lifecycle aware , I m still not sure how to do that , just going through bunch of things and experimenting out.
    * We have repeatOnLifecycle for safely collecting the flows but there is a problem with this that whenever app restarts again , flow restart again and the previous data is lost.
    * prevents you from wasting resources and app crashes because it stops and restarts the flow collection when the lifecycle moves in and out of the target state.
    * */
    fun <T> Flow<T>.safeCollect(
        owner: LifecycleOwner,
        block: (T.() -> Unit)? = null
    ) = owner.lifecycleScope.launch {
        flowWithLifecycle(owner.lifecycle).collectLatest { block?.invoke(it) }
    }

//    fun <T> Flow<T>.whenAtLeast(requiredState: Lifecycle.State, lifeCycleState: Flow<T>): Flow<T> {
//        return lifeCycleState.map { state -> state.isAtLeast(requiredState) }
//            .distinctUntilChanged()
//            .flatMapLatest {
//                // flatMapLatest will take care of cancelling the upstream Flow
//                if (it) this else emptyFlow()
//            }
//    }
}