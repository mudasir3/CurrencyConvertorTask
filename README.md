# Currency Convertor-Coding Task 

A currency converter app that allows a user to view a given amount in a given currency converted into other currencies.
The app is built using [Open Excahnge Rates Service](https://openexchangerates.org/)<br>

# Description
This is a single page app with a textfield to enter amount, a dropdown to select a currency and a grid view to show all other currencies

# Android versions
>The minimum Android SDK version is 24
>The compile SDK version is 33
>The target SDK version is 33

# Main Features
- Currencies List
- Search Currency functionality
- Conversion into multiple currencies 
- Local persistence
- Offline working capabilities

# Architecture
- Clean Architecture with MVVM


## Built With 🛠
- [Kotlin](https://kotlinlang.org/) - First class and official programming language for Android development.
- [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - For asynchronous and more..
- [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - Data objects that notify views when the underlying database changes.
- [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores UI-related data that isn't destroyed on UI changes.
- [Dependency Injection](https://developer.android.com/training/dependency-injection)
- [koin](https://insert-koin.io/docs/quickstart/android/) - Easier way to incorporate Koin DI into Android apps.
- [Retrofit](https://square.github.io/retrofit/) - A type-safe HTTP client for Android.
- [Room](https://developer.android.com/jetpack/androidx/releases/room) - Android Jetpack Library for Local persistence.
- [Material Components for Android](https://github.com/material-components/material-components-android) - Modular and customizable Material Design UI components for Android.

# Testing
- UseCaseTest - this test class was written to test functions in CurrencyConvertorUseCase.kt file under other package
- DaoTest - contains test cases for Local persisitence operations functionality
- LocalRepositoryTest - this test class was written to test functions in LocalCurrencyRepository.kt file under other package
- HttpRepositoryTest - this test class was written to test functions in HttpCurrencyRepository.kt file under other package



