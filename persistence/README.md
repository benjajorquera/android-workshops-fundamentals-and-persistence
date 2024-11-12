# Shopping List App

## Project Overview
This project is an Android application designed to manage a shopping list, allowing users to add, view, edit, and delete items. The app leverages ROOM for local data storage and Jetpack Compose for the UI.

## Features

1. **Main Screen**: Displays the shopping list, showing each item's name and a checkbox to mark it as purchased.
2. **Add Item**: Allows the user to add new items to the shopping list. When the "Add" option is selected, a screen opens where the user can enter the item's name. Upon saving, the item is stored in the database and displayed on the main list.
3. **Edit Item**: Users can edit the name of an existing item from the main screen. Saved changes update the item in the database and on the main list.
4. **Delete Item**: Users can delete items from the shopping list directly from the main screen. Upon confirmation, the item is removed from the database and the list.
5. **Data Persistence**: Uses ROOM to persist shopping list data. Defines an entity `ListItem` with columns for the item name and purchase status (purchased/not purchased).
6. **User Interface**: Built with Jetpack Compose, offering a seamless and responsive UI for managing the shopping list.

This app serves as a foundational project for exploring Android development techniques such as ROOM and Jetpack Compose.
