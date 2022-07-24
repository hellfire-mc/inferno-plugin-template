plugins {
    java
    id("io.freefair.lombok") version "6.5.0.2"
}
// disable build task
tasks.build {
    enabled = false
}
