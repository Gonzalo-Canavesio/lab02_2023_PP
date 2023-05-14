package namedEntity.heuristic;

import java.util.Map;
import java.util.HashMap;

public abstract class Heuristic {

	// TODO: Crear un map que asocie el string de una entidad con un map (o un array) que contenga su categoria y otros datos para rellenar sus atributos
	
	// Map.of(
	// 		"Microsft", "Company", 
	// 		"Apple", "Company", 
	// 		"Google", "Company",
	// 		"Musk", "Person",
	// 		"Biden", "Person",
	// 		"Trump", "Person",
	// 		"Messi", "Person",
	// 		"Federer", "Person",
	// 		"USA", "Country",
	// 		"Russia", "Country"
	// 		);
	
	
	public String getCategory(String entity){
		return categoryMap.get(entity);
	}

	public abstract boolean isEntity(String word);
		
}
