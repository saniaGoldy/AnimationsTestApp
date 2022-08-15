package com.example.animationstestapp.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.animationstestapp.data.model.Person
import com.example.animationstestapp.databinding.FragmentNotificationsBinding

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

        with(binding) {
            confrimButton.setOnClickListener {
                viewModel.saveChangesInFile(
                    createPersonFromInsertedData()
                )
            }
        }
        viewModel.person.observe(viewLifecycleOwner) { person: Person ->
            updateUi(person)
        }
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadData()
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


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}