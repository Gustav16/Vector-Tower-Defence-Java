

var q = [13, 14, 12, 31251, 41, 423, 1235, 0]


var travelDis= []

function targerting() {



    travelDis = [...q];


    travelDis.sort(function (a, b) { return b - a });

    console.log(travelDis)
    console.log(q)




        for (i = 0; i < q.length; i++) {
    
             travelDis[i] = q.indexOf(travelDis[i])

    
        }
    
         console.log(travelDis)
    
}






targerting();
