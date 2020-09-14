
# poker-evaluator

![GitHub release (latest by date including pre-releases)](https://img.shields.io/github/v/release/chrisdimaio/poker-evaluator?include_prereleases)
![GitHub](https://img.shields.io/github/license/chrisdimaio/poker-evaluator)
[![Build Status](https://travis-ci.com/chrisdimaio/poker-evaluator.svg?branch=master)](https://travis-ci.com/chrisdimaio/poker-evaluator)

## Overview
Evaluators a poker hands and determines the winning hand. 5 card draw style and Texas Hold'em style with community cards is supported.

## API
The simple API exposes three methods.

```java
hand = Evaluator.createHand(A list of cards);
```

```java
winner = Evaluator.winner(A list of hands);
```

```java
// The community cards version returns the winning hand and the total set of cards that won the hand.
winner = Evaluator.winner(A list of hands, community cards);
```


