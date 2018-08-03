var mongoose = require('mongoose');
var Schema = mongoose.Schema;

var IngredienteSchema = new Schema({
   imagemCategoria: String,
   categoria: String,
   ingredientes:[String]
});

module.exports = mongoose.model('Ingrediente', IngredienteSchema);