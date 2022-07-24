plugins {
    java
    id("com.diffplug.spotless") version "6.8.0"
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

allprojects {
    // Configure global repositories.
    repositories {
        mavenCentral()
    }
    // Configure global formatting. Applied in this scope to include
    // project root
    apply(plugin = "com.diffplug.spotless")
    spotless {
        java {
            googleJavaFormat().aosp().reflowLongStrings()
            licenseHeaderFile(rootProject.file("LICENSE_HEADER"))
        }
        kotlin {
            ktlint()
            licenseHeaderFile(rootProject.file("LICENSE_HEADER"))
        }
        format("styling") {
            target("**/*.json", "**/*.md")
            prettier().configFile(rootProject.file("./.prettierrc"))
        }
    }
}

subprojects {
    // Define global version
    version = "0.1.0"
    group = "app.hellfire.myplugin"
    // Target Java 17, with Java 19 language features.
    tasks.withType<JavaCompile> {
        // Emit bytecode for JVM 17
        targetCompatibility = JavaVersion.VERSION_17.toString()
        // Support JDK 19 features
        sourceCompatibility = JavaVersion.VERSION_17.toString()
    }
    // Add spotlessApply to task dependencies - this fixes formatting violations on build.
    tasks.build.configure {
        dependsOn.add("spotlessApply")
    }
}

// Disable jar output for root project.
tasks.jar.configure {
    enabled = false
}
// Set build to depend on git hook installation task.
tasks.build.configure {
    dependsOn("installGitHooks")
}
// Installs git hooks into the .git directory.
tasks.register<Copy>("installGitHooks") {
    description = "Installs git hooks from ./scripts/ into the git directory."
    from("${rootProject.rootDir}/scripts")
    into("${rootProject.rootDir}/.git/hooks") {
        fileMode = 775
    }
    // TODO: I am unsure why this also needs to be specified on top of into.
    destinationDir = File(rootProject.rootDir, ".git/hooks")
}
