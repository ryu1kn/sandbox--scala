
# Scala Sandbox

Whatever Scala experiments comes to this repo

## Run as a command line app

```sh
$ sbt run
```

## Package the app and run with scala command

```sh
$ sbt package
$ scala target/scala-2.12/scalasandbox_2.12-0.1.0-SNAPSHOT.jar
```

## Package the app and run with java command

```bash
$ sbt assembly
$ java -jar target/scala-2.12/ScalaSandbox-assembly-0.1.0-SNAPSHOT.jar
```

It indeed is a FAT jar...

```sh
$ du target/scala-2.12/*.jar
9.6M	target/scala-2.12/ScalaSandbox-assembly-0.1.0-SNAPSHOT.jar
4.0K	target/scala-2.12/scalasandbox_2.12-0.1.0-SNAPSHOT.jar
```

## Test

```sh
$ sbt test
```
