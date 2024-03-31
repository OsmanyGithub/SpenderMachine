3/31/2024
-Class Pair Removal: 
 It was no longer necessary for the overall functionality.
-Stock and Catalogue as HashMaps: 
 This change likely enhances efficiency and simplifies data management.
-New Class Supplier: 
 Introducing the Supplier class to handle machine refill. 
 It compartmentalizes the refill process and keeps the code modular.
-Operation Class Removal: 
 Operation class was no longer needed, its removal streamlines the codebase.
-Bank Class Approval Logic:
 The Bank class now determines whether a client can make a purchase based on their funds. 
 This approach aligns with good separation of concerns.
-Client Money Format: 
 Embedding the client’s money format directly in the VendingMachine class is a cleaner 
 design choice.
-BuyItem and Supply Methods Split: 
 Splitting the BuyItem and supply methods into smaller, more focused methods is a best 
 practice for maintainable code.