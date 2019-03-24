package fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.ui.amiibo.create

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.R
import fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.data.Amiibo
import fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.data.AmiiboRepository
import fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.extension.dateToString
import kotlinx.android.synthetic.main.activity_create_amiibo.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.sdk27.coroutines.onCheckedChange
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.sdk27.coroutines.textChangedListener
import org.jetbrains.anko.uiThread
import java.util.*

class CreateAmiiboActivity : AppCompatActivity() {

    private var amiibo = Amiibo()

    private var datePickerDialog: DatePickerDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_amiibo)

        setupDatePicker()
        setupToolbar()
        setupViews()
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_create_amiibo, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean = when (item?.itemId) {
        android.R.id.home -> {
            ActivityCompat.finishAfterTransition(this)
            true
        }
        R.id.confirm -> {
            doAsync {
                AmiiboRepository.insert(amiibo)
                uiThread { ActivityCompat.finishAfterTransition(this@CreateAmiiboActivity) }
            }
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupViews() {
        characterEditText.apply {
            requestFocus()
            textChangedListener { onTextChanged { charSequence, _, _, _ -> amiibo.character = charSequence.toString().capitalize() } }
        }

        amiiboSeriesEditText.textChangedListener { onTextChanged { charSequence, _, _, _ -> amiibo.amiiboSeries = charSequence.toString().capitalize() } }

        gameSeriesEditText.textChangedListener { onTextChanged { charSequence, _, _, _ -> amiibo.gameSeries = charSequence.toString().capitalize() } }

        releaseEditText.onClick { datePickerDialog?.show() }
    }

    private fun setupDatePicker() {
        val calendar: Calendar = Calendar.getInstance()
        calendar.time = amiibo.release

        datePickerDialog = DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                calendar.set(year, month, dayOfMonth)
                releaseEditText.setText(calendar.time.dateToString().capitalize())
                amiibo.release = calendar.time
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH))
    }
}