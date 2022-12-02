package jp.ac.it_college.std.s21005.pokeapi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import jp.ac.it_college.std.s21005.pokeapi.databinding.FragmentResultBinding

@Suppress("UNREACHABLE_CODE")
class ResultFragment : Fragment() {
    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentResultBinding.inflate(inflater, container, false)
        //binding.textView.text = toast
        binding.button.setOnClickListener {
            Navigation.findNavController(it).navigate(
                ResultFragmentDirections.actionResultFragmentToTitleFragment()
            )
        }
        return binding.root
    }
}