package com.carlostorres.tictactoe.ui.home

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Switch
import androidx.compose.material.SwitchColors
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.carlostorres.tictactoe.R
import com.carlostorres.tictactoe.ui.theme.Accent
import com.carlostorres.tictactoe.ui.theme.Background
import com.carlostorres.tictactoe.ui.theme.Orange1
import com.carlostorres.tictactoe.ui.theme.Orange2

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel(),
    navigateToGame: (String, String, Boolean) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Header()

        Body(
            onCreateGame = {
                homeViewModel.onCreateGame(navigateToGame)
            },
            onJoinGame = { gameId ->
                homeViewModel.onJoinGame(gameId, navigateToGame)
            }
        )

    }

}

@Composable
fun Body(
    onCreateGame: () -> Unit,
    onJoinGame: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(24.dp),
        backgroundColor = Background,
        border = BorderStroke(2.dp, Orange1),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            var createCame by remember {
                mutableStateOf(true)
            }

            Switch(
                checked = createCame,
                onCheckedChange = {
                    createCame = it
                },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Orange2
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            AnimatedContent(
                targetState = createCame,
                label = ""
            ) {

                when (it) {
                    true -> {
                        CreateGame { onCreateGame() }
                    }

                    false -> {
                        JoinGame(
                            onValueChange = {},
                            onJoinGame = { gameId ->
                                onJoinGame(gameId)
                            }
                        )
                    }
                }

            }

            Spacer(modifier = Modifier.height(24.dp))

        }

    }
}

@Composable
fun Header() {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(Modifier.height(12.dp))

        Box(
            modifier = Modifier
                .size(200.dp)
                .padding(24.dp)
                .clip(RoundedCornerShape(24.dp))
                .border(2.dp, Orange2, RoundedCornerShape(24.dp)),
            contentAlignment = Alignment.BottomCenter
        ) {

            Image(
                painter = painterResource(R.drawable.estrategia),
                contentDescription = "Logo",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
            )

            Text(
                text = "Icon By: Tru3 Art",
                color = Color.Gray,
                fontSize = 8.sp,
                modifier = Modifier
                    .padding(bottom = 2.dp)
            )

        }

        Text(
            text = "Firebase".uppercase(),
            fontSize = 32.sp,
            color = Orange1,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = " 3 en raya".uppercase(),
            fontSize = 28.sp,
            color = Orange2,
            fontWeight = FontWeight.Bold
        )

    }

}

@Composable
fun CreateGame(onCreateGame: () -> Unit) {

    Button(
        onClick = { onCreateGame() },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Orange1
        )
    ) {
        Text(
            "Create Game",
            color = Accent
        )
    }

}

@Composable
fun JoinGame(onValueChange: (String) -> Unit, onJoinGame: (String) -> Unit) {

    var text by remember { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {

        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = Accent,
                focusedBorderColor = Orange1,
                unfocusedBorderColor = Accent,
                cursorColor = Orange1
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = { onJoinGame(text) },
            enabled = text.isNotEmpty(),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Orange1
            )
        ) {

            Text(
                "Join to game",
                color = Accent
            )

        }

    }

}