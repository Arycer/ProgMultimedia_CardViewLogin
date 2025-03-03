package me.arycer.dam

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginActivity : AppCompatActivity() {
    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        setupUI()
        setupListeners()
    }

    private fun setupUI() {
        usernameEditText = findViewById(R.id.username_field)
        passwordEditText = findViewById(R.id.password_field)
        loginButton = findViewById(R.id.login_button)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun setupListeners() {
        loginButton.setOnClickListener {
            val userInput = usernameEditText.text.toString().trim()
            val passInput = passwordEditText.text.toString().trim()
            handleLogin(userInput, passInput)
        }
    }

    private fun handleLogin(userInput: String, passInput: String) {
        when {
            userInput.isBlank() || passInput.isBlank() -> {
                showToast("Tienes que intruducir un usuario y contraseña")
            }
            userInput == MYUSER && passInput == MYPASS -> {
                showToast("Inicio de sesión exitoso")
                navigateToMain(userInput)
            }
            else -> {
                showToast("Usuario o contraseña incorrectos")
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun navigateToMain(userInput: String) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("LoginUsername", userInput)
        startActivity(intent)
    }

    companion object {
        private const val MYUSER = "admin"
        private const val MYPASS = "admin"
    }
}