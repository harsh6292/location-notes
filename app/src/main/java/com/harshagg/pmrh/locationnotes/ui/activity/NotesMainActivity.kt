package com.harshagg.pmrh.locationnotes.ui.activity

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import com.harshagg.pmrh.locationnotes.AddNewNoteFragment
import com.harshagg.pmrh.locationnotes.AllNotesFragment
import com.harshagg.pmrh.locationnotes.R
import com.harshagg.pmrh.locationnotes.ui.viewmodel.NotesActivityViewModel
import com.harshagg.pmrh.locationnotes.utilities.InjectorUtils
import kotlinx.android.synthetic.main.activity_notes_main.*

class NotesMainActivity : AppCompatActivity(), AllNotesFragment.OnFragmentInteractionListener, AddNewNoteFragment.OnFragmentInteractionListener {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
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
                Snackbar.make(addNoteButton, "Total Notes found - " + noteEntries.size, Snackbar.LENGTH_SHORT).show()
            } else {
                Snackbar.make(addNoteButton, "No Notes found", Snackbar.LENGTH_SHORT).show()
            }
        })

        val host: NavHostFragment = supportFragmentManager.findFragmentById(R.id.notes_nav_host_fragment) as NavHostFragment? ?: return
        val navController = host.navController

        addNoteButton.setOnClickListener {
            //findNavController(R.id.notes_nav_host_fragment).navigate(R.id.action_allNotesFragment_to_addNewNoteFragment)
            navController.navigate(R.id.action_allNotesFragment_to_addNewNoteFragment)
        }
    }

    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
