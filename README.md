# Reset/Change Password Application

The service provides web page that user can change/reset password based on email.

### Prerequisites

* [Java 11 or 14 Runtime](https://www.oracle.com/java/technologies/downloads/)
* [Docker](https://docs.docker.com/get-docker/)

### Execution

First, unzip file:

1. Start Postgres Docker image, create database&table and add data from SQL file:

    ```
    cd ResetPassword/sql
    ./start_postgres.sh
    ```
 
2. Check data in database&table on command(optional):

    ```
    docker exec -it pg-docker psql -U postgres
    \c dev \d users
    select * from users;   
    ```

3. Navigate the root of this project via command and execute it on command:

    ```
    cd ..
    mvn spring-boot:run
    ```
    
4. Open browser and type `localhost:8080` to access to password change webpage:

    Validation criteria,
    - All fields not allow empty
    - Check input email format: xxxxx@domain.com
    - Check input password format: must contain at least 1 number, 1 uppercase and 1 lowercase letter and at least 8 or more characters
    - Check new password and confirm password match
    - Check email/user is existed in database; otherwise, rediect to error page

    ![](/resetpassword.png)
   
 5. Give some test data in `USERS` table:
    - Matilda  reset.password@gmail.com
    - Rosie    lovelyrosie@gmail.com
    - Gavin    gavin.real@gmail.com

*Note: login is not implemented yet, return with 302 error, I was trying to fix it but just realise this is not the part of our specification :)) That's it!*

