package com.example.animationstestapp.ui.notifications

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animationstestapp.data.model.Person
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

class NotificationsViewModel : ViewModel() {

    private val _person: MutableLiveData<Person> = MutableLiveData<Person>(Person())

    val person: LiveData<Person> get() = _person

    private var file: File? = null

    fun saveChangesInFile(person: Person) {
        if (file != null)
            viewModelScope.launch {
                val json = Json.encodeToString(person)
                file!!.writeText(json)
                Log.d(TAG, "saveChangesInFile: File:$file Data:$json")
            } else
            Log.e(TAG, "saveChangesInFile: $file", NullPointerException("Data file is not set"))
    }

    fun loadData() {
        if (file != null) {
            viewModelScope.launch {
                _person.value = Json.decodeFromString<Person>(file!!.readText())
            }
        } else
            Log.e(TAG, "saveChangesInFile: $file", NullPointerException("Data file is not set"))
    }

    fun setDataFile(file: File) {
        this.file = file
    }

    private companion object {
        const val TAG = "MyAppViewModel"
    }
}
