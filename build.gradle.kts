plugins {
    kotlin("jvm") version "1.9.10"
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.squareup.okio:okio:3.5.0")

    implementation("org.jetbrains.kotlinx:multik-core:0.2.2")
    implementation("org.jetbrains.kotlinx:multik-default:0.2.2")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(11)
}

application {
    mainClass.set("MainKt")
}