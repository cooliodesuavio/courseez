// web.js
var express = require("express");
var logfmt = require("logfmt");
var mysql = require('mysql');
var app = express();



// var port = Number(process.env.PORT || 5000);
app.configure(function() {
	app.set('views', __dirname + '/views');
  	app.set('view engine', 'jade');
	app.set('index', __dirname + '/views/index.jade');
	app.use(logfmt.requestLogger());
	app.use(express.static(__dirname + '/public'));
});

app.use(express.bodyParser());


app.get('/', function(req, res){res.render('index',{})});
// app.get('/',function(req,res){res.sendfile(__dirname + 'views/index.html');});


var port = Number(process.env.PORT || 5000);
app.listen(port, function() {
	  console.log("Listening on " + port);
});

var connection = mysql.createConnection({
	  host     : 'us-cdbr-east-05.cleardb.net',
		  user     : 'b26bd87f5fba4f',
			  password : '9c7b90d2',
});

connection.connect(function(err) {
	  // connected! (unless `err` is set)
});


var query = connection.query('USE heroku_3b96a714cd97ee2', function(err)
	{
		if(err)
			console.log("12341234134");
		else

 		connection.query('SELECT * FROM test', function(err, result) {
	if(err)
		console.log("EEERORROOOORR");
	else
		console.log(result[0]);
		});
	});




//     if (err) {
//         throw err; 
//     }       
//     http.createServer(function(request, response) {  
//         response.writeHeader(200, {"Content-Type": "text/html"});  
//         response.write(html);  
//         response.end();  
//         console.log("Listening on" + port);
//     }).listen(5000);
// });
