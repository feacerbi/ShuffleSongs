package br.com.felipeacerbi.shufflesongs.list.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import br.com.felipeacerbi.shufflesongs.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar.setupWithNavController(findNavController(R.id.nav_host_fragment),
            AppBarConfiguration(setOf(
                R.id.splashFragment,
                R.id.listFragment
            )))
        setSupportActionBar(toolbar)
    }
}
