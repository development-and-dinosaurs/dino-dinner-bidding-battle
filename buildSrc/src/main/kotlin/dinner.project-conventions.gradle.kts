import java.util.Base64

plugins {
    id("com.diffplug.spotless")
    id("org.jetbrains.kotlin.jvm")
    `maven-publish`
    signing
}
group = "uk.co.developmentanddinosaurs"

version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.8.22")
}

java {
    withSourcesJar()
    withJavadocJar()
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            pom {
                name.set("Dino Dinner Democracy")
                description.set("Code challenge game from Development and Dinosaurs.")
                url.set("https://github.com/development-and-dinosaurs/dino-dinner-democracy")
                licenses {
                    license {
                        name.set("The MIT License (MIT)")
                        url.set("http://opensource.org/licenses/MIT")
                    }
                }
                developers {
                    developer {
                        name.set("Tyrannoseanus")
                        email.set("tyrannoseanus@developmentanddinosaurs.co.uk")
                    }
                }
                scm {
                    connection.set("scm:git:https://github.com/development-and-dinosaurs/dino-dinner-democracy.git")
                    developerConnection.set("scm:git@github.com:development-and-dinosaurs/dino-dinner-democracy.git")
                    url.set("https://github.com/development-and-dinosaurs/dino-dinner-democracy/")
                }
            }
        }
    }
}


signing {
    val signingKey: String? by project
    val decodedSigningKey = decode(signingKey)
    val signingPassword: String? by project
    useInMemoryPgpKeys(decodedSigningKey, signingPassword)
    sign(publishing.publications["mavenJava"])
}

fun decode(base64Key: String?): String {
    return if (base64Key == null) "" else String(Base64.getDecoder().decode(base64Key))
}

spotless {
    kotlin { ktfmt() }
    kotlinGradle { ktfmt() }
}
