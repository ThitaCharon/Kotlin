/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.trackmysleepquality.sleeptracker

import android.app.Application
import android.provider.SyncStateContract.Helpers.insert
import android.provider.SyncStateContract.Helpers.update
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.android.trackmysleepquality.database.SleepDatabaseDao
import com.example.android.trackmysleepquality.database.SleepNight
import com.example.android.trackmysleepquality.formatNights
import kotlinx.coroutines.*

/**
 * ViewModel for SleepTrackerFragment.
 * First, is different from the previous extended viweModel as has application as a parameter
 * and make it available as a properties
 * Second, viewModel has to access to the database -> has to make an instance of the database
 */
class SleepTrackerViewModel (
        val database: SleepDatabaseDao,
        application: Application) : AndroidViewModel(application) {
    /**
     * coroutines need 3 fields
     * 1. job
     * 2. dispatcher
     * 3. scope which thread the coroutines will run on so pass-> (dispatcher + and a JOB)
     */
    private var viewModelJob = Job()
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private var uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private var tonight = MutableLiveData<SleepNight?>()
    private var allNights = database.getAllNights()
    val nightsString = Transformations.map(allNights) { allNights ->
        formatNights(allNights, application.resources)
    }

    private val _navigateToSleepQuality = MutableLiveData<SleepNight>()
    val navigateToSleepQuality : LiveData<SleepNight>
        get() = _navigateToSleepQuality

    fun doneNavigation(){
        _navigateToSleepQuality.value = null
    }

    /**
     * map the button visible with the state
     */
    val startButtonVisible = Transformations.map(tonight) {
        null == it
    }
    val stopButtonVisible = Transformations.map(tonight) {
        null != it
    }
    val clearButtonVisible = Transformations.map(allNights) {
        it?.isNotEmpty()
    }

    /**
     * Snackbar display when user pressed clear button
     */
    private var _showSnackbarEvent = MutableLiveData<Boolean>()

    val showSnackBarEvent: LiveData<Boolean>
        get() = _showSnackbarEvent
    fun doneShowingSnackbar() {
        _showSnackbarEvent.value = false
    }
    init {
        initializeTonight()
    }

    /**
     * when the code run initialize tonight and make sure that initializeTonight will not be blocked
     * by creates suspend function which only called by coroutines
     */
    private fun initializeTonight(){
        uiScope.launch {
          //  tonight.value = database.getTonight() move to suspend
            tonight.value = getTonightFromDatabase()}
    }
    private suspend fun getTonightFromDatabase() : SleepNight?{
        /**
         * crates another coroutines
         */
        return  withContext(Dispatchers.IO) {
            var night = database.getTonight()
            if(night?.endTimeMilli != night?.startTimeMilli){
                night = null
            }
            night
        }
    }

    /**
     * onStartTracking
     */
    fun onStartTracking(){
        uiScope.launch {
            val newNight = SleepNight()
            insert(newNight)
            tonight.value = getTonightFromDatabase()
        }
    }
    private suspend fun insert(newNight : SleepNight){
        withContext(Dispatchers.IO){
            database.insert(newNight)
        }
    }
    /**
     * onStopTracking
     */
    fun onStopTracking() {
        uiScope.launch {
            val oldNight = tonight.value ?: return@launch
            oldNight.endTimeMilli = System.currentTimeMillis()
            update(oldNight)
            _navigateToSleepQuality.value = oldNight
        }
    }
    private suspend fun update(night: SleepNight) {
        withContext(Dispatchers.IO) {
            database.update(night)
        }
    }

    fun onClear() {
        uiScope.launch {
            clear()
            tonight.value = null
            _showSnackbarEvent.value = true
        }
    }

    suspend fun clear() {
        withContext(Dispatchers.IO) {
            database.clear()
        }
    }
}

