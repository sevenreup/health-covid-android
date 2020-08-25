package com.skybox.seven.covid.ui.profile.contacts.pages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.skybox.seven.covid.databinding.FragmentGenericEpoxySwipeBinding
import com.skybox.seven.covid.epoxy.contacts.ContactsController
import com.skybox.seven.covid.model.ContactModel.ContactUsersContacts
import com.skybox.seven.covid.ui.profile.contacts.ContactsViewModel
import com.skybox.seven.covid.util.SpaceItemDecorator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContactTraceFragment : Fragment() {
    private lateinit var binding: FragmentGenericEpoxySwipeBinding
    private var controller: ContactsController = ContactsController()
    private val viewModel: ContactsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.contactsRefresh.observe(this, Observer { viewModel.allContacts })
        viewModel.actualContacts.observe(this, Observer { contactUsersContacts: List<ContactUsersContacts> -> controller.setData(false, contactUsersContacts) })
        viewModel.allContacts
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentGenericEpoxySwipeBinding.inflate(inflater, container, false)
        binding.genericRecyclerId.addItemDecoration(SpaceItemDecorator(20, true, false))
        binding.genericRecyclerId.setController(controller)

        binding.genericSwipeView.setOnRefreshListener { viewModel.allContacts }

        viewModel.contactsLoading.observe(viewLifecycleOwner, Observer { aBoolean: Boolean ->
            controller.setData(aBoolean, viewModel.actualContacts.value)
            if (!aBoolean) {
                binding.genericSwipeView.isRefreshing = false
            }
        })
        return binding.root
    }
}