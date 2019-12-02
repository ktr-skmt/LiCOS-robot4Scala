# LiCOS-robot4Scala

[![Build Status](https://travis-ci.org/ktr-skmt/LiCOS-robot4Scala.svg?branch=master)](https://travis-ci.org/ktr-skmt/LiCOS-robot4Scala)
[![codecov](https://codecov.io/gh/ktr-skmt/LiCOS-robot4Scala/branch/master/graph/badge.svg)](https://codecov.io/gh/ktr-skmt/LiCOS-robot4Scala)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://github.com/ktr-skmt/LiCOS-robot4Scala/blob/master/LICENSE)
[![CircleCI](https://circleci.com/gh/ktr-skmt/LiCOS-robot4Scala.svg?style=svg)](https://circleci.com/gh/ktr-skmt/LiCOS-robot4Scala)

## API

### The latest version

https://ktr-skmt.github.io/LiCOS-robot4Scala/0.0.1/api/index.html

### Old versions

https://ktr-skmt.github.io/LiCOS-robot4Scala/index.html

## Import

### SBT

```scala
resolvers += "LiCOS-robot4Scala-snapshots-repository" at "https://github.com/ktr-skmt/LiCOS-robot4Scala/raw/master/maven-repo/snapshots"
```

```scala
libraryDependences += "online.licos" % "licos-robot4scala_2.12" % "0.0.1"
```

### Gradle

```javascript
repositories {
    maven {
        url "https://github.com/ktr-skmt/LiCOS-robot4Scala/raw/master/maven-repo/snapshots"
    }
}
```

```javascript
dependencies {
    compile group: 'online.licos', name: 'licos-robot4scala_2.12', version: '0.0.1'
}
```

### Maven

```xml
<repository>
  <id>LiCOS-robot4Scala-snapshots-repository</id>
  <url>https://github.com/ktr-skmt/LiCOS-robot4Scala/raw/master/maven-repo/snapshots</url>
</repository>
```

```xml
<dependency>
  <groupId>online.licos</groupId>
  <artifactId>licos-robot4scala_2.12</artifactId>
  <version>0.0.1</version>
</dependency>
```

### Ivy

```xml
<ivysettings>
    <settings defaultResolver="chain"/>
    <resolvers>
        <chain name="chain">
            <ibiblio name="LiCOS-robot4Scala-snapshots-repository" m2compatible="true" root="https://github.com/ktr-skmt/LiCOS-robot4Scala/raw/master/maven-repo/snapshots"/>
        </chain>
    </resolvers>
</ivysettings>
```

```xml
<dependency org="online.licos" name="licos-robot4scala_2.12" rev="0.0.1"/>
```
