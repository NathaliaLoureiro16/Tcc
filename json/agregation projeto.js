db.getCollection("receitas").aggregate(

	// Pipeline
	[
		// Stage 1
		{
			$match: { "ingredientesBase": { "$in": [ "ovo", "óleo", "leite" ] } }
		},

		// Stage 2
		{
			$project: {
			  "titulo": 1,
			  "descricaoIngredientes": 1,
			  "ingredientesBase": 1,
			  "modoPreparo": 1,
			    "quantidadeEncontrada": {
			      "$size": {
			        "$setIntersection": [ ["leite", "ovo", "óleo"], "$ingredientesBase" ]
			      }
			    }
			}
		},

		// Stage 3
		{
			$sort: {
				quantidadeEncontrada: -1
			}
		},

		// Stage 4
		{
			$count: "quantidadeReceitas"
		},

	]

	// Created with Studio 3T, the IDE for MongoDB - https://studio3t.com/

);
