package com.example.rowcolmastery_q1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rowcolmastery_q1.ui.theme.RowColMasteryQ1Theme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RowColMasteryQ1Theme {
                SettingScreen()
            }
        }
    }
}


/*
VIEW REQS:
- Column as the main layout
- Rows that have:
    - LEFT: Label w/ supporting text (column inside our row)
    - RIGHT: Control (switch, checkbox, slider, or button)

MODIFIER REQS:
- Modifier.weight to correctly prevent truncation & keep controls ALIGNED
- padding, fillMaxWidth. weight, heightIn or sizeIn, align, & at least one of: border, clip, background, or clickable

MAT3 REQS:
- Use at least 6 Mat 3 Comps
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingScreen(modifier: Modifier = Modifier) {
    var checked by remember { mutableStateOf(false) }
    var sliderPos by remember {mutableStateOf(0f)}
    var deviceName by remember {mutableStateOf("Ilay's Device")}

    val checkBoxOptions = listOf("Microphone","Location","Camera")
    var checkBoxStates = remember { mutableStateListOf(false,false,false)}

    val radioOptions = listOf("Favorites", "Two calls in a row", "None")
    var selectedRadio by remember { mutableStateOf(radioOptions[0]) }

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    // Scaffolding for our TopAppBar and SnackBarHost
    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary,
            ),
            title = {
                Text("Settings")
            }
        )}

    ) { paddingValues ->
        // The actual interactable content of our settings screen
        Column(
            modifier = Modifier.padding(paddingValues).background(color= MaterialTheme.colorScheme.background)
        ) {

            // This switch will trigger our Snackbar
            SettingRow(label = "Low Power", supportText = "Limits CPU usage to save power") {
                Switch(
                    checked=checked,
                    onCheckedChange = {
                        scope.launch {
                            snackbarHostState.showSnackbar(
                                message = "Low Power mode $checked",
                            )
                        }
                        checked = it
                    }
                )
            }
            //Dividers between every setting item
            HorizontalDivider(thickness = 2.dp)
            SettingRow( "Volume", "Adjust volume output", modifier = modifier) {
                Slider(
                    value = sliderPos,
                    onValueChange = {sliderPos = it},
                )
            }

            /*SettingRow(DatePicker(), "Date", "Adjust system date")*/
            //TextField component
            HorizontalDivider(thickness = 2.dp)
            SettingRow("Device Name", "Change device name") {
                TextField(
                    value = deviceName,
                    onValueChange = { deviceName = it }
                )
            }

            //Checkbox components laid out dynamically in a column where each checkbox has its own row
            HorizontalDivider(thickness = 2.dp)
            SettingRow("Privacy Settings", "Enable or disable access") {
                Column{ checkBoxOptions.forEachIndexed { index, option ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Checkbox(
                            checked = checkBoxStates[index],
                            onCheckedChange = {checkBoxStates[index] = it}
                        )
                        Text(text=option, modifier= Modifier.align(Alignment.CenterVertically))
                    }
                }}
            }

            //Radio button components laid out dyna ically in a column where each radio button has its own row
            HorizontalDivider(thickness = 2.dp)
            SettingRow("Do Not Disturb Priority", "Allows calls to go through when on DND.") {
                Column {
                    radioOptions.forEach { option ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = (option == selectedRadio),
                                onClick = { selectedRadio = option }
                            )
                            Text(text = option, modifier= Modifier.align(Alignment.CenterVertically))
                        }
                    }
                }
            }
        }
    }
}


/*
Dynamically set and call rows in our settings menu
 */
@Composable
fun SettingRow(label: String, supportText: String, modifier: Modifier =Modifier, content: @Composable () -> Unit) {
    //Calls our text label & supporting text inside their own column, taking up only 1 half of the row using Modifier.weight
    //Then calls our composable components defined above, inside their own box, taking up only 1 half of the row using Modifier.weight
    Row(
        modifier = modifier
            .padding(10.dp)
            .fillMaxWidth() //Let each row take up whole screen width, besides the padding around it
            .heightIn(min = 30.dp, max = 200.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // LEFT: Text Column — take 50% of available width
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp)
        ) {
            Text(text= label, fontSize = 18.sp)
            Text(text= supportText, fontSize = 14.sp)
        }

        // RIGHT: Control — take 50% of available width
        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.CenterEnd
        ) {
            content()
        }
    }
}

@Preview
@Composable
fun PreviewSettingScreen(){
    SettingScreen()
}