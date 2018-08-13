var mongoose = require('mongoose');
var config   = require('./config');
var db = mongoose.connection;
mongoose.Promise = global.Promise;
console.log(config.mongodb.dbURI);
mongoose.connect(config.mongodb.dbURI, { useMongoClient: true });

db.on('error', console.error);

db.once('open', function() {
	console.log("Connect MongoDB!");
});

module.exports = mongoose;
