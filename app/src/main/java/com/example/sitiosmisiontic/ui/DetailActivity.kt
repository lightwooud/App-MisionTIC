package com.example.sitiosmisiontic.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.sitiosmisiontic.R
import com.squareup.picasso.Picasso


class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity)

        val titleDetail : TextView = findViewById(R.id.cityNameTv)
        val image1Detail : ImageView = findViewById(R.id.image1)
        val paragraph1Detail : TextView = findViewById(R.id.paragraph1)
        val paragraph2Detail : TextView = findViewById(R.id.paragraph2)
        val paragraph3Detail : TextView = findViewById(R.id.paragraph3)
        val paragraph4Detail : TextView = findViewById(R.id.paragraph4)

        val bundle : Bundle? = intent.extras

        val cityName = bundle?.getString("cityName")
        val imageURL = bundle?.getString("imageURL")
        val largeInfo = bundle?.getString("largeInfo")
        val ubiGeo = bundle?.getString("ubiGeo")
        val tempClima = bundle?.getString("tempClima")
        val sitesRec = bundle?.getString("sitesRec")
        val latitud = bundle?.getString("latitud")
        val longitud = bundle?.getString("longitud")

        titleDetail.text = cityName
        Picasso.get().load(imageURL).resize(1000,600).into(image1Detail)
        paragraph1Detail.text = largeInfo
        paragraph2Detail.text = ubiGeo
        paragraph3Detail.text = tempClima
        paragraph4Detail.text = sitesRec

        val buttonMap : ImageButton = findViewById<ImageButton>(R.id.imageButton)
        buttonMap.setOnClickListener(){
            if (latitud != null && longitud != null) {
                launchMap(latitud,longitud)
            }
        }
    }

    private fun launchMap(latitud: String, longitud: String) {
        val gmmIntentUri = Uri.parse("geo:$latitud,$longitud")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        startActivity(mapIntent)
    }
}