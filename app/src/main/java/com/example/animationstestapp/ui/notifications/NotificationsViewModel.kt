package com.example.animationstestapp.ui.notifications

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.animationstestapp.data.model.Person
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

class NotificationsViewModel(application: Application) : AndroidViewModel(application) {

    private val _person: MutableLiveData<Person> = MutableLiveData<Person>(Person())

    val person: LiveData<Person> get() = _person

    private val resDir
        get() = getApplication<Application>().applicationContext
            .getDir(RES_DIR_FILE_NAME, Context.MODE_PRIVATE)

    private val file: File
        get() = File(resDir, PERSON_DATA_FILE_NAME)

    fun saveChangesInFile(person: Person) {
        if (file.exists())
            viewModelScope.launch {
                val json = Json.encodeToString(person)
                file.writeText(json)
                Log.d(TAG, "saveChangesInFile: File:$file Data:$json")
            } else
            Log.e(TAG, "saveChangesInFile: $file", NullPointerException("Data file is not set"))
    }

    fun loadData() {
        if (file.exists()) {
            viewModelScope.launch {
                _person.value = Json.decodeFromString<Person>(file.readText())
            }
        } else
            Log.e(TAG, "saveChangesInFile: $file", NullPointerException("Data file is not set"))
    }

    private companion object {
        const val TAG = "MyAppViewModel"
        const val RES_DIR_FILE_NAME = "PersonData"
        const val PERSON_DATA_FILE_NAME = "person"
    }
}
