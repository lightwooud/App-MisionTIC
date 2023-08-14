package com.example.sitiosmisiontic

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sitiosmisiontic.ui.DetailActivity
import com.example.sitiosmisiontic.model.SiteData
import com.example.sitiosmisiontic.model.SiteModel
import com.example.sitiosmisiontic.adapter.RecyclerViewAdapter
import com.example.sitiosmisiontic.viewmodel.MainViewModel

class MainFragment : Fragment() {

    private lateinit var recyclerAdapter: RecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.main_fragment, container, false)

        initViewModel1(view)
        initViewModel1()
        return view

    }

    private fun initViewModel1(view: View){
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.setHasFixedSize(true)

        val decortion = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(decortion)

        recyclerAdapter = RecyclerViewAdapter()
        recyclerView.adapter = recyclerAdapter

        //Listener Detail
        recyclerAdapter.setOnItemClickListener(object : RecyclerViewAdapter.OnItemClickListener{
            override fun onItemClick(position: Int, data: SiteData) {
                Toast.makeText(activity,"Has seleccionado ${data.cityName}",Toast.LENGTH_SHORT).show()

                val intent = Intent(activity, DetailActivity::class.java)

                intent.putExtra("cityName", data.cityName)
                intent.putExtra("imageURL", data.imageURL)
                intent.putExtra("largeInfo", data.largeInfo)
                intent.putExtra("ubiGeo", data.ubiGeo)
                intent.putExtra("tempClima", data.tempClima)
                intent.putExtra("sitesRec", data.sitesRec)
                intent.putExtra("latitud", data.latitud)
                intent.putExtra("longitud", data.longitud)

                activity?.startActivity(intent)
            }
        })
    }

    private fun initViewModel1() {
        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getSiteModelObserver().observe(viewLifecycleOwner, Observer<SiteModel>{
            if (it != null){
                recyclerAdapter.setUpdateData(it.cities)
            } else {
                Toast.makeText(activity, "Error al traer los datos", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.makeApiCall()
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}

