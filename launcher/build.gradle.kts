plugins { id("dinner.project-conventions") }

dependencies {
  implementation(project(":dino-dinner-bidding-battle-core"))
  api(libs.gdx.lwjgl3)
  api(libs.gdx.platform) { artifact { classifier = "natives-desktop" } }
  api(libs.gdx.freetype.platform) { artifact { classifier = "natives-desktop" } }
  implementation(libs.ktx.app)
}

tasks.jar {
  duplicatesStrategy = DuplicatesStrategy.EXCLUDE
  manifest {
    attributes["Main-Class"] = "uk.co.developmentanddinosaurs.games.dinner.DesktopLauncherKt"
  }
  from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
}
