# Github User Explorer

An example / template app

<p align="center">
  <img src="https://res.cloudinary.com/hyuwah-github-io/image/upload/v1592448170/GithubUserExplorer/init-user-social-optimized.gif" width="270" alt="User Search">
  <img src="https://res.cloudinary.com/hyuwah-github-io/image/upload/v1592830712/GithubUserExplorer/init_repo_list_optimizied.gif" width="270" alt="User Repo">
</p>

## Features

- [x] User Search
    - [ ] Pagination
- [x] User Detail
- [x] User Repo List
- [x] User Following & Follower List
    - [ ] Pagination
- [ ] Github Authentication for higher rate limit
- [ ] Favorite / Bookmark User

## Tech Stack

### Architecture

MVVM + Repository pattern

(Diagram & Explanation here...)

### Libraries

- [x] [LiveData](https://developer.android.com/topic/libraries/architecture/livedata)
- [x] [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
- [x] [Coroutines](https://developer.android.com/topic/libraries/architecture/coroutines)
- [x] [Retrofit](https://square.github.io/retrofit/)
- [x] [View Binding](https://developer.android.com/topic/libraries/view-binding)
- [x] [Dagger Hilt](https://developer.android.com/training/dependency-injection/hilt-android)
- [x] [Coil](https://github.com/coil-kt/coil)
- [x] [Chucker](https://github.com/ChuckerTeam/chucker)
- [x] [Jetpack Navigation](https://developer.android.com/guide/navigation/navigation-getting-started)
- [ ] Room
- [ ] Ktlint
- [ ] Spek


## API

[Github Api](https://developer.github.com/v3/)
- [Search](https://developer.github.com/v3/search/) (rate limit 10 requests per minute)
- [Users](https://developer.github.com/v3/users/) (rate limit 60 requests per hour)