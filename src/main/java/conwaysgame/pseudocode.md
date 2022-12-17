# Rules
1. A live cell with 2-3 neighbors survives
2. A live cell with < 2 neighbors dies of solitude
3. A live cell with > 3 neighbors dies of suffocation
4. A cell is born in an unpopulated position with 3 neighbors

# Pseudocode
Setup:
Instantiate empty newCells list.
Instantiate empty cells list.

Create new Cell objects and add them to cells list.
Each cell knows its own position and if it is doomed to die.

Game logic:
WHILE the list has more than two elements
1. Loop through the list:
   - Get number of neighbors for each cell and apply rules 1-3. If the cell dies this round, set isDoomed to 
     true.
   - Get the position of all empty fields adjacent to a cell and add to a unique list.

2. Loop through the list of empty fields
   - Get the number of neighbors for each field. 
   - If a field has exactly 3 neighboring Cell objects, add a Cell object with its coordinates to the list of 
     newCells 

3. Loop through the list of cells again
   - If a cell is dead, remove it from the list

4. Add cells from newCells to cells and clear newCells

5. Display all cells in cells list
END WHILE

# Classes
2. Cell
   - Point position
   - isDoomed
3. GameOfLife
   - List<Cell> cells
   - Set<Cell> newCells
   - Main class - starts the game
   - Game logic (See above)
   - Methods
     - List<int[]> getFieldsToCheck()
     - int getNumberOfNeighbors(x, y)
     - populateNewCells(fieldsToCheck)
     - removeDeadCells()