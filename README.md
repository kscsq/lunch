# The voting system to decide where to lunch

Design and implement a REST API using Hibernate/Spring/SpringMVC (or Spring-Boot) without frontend.

The task is:

 * 2 types of users: admin and regular users
 * Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)
 * Menu changes each day (admins do the updates)
 * Users can vote on which restaurant they want to have lunch at
 * Only one vote counted per user
 * If user votes again the same day:
    - If it is before 11:00 we asume that he changed his mind.
    - If it is after 11:00 then it is too late, vote can't be changed

Each restaurant provides new menu each day.

As a result, provide a link to github repository. It should contain the code, README.md with API documentation and couple curl commands to test it.



##Instructions

1. Clone this repository into your local machine in the directory you picked
2. Make sure you set LUNCH_ROOT(p.1 directory) as your environment variable
3. Run from Intellij Idea and make sure "lunch" artifact is successfully deployed and web page with "Loaded" is shown


##Database
HSQLDB embedded database is used so no further installations are expected

##REST commands to be used

###Add new user

####Request

    POST http://localhost:8080/lunch/rest/admin/users
    Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu
    Content-Type: application/json
    
    {    "name": "AddUser",    "email": "AddUser@yandex.ru",    "password": "AddPassword",    "roles": ["ROLE_USER"] }


####Response

    HTTP/1.1 201 
    Cache-Control: no-cache, no-store, max-age=0, must-revalidate
    Pragma: no-cache
    Expires: 0
    X-XSS-Protection: 1; mode=block
    X-Frame-Options: DENY
    X-Content-Type-Options: nosniff
    Location: http://localhost:8080/lunch/rest/admin/users/rest/admin/users/100020
    Content-Type: application/json;charset=UTF-8
    Transfer-Encoding: chunked
    Date: Fri, 01 Jun 2018 12:16:38 GMT
    
    {
      "id": 100020,
      "name": "AddUser",
      "email": "AddUser@yandex.ru",
      "password": "AddPassword",
      "enabled": true,
      "registered": "2018-06-01T12:16:38.254+0000",
      "roles": [
        "ROLE_USER"
      ]
    }
    
    Response code: 201; Time: 459ms; Content length: 164 bytes

###Get all restaurants

####Request

    GET http://localhost:8080/lunch/rest/restaurants/
    Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu
    Content-Type: application/json

####Response

    HTTP/1.1 200 
    Cache-Control: no-cache, no-store, max-age=0, must-revalidate
    Pragma: no-cache
    Expires: 0
    X-XSS-Protection: 1; mode=block
    X-Frame-Options: DENY
    X-Content-Type-Options: nosniff
    Content-Type: application/json;charset=UTF-8
    Transfer-Encoding: chunked
    Date: Wed, 30 May 2018 15:35:28 GMT

    [
      {
        "id": 100002,
        "name": "Restaurant 1",
        "registered": "2018-05-30T15:35:19.704+0000",
        "meals": null
      },
      {
        "id": 100003,
        "name": "Restaurant 2",
        "registered": "2018-05-30T15:35:19.704+0000",
        "meals": null
      },
      {
        "id": 100004,
        "name": "Restaurant 3",
        "registered": "2018-05-30T15:35:19.704+0000",
        "meals": null
      }
    ]

    Response code: 200; Time: 850ms; Content length: 280 bytes

###Get particular restaurant

####Request

    GET http://localhost:8080/lunch/rest/restaurants/100002
    Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu
    Content-Type: application/json

####Response

    HTTP/1.1 200 
    Cache-Control: no-cache, no-store, max-age=0, must-revalidate
    Pragma: no-cache
    Expires: 0
    X-XSS-Protection: 1; mode=block
    X-Frame-Options: DENY
    X-Content-Type-Options: nosniff
    Content-Type: application/json;charset=UTF-8
    Transfer-Encoding: chunked
    Date: Wed, 30 May 2018 15:37:30 GMT
    
    {
      "id": 100002,
      "name": "Restaurant 1",
      "registered": "2018-05-30T15:35:19.704+0000",
      "meals": null
    }
    
    Response code: 200; Time: 141ms; Content length: 92 bytes

###Add new restaurant

