# Rules
1. A live cell with 2-3 neighbors survives
2. A live cell with < 2 neighbors dies of solitude
3. A live cell with > 3 neighbors dies of suffocation
4. A cell is born in an unpopulated position with 3 neighbors

# Pseudocode
Setup:
Initialize empty `newCells` list.
Initialize empty `cells` list.

Create new `Cell` objects and add them to `cells` list.
Each cell knows its own position and if it is doomed to die.

Game logic:
WHILE the list has more than two elements
1. Loop through the list:
   - Get number of neighbors for each cell and apply rules 1-3: If the cell dies, set `isDoomed` to `true`.
   - Get the position of all empty fields adjacent to a cell and add unique fields to list (`Set`).

2. Loop through the list of empty fields
   - Get the number of cell neighbors for each field. 
   - If a field has exactly 3 cell neighbors, add a new cell with the field's coordinates to the list `newCells`

3. Remove dead cells and add new cells 
   - Loop through the list of `cells` again
      - If a cell is dead, remove it from the list 
   - Add Cell objects from `newCells` to `cells` and clear `newCells`

5. Display all cells in `cells` list on screen
END WHILE

# Classes
1. `Field`: x,y grid position
   - Instance variables
     - `int x`
     - `int y`
   - Methods
     - `List<Field> getNeighbors`
   - Other
     - Override equals and hashCode
2. `Cell`: live cell
   - Instance variables
     - `Field position`
     - `isDoomed`
   - Methods
3. `GameOfLife`: Starts the game, game logic, and methods
   - Instance variables
     - `List<Cell> cells`
     - `Set<Cell> newCells`
   - Methods
     - `main`
     - `List<Field> getEmptyFieldsToCheck()`
     - `int getNumberOfNeighbors(x, y)`
     - `populateNewCells(fieldsToCheck)`
     - `removeDeadCells()`