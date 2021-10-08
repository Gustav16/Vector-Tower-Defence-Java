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

    constructor(){
        super();

        

    }

 
    color = "green"

   // var hvem=13
    



    print(){


        console.log(col)
    }


}


var hv = 3;


console.log(hv)




var figur1 = new Polygon(10, 20);




var figur2 = new Square(100, 12);

figur1.draw();

figur2.draw();

figur2.print();