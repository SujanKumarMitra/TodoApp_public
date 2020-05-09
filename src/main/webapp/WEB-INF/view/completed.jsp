<%@ page contentType="text/html" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">

<head>
  <!-- Required meta tags -->
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <script type="text/javascript"
    src="https://cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.0.0-alpha14/js/tempusdominus-bootstrap-4.min.js"></script>
  <link rel="stylesheet"
    href="https://cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.0.0-alpha14/css/tempusdominus-bootstrap-4.min.css" />
  <!-- Bootstrap CSS -->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
    integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
  <link rel="icon" href="/images/icon.png" type="image/png">
  <link rel="stylesheet" href="/css/dashboard.css">
  <link rel="stylesheet" href="/css/alert.css">

  <title>Missed Todos</title>
</head>

<body>
  <!-- navbar -->
  <nav class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0 shadow">
    <a class="navbar-brand col-md-3 col-lg-2 mr-0 px-3" href="/todo">To Do App</a>
    <button class="navbar-toggler position-absolute d-md-none collapsed" type="button" data-toggle="collapse"
      data-target="#sidebarMenu" aria-controls="sidebarMenu" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <ul class="navbar-nav px-3">
      <li class="nav-item text-nowrap">
        <a class="btn btn-outline-primary btn-sm" href="/profile">Profile</a>
        <a class="btn btn-outline-danger btn-sm" href="/logout">Log out</a>
      </li>
    </ul>
  </nav>
  <!-- navbar ends -->
  <div class="container-fluid">

    <div class="row">
      <nav id="sidebarMenu" class="col-md-2 col-lg-2 d-md-block bg-light sidebar collapse">
        <div class="position-sticky pt-3">
          <ul class="nav flex-column">
            <li class="nav-item">
              <a class="nav-link" aria-current="page" href="/todo/upcoming">
                <span data-feather="home"></span>
                Upcoming
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link active" href="/todo/completed">
                <span data-feather="file"></span>
                Completed
              </a>
            <li class="nav-item">
              <a class="nav-link" href="/todo/missed">
                <span data-feather="shopping-cart"></span>
                Missed
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="/todo/add">
                <span data-feather="shopping-cart"></span>
                Add a Todo
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link"
                onclick="if(!confirm('Are you sure you want to mark all Todos complete?')) return false;"
                href="/todo/markComplete/all">
                <span data-feather="shopping-cart"></span>
                Mark all Pending Complete
              </a>
            </li>
          </ul>

        </div>
      </nav>

      <main class="col-md-9 ml-sm-auto col-lg-10 px-md-4 bg-da">
        <div
          class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
          <h1 class="h2">Completed To Dos</h1>
        </div>
        <div class="error">${error}</div>
        <div class="success">${success}</div>
        <div class="warning">${warning}</div>
        <c:if test="${todos.size() == 0 }">
          <h3 class="text-center">
            No Completed ToDos.
          </h3>
        </c:if>
        <div class="row">
          <c:forEach var="todo" items="${todos}">
            <div class="col-sm-4">
              <div class="card text-white bg-success mb-3" style="max-width: 18rem;">
                <div class="card-header">Deadline: ${dateFormatter.format(todo.deadLine)}</div>
                <div class="card-body">
                  <h5 class="card-title">${todo.heading}</h5>
                  <p class="card-text">${todo.detail}</p>
                  <a href="/todo/delete?id=${todo.id}"
                    onclick="if(!confirm('Are you sure you want to delete this Todo?')) return false;"
                    class="btn btn-danger btn-sm btn-block">Delete</a>
                </div>
              </div>
            </div>

            <!-- another col -->
          </c:forEach>
        </div>
      </main>
    </div>
  </div>

  <!-- Optional JavaScript -->
  <!-- jQuery first, then Popper.js, then Bootstrap JS -->
  <script src="https://cdnjs.cloudflare.com/ajax/libs/feather-icons/4.24.1/feather.min.js"
    integrity="sha384-EbSscX4STvYAC/DxHse8z5gEDaNiKAIGW+EpfzYTfQrgIlHywXXrM9SUIZ0BlyfF"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.3/Chart.min.js"
    integrity="sha384-i+dHPTzZw7YVZOx9lbH5l6lP74sLRtMtwN2XjVqjf3uAGAREAF4LMIUDTWEVs4LI"></script>
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