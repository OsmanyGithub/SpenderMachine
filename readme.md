## 24.03.20

Good stuff. Some of them:
1. Separation of the entities in multiple classes: VendingMachine, Store, Product. The complexity is a bit higher too (the machine, stock, and client's balances).
2. Vending machine is a class with and its state has the available products
3. Having a store with its stock
4. Verifying and updating the clients balance 

Some possible design issues:
1. The VendingMachine has two responsibilities that could be extracted to other classes, to simplify it and to get closer to a single responsibility principle:
    1. Responsibility 1.1: Supplying the vending machine with products from a store, and keeping the store stock updated
    2. Responsibility 1.2: Doing the banking for a client, updating the client's account balance

Suggestions for improvement:
1. Address issue 1.1: Extract the responsibility of supplying a vending machine (Issue 1.1) out of the VendingMachine class.
   - For example, add a new class Supplier to perform the supply of the VendingMachine from a store.
   - This class has references to both a VendingMachine and a Store.
   - The supplier uses the addProduct and other methods of the VendingMachine
   - The supplier uses the removeProduct (needs to be implemented, see above) of the Store
   - A good consequence of this is that the Store will not be part of the state of a VendingMachine, which seems more accurate.
2. Address issue 1.2: Extract the responsibility of doing the banking for a client out of the VendingMachine class  (Issue 1.2).
   - A possible approach is: 
     - Add a new class Bank to do the banking for a client.
     - The bank keeps the balance of the client accounts
     - The bank provides methods to charge a client some amount. The method works if the client's account's balance has sufficient funds, and fails otherwise.
     - A client has a reference to its bank
     - The VendingMachine's buy method receives a client, and access the client's bank, and tries to debit from the client's bank
3. The Operation class reduces the precision of operations. The bank operations should be as precise as possible, and the presentation layer should format the output to 2 decimals if needed.
Remove the Operation class.
4. VendingMachine's buyItem and supply methods are too large and complex (too many nested expressions: `if`, `for` and `while`s). 
In general, consider the following in order to reduce the size and complexity of large methods:
   - Extract code into private methods.
   - Call private methods when possible. For example: use method `buyItem` withing `buySeveralItems`
   - To reduce nesting in loops, consider the `break` and `continue` expressions.
5. Improve the Store class 
   - Store can use another collection `catalog` to keep track of the possible product-price values. For example, a `Map<Product, Int>` , or a `Set<Pair>`. So two collections, `stock` and `catalog`.
    - Make the `stock` field private (information hiding). Add instead other methods to modify the Store's stock, e.g. removeProduct, addProduct.