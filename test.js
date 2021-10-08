class Polygon {



    constructor() {

        this.width = 30;
        this.color = "red"


    }


    draw() {


        ctx.beginPath();
        ctx.fillStyle = color;
        ctx.fillRect(100, 100, this.width, this.width);

    }



}