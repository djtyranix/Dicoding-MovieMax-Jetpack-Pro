package com.nixstudio.moviemax.views.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nixstudio.moviemax.R

class ItemDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_detail_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ItemDetailFragment.newInstance())
                .commitNow()
        }
    }
}