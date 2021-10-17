const canvas = document.querySelector('canvas')
const ctx = canvas.getContext('2d')









ctx.beginPath();
ctx.fillStyle = "green";
ctx.fillRect(0, 0, 1000, 1000)









class Dummy {
    constructor() {

        this.su = 100;


    }




}



console.log(Dummy.su)


var dummy = new Dummy();

console.log(dummy.su)





console.log(Dummy.name)
