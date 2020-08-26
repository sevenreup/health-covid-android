package com.skybox.seven.covid.ui.profile.contacts.pages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.skybox.seven.covid.databinding.FragmentGenericEpoxySwipeBinding
import com.skybox.seven.covid.epoxy.contacts.ContactsRequestController
import com.skybox.seven.covid.epoxy.contacts.ContactsRequestController.ContactsCallBack
import com.skybox.seven.covid.model.ContactRequestModel.PendingContacts
import com.skybox.seven.covid.ui.profile.contacts.ContactsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContactRequestFragment : Fragment(), ContactsCallBack {
    private lateinit var binding: FragmentGenericEpoxySwipeBinding

    private var controller: ContactsRequestController = ContactsRequestController(this)
    private val viewModel: ContactsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.contactsRefresh.observe(this, Observer { viewModel.allContacts })
        viewModel.pendingContacts.observe(this, Observer<List<PendingContacts?>> { pendingContacts: List<PendingContacts?> -> controller.setData(false, pendingContacts) })
        viewModel.generatePendingContactList()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentGenericEpoxySwipeBinding.inflate(inflater, container, false)

        binding.genericRecyclerId.setController(controller)
        binding.genericSwipeView.setOnRefreshListener { viewModel.generatePendingContactList() }

        viewModel.contactsLoading.observe(viewLifecycleOwner, Observer { aBoolean: Boolean ->
            controller.setData(aBoolean, viewModel.pendingContacts.value)
            if (!aBoolean) {
                binding.genericSwipeView.isRefreshing = false
            }
        })
        return binding.root
    }

    override fun onContactAcceptClick(id: Int, position: Int) {
        viewModel.acceptContact(id, position)
    }

    override fun onContactRejectClick(id: Int, position: Int) {
        viewModel.rejectContact(id, position)
    }
}