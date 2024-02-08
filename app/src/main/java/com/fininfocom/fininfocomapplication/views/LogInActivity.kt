package com.fininfocom.fininfocomapplication.views

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.fininfocom.fininfocomapplication.MainActivity
import com.fininfocom.fininfocomapplication.Model.userList
import com.fininfocom.fininfocomapplication.R
import com.fininfocom.fininfocomapplication.databinding.ActivityLogInBinding

class LogInActivity : AppCompatActivity() {
    private val binding: ActivityLogInBinding by lazy {
        ActivityLogInBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.logInBtn.setOnClickListener {
            val username = binding.username.text.toString().trim()
            val password = binding.password.text.toString().trim()
            val usernameRegex = Regex("[a-zA-Z0-9]{10}")
            val passwordRegex = Regex("(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%^&+=])(?=\\S+\$).{7,}")

            when {
                username.isEmpty() && !username.matches(usernameRegex) -> {
                    binding.username.error = "Username not be empty"
                    return@setOnClickListener
                }
                !username.matches(usernameRegex) -> {
                    binding.username.error = "Please enter valid username"
                    return@setOnClickListener
                }
                password.isEmpty() && !password.matches(passwordRegex) -> {
                    binding.password.error = "Password not be empty"
                    return@setOnClickListener
                }
                !password.matches(passwordRegex) -> {
                    binding.password.error = "Please enter valid password"
                    return@setOnClickListener
                }
                username != "Fininfocom" -> {
                    binding.username.error = "Username Invalid"
                    return@setOnClickListener
                }
                password != "Fin@123" -> {
                    binding.password.error = "Password Invalid"
                    return@setOnClickListener
                }
                else -> {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }

        }

    }
    override fun onCreateOptionsMenu(menu: Menu?) : Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.sortByName -> {
                userList.sortedBy { it.name }
                true
            }
            R.id.sortByAge -> {
                userList.sortedBy { it.age }
                true
            }
            R.id.sortByCity -> {
                userList.sortedBy { it.city }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}