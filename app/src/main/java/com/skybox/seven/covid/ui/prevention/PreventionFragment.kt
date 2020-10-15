package com.skybox.seven.covid.ui.prevention

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.skybox.seven.covid.R
import com.skybox.seven.covid.data.entities.Advice
import com.skybox.seven.covid.databinding.FragmentPreventionBinding
import com.skybox.seven.covid.epoxy.prevention.PreventionController
import com.skybox.seven.covid.epoxy.prevention.PreventionController.PreventionCallback
import com.skybox.seven.covid.ui.share.ShareDialogViewModel
import com.skybox.seven.covid.util.SpaceItemDecorator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PreventionFragment : Fragment(), PreventionCallback, View.OnClickListener {
    private var controller: PreventionController = PreventionController(this)
    private val viewModel: PreventionViewModel by viewModels()
    private val shareViewModel: ShareDialogViewModel by activityViewModels()

    private lateinit var binding: FragmentPreventionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.adviceList.observe(this, Observer { preventionModel: List<Advice?> -> controller.setData(false, preventionModel) })
        viewModel.getAdviceList()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentPreventionBinding.inflate(inflater, container, false)
        binding.PreventionRecyclerView.addItemDecoration(SpaceItemDecorator(20, true, false))
        binding.PreventionRecyclerView.setController(controller)
        binding.backBtnn.setOnClickListener(this)
        return binding.root
    }

    override fun preventionClick(advice: Advice) {
        shareViewModel.advice.value = advice
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