# Lost Pet
Simple Kotlin App for posting lost pets, using a [Clean Architecture](https://8thlight.com/blog/uncle-bob/2012/08/13/the-clean-architecture.html) approach. This defines the inner layers (domain and use case layers), which is actually the app. 
If you want to see a specific fronted using this app check it's Android detail layer [here](https://github.com/drodobyte/lostpet-android).

### Packages
* **Entity**, with basic entities defining business model
* **Service**, abstract service interface defining operations within business model
* **Case**, the **real** app, the use-cases defining what does the app really do. Unit testing is done against the use-cases.
