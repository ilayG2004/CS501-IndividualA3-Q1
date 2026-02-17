This is an interactive dummy settings menu using material 3 components, composable functions, and states. Modifiers are used to make every row in this settings menu fit the screen and look pretty.
Each interactable settings composable utilizes state to change values on click. 

Material 3 Components Used:
1. TopAppBar
2. SnackBar
3. Checkbox
4. Slider
5. Divider
6. Switch

Modifiers used:
1. weight(1f): In each row in settings, we have a Column for text set to a weight of 1f, and a Box which contains an interactable composable with also a weight of 1f. This prevents the text, or the interactables from taking up too much of the row space
2. fillMaxWidth(): In SettingRow, the row composable takes up the whole width available to it
3. padding(x.dp): Used to give space between the items inside the row of SettingRow and the actual border of the screen.
4. heightIn(min = 30.dp, max = 200.dp): Used to constraint the height of each row to be between 30 dp and 200dp.
5. align(Alignment.CenterVertically): Used inside the Text composables for my radio buttons and check boxes to put the text CenterVertically with its parent Row
.background(color= MaterialTheme.colorScheme.background): Used inside the parent column for all SettingRow composables. Sets the background color to MaterialTheme.colorScheme.background


Interactable Settings:
1. Switch (prompts a snackbar on change)
2. Slider
3. TextField
4. CheckBox
5. Radio Buttons
Divider featured between all of these

