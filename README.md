 Android Jetpack Compose Paginated List Optimization
 ----------------------------------------------------------

Description:
==================
This repository hosts a simple Android application designed to fetch data from a public API endpoint and display it in a paginated list format. The application aims to optimize performance by implementing efficient data management and rendering techniques.

Data Fetching and Rendering:
==============================

To implement pagination support for the JSONPlaceholder API (https://jsonplaceholder.typicode.com/posts), you can use the following query parameters:
Endpoint: https://jsonplaceholder.typicode.com/posts

Query Parameters:
========================
_page: Specifies the page number of the results.
_limit: Specifies the number of items per page.
For example, to retrieve the first page of posts with 10 posts per page, you would make the request like this:
 https://jsonplaceholder.typicode.com/posts?_page=1&_limit=10
This would return the first 10 posts. Adjust the _page parameter to navigate through different pages, and the _limit parameter to change the number of posts per page.

API Documentation:
=======================
For API interactions, the application utilizes the JSONPlaceholder API. Further details on API usage can be found in the JSONPlaceholder API Documentation.

https://github.com/shivayogih/AssignmentApp/assets/25000572/b6449e0c-1e9f-4b0f-88b6-bc9451dc04f2

![pagination_img1](https://github.com/shivayogih/AssignmentApp/assets/25000572/6bb6a09a-0c19-4325-baaa-fd071b61c583)

![pagination_img2](https://github.com/shivayogih/AssignmentApp/assets/25000572/4d22c493-77c6-4285-88ad-bc24fc4a4487)




Additional Notes:
======================
Feel free to explore the codebase and contribute to enhancing the application's efficiency and performance. Any feedback or suggestions are highly appreciated.
