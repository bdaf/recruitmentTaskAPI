# Requirement Task - Bridge (Wrapper) for Github API.

Using this application after provided login of GitHub user we can get his all repos which are not forks. Information, which we can get in the response, is:

 - Repository Name
 - Owner Login
 - For each branch it’s name and last commit sha

<h2>Endpoint address and example of response.</h2>

<h3>Endpoint link:</h3>

    http://localhost:8080/github/repositories/bdaf


<h3>Response:</h3>

<img width="941" alt="image" src="https://github.com/bdaf/recruitmentTaskAPI/assets/39047457/55d147b6-0279-48d4-8108-7da7585a372c">

<h2>Exceptions in responses.</h2>

With not valid "Accept" header (which value should be "application/json") we will get following error response with 406 status code:
<img width="951" alt="image" src="https://github.com/bdaf/recruitmentTaskAPI/assets/39047457/1b101b01-c0f8-4508-a939-3eae2bcb9b14">


If login of GitHub user will not be found, following error response will occur:
<img width="966" alt="image" src="https://github.com/bdaf/recruitmentTaskAPI/assets/39047457/2e831fe2-0b9f-4804-a387-a33c3d44e10e">

