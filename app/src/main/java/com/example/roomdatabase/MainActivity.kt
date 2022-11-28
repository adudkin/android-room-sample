package com.example.roomdatabase

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabase.room.Product
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private val productViewModel: ProductViewModel by viewModels{
        ProductViewModelFactory((application as InjectedApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = ProductListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        productViewModel.allProducts.observe(this) { products ->
            // Update the cached copy of the items in the adapter.
            products?.let { adapter.submitList(it) }
        }

        findViewById<FloatingActionButton>(R.id.fab)?.setOnClickListener { requestNewItem() }
    }

    val itemsCreator = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            result.data?.let{
                val productName = it.getStringExtra(NewItemActivity.EXTRA_NEW_ITEM_NAME)
                val productValue = it.getIntExtra(NewItemActivity.EXTRA_NEW_ITEM_VALUE, 0)
                if (productName != null && productValue != 0) {
                    productViewModel.insert(Product(productName, productValue))
                }
            }

        } else {
            Toast.makeText(
                applicationContext,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG).show()
        }
    }
    private fun requestNewItem() {
        itemsCreator.launch(Intent(this, NewItemActivity::class.java))
    }

}