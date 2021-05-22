package gaur.himanshu.august.flowplayground

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.coroutineScope
import com.google.android.material.snackbar.Snackbar
import gaur.himanshu.august.flowplayground.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collect

class MainActivity : AppCompatActivity() {


    lateinit var myVIewModel: MyVIewModel

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        myVIewModel = ViewModelProvider(this)[MyVIewModel::class.java]


        binding.clickMe.setOnClickListener {
            myVIewModel.login(binding.username.text.toString(), binding.password.text.toString())
        }


        lifecycle.coroutineScope.launchWhenCreated {
            myVIewModel.state.collect {
                when (it) {

                    is MyVIewModel.StateController.Loading -> {
                        binding.progress.visibility = View.VISIBLE
                    }
                    is MyVIewModel.StateController.Error -> {
                        binding.progress.visibility = View.GONE
                        Snackbar.make(binding.root, it.message, Snackbar.LENGTH_SHORT).show()
                    }

                    is MyVIewModel.StateController.Suceess -> {
                        binding.progress.visibility = View.GONE
                        Snackbar.make(binding.root, "Authenticated", Snackbar.LENGTH_SHORT).show()
                    }
                }
            }
        }


    }
}