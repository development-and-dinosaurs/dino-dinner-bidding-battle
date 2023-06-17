plugins {
    id("dinner.project-conventions")
}

dependencies {
    api(libs.gdx.core)
    implementation(libs.gdx.freetype.core)
    implementation(libs.ktx.app)
    implementation(libs.ktx.assets)
    implementation(libs.ktx.inject)
    implementation(libs.ktx.scene2d)
    implementation(project(":carnivore"))
}
