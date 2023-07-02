# Dino Dinner Democracy - Crafty Code Carnivore

So you've read the information in the main [README](../README.md), and suffice to say, you're interested, right?

This is where you want to be next, figuring out how you're going to create your Crafty Code Carnivore to take part in
the challenge.

## Picking a Hat

We need to get the most important part out of the way first, and that is picking a hat for your carnivore to wear.
The `-carnivore` distributable comes with an assortment of hats for you to choose from.

After implementing the `CraftyCodeCarnivore` interface, you'll want to do something like this to choose a hat:

```kotlin
class Tyrannoseanus : CraftyCodeCarnivore {
  override fun hat() = CarnivoreHat.TOP_HAT
}
```

| Banana Skin                                                     | Chef Hat                                                  | Cowboy Hat                                                    | Four Leaf Clover                                                          | Graduation Cap                                                        |
|-----------------------------------------------------------------|-----------------------------------------------------------|---------------------------------------------------------------|---------------------------------------------------------------------------|-----------------------------------------------------------------------|
| ![Banana Skin](src/main/resources/sprites/hats/banana_skin.png) | ![Chef Hat](src/main/resources/sprites/hats/chef_hat.png) | ![Cowboy Hat](src/main/resources/sprites/hats/cowboy_hat.png) | ![Four Leaf Clover](src/main/resources/sprites/hats/four_leaf_clover.png) | ![Graduation Cap](src/main/resources/sprites/hats/graduation_cap.png) |

| Paper Hat                                                   | Party Hat                                                   | Santa Hat                                                   | Sombrero                                                  | Top Hat                                                 |
|-------------------------------------------------------------|-------------------------------------------------------------|-------------------------------------------------------------|-----------------------------------------------------------|---------------------------------------------------------|
| ![Paper Hat](src/main/resources/sprites/hats/paper_hat.png) | ![Party Hat](src/main/resources/sprites/hats/party_hat.png) | ![Santa Hat](src/main/resources/sprites/hats/santa_hat.png) | ![Sombrero](src/main/resources/sprites/hats/sombrero.png) | ![Top Hat](src/main/resources/sprites/hats/top_hat.png) |

Think long and hard about this choice, as it's the most important one you'll make.

## Picking a Colour 

Next up you're going to want to pick what colour of dinosaur you're going to be playing as. 

After implementing the `CraftyCodeCarnivore` interface, you'll want to do something like this to choose a colour:

```kotlin
class Tyrannoseanus : CraftyCodeCarnivore {
  override fun colour() = CarnivoreColour.ORANGE
}
```

You get a virtual rainbow of colours to choose from:

| Red                                                                       | Orange                                                                          | Yellow                                                                          | Green                                                                         |
|---------------------------------------------------------------------------|---------------------------------------------------------------------------------|---------------------------------------------------------------------------------|-------------------------------------------------------------------------------|
| ![Red Carnivore](src/main/resources/sprites/carnivores/carnivore_red.png) | ![Orange Carnivore](src/main/resources/sprites/carnivores/carnivore_orange.png) | ![Yellow Carnivore](src/main/resources/sprites/carnivores/carnivore_yellow.png) | ![Green Carnivore](src/main/resources/sprites/carnivores/carnivore_green.png) |

| Blue                                                                       | Dark Blue                                                                          | Indigo                                                                          | Violet                                                                         |
|----------------------------------------------------------------------------|------------------------------------------------------------------------------------|---------------------------------------------------------------------------------|--------------------------------------------------------------------------------|
| ![Red Carnivore](src/main/resources/sprites/carnivores/carnivore_blue.png) | ![Orange Carnivore](src/main/resources/sprites/carnivores/carnivore_dark_blue.png) | ![Yellow Carnivore](src/main/resources/sprites/carnivores/carnivore_indigo.png) | ![Green Carnivore](src/main/resources/sprites/carnivores/carnivore_violet.png) |

## Crafting Your Code

Last and quite possibly least is writing the code for your algorithm to take part in the game. But you should probably do it so that everyone can see your colour and hat combination for your carnivore. 

For this, you just need to implement the last three functions available to you from the `CraftyCodeCarnivore` interface. 

You use `initialise` to set your code up for the first round. You'll be told how many people are playing and how much meat is up for grabs. 

Next is `bid`, where you return an `Int` for the amount of meat you want this round. 

Lastly we've got `update`, which is called once per round with the amount of meat eaten that round. Use this to keep track of how many players are left and how much meat is left.

```kotlin
class Tyrannoseanus : CraftyCodeCarnivore {

  private var playersRemaining by Delegates.notNull<Int>()
  private var meatRemaining by Delegates.notNull<Int>()
  private val random = Random.Default
  
  override fun initialise(numberOfPlayers: Int, sizeOfMeat: Int) {
    playersRemaining = numberOfPlayers
    meatRemaining = sizeOfMeat
  }

  override fun update(meatEaten: Int) {
    playersRemaining -= 1
    meatRemaining -= meatEaten
  }

  override fun bid(): Int =
    if (playersRemaining == 1) {
      meatRemaining
    } else {
      random.nextInt(meatRemaining / (playersRemaining + 1) until meatRemaining / (playersRemaining - 1))
    }
}
```

This has been a quick getting started guide, you can find a lot more information in the KDoc included with the distributed library, so make sure you give that a read too. 
