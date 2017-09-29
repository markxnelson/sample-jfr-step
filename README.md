# Java Flight Recorder Wercker Step Sample

This project demonstrates how to use the Java Flight Recorder Wercker Step to collect a
recording of your application's execution. 


## Usage

The example `wercker.yml` below demonstrates how you can use the Java Flight Recorder Wercker Step to run the `SampleApplication` and conduct a recrording. 

In this example, we set the global timeout to two minutes.  If the application has not finished in two minutes (which it will not have because it runs for five minutes) then the application and the recording will both end at that point, i.e. after two minutes elapsed time. 

Some of the other parameters are demonstrated also, e.g. the `delay` parameter which will delay the start of the recording, giving the application time to get itself into the state we wish to observe.  The example application will allocate some objects on the heap during this startup time.


``` 
box: 
  id: store/oracle/serverjre
  username: $DOCKER_USERNAME
  password: $DOCKER_PASSWORD
  tag: 8

test-timeout:
  steps:
    - markxnelson/java-flight-recorder:
      timeout: 2m
      application: com.oracle.sample.SampleApplication
      classpath: /pipeline/source/build/libs/sample-jfr-step.jar
      filename: myrecording.jfr
      experimental: true
      duration: 4m
      delay: 30s 
      maxsize: 10M
      maxage: 1h
```

When the build is finished, the output will be available in the file `myrecording.jfr`, as requested.  You can open the recording in Java Mission Control as in the example below:

![sample screen](doc/jfr-sample.jpg)