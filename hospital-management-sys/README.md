# 🏥 Hospital Management System (Java, Serialization-Based)

A Java desktop application that simulates a complete hospital management system using object-oriented design and file-based data persistence via **serialization**.

This project demonstrates how to manage entities like hospitals, departments, doctors, nurses, and medical problems — without relying on a database.

---

## 🧠 Core Features

- 👨‍⚕️ Manage **doctors**, **nurses**, and **patients**
- 🏥 Create and organize **departments** and assign staff
- 🩺 Track **medical problems**: `Fracture`, `Disease`, `Injury`
- 🧬 Support for **Intensive Care Units**
- 💾 Data is saved and loaded using **Java serialization**
- 📄 Input via CSV (`INPUT.csv`) and serialized file (`Hospital.ser`)
- 🖥️ Clean object-oriented structure with exception handling

---

## 💡 Technologies Used

- **Java**
- **Java Serialization**
- **Custom Exception Handling**
- **File I/O (CSV and SER)**
- **Command-line Interface**
- Compatible with Eclipse or any IDE

---

## 📁 Folder Structure

```
hospital-management/
├── src/               # Source code
├── data/              # Input/output files
├── Hospital.ser       # Serialized saved data
├── INPUT.csv          # CSV input (optional)
├── StandardOutput.txt # Output report/log
└── README.md
```

---

## 🚀 How to Run

1. Clone the repo:
```bash
git clone https://github.com/khalildabbah/My-projects.git
cd My-projects/hospital-management
```

2. Open the project in **Eclipse** or another Java IDE

3. Run the main class (e.g., `Main.java` or `HospitalApp.java`)

4. Serialized data will be saved to `Hospital.ser` and optionally exported to text/CSV

---

## 🧠 What This Project Demonstrates

- Real-world Java modeling with multiple object types
- Hierarchical structure: Hospital > Departments > Staff > Problems
- Data persistence with `.ser` files (without DB)
- Clean file-based architecture with extensible design

---

## 👨‍💻 Author

**Khalil Dabbah**  
[GitHub](https://github.com/khalildabbah)

---

## 📜 License

This project is for academic, learning, and portfolio purposes.
