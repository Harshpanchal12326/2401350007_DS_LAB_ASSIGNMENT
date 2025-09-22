
# Grocery Store Inventory System

Name: Harsh Panchal
Roll Number: 2401350007

# Project Description

This project implements an Inventory Management System for a grocery store using C++. It allows storing and managing stock of items, tracking inventory status, and performing operations like insertion, deletion, searching, and managing low-stock items. It also demonstrates the use of arrays, multi-dimensional arrays, sparse representation, and row-major/column-major ordering.

# Features

Add new items to the inventory

Delete items by ID

Search items by ID or Name

Display all inventory items

Manage Price-Quantity Table using Row-Major or Column-Major ordering

Optimize storage for rarely restocked items using Sparse Representation

Time and space complexity analysis for each operation

Inventory Item Structure

ItemID: Unique integer identifier

ItemName: Name of the item

Quantity: Stock quantity

Price: Price per unit

# How to Run

Open the project in a C++ IDE (like Code::Blocks, Visual Studio, or online compilers).

Compile the program.

Run the executable.

Follow the menu options to manage inventory.

# Complexity Analysis

Insert Item: O(1) time, O(1) space

Delete Item: O(n) time, O(1) space

Search Item: O(n) time, O(1) space

Manage Price-Quantity Table: O(n) time, O(1) space

Sparse Representation: O(k) time, O(k) space (k = rarely restocked items)

Notes

Maximum of 100 items can be stored in the inventory.

Items with quantity less than 5 are considered rarely restocked and stored in sparse representation.
