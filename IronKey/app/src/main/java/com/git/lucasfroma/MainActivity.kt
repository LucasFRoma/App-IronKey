package com.git.lucasfroma

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.git.lucasfroma.ui.theme.IronKeyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IronKeyTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    IronKeyForm(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

fun copyPassword(context: Context, password: String) {
    val clipboardManager =
        context.getSystemService(Context.CLIPBOARD_SERVICE) as
                ClipboardManager
    val clip = ClipData.newPlainText("Senha", password)
    clipboardManager.setPrimaryClip(clip)
    Toast.makeText(context, "Senha copiada!",
        Toast.LENGTH_SHORT).show()
}


@Composable
fun IronKeyForm(modifier: Modifier = Modifier) {

    val context = LocalContext.current
    var generatedPassword by remember { mutableStateOf("") }
    var  maxCharacter by remember { mutableIntStateOf(8) }



    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Image(
            // Carrega uma imagem local do diretório res/drawable
            painter = painterResource(id = R.drawable.img),
            // Descrição para acessibilidade (ex: leitores de tela)
            contentDescription = "Logo do app",
            // modifier permite configurar a aparência da imagem
            modifier = Modifier
                // Define um tamanho fixo (largura e altura de 150)
                .size(150.dp)
                // Recorta a imagem em formato circular
                .clip(CircleShape)
                // Aplica um fundo colorido no formato de um
                //círculo atrás da imagem. onBackground é uma cor do tema
                // atual (contraste com o fundo)
                .background(
                    MaterialTheme
                        .colorScheme
                        .onBackground,
                    shape = CircleShape
                )
                // Centraliza a imagem horizontalmente dentro de um
                    //Column ou outro layout que aceite alinhamento de filhos.
                    //Só tem efeito se usada dentro de layouts como Column ou
                    //Box com alinhamento.
                .align(Alignment.CenterHorizontally),
            // A imagem será cortada para preencher completamente os 150×150, sem distorcer.
            contentScale = ContentScale.Crop
        )
        Text(
            text = "Senhas seguras",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.SemiBold,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            textAlign = TextAlign.Center
        )

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(8.dp)
        ){
            Column {
                OutlinedTextField(
                    value = generatedPassword,
                    onValueChange = { newPassword ->
                        if (newPassword.length <= maxCharacter)
                        generatedPassword = newPassword
                    },
                    label = { Text("Password") },
                    modifier = Modifier.fillMaxSize(),
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Lock,
                            contentDescription = "Password")
                    },
                    trailingIcon = {
                        if (generatedPassword.isNotEmpty()) {
                            Icon(
                                imageVector = Icons.Filled.ContentCopy,
                                contentDescription = "Copiar",
                                modifier = Modifier.clickable {
                                    copyPassword(context, generatedPassword)
                                }
                            )
                            
                        }
                    }
                )
                Text("${generatedPassword.length} / $maxCharacter",
                    style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier.align(Alignment.End)
                        .padding(end = 8.dp, top = 4.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun prev() {
    IronKeyForm()
}

