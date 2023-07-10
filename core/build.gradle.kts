plugins { id("dinner.project-conventions") }

dependencies {
  api(libs.gdx.core)
  implementation(libs.gdx.freetype.core)
  implementation(libs.ktx.actors)
  implementation(libs.ktx.app)
  implementation(libs.ktx.assets)
  implementation(libs.ktx.async)
  implementation(libs.ktx.freetype)
  implementation(libs.ktx.inject)
  implementation(libs.ktx.scene2d)
  implementation(libs.ktx.style)
  implementation(libs.kotlinx.coroutines)
  implementation(project(":dino-dinner-bidding-battle-crafty-code-carnivore"))
}

publishing {
  publications {
    val mavenPublication = getAt("mavenJava") as MavenPublication
    mavenPublication.pom {
      name.set("Dino Dinner: Bidding Battle Core")
      description.set("Core game logic for Dino Dinner: Bidding Battle")
    }
  }
}
