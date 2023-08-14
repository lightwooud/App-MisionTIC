package com.example.sitiosmisiontic.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sitiosmisiontic.R
import com.example.sitiosmisiontic.databinding.RecyclerListRowBinding
import com.example.sitiosmisiontic.model.SiteData
import com.squareup.picasso.Picasso

class RecyclerViewAdapter: RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    private var cities = ArrayList<SiteData>()

    private lateinit var mListener : OnItemClickListener

    interface OnItemClickListener{
        fun onItemClick (position : Int, data: SiteData)
    }

    fun setOnItemClickListener(listener: OnItemClickListener){
        mListener = listener
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setUpdateData(cities: ArrayList<SiteData>){

        this.cities = cities
        notifyDataSetChanged()

    }

    inner class MyViewHolder(view: View, listener: OnItemClickListener): RecyclerView.ViewHolder(view){

        private val binding = RecyclerListRowBinding.bind(view)

        fun bind(data: SiteData) {
            binding.cityName.text = data.cityName
            binding.shortInfo.text = data.shortInfo
            binding.punctuation.text = data.punctuation
            Picasso.get().load(data.imageURL).resize(208, 208).into(binding.imageURL)
        }

        init {
            view.setOnClickListener {
                listener.onItemClick(adapterPosition, cities[position])
            }
        }

    }

    override fun onCreateViewHolder(container: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(container.context).inflate(R.layout.recycler_list_row, container, false)

        return MyViewHolder(view, mListener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.bind(cities[position])
    }

    override fun getItemCount(): Int {
        return cities.size
    }
}