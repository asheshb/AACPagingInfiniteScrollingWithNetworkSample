# AAC Paging Infinite Scrolling With Network Sample

This project is a sample for implementation of Clean Architecture written in Kotlin. It uses [Jetpack's Paging component](https://developer.android.com/topic/libraries/architecture/paging/) to demonstrate endless scrolling using database + network.

## What you will learn?

1. **Clean Architecture**: Design an app using layered architecture based on [Clean Architecture by Uncle Bob](http://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html). 
2. **Paging library**: Learn how to use PagedList callbacks to coordinate between local database and fetching more data from network.
3. **Navigation component**: At last it's settled, single activity is what's Google recommend now. Navigation editor make things easy for us to design navigation path of our app. 
4. **Architecture components**: The good old tristar LiveData, ViewModel and Room.
5. **Kotlin**: We all love it. Learn how to use it to develop an Android app.
6. **Dagger 2**: Dependency injection help us to abstract the creation of a dependency. Dagger 2 further helps to manage the dependencies in an optimal way.

## Pre-requisites
Android Studio 3.2 or higher

## Getting Started
The app requires an API key from The Movie Database (TMDB) which you can get using free registration. [https://www.themoviedb.org/settings/api](https://www.themoviedb.org/settings/api)

You can add the key in your global `.gradle/gradle.properties` file. 

Ex: `TMDB_API_KEY="YOUR-API-KEY"`

**OR**

you can replace the `TMDB_API_KEY` in `build.gradle (Module: app)`. 

Ex: `it.buildConfigField 'String', 'TMDB_API_KEY', "\"YOUR-API-KEY\""`

## Screenshot

![AAC Paging Infinite Scrolling With Network Sample Screenshot](art/aacpaginginfinitescrollingwithnetworksample-screenshot.png?raw=true)

## Article

Here is an article providing the overview of the sample. 

[https://medium.com/@asheshb/android-architecture-components-and-paging-library-in-kotlin-infinite-scrolling-with-network-c75b6cd183c0](https://medium.com/@asheshb/android-architecture-components-and-paging-library-in-kotlin-infinite-scrolling-with-network-c75b6cd183c0)


## Author

### Ashesh Bharadwaj

Android app developer and amateur photographer

<a href="https://www.linkedin.com/in/asheshb/"><img src="art/linkedin-logo.png?raw=true" alt="LinkedIn"></a>
<a href="https://twitter.com/asheshbt"><img src="art/twitter-logo.png?raw=true" alt="Twitter"></a>



    Copyright 2018 Ashesh Bharadwaj

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
