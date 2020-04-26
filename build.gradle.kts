import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    java
    id("org.springframework.boot") version "2.2.5.RELEASE"
}

allprojects {
    apply {
        plugin("java")
    }

    group = "ru.relex"
    version = "1.0"

    configure<JavaPluginConvention> {
        sourceCompatibility = JavaVersion.VERSION_11
    }
    repositories {
        mavenCentral()
    }

    dependencies {
        implementation("ru.relex:charity-common:1.0")
    }
}

dependencies {
    implementation(project(":data"))

    implementation(project(":services"))
    implementation(project(":security"))

    implementation("org.springframework.boot:spring-boot-starter-web:2.2.5.RELEASE")
}

springBoot {
    mainClassName = "ru.relex.charity.rest.CharityApp"
}

val bootJar: BootJar by tasks

bootJar.apply {
    launchScript()
}
