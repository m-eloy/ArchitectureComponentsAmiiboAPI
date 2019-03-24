package fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.ui.amiibo.detail

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.R
import fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.data.Amiibo
import fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.data.AmiiboRepository
import fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.extension.dateToString
import kotlinx.android.synthetic.main.activity_detail_amiibo.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class DetailAmiiboActivity : AppCompatActivity() {

    private var amiibo: Amiibo? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_amiibo)

        doAsync {
            amiibo = AmiiboRepository.getById(intent.getIntExtra("id", 0))
            uiThread {
                setupToolbar()
                setupViews()
            }
        }
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean = when (item?.itemId) {
        android.R.id.home -> {
            ActivityCompat.finishAfterTransition(this)
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        title = amiibo?.character
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupViews() {
        amiibo?.let { amiibo ->
            amiiboSeries.text = amiibo.amiiboSeries

            gameSeries.text = amiibo.gameSeries

            release.text = amiibo.release.dateToString().capitalize()
        }
    }
}