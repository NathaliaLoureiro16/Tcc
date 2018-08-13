var Receita = require('../models/Receita');
var Ingrediente = require('../models/Ingrediente');

exports.save = function(titulo, descricaoIngredientes , ingredientesBase, modoPreparo, callback) {
    const receita = new Receita({
        "titulo" : titulo,
        "descricaoIngredientes" : [descricaoIngredientes],
        "ingredientesBase" : [ingredientesBase],
        "modoPreparo":[modoPreparo]
    });
    receita.save(function(error, receita) {
        
        if(error) {
            callback({error : 'não foi possível salvar'});
        } else {
            callback(receita);
        }

    });
}

exports.getReceitasByIngredientes = function(ingredientes, callback) {

    Ingrediente.find({categoria: categoria}, function(error, receitas) {

        if (error) {
            callback({error:'não foi possível encontrar as receitas'});
        } else {
            callback(receitas);
        }
    });
}
/*
exports.getQuantidadeReceitasCompativeis(ingredientes, callback) {

};

exports.getReceitaById(id, callback) {

};
*/