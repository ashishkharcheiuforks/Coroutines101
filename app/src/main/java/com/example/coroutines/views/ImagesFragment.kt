package com.example.coroutines.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.coroutines.CoroutinesApp
import com.example.coroutines.R
import kotlinx.android.synthetic.main.images_fragment.*
import javax.inject.Inject

class ImagesFragment : Fragment() {

  companion object {
    fun newInstance() = ImagesFragment()
  }

  @Inject
  lateinit var viewModel: ImagesViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    (requireActivity().application as CoroutinesApp)
      .appComponent
      .inject(this)
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    return inflater.inflate(R.layout.images_fragment, container, false)
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)

    viewModel.images.observe(viewLifecycleOwner, Observer {
      image1.setImageBitmap(it.first)
      image2.setImageBitmap(it.second)
    })

    viewModel.lookupImages()
  }
}