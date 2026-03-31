# Auction system

## Introduction

Specification of functional requirements as part of computerisation of the product sale process based on the auction mechanism.

## Business processes

---
<a id="bc1"></a>
### BC1: Auction sale

**Actors:** [Seller](#ac1), [Buyer](#ac2)

**Description:** Business process describing a sale by the auction mechanism.

**Main scenario:**
1. [Seller](#ac1) offers the product at an auction. ([UC1](#uc1))
2. [Buyer](#ac2) offers a bid for the product that is higher than the currently highest bid. ([BR1](#br1), [UC2](#uc2))
3. [Buyer](#ac2) wins the auction ([BR2](#br2))
4. [Buyer](#ac2) transfers the amount due to the Seller. ([UC3](#uc3))
5. [Seller](#ac1) transfers the product to the Buyer.

**Alternative scenarios:** 

2.A. Buyer's bid has been outbid and [Buyer](#ac2) wants to outbid the current highest bid.
* 2.A.1. Continue at step 2.

3.A. Auction time has elapsed and [Buyer](#ac2) has lost the auction. ([BR2](#br2))
* 3.A.1. End of use case.

---

## Actors

<a id="ac1"></a>
### AC1: Seller

A person offering goods at an auction.

<a id="ac2"></a>
### AC2: Buyer

A person intending to purchase a product at an auction..


## User level use cases

### Actors and their goals 

[Seller](#ac1):
* [UC1](#uc1): Offering a product at an auction
* [UC4](#uc4): Editing product/auction details
* [UC5](#uc5): Canceling an active auction

[Buyer](#ac2):
* [UC2](#uc2): Bidding at an auction
* [UC3](#uc3): Paying for a won auction

---
<a id="uc1"></a>
### UC1: Offering a product at an auction

**Actors:** [Seller](#ac1)

**Main scenario:**
1. [Seller](#ac1) reports to the system the willingness to offer the product up at an auction.
2. System asks for the product data and initial price.
3. [Seller](#ac1) provides product data and the initial price.
4. System verifies data correctness.
5. System informs that the product has been successfully put up for auction.

**Alternative scenarios:** 

4.A. Incorrect or incomplete product data has been entered.
* 4.A.1. informs about incorrectly entered data.
* 4.A.2. Continue at step 2.

---

<a id="uc2"></a>
### UC2: Placing a bid

**Actors:** [Seller](#ac1), [Buyer](#ac2)

**Main scenario:**
1. [Buyer](#ac2) chooses to bid on the selected [Auction](#bo1).
2. System displays the current highest bid and prompts for a new bid amount.
3. [Buyer](#ac2) enters their bid amount.
4. System checks if the new bid is high enough ([BR1](#br1)).
5. System saves the [Bid](#bo3) and updates the auction's current price.
6. System confirms to the [Buyer](#ac2) that the bid was accepted.
7. System sends a notification to the [Seller](#ac1) about the new highest bid.

**Alternative scenarios:** 4.A. The entered amount is too low.
* 4.A.1. System displays an error message explaining the minimum bid rule ([BR1](#br1)).
* 4.A.2. Continue at step 2.

---

## Business objects (also known as domain or IT objects)

### BO1: Auction

The auction is a form of concluding a sale and purchase transaction in which the Seller specifies the starting bid of the product, while the Buyers may offer their own purchase offer, each time proposing a bid higher than the currently offered bid. The auction ends after a specified period of time. If at least one purchase offer has been submitted, the product is purchased by the Buyer who offered the highest bid. 

### BO2: Product

A physical or digital item to be auctioned.

## Business rules

<a id="br1"></a>
### BR1: Bidding at auction

Bidding at auction requires submitting an amount higher than current by a minimum of EUR 1.00

<a id="br2"></a>
### BR2: Winning an auction

<a id="bo3"></a>
### BO3: Bid

An amount of money offered by a [Buyer](#ac2) to buy a [Product](#bo2) in an [Auction](#bo1).

Auction is won by [Buyer](#ac2) who submitted the highest bid before the end of the auction (time expires).


## CRUDL Matrix


| Use case                                  | Auction | Product | ... |
| ----------------------------------------- | ------- | ------- | --- |
| UC1: Offering a product at an auction     |    C    |    C    | ... |
| ???                                       |   ...   |   ...   | ... |

