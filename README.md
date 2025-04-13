# cs301-crm-project-2024-25t2-301-g3t3-load-testing
## Running Simulations 
Use the task `gatlingRun` to execute Gatling simulations. 
By default, the `gatlingRun` task runs in interactive mode and suggests the simuation class to launch unless:
* there's only one Simulation available,
* or the Simulation class is forced with the `--simulation=<FullyQualifiedClassName>` option,

### To run a simulation 
Linux/MacOS:  
`./gradlew gatlingRun`
Windows:  
`gradlew.bat gatlingRun`

### To run a single simulation by its FQN (fully qualifed class name)
Linux/MacOS:  
`./gradlew gatlingRun --simulation com.project.simu.MySimulation`  
Windows:  
`gradlew.bat gatlingRun --simulation com.project.simu.MySimulation`

### To run all simulations
Linux/MacOS:  
`./gradlew gatlingRun --all`
Windows:  
`gradlew.bat gatlingRun --all`

