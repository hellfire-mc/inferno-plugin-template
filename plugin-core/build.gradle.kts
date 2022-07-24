import com.github.jengelman.gradle.plugins.shadow.tasks.ConfigureShadowRelocation

plugins {
    java
    kotlin("jvm") version "1.7.10"
    id("com.github.johnrengelman.shadow")
}

group = "app.hellfire.myplugin"

repositories {
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots")
}

dependencies {
    compileOnly("org.spigotmc:plugin-annotations:1.2.3-SNAPSHOT")
    annotationProcessor("org.spigotmc:plugin-annotations:1.2.3-SNAPSHOT")
    // Inferno Core
    implementation(project(":plugin-api"))
}

// Set up shadowjar relocation
tasks.create<ConfigureShadowRelocation>("relocateShadowJar") {
    target = tasks.shadowJar.get()
    prefix = "lib"
}
tasks.shadowJar.configure {
    dependsOn("relocateShadowJar")
//    archiveClassifier.set("")
}
tasks.build.configure {
    dependsOn("shadowJar")
}
