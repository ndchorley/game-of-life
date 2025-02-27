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

function drawGrid(grid) {
    grid.liveCells
        .forEach(coordinates => drawLiveCellAt(coordinates));
}

function updateCanvas() {
    nextGridRequest =
        new Request(
            "http://localhost:8080/next",
            {method: "POST"}
        );

    fetch(nextGridRequest)
        .then(respose => respose.blob())
        .then(blob => blob.text())
        .then(gridJson => {
            canvas = document.getElementById("canvas");
            context = canvas.getContext("2d");
            context.clearRect(0, 0, gridSideLengthPixels, gridSideLengthPixels);

            drawEmptyGrid();

            grid = JSON.parse(gridJson);
            drawGrid(grid);
        });
}

drawEmptyGrid();

initialGrid = {
    sideLength: gridSize,
    liveCells: [{x: 4, y: 5}, {x: 5, y: 5}, {x: 6, y: 5}]
}

drawGrid(initialGrid);

newGridRequest =
    new Request(
        "http://localhost:8080/new",
        {method: "POST", body: JSON.stringify(initialGrid)}
    );

fetch(newGridRequest).then(response => null);

intervalId = null;
document.getElementById("start")
    .addEventListener("click", _ => {
        intervalId = setInterval(updateCanvas, 400)
    });

document.getElementById("stop")
    .addEventListener("click", _ => {
        clearInterval(intervalId);
    });
