package com.example.test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculatorApp()
        }
    }
}

@Composable
fun CalculatorApp() {
    var number1 by remember { mutableStateOf("") }
    var number2 by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        //bouton de profil
        Button(onClick ={

        }) { Text("Profil")
        }

        //Titre
        Text(
            text = "Calculatrice simple",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Input Fields
        BasicTextField(
            value = number1,
            onValueChange = { number1 = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            singleLine = true,
            decorationBox = { innerTextField ->
                if (number1.isEmpty()) Text("Entrez le premier nombre")
                innerTextField()
            }
        )
        BasicTextField(
            value = number2,
            onValueChange = { number2 = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            singleLine = true,
            decorationBox = { innerTextField ->
                if (number2.isEmpty()) Text("Entrez le second nombre")
                innerTextField()
            }
        )

        // Buttons Row
        Row(
            modifier = Modifier.padding(8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = {
                result = calculate(number1, number2, "+")
            }) {
                Text("+")
            }
            Button(onClick = {
                result = calculate(number1, number2, "-")
            }) {
                Text("-")
            }
            Button(onClick = {
                result = calculate(number1, number2, "*")
            }) {
                Text("*")
            }
            Button(onClick = {
                result = calculate(number1, number2, "/")
            }) {
                Text("/")
            }
        }

        // Result
        Text(
            text = "Résultat : $result",
            fontSize = 20.sp,
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}

fun calculate(num1: String, num2: String, operator: String): String {
    return try {
        val n1 = num1.toDouble()
        val n2 = num2.toDouble()
        when (operator) {
            "+" -> (n1 + n2).toString()
            "-" -> (n1 - n2).toString()
            "*" -> (n1 * n2).toString()
            "/" -> if (n2 != 0.0) (n1 / n2).toString() else "Erreur : Division par zéro"
            else -> "Erreur"
        }
    } catch (e: Exception) {
        "Entrée invalide"
    }
}

