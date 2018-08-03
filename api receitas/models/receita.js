var mongoose = require('mongoose');
var Schema = mongoose.Schema;

var ReceitaSchema = new Schema({
    titulo : String,
    descricaoIngredientes : [String],
    ingredientesBase : [String],
    modoPreparo:[String]
});

module.exports = mongoose.model('Receita', ReceitaSchema);