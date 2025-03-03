const gridSideLengthPixels = 500;
const gridSize = 30;
const cellSize = gridSideLengthPixels / gridSize;

const blinker = {
    sideLength: gridSize,
    liveCells: [{x: 14, y: 15}, {x: 15, y: 15}, {x: 16, y: 15}]
};

const glider = {
    sideLength: gridSize,
    liveCells: [
        {x: 4, y: 2}, {x: 4, y: 3}, {x: 4, y: 4},
        {x: 3, y: 4}, {x: 2, y: 3}
    ]
};

const i = {
    sideLength: gridSize,
    liveCells: [
        {x: 13, y: 13}, {x: 14, y: 13}, {x: 15, y: 13},
        {x: 14, y: 14}, {x: 14, y: 15}, {x: 14, y: 16}, {x: 14, y: 17},
        {x: 13, y: 17}, {x: 15, y: 17}
    ]
};

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

    ticks = Array.from({length: gridSize - 1}, (_, i) => (i + 1) * cellSize);
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

function clearCanvas() {
    canvas = document.getElementById("canvas");
    context = canvas.getContext("2d");
    context.clearRect(0, 0, gridSideLengthPixels, gridSideLengthPixels);
    
    drawEmptyGrid();
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
            clearCanvas();

            grid = JSON.parse(gridJson);
            drawGrid(grid);
        });
}

function setUpPattern(initialGrid) {
    clearCanvas();
    
    drawGrid(initialGrid);

    newGridRequest =
        new Request(
            "http://localhost:8080/new",
            {method: "POST", body: JSON.stringify(initialGrid)}
        );
    
    fetch(newGridRequest).then(response => null);
}

drawEmptyGrid();

intervalId = null;
document.getElementById("start")
    .addEventListener("click", _ => {
        intervalId = setInterval(updateCanvas, 400)
    });

document.getElementById("stop")
    .addEventListener("click", _ => {
        clearInterval(intervalId);
    });
    
document.getElementById("pattern")
    .addEventListener("click", event => {
        pattern = event.target.value;
        
        if (pattern === "blinker") {
            setUpPattern(blinker);
        } else if (pattern === "glider") {
            setUpPattern(glider);
        } else if (pattern === "i") {
            setUpPattern(i);
        }
    });
