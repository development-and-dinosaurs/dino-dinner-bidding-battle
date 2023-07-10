plugins { id("dinner.project-conventions") }

publishing {
  publications {
    val mavenPublication = getAt("mavenJava") as MavenPublication
    mavenPublication.pom {
      name.set("Dino Dinner: Bidding Battle Crafty Code Carnivore")
      description.set("Implement the Crafty Code Carnivore to play Dino Dinner: Bidding Battle")
    }
  }
}
