# Stream API Fundamentals

## Goal

Build comfort with the filter/map/reduce/collect stream pipeline so that summarizing a collection stops being a hand-written `for` loop with accumulator variables and becomes a declarative description of the result you want.

## Prerequisites

- Basic Java syntax
- Lambda expressions and method references

## Task

`SalesReport` answers questions about a list of `Sale` records, where each `Sale` is a product name plus an amount. You'll implement five report methods: the grand total of all amounts, the distinct product names in alphabetical order, the single largest sale, the total per product, and the average sale amount.

Every method takes a `List<Sale>` and should be written as a stream pipeline rather than a loop.

## Instructions

Complete the following TODOs in `SalesReport`:

- TODO-00: Implement `totalAmount()` — sum the amount of every sale.
- TODO-01: Implement `productNamesSorted()` — the distinct product names, sorted alphabetically.
- TODO-02: Implement `topSaleByAmount()` — the sale with the highest amount, throwing `NoSuchElementException("no sales")` when the list is empty.
- TODO-03: Implement `totalsByProduct()` — group the sales by product name and sum the amount per product.

Run the tests until they all pass.

## Running the Lab

From the project root:

```bash
mvn -pl java-concepts/stream-api-fundamentals test
```

Or from the lab directory:

```bash
cd java-concepts/stream-api-fundamentals
mvn test
```

## Bonus (Optional)

- TODO-04 (optional): Implement `averageAmount()`, returning the average sale amount, or `0.0` when the list is empty. Note that "no sales" is an empty result here rather than an exception — compare how you express that with the way `topSaleByAmount()` handles the same input.
