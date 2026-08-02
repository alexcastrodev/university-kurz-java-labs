# Records and Sealed Types: Modeling a Closed Set of Payment Methods

## Goal

Learn how a compact constructor validates a record's data at construction time (before it's assigned to the record's fields), and how sealing a hierarchy of records lets a `switch` over it be exhaustive — every permitted case handled, no `default` branch needed.

## Prerequisites

- Basic Java syntax
- `switch` expressions
- Basic exception handling (`throw`, `assertThrows`)

## Task

`PaymentMethod` is a sealed interface permitting exactly three records: `CreditCard`, `BankTransfer`, and `DigitalWallet`. Implement each record's compact constructor to reject invalid data, and implement `PaymentMethods`' two utility methods using an exhaustive switch over the sealed hierarchy.

## Instructions

Complete the following TODOs:

- TODO-00: Validate `CreditCard`'s compact constructor.
- TODO-01: Validate `BankTransfer`'s compact constructor.
- TODO-02: Implement `PaymentMethods.describe()`.
- TODO-03: Implement `PaymentMethods.feePercentage()`.

Run the tests until they all pass.

## Running the Lab

From the project root:

```bash
mvn -pl java-concepts/records-and-sealed-types test
```

Or from the lab directory:

```bash
cd java-concepts/records-and-sealed-types
mvn test
```

## Bonus

- TODO-04 (optional): Implement `PaymentMethods.isFeeWaived()`.
