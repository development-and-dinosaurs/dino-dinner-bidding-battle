plugins {
  id("io.github.gradle-nexus.publish-plugin") version "2.0.0-rc-1"
  id("org.panteleyev.jpackageplugin") version "1.5.2"
  `maven-publish`
  signing
}

group = "uk.co.developmentanddinosaurs"
version = "0.0.1"

nexusPublishing {
  repositories {
    sonatype {
      nexusUrl.set(uri("https://s01.oss.sonatype.org/service/local/"))
      snapshotRepositoryUrl.set(uri("https://s01.oss.sonatype.org/content/repositories/snapshots/"))
    }
  }
}
