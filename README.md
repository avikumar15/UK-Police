## UK Police
The app shows the data about all the police forces and crimes happening in United Kingdom in an organised manner. The app makes use of the data available on the UK Police website : https://data.police.uk/ and makes API calls to fetch the data. Logo of 

# App Features

1. Search for crime records by entering approximate Latitude, Longitude and the month for which you are looking the data for. If there are crimes for the set data, results will show in form of a recycler list. For making it more user-friendly, the latitude and longitude range of UK is mentioned in this activity.

2. To make the app more user-friendly there is a feature to add a crime to favourites by swiping right any crime and removing from favourites by swiping left. The star turns yellow if the particular crime is found in favourites and appropriate toast is made.

3. The crime record activity contains all the data available on the server along with a Google Map icon, which when clicked launches the map location of the place where the crime is committed. This is done by using a Google Map API.

4. To make the app more user-friendly, there is a search option given in the "Favourite Crimes" and "Police Force" activity.

5. Fragments are implemented in the primary activities which show the purpose of the app and data source.

# Things Learnt

1. Implementation of a RecyclerView.
2. Making API calls using retrofit.
3. Implementation of Fragments.
4. Handling Swipe Gesture event.
5. Making an API call to launch Google Map.
6. Use of SQLite to store favourite crimes.
7. Implementation of Search Query.
