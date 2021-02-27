//1. 导入mysql依赖包
let mysql = require("mysql");

//2. 创建mysql连接
let conn = mysql.createConnection({
    //3. 配置数据库连接信息
    host: '127.0.0.1',
    port: 3306,
    user: 'root',
    password: '123456',
    database: 'testdb'
})

//4. 开辟连接
conn.connect();
//5. 执行crud
conn.query('select * from user',function(err,result,fields){
    //如果查询出错，直接抛出
    if(err)throw err;
    else console.log(result);
});

//6. 关闭连接
conn.end();