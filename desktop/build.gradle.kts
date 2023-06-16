plugins {
    id("dinner.project-conventions")
}

dependencies {
    implementation(project(":core"))
    api(libs.gdx.lwjgl3)
    api(libs.gdx.platform) { artifact { classifier = "natives-desktop" } }
    api(libs.gdx.freetype.platform) { artifact { classifier = "natives-desktop" } }
}

tasks.register("dist", Jar::class) {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    manifest {
        attributes["Main-Class"] = "uk.co.developmentanddinosaurs.games.dinner.DesktopLauncherKt"
    }
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
    with(tasks.jar.get())
}
