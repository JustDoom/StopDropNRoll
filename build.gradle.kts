plugins {
    `java-library`
    `maven-publish`
    id("com.gradleup.shadow") version "8.3.5"
    id("xyz.jpenilla.run-paper") version "2.3.1"
}

group = "com.imjustdoom.stopdropnroll"

repositories {
    maven {
        name = "papermc"
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21.4-R0.1-SNAPSHOT")
    implementation("org.bstats:bstats-bukkit:3.1.0")
}
publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
    }
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}

tasks {
    shadowJar {
        dependencies {
            include(dependency("org.bstats:bstats-bukkit:3.1.0"))
            include(dependency("org.bstats:bstats-base:3.1.0"))
        }
        relocate("org.bstats", project.group.toString() + ".bstats")
        archiveFileName.set("${rootProject.name}-paper-${rootProject.version}.jar")
    }

    processResources {
        val pluginName = rootProject.name
        val pluginVersion = rootProject.version
        val pluginGroup = project.group

        filesMatching("**/plugin.yml") {
            expand(
                "name" to pluginName,
                "version" to pluginVersion,
                "group" to pluginGroup
            )
        }
    }

    runServer {
        minecraftVersion("1.21.4")
        dependsOn(shadowJar)
    }

    build {
        dependsOn(shadowJar)
    }
}