package com.skybox.seven.covid.ui.mythbusters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.skybox.seven.covid.R
import com.skybox.seven.covid.data.entities.Myth
import com.skybox.seven.covid.databinding.FragmentMythBustersBinding
import com.skybox.seven.covid.epoxy.mythbuster.MythBusterController
import com.skybox.seven.covid.epoxy.mythbuster.MythBusterController.MythCallbacks
import com.skybox.seven.covid.ui.MainViewModel
import com.skybox.seven.covid.ui.share.ShareDialogViewModel
import com.skybox.seven.covid.util.SpaceItemDecorator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MythBustersFragment : Fragment(), MythCallbacks, View.OnClickListener {
    private var controller: MythBusterController = MythBusterController(this)
    private val viewModel: MythViewModel by viewModels()
    private val shareViewModel: MainViewModel by activityViewModels()
    private lateinit var binding: FragmentMythBustersBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.mythsList.observe(this, Observer { mythbusterModel: List<Myth?> -> controller!!.setData(false, mythbusterModel) })
        viewModel.getAllMyths()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentMythBustersBinding.inflate(inflater, container, false)
        binding.MythRecyclerView.addItemDecoration(SpaceItemDecorator(20, true, false))
        binding.MythRecyclerView.setController(controller)
        binding.backBtn.setOnClickListener(this)
        return binding.root
    }

    override fun onMythClicked(myth: Myth) {
        shareViewModel.myth.value = myth
        shareViewModel.open.value = true
    }

    override fun onClick(view: View) {
        Navigation.findNavController(requireActivity(), R.id.nav_host_fragment).navigateUp()
    }

    override fun onDestroy() {
        shareViewModel.open.value = false
        super.onDestroy()
    }
}