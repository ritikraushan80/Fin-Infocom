package com.fininfocom.fininfocomapplication.views

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.fininfocom.fininfocomapplication.Model.userList
import com.fininfocom.fininfocomapplication.R
import com.fininfocom.fininfocomapplication.adapter.ListAdapter
import com.fininfocom.fininfocomapplication.databinding.FragmentDashboardBinding
import com.fininfocom.fininfocomapplication.realm.UserDataEntity
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class DashboardFragment : Fragment() {
   private lateinit var _binding:FragmentDashboardBinding
    private val binding get() = _binding
    lateinit var adapter: ListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDashboardBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val configuration = RealmConfiguration.create(schema = setOf(UserDataEntity::class))
        val realm = Realm.open(configuration)

        val user = UserDataEntity().apply {
            name = "Ritik"
            age = 25
            city = "Noida"
        }

        realm.writeBlocking { // this : MutableRealm
            val managedPerson = copyToRealm(user)
        }

        CoroutineScope(Dispatchers.IO).async {
            realm.write { // this : MutableRealm
                val managedPerson = copyToRealm(user)
            }
        }


        userList()

        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
            }
        })
    }

    fun userList(){
        binding.recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        val data = userList
        adapter = ListAdapter(data)
        binding.recyclerView.adapter = adapter

    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.option_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.sortByName -> {
                userList()
                userList.sortedBy { it.name }
                adapter.notifyDataSetChanged()
                Toast.makeText(requireContext(), "username", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.sortByAge -> {
                userList()
                userList.sortedBy { it.age }
                adapter.notifyDataSetChanged()
                Toast.makeText(requireContext(), "username", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.sortByCity -> {
                userList.sortedBy { it.city }
                adapter.notifyDataSetChanged()
                Toast.makeText(requireContext(), "username", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.log_out -> {
                val intent = Intent(requireActivity(), LogInActivity::class.java)
                startActivity(intent)
               requireActivity().finish()
                Toast.makeText(requireContext(), "username", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


}