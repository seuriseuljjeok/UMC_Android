package com.example.practice

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.practice.databinding.FragmentHomeBinding

private const val ARG_PARA1 = "para1"
private const val ARG_PARA2 = "para2"


class HomeFragment : Fragment() {
    private var para1: String? = null
    private var para2: String? = null

    private var binding: FragmentHomeBinding? = null
    private val getBinding get() = binding!!
    companion object {
        fun newInstance(para1: String, para2: String): HomeFragment =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARA1, para1)
                    putString(ARG_PARA2, para2)
                }
            }
    }

    //메모리에 올라갔을 때
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            para1 = it.getString(ARG_PARA1)
            para2 = it.getString(ARG_PARA2)
        }
    }

    //Fragment를 안고 있는 액티비티에 붙었을 때
    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    //액티비티에서의 onCreate와 같음.
    //뷰가 생성되었을 때
    //프래그먼트와 레이아웃을 연결시켜주는 부분
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return getBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getBinding.home.text=para1
    }
}