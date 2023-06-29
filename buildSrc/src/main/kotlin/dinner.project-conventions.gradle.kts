plugins {
    id("org.jetbrains.kotlin.jvm")
    id("com.diffplug.spotless")
}

version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.8.22")
}

spotless {
    kotlin { diktat() }
    kotlinGradle { diktat() }
}
