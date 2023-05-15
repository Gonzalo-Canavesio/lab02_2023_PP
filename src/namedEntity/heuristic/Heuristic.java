package namedEntity.heuristic;

import java.util.Map;
import java.util.HashMap;

public abstract class Heuristic {

	// TODO: Crear un map que asocie el string de una entidad con un map (o un array) que contenga su categoria y otros datos para rellenar sus atributos
	private Map<String, Map<String, Object>> entityMap = new HashMap<>();
	public Heuristic() {
		initializeEntityMap();
	}
	private void initializeEntityMap() {
		entityMap.put("Messi", Map.of("categoria", "Apellido", "tema", "Futbol", "id", "10", "origen", "italiano"));
		entityMap.put("Lionel", Map.of("categoria", "Nombre", "tema", "Futbol", "id", "10", "origen", "frances"));
		entityMap.put("Spielberg", Map.of("categoria", "Apellido", "tema", "Cine", "id", "11", "origen", "aleman"));
		entityMap.put("Steven", Map.of("categoria", "Nombre", "tema", "Cine", "id", "11", "origen", "griego"));
		entityMap.put("Fernandez", Map.of("categoria", "Apellido", "tema", "Nacional", "id", "12", "origen", "español"));
        entityMap.put("Manchester",Map.of("categoria","Ciudad","tema","Futbol", "id", "13", "pais", "Inglaterra", "capital", "", "poblacion", "530.300"));
		entityMap.put("USA", Map.of("categoria", "Pais", "tema", "Cine", "id", "14", "capital", "Washington", "poblacion", "328.200.000"));
		entityMap.put("Cordoba", Map.of("categoria", "Ciudad", "tema", "Nacional", "id", "15", "pais", "Argentina", "capital", "Cordoba", "poblacion", "1.330.023"));
	}

	public String getCategory(String entity) {
		Map<String, Object> entityData = entityMap.get(entity);
		if (entityData != null) {
			return (String) entityData.get("category");
		}
		return null;
	}

	public abstract boolean isEntity(String word);

}
