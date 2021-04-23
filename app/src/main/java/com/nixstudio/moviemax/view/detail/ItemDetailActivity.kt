package com.nixstudio.moviemax.view.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

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