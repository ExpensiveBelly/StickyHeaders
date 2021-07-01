package com.example.stickyheaders

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.stickyheaders.stickyheaders.StickyHeaderDecoration
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val adapter = MyStickyHeaderAdapter()
        findViewById<RecyclerView>(R.id.recyclerview).apply {
            this.adapter = adapter
            addItemDecoration(StickyHeaderDecoration(adapter, null))
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }

        val words =
            getString(R.string.lorem_ipsum).replace(",", "").replace(".", "").split(" ")
                .map { it.lowercase(Locale.getDefault()).trim() }.groupBy { it.first() }.toSortedMap()

        adapter.submitList(words.toList().mapIndexed { index, (header, items) ->
            items.map { StickyItem(index.toLong(), header.toString(), it) }
        }.flatten())
    }
}