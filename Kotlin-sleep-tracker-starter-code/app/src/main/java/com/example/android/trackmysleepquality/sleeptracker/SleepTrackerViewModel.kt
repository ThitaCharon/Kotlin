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
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.android.trackmysleepquality.database.SleepDatabaseDao
import com.example.android.trackmysleepquality.database.SleepNight
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
}

