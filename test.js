const canvas = document.querySelector('canvas')
const ctx = canvas.getContext('2d')

class Polygon {
    constructor(width, height) {
        this.width = width;
        this.height = height
        this.color = "red"
        this.x = 100
        this.y = 4
    }


    draw() {
        //console.log("Test")
        ctx.beginPath();
        ctx.fillStyle = this.color;
        ctx.fillRect(this.x, this.y, this.width, this.height);

    }



}






class Square extends Polygon {
    constructor(width, height) {
        
        super();
        this.width = width;
        this.height = height


        // this.y=y
        this.width = 100
        this.x = 400
    }



}


var figur1 = new Polygon(10, 20);




var figur2 = new Square(100, 12);

figur1.draw();
figur2.draw();