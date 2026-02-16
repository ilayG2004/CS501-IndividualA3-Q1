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
-.weight(1f): In each row in settings, we have a Column for text set to a weight of 1f, and a Box which contains an interactable composable with also a weight of 1f. This prevents the text, or the interactables from taking up too much of the row space
-.fillMaxWidth(): In SettingRow, the row composable takes up the whole width available to it
-.padding(): Used to give space between the items inside the row of SettingRow and the actual border of the screen.


Interactable Settings:
1. Switch (prompts a snackbar on change)
2. Slider
3. TextField
4. CheckBox
5. Radio Buttons
Divider featured between all of these

