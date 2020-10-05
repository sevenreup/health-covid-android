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
        clickListeners()
        return binding.root

    }

    private fun clickListeners(){

        binding.chatbotContainer.setOnClickListener {
            openWhatsApp(binding.WhatsAppNumber.text as String)
        }
        binding.tnmUSSDone.setOnClickListener {
            callUSSD(binding.tnmUSSDone.text.toString())
        }
        binding.tnmUSSDtwo.setOnClickListener {
            callUSSD(binding.tnmUSSDtwo.text.toString())
        }
        binding.airtelUSSDone.setOnClickListener {
            callUSSD(binding.airtelUSSDone.text.toString())
        }
        binding.airtelUSSDtwo.setOnClickListener {
            callUSSD(binding.airtelUSSDtwo.text.toString())
        }

        binding.nationalHelpline.setOnClickListener {
            callNumber(binding.nationalHelpline.text.toString())
        }

    }

    private fun callNumber(number: String){
        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$number"))
        startActivity(intent)
    }

    private fun callUSSD(code: String){


        if (ActivityCompat.checkSelfPermission(this.requireContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.CALL_PHONE, Manifest.permission.CALL_PHONE), REQUEST_CALL)
        }else{

            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:*$code"+Uri.encode("#")))
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

    fun toHomePage(){
        findNavController().navigateUp()
    }

}