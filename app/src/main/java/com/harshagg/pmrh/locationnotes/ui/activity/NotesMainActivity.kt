package com.harshagg.pmrh.locationnotes.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import com.harshagg.pmrh.locationnotes.R
import com.harshagg.pmrh.locationnotes.ui.viewmodel.NotesActivityViewModel
import com.harshagg.pmrh.locationnotes.utilities.InjectorUtils
import kotlinx.android.synthetic.main.activity_notes_main.*

class NotesMainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                message.setText(R.string.title_home)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                message.setText(R.string.title_dashboard)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                message.setText(R.string.title_notifications)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private var mNotesActivityViewModel: NotesActivityViewModel? = null

    companion object {
        private const val REQUEST_CODE_ADD_NEW_NOTE = 1000
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        val notesViewModelFactory = InjectorUtils.provideNotesActivityViewModelFactory(this)

        mNotesActivityViewModel = ViewModelProviders.of(this, notesViewModelFactory).get(NotesActivityViewModel::class.java)

        mNotesActivityViewModel!!.getAllNotes().observe(this, Observer { noteEntries ->
            if (noteEntries != null) {
                Snackbar.make(message, "Total Notes found - " + noteEntries.size, Snackbar.LENGTH_SHORT).show()
            } else {
                Snackbar.make(message, "No Notes found", Snackbar.LENGTH_SHORT).show()
            }
        })

        notes_add_icon.setOnClickListener { view ->
            intent = Intent(this, NotesDetailActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_ADD_NEW_NOTE)
        }
    }
}
