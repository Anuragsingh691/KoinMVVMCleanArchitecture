package com.example.swipemvvmkoin.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.swipemvvmkoin.util.Event

class EventWrapperSampleViewModel : ViewModel() {
    private val _navigateToDetails = MutableLiveData<Event<String>>()

    val navigateToDetails: LiveData<Event<String>>
        get() = _navigateToDetails


    fun userClicksOnButton(itemId: String) {
        _navigateToDetails.value =
            Event(itemId)  // Trigger the event by setting a new Event as a new value
    }
}


/* usage
* myViewModel.navigateToDetails.observe(this, Observer {
    it.getContentIfNotHandled()?.let { // Only proceed if the event has never been handled
        startActivity(DetailsActivity...)
    }
})
* */