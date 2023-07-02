# Dino Dinner: Bidding Battle

Welcome to "Dino Dinner: Bidding Battle", a thrilling coding challenge where crafty code carnivores running ancient algorithms compete in a progressive elimination auction to maximise their meat. Strategise your bids to secure the most meat while avoiding elimination, and prove your algorithm's prowess in this intense battle for carnivorous supremacy!

## Objective
The objective of the game is to maximize the amount of meat that your crafty carnivore receives throughout the rounds by strategically bidding against other participants.

## Game Setup
Up to ten participants can take part in the game simultaneously. Each participant should submit their algorithm's jar file before the game starts. You will be provided with a distributed library containing the interface that your algorithm must work with. This library can be found on Maven Central, under `uk.co.developmentanddinosaurs:dino-dinner-bidding-battle-carnivore:<version>`.

## Creating Your Carnivore
Once you've got the carnivore library set up, you can work on creating your crafty carnivore. You can get the full instructions on the README for the carnivore project, so check those out when you're ready. 

## Round Mechanics
The game consists of a number of rounds equal to the number of players. In each round, the crafty carnivores simultaneously place their bids for their share of the current amount of meat.

At the start of the game you will receive information about how many players there are, and how much meat is up for grabs. 

After each round, you will be told how much meat was eaten. 

Carnivores that have been eliminated (by making a successful bid) will not receive further information. 

## Bidding Rules
Crafty carnivores can bid any amount of meat they desire, up to the total amount of meat available in that round.

Your goal is to bid an amount that ensures you end up with the most meat at the end of the game. You win a round by bidding for the least amount of meat that round. After all rounds are complete, the carnivore that secured the most meat is the winner. 

## Revealing Bids
Once all participants have submitted their bids, the bids will be revealed simultaneously.

The crafty carnivore with the smallest bid in each round will be successful in their bid and removed from future rounds. The remaining participants move on to the next round.

## Historical Bidding Data
You are allowed to store historical bidding data within your program, either in memory or using the file system. This data can be used to inform your future bidding decisions. This won't necessarily help in the context of one game, but across thousands of iterations you should be able to make your carnivore craftier. 

## Determining the Winner
The winner of an iteration of the game is the crafty carnivore that received the most meat throughout the game, determined by having the highest winning bid across the rounds.

The coding challenge can simulate several thousand iterations of the game to reveal the overall winner. The first iteration will be shown through the UI, but the ultimate winner will be determined based on the results of the simulation.

## Supported Languages
JVM languages are supported "out of the box", just ensure you build a jar file with your crafty carnivore class that implements the correct interface. You are free to use any other language, as long as you can wrap that in an executable jar. This is (currently) left as an exercise for the reader.

## Submitting Your Algorithm
- Prepare your algorithm by implementing the interface provided in the distributed library.
- Package your algorithm as a jar file.
- Place your jar file in a `carnivores` directory in the same directory as the game jar.

Good luck, and may the craftiest carnivore win!
