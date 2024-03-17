plugins {
    application
    id("xyz.jpenilla.run-paper") version "2.2.2"
    id("com.github.johnrengelman.shadow") version "8.1.1"
}


val author by extra("sharp378")
val mcApiVersion by extra("1.20.4")
val mcApiPartialVersion by extra(mcApiVersion.substring(0, mcApiVersion.lastIndexOf('.')))
val website by extra("https://github.com/sharp378/Servinator")

description = "Shuts down the server if there are no players online"
version = "1.0.0"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    implementation(libs.guava)
    compileOnly("io.papermc.paper:paper-api:1.20.4-R0.1-SNAPSHOT")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

application {
    mainClass.set("com.github.sharp378.paper.servinator.ServinatorPlugin")
}

tasks {
    processResources {
	filesMatching("plugin.yml") {
	    expand(project.properties)
	}
    }

    runServer {
        minecraftVersion(mcApiVersion)
    }
}

