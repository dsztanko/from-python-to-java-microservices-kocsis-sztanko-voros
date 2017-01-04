####You Might Also Like API Service
#####Created by Team GIT_Egylet
######2017

This API Service is responsible for handing over recommendations on what elements might be interesting to the current user based on his/her (past) behaviour, interactions and other users' habits.

This Maven Project was designed to be universal, written in Java8 and using Spark Framework.
Let's see some examples of its purposes:
   - We can integrate this microservice into a webshop and make some proper advice on what products the current user might be curious of.
   - It can be helpful during finding new friends - in terms of developing an app using social network aspects - according to our friend-circle.
   - Suggesting music hits with the help of others' playlists.
   - Movies, books, dishes, recipes etc...
  
#####Working mechanism

The microservice has 2 routes it listens to:

1. http://localhost:60002/api/preferences/save
Parameters that needs to be added: accessToken, userId, cartItemId
>Example: http://localhost:60002/api/preferences/save?accessToken=page1&userId=user1&cartItemId=5

2. http://localhost:60002/api/preferences/select
Parameters that needs to be added: accessToken, userId
>Example: http://localhost:60002/api/preferences/select?accessToken=page1&userId=user1

Both routes have parameters which has to be added to the basic URL with addParams() method. Access token is chosen by developers. Use always the same access token if you'd like to get back valid recommendations. Access token identifies your web page.
#####Guide to use it in the web shop project
Building and triggering URL is advised every time when 'Add to cart' button is clicked. Saving stores Users and updates their carts, meanwhile selecting returns a JSON file with the following format: 

{"recommendations": ["5", "2", "1"]}

JSON gives back sorted ids (product_id, friend_id). The first one is the most relevant suggestion to the user. The number of return values can be changed in the codebase. If there's not relevant amount of data in the storage, an empty list will be returned.

#####What's next - further development

1. Storing in database (JDBC)
2. SQL operations instead of code logic.
3. Use of more complicated algorithms, data mining.
#####In case of upcoming questions find our table by the door. Thank you for using our very first MC!

###You Might Also Like it!
###Cheers!

