'use strict'

let fs1 = require('fs');
fs1.readFile('demo.txt','utf-8',function(err,data){
    if(err){
        console.log(err);
    }else{
        console.log(data);
    }
});

let fs2 = require('fs');
let data = fs2.readFileSync('demo.txt','utf-8');
console.log(data);

try{
    let data = fs2.readFileSync('data.txt','utf-8');
    console.log(data);
}catch(err){
    //错误处理
}

