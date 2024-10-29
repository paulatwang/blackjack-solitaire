# Black Solitaire in Java
CIT 5910 HW 4

Completed with no collaborators.  

This is a Java-based implementation of [Blackjack Solitaire](https://www.solitairenetwork.com/solitaire/blackjack-square-solitaire-game.html). Blackjack Solitaire is a one-player game with scoring rules inspired by the casino game of Blackjack, where players attempt to build a hand with a value as close to 21 as possible without exceeding 21.


## Gameplay
In this game, playing cards are played onto a grid with four rows and five columns. Each available position in the grid is denoted by a number from 1-16. Cards are drawn one at a time from a standard deck of 52, and the player must choose a location for each card before proceeding. Once a card has been placed on the grid, the card cannot be moved. There are four special discard spots that allow cards placed there to be ignored–they will not contribute to the final scoring. This leaves 16 spaces for cards to be placed where they will be counted in the final score. The number of cards played as well as the number of cards discarded will be shown at each play. 

<img width="358" alt="Screenshot 2024-10-29 at 16 09 39" src="https://github.com/user-attachments/assets/d87c3c71-964a-402e-98ff-ce04532d1e9b">

Once all 16 scored spaces in the grid have been filled with a card, a score is calculated. Each of the four rows and five columns in the grid count as an individual hand and will be scored as such.

## Scoring
Each card has a value. Cards with numbers on them (2s through 10s) have value equal to the number on the card. “Face” cards (Kings, Queens, Jacks) are valued at 10 each. Aces can be “high”, counting as value 11, or “low”, counting as value 1, depending on which value would give the player a higher score without exceeding 21. Hands are calculated by summing the values of the cards in them. Each hand, then, has a cumulative value. The points that the player scores per hand are calculated as follows:

<img width="335" alt="Screenshot 2024-10-29 at 16 06 31" src="https://github.com/user-attachments/assets/5f3fe44c-8e1c-460f-be78-276ed0239e7b">

A Blackjack (10 points) comprises of a score of 21 using only 2 cards. Hands that score 21 using 3 or more cards are given a score of 7. 

# New Game Screen

Upon starting a new game, you will be shown information as follows:

<img width="457" alt="Screenshot 2024-10-29 at 16 07 58" src="https://github.com/user-attachments/assets/077ea4a3-0bf6-40fd-8826-863ff119d247">

Enter a number between 1-16 to play a card or enter "discard" to add the card to the discard pile. 

A score will the calculated when all 16 spots on the grid have been filled. 

