## Kalah Game

### Description
Api application that emulate a Kalah game engine.

### Build and Run

##### Local
- Install dependencies with maven `mvn install`
- Execute main class `KalahApplication`

##### Production
- Build application using maven `mvn clan install`
- Execute generated jar `target\kalah-0.0.1-SNAPSHOT.jar`

### Architecture
This application is divided in layers:
- Controller: API endpoint definitions
- Service: Loading, validating and saving information on database
- Repository: Interface with database, executing operations as reads and writes
- DTO (Data transfer object pattern): Keep data to transfer to other layers and api response
- Model (Domain driven design): Keep data and is responsible to change its own state