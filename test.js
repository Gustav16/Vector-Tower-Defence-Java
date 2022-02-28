




/*


<!DOCTYPE html>
<html>
  
<head>
    <title>Read Text File</title>
</head>
  
<body>
    <input type="file" name="inputfile"
            id="inputfile">
    <br>
   
    <pre id="output"></pre>
      
    <script>
        document.getElementById('inputfile')
            .addEventListener('change', function() {
              
            var fr=new FileReader();

            fr.onload=function(){
                console.log(fr.result)
         
                
            }


            fr.readAsText(this.files[0]);


        })

        
    </script>
</body>
  
</html>






*/






/*
var count = 0

function dummy() {

    console.log(count)



}







function intaval(functionName, repetition, intaval, time) {

    //console.log("test")

    rep();


    function rep() {



        functionName;
        //dummy();



        count++;







        if (count < repetition) {



            setTimeout(rep, 300);


        }
    }



}


intaval( dummy(), 3);





function makeEnemie() {
    enemies[counter] = new Enemy(map[3][0].x, map[3][0].y, counter)
    counter++;

    if (counter < 10) {

        setTimeout(makeEnemie, 300);
    }

}



*/











/*

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



var figur1 = new Polygon(10, 20);




var figur2 = new Square(100, 12);

figur1.draw();

figur2.draw();

figur2.print();

*/