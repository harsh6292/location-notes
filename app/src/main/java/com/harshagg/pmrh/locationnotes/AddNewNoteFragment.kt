package com.harshagg.pmrh.locationnotes

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.harshagg.pmrh.locationnotes.ui.viewmodel.NewNoteFragmentViewModel
import com.harshagg.pmrh.locationnotes.utilities.DateConverterUtils
import kotlinx.android.synthetic.main.fragment_add_new_note.*

private const val ARG_NOTE_ID = "note_id"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [AddNewNoteFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [AddNewNoteFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class AddNewNoteFragment : Fragment() {
    private var listener: OnFragmentInteractionListener? = null

    private lateinit var mNewNoteViewModel: NewNoteFragmentViewModel

    private var mStoredNoteId: Int = -1

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mNewNoteViewModel = ViewModelProviders.of(this).get(NewNoteFragmentViewModel::class.java)

        mNewNoteViewModel.getNote().observe(this, Observer {
            // load the UI
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_new_note, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        arguments?.let {
            mStoredNoteId = it.getInt(ARG_NOTE_ID, -1)
        }

        if (mStoredNoteId != -1) {
            Log.i("LocationNotes#", "Stored ID: $mStoredNoteId, Loading existing note!")
            mNewNoteViewModel.loadNote(mStoredNoteId)
        } else {
            Log.i("LocationNotes#", "No stored ID")
        }
    }


    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }


    override fun onPause() {
        super.onPause()

        val title = new_note_title_text.text.toString().trim()
        val body = new_note_body_text.text.toString().trim()

        if (!(TextUtils.isEmpty(title) && TextUtils.isEmpty(body))) {
            val date = DateConverterUtils.normalizedUtcDateForToday
            mNewNoteViewModel.createNote(title, body, date)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.i("LocationNotes#", "Storing note to database")
        mNewNoteViewModel.saveNote()
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        @JvmStatic
        fun newInstance() = AddNewNoteFragment()

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AddNewNoteFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(storedNoteId: Int) =
            AddNewNoteFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_NOTE_ID, storedNoteId)
                }
            }
    }
}
