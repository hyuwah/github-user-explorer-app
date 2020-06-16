# Github User Explorer

An example / template app

![preview-gif](https://res.cloudinary.com/hyuwah-github-io/image/upload/v1592326323/GithubUserExplorer/init-user-detail.gif)

## Features

- [x] User Search
    - [ ] Pagination
- [x] User Detail
- [ ] User Repo List
- [ ] User Following & Follower List
    - [ ] Pagination
- [ ] Github Authentication for higher rate limit

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
- [x] Jetpack Navigation
- [ ] Room
- [ ] Ktlint
- [ ] Spek


## API

[Github Api](https://developer.github.com/v3/)
- [Search](https://developer.github.com/v3/search/) (rate limit 10 requests per minute)
- [Users](https://developer.github.com/v3/users/) (rate limit 60 requests per hour)