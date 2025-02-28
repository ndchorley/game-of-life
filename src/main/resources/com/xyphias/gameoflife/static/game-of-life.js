const gridSideLengthPixels = 500;
const gridSize = 10;
const cellSize = gridSideLengthPixels / gridSize;

function drawVerticalLine(context, x) {
    context.moveTo(x, 0)
    context.lineTo(x, gridSideLengthPixels);
    context.stroke();
}

function drawHorizontalLine(context, y) {
    context.moveTo(0, y)
    context.lineTo(gridSideLengthPixels, y);
    context.stroke();
}

function drawEmptyGrid() {
    canvas = document.getElementById("canvas");
    context = canvas.getContext("2d");

    context.strokeRect(0, 0, gridSideLengthPixels, gridSideLengthPixels);

    context.beginPath();
    ticks = [50, 100, 150, 200, 250, 300, 350, 400, 450];
    ticks.forEach(x => drawVerticalLine(context, x));
    ticks.forEach(y => drawHorizontalLine(context, y));
}

function drawLiveCellAt(coordinates) {
    canvas = document.getElementById("canvas");
    context = canvas.getContext("2d");

    startX = (coordinates.x - 1) * cellSize;
    startY = (coordinates.y - 1) * cellSize;
    context.fillRect(startX, startY, cellSize, cellSize);
}

drawEmptyGrid();

initialState = {
    sideLength: gridSize,
    liveCells: [{x: 4, y: 5}, {x: 5, y: 5}, {x: 6, y: 5}]
}

drawLiveCellAt(initialState.liveCells[0]);
drawLiveCellAt(initialState.liveCells[1]);
drawLiveCellAt(initialState.liveCells[2]);
