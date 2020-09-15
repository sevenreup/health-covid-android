package com.skybox.seven.covid.ui.details

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.skybox.seven.covid.R
import com.skybox.seven.covid.data.entities.DetailsPhoneNumbers
import com.skybox.seven.covid.data.entities.getDetailsData
import com.skybox.seven.covid.databinding.FragmentDetailsBinding
import com.skybox.seven.covid.epoxy.details.DetailsController
import kotlin.properties.Delegates

const val REQUEST_CALL = 1


class DetailsFragment : Fragment() {

    private var installed by Delegates.notNull<Boolean>()

    private lateinit var binding: FragmentDetailsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding =  FragmentDetailsBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.fragment = this
        return binding.root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        super.onCreate(savedInstanceState)

        val data = getDetailsData()
        bindEpoxyRecycler(data as MutableList<DetailsPhoneNumbers>)

        clickListeners()
    }

    private fun clickListeners(){

        binding.chatbotContainer.setOnClickListener {
            openWhatsApp(binding.WhatsAppNumber.text as String)
        }
        binding.tnmUSSD.setOnClickListener {
            callUSSD(binding.tnmUSSD.text.toString())
        }
        binding.airtelUSSD.setOnClickListener {
            callUSSD(binding.airtelUSSD.text.toString())
        }

    }

    private fun bindEpoxyRecycler(data: MutableList<DetailsPhoneNumbers>){

        val recycler = binding.detailsRecycler
        val controller = DetailsController()

        controller.setData(false, data)

        recycler.apply {

            setController(controller)
        }

    }

    private fun callUSSD(code: String){


        if (ActivityCompat.checkSelfPermission(this.requireContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.CALL_PHONE, Manifest.permission.CALL_PHONE), REQUEST_CALL)
        }else{

            val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:*$code"+Uri.encode("#")))
            startActivity(intent)
        }

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == REQUEST_CALL){
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED ){
                callUSSD("*352")
            }else{
                Toast.makeText(requireContext(), "Permission DENIED", Toast.LENGTH_SHORT)
                        .show()
            }
        }
    }

    private fun openWhatsApp(number: String){

        val installed: Boolean = check("com.whatsapp")
        val startText = "hello"

        if (installed){

            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone$number&text=$startText"))
            startActivity(intent)
        }else{

            Toast.makeText(requireContext(), "WhatsApp not installed in this device", Toast.LENGTH_SHORT)
                    .show()
        }

    }

    private fun check(uri: String):Boolean{
        val packageManager: PackageManager = requireActivity().packageManager

        return try {

            packageManager.getPackageInfo(uri, PackageManager.GET_ACTIVITIES)
            installed = true
            true
        }catch (e: PackageManager.NameNotFoundException){
            installed = false
            false
        }
    }

    fun toHomePage(view: View){
        findNavController().navigate(R.id.action_detailsFragment_to_homePageFragment)
    }

}