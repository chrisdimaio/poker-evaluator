
# poker-evaluator

![GitHub release (latest by date including pre-releases)](https://img.shields.io/github/v/release/chrisdimaio/poker-evaluator?include_prereleases)
![GitHub](https://img.shields.io/github/license/chrisdimaio/poker-evaluator)
[![Build Status](https://travis-ci.com/chrisdimaio/poker-evaluator.svg?branch=master)](https://travis-ci.com/chrisdimaio/poker-evaluator)

## Overview
Evaluates a poker hand and determines the winner. Five card draw and Texas Hold'em style with community cards is supported.

## API
The simple API exposes three methods and a class.

```java
Card card = Card(Rank.ACA, Suit.SPADES);

```java
Hand hand = Evaluator.createHand(A list of cards);
```

```java
Hand winner = Evaluator.winner(A list of hands);
```

```java
// The community cards version returns the winning hand and the total set of cards hand that won.
ArrayList<Hand, Hand> winner = Evaluator.winner(A list of hands, community cards);
```


