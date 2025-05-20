# 🍷 Wine Store System

An advanced Java-based desktop application for managing a wine store’s inventory, manufacturers, and product records — built with real-world technologies like **JDBC**, **JasperReports**, **Swing**, and **UCanAccess**.

This application demonstrates full-stack Java desktop development with integrated **report generation**, **JSON/XML processing**, and **database persistence** — ideal for showcasing Java programming and system design skills.

---

## 💡 Key Features

- 🧾 **Inventory Management**  
  Add, remove, and view wines and their manufacturers

- 🏭 **Manufacturer Association**  
  Group wines by producers and filter inventory by supplier

- 📄 **Report Generation (JasperReports)**  
  Generate PDF reports for wines and manufacturers with professional formatting

- 📤 **JSON Export**  
  Export wine records to JSON format for integration or backup

- 📥 **XML Parsing & Import**  
  Import wine data from XML files and automatically store them into the database

- 💾 **MS Access Integration (via UCanAccess)**  
  Data is persisted in a Microsoft Access database, making the system portable and lightweight

- 🖥️ **User-Friendly GUI (Swing)**  
  Built with Swing and intuitive navigation for managing the entire system

---

## 🧰 Technologies Used

| Tool/Library      | Purpose                          |
|-------------------|----------------------------------|
| `Java 11+`        | Core language                    |
| `Swing`           | GUI development                  |
| `JDBC`            | Database connection              |
| `UCanAccess`      | MS Access DB integration         |
| `JasperReports`   | Report creation (PDF export)     |
| `org.json`        | JSON creation/export             |
| `DOM Parser`      | XML reading and data extraction  |

---

## 📁 Project Structure

```
wine-store-system/
├── src/                     # Java source code
│   ├── entities/            # Wine, Manufacturer classes
│   ├── ui/                  # GUI classes
│   ├── db/                  # DB connection logic
│   └── reports/             # Jasper report handlers
├── wine-db.mdb              # Microsoft Access database file
├── WineStoreSystem.jar      # Runnable compiled JAR
├── lib/                     # UCanAccess & Jasper dependencies
└── README.md
```

---

## 🚀 How to Run

### 🟢 Option 1: Run JAR (no IDE needed)

```bash
java -jar WineStoreSystem.jar
```

> Make sure the `wine-db.mdb` and `lib/` folder are in the same directory.

---

### 🛠️ Option 2: Run from source (for developers)

1. Clone the repo:

```bash
git clone https://github.com/khalildabbah/My-projects.git
cd My-projects/wine-store-system
```

2. Import the project in **Eclipse** or **NetBeans**

3. Make sure to include all `.jar` files inside the `lib/` folder as dependencies

4. Run the main class (e.g. `WineStoreApp.java`)

---

## 🔑 What This Project Shows

- End-to-end Java desktop development
- Integration of structured data (JSON, XML, SQL)
- Clean GUI and code architecture using MVC patterns
- Working with file I/O and reporting tools
- Real database persistence with UCanAccess

---

## 👨‍💻 Author

**Khalil Dabbah**  
[GitHub Profile](https://github.com/khalildabbah)

---

## 📝 License

This project is for educational and portfolio use.
