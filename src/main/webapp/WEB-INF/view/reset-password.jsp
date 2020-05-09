<%@ page contentType="text/html"  %>
<!doctype html>
<html lang="en">

<head>
  <!-- Required meta tags -->
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

  <!-- Bootstrap CSS -->
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
    integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

  <link rel="stylesheet" href="/css/login-signup.css">
  <link rel="icon" href="/images/icon.png" type="image/png">
  <link rel="stylesheet" href="/css/alert.css">
  <title>Sign up</title>
</head>

<body class="text-center">

  <form class="form-signin" action="resetPassword" method="post">
    <label hidden for="userId" class="">User Id</label>
    <input hidden type="number" name="id" value="${user.id}" id="userId" class="form-control" required>
    <br />
    <label hidden for="firstName" class="">First Name</label>
    <input hidden size="50" type="text" name="firstName" value="${user.firstName}" id="firstName" class="form-control"
      required>
    <br />
    <label hidden for="lastName" class="">Last Name</label>
    <input hidden type="text" name="lastName" value="${user.lastName}" id="lastName" class="form-control" required>
    <br />
    <label hidden for="email" class="">Email</label>
    <input hidden type="email" name="email" value="${user.email}" id="lastName" class="form-control" required>
    <br />
    <div class="error">${error}</div>
    <div class="warning">${warning}</div>
    <div class="success">${success}</div>
    <h1 class="h3 mb-3 font-weight-normal">Please enter new password:</h1>
    <label for="password" class="">Password</label>
    <input type="password" name="password" id="lastName" class="form-control" required>
    <br />
    <button class="btn btn-lg btn-primary btn-block" type="submit">Update</button>
    <br />
  </form>

  <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
    integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
    crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
    integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
    crossorigin="anonymous"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
    integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
    crossorigin="anonymous"></script>
</body>

</html>