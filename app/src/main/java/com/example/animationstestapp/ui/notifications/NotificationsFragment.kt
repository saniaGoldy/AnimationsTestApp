package com.example.animationstestapp.ui.notifications

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.animationstestapp.data.model.Person
import com.example.animationstestapp.databinding.FragmentNotificationsBinding
import java.io.File

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: NotificationsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)

        setupRepo()

        with(binding) {
            confrimButton.setOnClickListener {
                viewModel.saveChangesInFile(
                    createPersonFromInsertedData()
                )
            }
        }

        viewModel.loadData()

        viewModel.person.observe(viewLifecycleOwner) { person: Person ->
            updateUi(person)
        }
        return binding.root
    }

    private fun createPersonFromInsertedData(): Person {
        return with(binding) {
            Person(
                editTextTextPersonName.text.toString(),
                editTextTextPersonSurname.text.toString(),
                editTextTextEmailAddress.text.toString(),
                editTextTextPassword.text.toString(),
                editTextPhone.text.toString(),
                editTextTextPostalAddress.text.toString()
            )
        }
    }

    private fun updateUi(person: Person) {
        with(binding) {
            editTextTextPersonName.setText(person.name)
            editTextTextPersonSurname.setText(person.surname)
            editTextTextEmailAddress.setText(person.email)
            editTextPhone.setText(person.phoneNumber)
            editTextTextPassword.setText(person.password)
            editTextTextPostalAddress.setText(person.postalAddress)
        }
    }

    private fun setupRepo() {
        val resDir = this@NotificationsFragment.requireContext()
            .getDir(RES_DIR_FILE_NAME, Context.MODE_PRIVATE)
        viewModel.setDataFile(File(resDir, PERSON_DATA_FILE_NAME))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val RES_DIR_FILE_NAME = "PersonData"
        const val PERSON_DATA_FILE_NAME = "person"
    }
}