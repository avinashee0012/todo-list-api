# [COMPLETE] Todo List API

A RESTful API to allow users to manage their to-do list. The previous backend projects have only focused on the CRUD operations, but this project implements user authentication as well.

### Goals
- User authentication
- Schema design and Databases
- RESTful API design
- CRUD operations
- Error handling
- Security

![Data Flow](images/data_flow.png)

### Technologies Used:
- **Backend:** Java, Springboot, MySQL, Spring Data JPA, Rest API, Authentication, Input Validation
- **Source Code Management:** Git, GitHub, Maven, Swagger

### Installation
1. Clone the repo
```bash
    git git@github.com:avinashee0012/todo-list-api.git
    cd todo-list-api
```
2. Setup MySQL
    - [Install and Run MySQL](https://dev.mysql.com/doc/mysql-getting-started/en/)
    - Locate MySQL Port, Username and Password
    - Create a database for application

3. Run Jar
```bash
    # Default MySQL port is 3306
    # The default MySQL username is "root", and by default, it has no password on a fresh installation.
    java -jar todo-list-api-0.0.1.jar --spring.datasource.url=jdbc:mysql://localhost:<MySQL_PORT>/<DATABASE_NAME> --spring.datasource.username=<MYSQL_USERNAME> --spring.datasource.password=<MySQL_PASSWORD>
```
4. Visit http://localhost:8080/swagger-ui/index.html

NOTE: 
- If port 8080 is busy, another port will be used by Tomcat and can be found from terminal logs (see example below):
![Tomcat_Port_Find_Screenshot](images/Tomcat_Port_Find_Screenshot.png)

### Usage Example
- <code>/</code> --> GET (Get Server status)
- <code>/v1.0/api</code> --> GET (Get API status)
- <code>/v1.0/api/register</code> --> POST (Register User)
- <code>/v1.0/api/login</code> --> POST (Login User)
- <code>/v1.0/api/user/tasks</code> --> POST (Create Task)
- <code>/v1.0/api/user/tasks?page=1&limit=5</code> --> GET (Get all tasks in paginated form)
- <code>/v1.0/api/user/tasks</code> --> GET (Get all tasks in paginated form. Default page = 0, limit = 10)
- <code>/v1.0/api/user/tasks/{id}</code> --> GET (Get task by Id, if created by logged-in user)
- <code>/v1.0/api/user/tasks/{id}</code> --> PUT (Update task by Id, if created by logged-in user)
- <code>/v1.0/api/user/tasks/{id}</code> --> DELETE (Delete task by Id, if created by logged-in user)
- <code>/v1.0/api/logout</code> --> GET (Logout user)
_____

### Future releases
- Implement filtering and sorting for the to-do list
- Implement unit tests for the API
- Implement rate limiting and throttling for the API
- Implement refresh token mechanism for the authentication

####
Project Idea: [roadmap.sh](https://roadmap.sh/projects/todo-list-api)