####Request

    POST http://localhost:8080/lunch/rest/admin/restaurants/
    Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu
    Content-Type: application/json
    
    {    "name": "NewRestaurant"}

####Response

    HTTP/1.1 201 
    Cache-Control: no-cache, no-store, max-age=0, must-revalidate
    Pragma: no-cache
    Expires: 0
    X-XSS-Protection: 1; mode=block
    X-Frame-Options: DENY
    X-Content-Type-Options: nosniff
    Location: http://localhost:8080/lunch/rest/admin/restaurants/100019
    Content-Type: application/json;charset=UTF-8
    Transfer-Encoding: chunked
    Date: Wed, 30 May 2018 15:39:43 GMT
    
    {
      "id": 100019,
      "name": "NewRestaurant",
      "registered": "2018-05-30T15:39:42.962+0000"
    }
    
    Response code: 201; Time: 421ms; Content length: 80 bytes

###Remove a restaurant

####Request

    DELETE http://localhost:8080/lunch/rest/admin/restaurants/100019
    Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu
    Content-Type: application/json

####Response

    HTTP/1.1 200 
    Cache-Control: no-cache, no-store, max-age=0, must-revalidate
    Pragma: no-cache
    Expires: 0
    X-XSS-Protection: 1; mode=block
    X-Frame-Options: DENY
    X-Content-Type-Options: nosniff
    Content-Length: 0
    Date: Wed, 30 May 2018 15:41:11 GMT
    
    <Response body is empty>
    
    Response code: 200; Time: 111ms; Content length: 0 bytes

###Update a restaurant

####Request

    PUT http://localhost:8080/lunch/rest/admin/restaurants/100002
    Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu
    Content-Type: application/json
    
    {    "name": "Restaurant 1 updated"}

####Response

    HTTP/1.1 200 
    Cache-Control: no-cache, no-store, max-age=0, must-revalidate
    Pragma: no-cache
    Expires: 0
    X-XSS-Protection: 1; mode=block
    X-Frame-Options: DENY
    X-Content-Type-Options: nosniff
    Content-Length: 0
    Date: Wed, 30 May 2018 15:43:29 GMT
    
    <Response body is empty>
    
    Response code: 200; Time: 295ms; Content length: 0 bytes

###Get lunch in a particular restaurant by date

####Request

    GET http://localhost:8080/lunch/rest/restaurants/100003/lunch?date=2018-05-06
    Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu
    Content-Type: application/json

####Response

    HTTP/1.1 200 
    Cache-Control: no-cache, no-store, max-age=0, must-revalidate
    Pragma: no-cache
    Expires: 0
    X-XSS-Protection: 1; mode=block
    X-Frame-Options: DENY
    X-Content-Type-Options: nosniff
    Content-Type: application/json;charset=UTF-8
    Transfer-Encoding: chunked
    Date: Wed, 30 May 2018 15:53:12 GMT
    
    [
      {
        "id": 100010,
        "date": "2018-05-06",
        "description": "second course 2",
        "restaurantId": 100003,
        "price": 500.00
      },
      {
        "id": 100009,
        "date": "2018-05-06",
        "description": "garnish 2",
        "restaurantId": 100003,
        "price": 100.00
      },
      {
        "id": 100008,
        "date": "2018-05-06",
        "description": "first course 2",
        "restaurantId": 100003,
        "price": 500.00
      }
    ]
    
    Response code: 200; Time: 115ms; Content length: 303 bytes

###Get particular meal in a particular restaurant

####Request

    GET http://localhost:8080/lunch/rest/restaurants/100003/meals/100009
    Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu
    Content-Type: application/json

####Response

    HTTP/1.1 200 
    Cache-Control: no-cache, no-store, max-age=0, must-revalidate
    Pragma: no-cache
    Expires: 0
    X-XSS-Protection: 1; mode=block
    X-Frame-Options: DENY
    X-Content-Type-Options: nosniff
    Content-Type: application/json;charset=UTF-8
    Transfer-Encoding: chunked
    Date: Wed, 30 May 2018 15:55:24 GMT
    
    {
      "id": 100009,
      "date": "2018-05-06",
      "description": "garnish 2",
      "restaurantId": 100003,
      "price": 100.00
    }
    
    Response code: 200; Time: 85ms; Content length: 96 bytes

