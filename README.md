####You Might Also Like API Service
#####Created by Team GIT_Egylet
######2017

This API Service is responsible for handing over recommendations on what elements might be interesting to the current user based on his/her (past) behaviour, interactions and other users' habits.

This Maven Project was designed to be universal, written in Java8 and using Spark Framework. Let's see some examples:
   - We can integrate this microservice into a webshop and make some proper advice on what products the current user might be curious of.
   - It can be helpful during finding new friends - in terms of developing an app using social network aspects - according to our friend-circle.
   - Suggesting music hits with the help of others' playlists.
   - Movies, books, dishes, recipes etc...
   
  
  
#####Working mechanism

The microservice has 2 application routes it listens to:

1. http://localhost:60002/api/preferences/save
2. http://localhost:60002/api/preferences/select

Both routes have parameters which has to be added to the basic URL with addParams() method.
#####Guide to use it

What's next - further development

JDBC - working with more data
SQL operations instead of code logic
more complicated algorithms, data mining
