# LogicVerse

**A desktop digital-logic circuit simulator in Java.** You drag gates onto a canvas, wire them together, flip switches and watch probes light up. You can save circuits to a database, reuse them as building blocks, and generate truth tables automatically.

## Features

- 🧩 **Drag-and-drop circuit editor:** AND, OR and NOT gates, switches, probes and wires on a scrollable canvas
- 🔁 **Reusable circuits:** save a circuit and drop it into another as a component
- 📊 **Truth-table analysis:** generates the full truth table for any circuit
- 💾 **Persistence:** circuits, components, connections and positions are stored in SQLite
- ✅ **Test-driven:** unit tests for every gate, switch, probe and controller

## Architecture

The code is layered so that the UI, the simulation logic and storage are kept separate:

```
src/logicverse/
├── app/            # entry point
├── presentation/   # Swing UI: MainFrame, Canvas, dialogs, drawable components
├── domain/         # simulation core: gates, components, connections, controllers
└── data/           # DAO layer (IDAO → AbstractDBDAO → Circuit/Component/Connection/Point DAOs) over SQLite
```

**Patterns used:** DAO, template method (`AbstractDBDAO`), and a class hierarchy for components (`Component → AbstractComponent → ClickableComponent`).

## Tech Stack

Java · Swing · SQLite (JDBC) · JUnit · NetBeans / Ant

## Run

```bash
# Open in NetBeans and press Run, or:
ant clean jar
java -jar dist/LogicVerse.jar
```

## Tests

```bash
ant test
```
