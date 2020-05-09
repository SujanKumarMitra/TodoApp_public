# <img src = "src/main/resources/static/images/icon.png" width="150px" alt = "logo" /> TodoApp
A Spring Boot Application in which, users can perform CRUD operations on Todo Events. <br/>
Link: [Heroku](https://skm-todo-app.herokuapp.com) OR https://skm-todo-app.herokuapp.com/                       

## Features
1. Users can signup using a unique email, which needs to be verified.
1. Users can create Todos and it will be sorted under categories like Upcoming, Completed, Missed.
1. If a user forgets his/her password, he/she can request for password recovery.
1. User can update their profile info given during the time of account creation.
1. If a user wishes to delete their profile,
 1. He/She can submit a delete request.
 1. An email confirmation will be sent for extra authentication. 
 1. Then the user's account __(along with their data)__ will be permanently deleted.

## To run
1. Import the project in your ide.
1. Fill up the appropriate credentials mark like __<>__ in _src/main/resources/application.properties_
 1. DataSource Configurations
 1. Email Account Details __(You need to allow less sceured app access)__
 1. If your don't want email verification, then use Version 0.1 commit
1. Run the project
