# simpleBlackjack
#### Assessed Exercise 3 - Simple Blackjack Game

This game resembles, but is curently significantly different from, the fully featured game of Blackjack.

This was an assessed exercise for my BSc Computer Science - Year 1.

## Functionality
* Dealer / computer stands on 18  and higher.
* Ace has a value of 1 only.
* Only one hand is played per game.
* One deck of 52 cards is used.
* Cards have suits, but are not used in play.

```java
public Card(String theName, String theSuit, int theValue)
    {
        name = theName;
        suit = theSuit;
        value = theValue;
    }
```

I plan to add further functionality to this game in the future; see the [Issues](../../issues).
