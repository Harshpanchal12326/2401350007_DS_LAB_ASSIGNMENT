#include <iostream>
#include <string>
#include <iomanip>
using namespace std;

// ---------------------------
// Inventory Item ADT
// ---------------------------
struct InventoryItem {
    int ItemID;
    string ItemName;
    int Quantity;
    float Price;
};

// ---------------------------
// Inventory Management System
// ---------------------------
class InventorySystem {
private:
    static const int MAX_ITEMS = 100;
    InventoryItem items[MAX_ITEMS]; // single-dimensional array
    int itemCount;

    // Multi-dimensional array (Price-Quantity table)
    float PriceQuantityTable[MAX_ITEMS][2]; // 0 -> Price, 1 -> Quantity

    // Sparse Matrix representation (for rarely restocked items)
    struct SparseItem {
        int row;
        int col;
        float value;
    };
    SparseItem sparseMatrix[MAX_ITEMS];
    int sparseCount;

public:
    InventorySystem() {
        itemCount = 0;
        sparseCount = 0;
    }

    // ---------------------------
    // Insert Item
    // ---------------------------
    void insertItem(int id, string name, int quantity, float price) {
        if (itemCount >= MAX_ITEMS) {
            cout << "Inventory Full!" << endl;
            return;
        }
        items[itemCount] = {id, name, quantity, price};
        PriceQuantityTable[itemCount][0] = price;
        PriceQuantityTable[itemCount][1] = quantity;

        // If item is rarely restocked (low quantity), add to sparse representation
        if (quantity < 5) {
            sparseMatrix[sparseCount++] = {itemCount, 1, (float)quantity};
        }

        itemCount++;
        cout << "Item Inserted Successfully!" << endl;

        // Complexity:
        cout << "Time Complexity: O(1), Space Complexity: O(1)" << endl;
    }

    // ---------------------------
    // Delete Item
    // ---------------------------
    void deleteItem(int id) {
        for (int i = 0; i < itemCount; i++) {
            if (items[i].ItemID == id) {
                for (int j = i; j < itemCount - 1; j++) {
                    items[j] = items[j + 1];
                    PriceQuantityTable[j][0] = PriceQuantityTable[j + 1][0];
                    PriceQuantityTable[j][1] = PriceQuantityTable[j + 1][1];
                }
                itemCount--;
                cout << "Item Deleted Successfully!" << endl;
                cout << "Time Complexity: O(n), Space Complexity: O(1)" << endl;
                return;
            }
        }
        cout << "Item not found!" << endl;
    }

    // ---------------------------
    // Search Item (by ID or Name)
    // ---------------------------
    void searchItem(int id) {
        for (int i = 0; i < itemCount; i++) {
            if (items[i].ItemID == id) {
                displayItem(items[i]);
                cout << "Time Complexity: O(n), Space Complexity: O(1)" << endl;
                return;
            }
        }
        cout << "Item not found!" << endl;
    }

    void searchItem(string name) {
        for (int i = 0; i < itemCount; i++) {
            if (items[i].ItemName == name) {
                displayItem(items[i]);
                cout << "Time Complexity: O(n), Space Complexity: O(1)" << endl;
                return;
            }
        }
        cout << "Item not found!" << endl;
    }

    // ---------------------------
    // Row-Major and Column-Major Ordering
    // ---------------------------
    void managePriceQuantity(bool rowMajor = true) {
        cout << "\n--- Price-Quantity Table (" << (rowMajor ? "Row-Major" : "Column-Major") << ") ---\n";
        if (rowMajor) {
            for (int i = 0; i < itemCount; i++) {
                cout << "ItemID " << items[i].ItemID
                     << " -> Price: " << PriceQuantityTable[i][0]
                     << ", Quantity: " << PriceQuantityTable[i][1] << endl;
            }
        } else {
            for (int j = 0; j < 2; j++) {
                for (int i = 0; i < itemCount; i++) {
                    cout << PriceQuantityTable[i][j] << " ";
                }
                cout << endl;
            }
        }
        cout << "Time Complexity: O(n), Space Complexity: O(1)" << endl;
    }

    // ---------------------------
    // Sparse Representation
    // ---------------------------
    void optimizeSparseStorage() {
        cout << "\n--- Sparse Representation (Rarely Restocked Items) ---\n";
        for (int i = 0; i < sparseCount; i++) {
            cout << "Row: " << sparseMatrix[i].row
                 << " Col: " << sparseMatrix[i].col
                 << " Value: " << sparseMatrix[i].value << endl;
        }
        cout << "Time Complexity: O(k), Space Complexity: O(k)" << endl;
    }

    // ---------------------------
    // Display All Items
    // ---------------------------
    void displayInventory() {
        cout << "\n--- Inventory Items ---\n";
        cout << setw(10) << "ID" << setw(20) << "Name" << setw(15) << "Quantity" << setw(15) << "Price\n";
        for (int i = 0; i < itemCount; i++) {
            cout << setw(10) << items[i].ItemID
                 << setw(20) << items[i].ItemName
                 << setw(15) << items[i].Quantity
                 << setw(15) << items[i].Price << endl;
        }
    }

private:
    void displayItem(InventoryItem item) {
        cout << "Item Found -> ID: " << item.ItemID
             << ", Name: " << item.ItemName
             << ", Quantity: " << item.Quantity
             << ", Price: " << item.Price << endl;
    }
};

// ---------------------------
// Main Function
// ---------------------------
int main() {
    InventorySystem system;
    int choice;

    do {
        cout << "\n==== Grocery Store Inventory System ====\n";
        cout << "1. Insert Item\n";
        cout << "2. Delete Item\n";
        cout << "3. Search Item by ID\n";
        cout << "4. Search Item by Name\n";
        cout << "5. Display Inventory\n";
        cout << "6. Manage Price-Quantity Table (Row-Major)\n";
        cout << "7. Manage Price-Quantity Table (Column-Major)\n";
        cout << "8. Optimize Sparse Storage\n";
        cout << "9. Exit\n";
        cout << "Enter your choice: ";
        cin >> choice;

        if (choice == 1) {
            int id, qty;
            string name;
            float price;
            cout << "Enter ID, Name, Quantity, Price: ";
            cin >> id >> name >> qty >> price;
            system.insertItem(id, name, qty, price);
        } else if (choice == 2) {
            int id;
            cout << "Enter ID to delete: ";
            cin >> id;
            system.deleteItem(id);
        } else if (choice == 3) {
            int id;
            cout << "Enter ID to search: ";
            cin >> id;
            system.searchItem(id);
        } else if (choice == 4) {
            string name;
            cout << "Enter Name to search: ";
            cin >> name;
            system.searchItem(name);
        } else if (choice == 5) {
            system.displayInventory();
        } else if (choice == 6) {
            system.managePriceQuantity(true);
        } else if (choice == 7) {
            system.managePriceQuantity(false);
        } else if (choice == 8) {
            system.optimizeSparseStorage();
        } else if (choice == 9) {
            cout << "Exiting..." << endl;
        } else {
            cout << "Invalid choice!" << endl;
        }

    } while (choice != 9);

    return 0;
}
