# AAC Paging Infinite Scrolling With Network Sample

This project is a sample for implementation of Android Architecture Components written in Kotlin. It uses [Jetpack's Paging component](https://developer.android.com/topic/libraries/architecture/paging/) to demonstrate endless scrolling using database + network.

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