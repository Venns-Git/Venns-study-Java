'use strict';
let fs = require('fs');
let data = 'Hello Node.js';
fs.writeFile('demo.txt',data,function(err){
    if(err) console.log(err);
    else console.log('ok');
});