###Add new meal for a particular restaurant

####Request

    POST http://localhost:8080/lunch/rest/admin/restaurants/100003/meals/
    Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu
    Content-Type: application/json
    
    {
      "date": "2018-05-06",
      "description": "garnish added",
      "price": 1000.00
    }

####Response

    HTTP/1.1 201 
    Cache-Control: no-cache, no-store, max-age=0, must-revalidate
    Pragma: no-cache
    Expires: 0
    X-XSS-Protection: 1; mode=block
    X-Frame-Options: DENY
    X-Content-Type-Options: nosniff
    Location: http://localhost:8080/lunch/rest/admin/restaurants/100020
    Content-Type: application/json;charset=UTF-8
    Transfer-Encoding: chunked
    Date: Wed, 30 May 2018 15:57:50 GMT
    
    {
      "id": 100020,
      "date": "2018-05-06",
      "description": "garnish added",
      "price": 1000.00
    }
    
    Response code: 201; Time: 102ms; Content length: 79 bytes

###Update meal for a particular restaurant

####Request

    PUT http://localhost:8080/lunch/rest/admin/restaurants/100003/meals/
    Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu
    Content-Type: application/json
    
    {
      "id": 100020,
      "date": "2018-05-06",
      "description": "garnish added updated",
      "price": 1005.00
    }

####Response

    HTTP/1.1 200 
    Cache-Control: no-cache, no-store, max-age=0, must-revalidate
    Pragma: no-cache
    Expires: 0
    X-XSS-Protection: 1; mode=block
    X-Frame-Options: DENY
    X-Content-Type-Options: nosniff
    Content-Length: 0
    Date: Wed, 30 May 2018 16:01:52 GMT
    
    <Response body is empty>
    
    Response code: 200; Time: 77ms; Content length: 0 bytes

###Delete meal in a particular restaurant

####Request

    DELETE http://localhost:8080/lunch/rest/admin/restaurants/100003/meals/100020
    Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu
    Content-Type: application/json

####Response

    HTTP/1.1 200 
    Cache-Control: no-cache, no-store, max-age=0, must-revalidate
    Pragma: no-cache
    Expires: 0
    X-XSS-Protection: 1; mode=block
    X-Frame-Options: DENY
    X-Content-Type-Options: nosniff
    Content-Length: 0
    Date: Wed, 30 May 2018 16:05:45 GMT
    
    <Response body is empty>
    
    Response code: 200; Time: 64ms; Content length: 0 bytes

###Vote for a particular restaurant

####Request

    POST http://localhost:8080/lunch/rest/profile/restaurants/100002/vote
    Authorization: Basic dXNlckB5YW5kZXgucnU6cGFzc3dvcmQ=
    Content-Type: application/json

####Response

    HTTP/1.1 201 
    Cache-Control: no-cache, no-store, max-age=0, must-revalidate
    Pragma: no-cache
    Expires: 0
    X-XSS-Protection: 1; mode=block
    X-Frame-Options: DENY
    X-Content-Type-Options: nosniff
    Location: http://localhost:8080/lunch/rest/profile/restaurants/100019
    Content-Type: application/json;charset=UTF-8
    Transfer-Encoding: chunked
    Date: Fri, 01 Jun 2018 12:25:18 GMT
    
    {
      "id": 100019
    }
    
    Response code: 201; Time: 432ms; Content length: 13 bytes
    
###Repeat vote for a particular restaurant after cur-off time
    
####Request
    
        POST http://localhost:8080/lunch/rest/profile/restaurants/100002/vote
        Authorization: Basic dXNlckB5YW5kZXgucnU6cGFzc3dvcmQ=
        Content-Type: application/json
    
####Response
    
    HTTP/1.1 304 
    Cache-Control: no-cache, no-store, max-age=0, must-revalidate
    Pragma: no-cache
    Expires: 0
    X-XSS-Protection: 1; mode=block
    X-Frame-Options: DENY
    X-Content-Type-Options: nosniff
    Date: Fri, 01 Jun 2018 12:26:45 GMT
    
    <Response body is empty>
    
    Response code: 304; Time: 82ms; Content length: 0 bytes