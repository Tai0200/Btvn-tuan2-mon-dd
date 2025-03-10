package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LibraryManagementApp()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LibraryManagementApp() {
    var name by remember { mutableStateOf(TextFieldValue("Nguyen Van A")) }
    var books by remember { mutableStateOf(listOf("Sách 01", "Sách 02")) }

    Scaffold(
        bottomBar = { BottomNavigationBar() } // Chân trang cố định
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            // **Tiêu đề cố định đầu trang**
            Text(
                text = "Hệ thống Quản lý Thư viện",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // **Body cuộn được**
            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                item {
                    // Nhập nhân viên
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        OutlinedTextField(
                            value = name.text,
                            onValueChange = { name = TextFieldValue(it) },
                            label = { Text("Nhân viên") },
                            modifier = Modifier.weight(1f)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Button(onClick = { /* Xử lý đổi nhân viên */ }) {
                            Text("Đổi")
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(text = "Danh sách sách", style = MaterialTheme.typography.titleMedium)
                }

                // **Danh sách sách cuộn được**
                items(books) { book ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(vertical = 4.dp)
                    ) {
                        Checkbox(
                            checked = false,
                            onCheckedChange = { /* Xử lý chọn sách */ }
                        )
                        Text(text = book)
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(16.dp))

                    // Nút thêm sách
                    Button(
                        onClick = { books = books + "Sách ${books.size + 1}" },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Thêm")
                    }
                }
            }
        }
    }
}

// **Chân trang cố định**
@Composable
fun BottomNavigationBar() {
    BottomAppBar {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            TextButton(onClick = { /* Xử lý Quản lý */ }) {
                Icon(Icons.Filled.Home, contentDescription = "Quản lý")
                Spacer(modifier = Modifier.width(4.dp))
                Text("Quản lý")
            }
            TextButton(onClick = { /* Xử lý DS Sách */ }) {
                Icon(Icons.Filled.Menu, contentDescription = "DS Sách")
                Spacer(modifier = Modifier.width(4.dp))
                Text("DS Sách")
            }
            TextButton(onClick = { /* Xử lý Nhân viên */ }) {
                Icon(Icons.Filled.Person, contentDescription = "Nhân viên")
                Spacer(modifier = Modifier.width(4.dp))
                Text("Nhân viên")
            }
        }
    }
}
// **Xem trước UI**
@Preview(showBackground = true)
@Composable
fun PreviewLibraryManagementApp() {
    LibraryManagementApp()
}
