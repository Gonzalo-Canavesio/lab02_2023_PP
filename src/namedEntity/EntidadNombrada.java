package namedEntity;


/*Esta clase modela la nocion de entidad nombrada*/

public class EntidadNombrada {
	String name;
	String category;
	int frequency;
	String tema;
	static protected int EntidadNombradaFrequency = 0;

	public EntidadNombrada(String name, String category, int frequency) {
		super();
		this.name = name;
		this.category = category;
		this.frequency = frequency;
		EntidadNombradaFrequency++;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return name;
	}

	public void setCategory(String name) {
		this.name = name;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	public void incFrequency() {
		this.frequency++;
	}

	public int getEntidadNombradaFrequency() {
		return EntidadNombradaFrequency;
	}

	public void setEntidadNombradaFrequency(int entidadNombradaFrequency) {
		EntidadNombradaFrequency = entidadNombradaFrequency;
	}

	@Override
	public String toString() {
		return "ObjectNamedEntity [name=" + name + ", frequency=" + frequency + "]";
	}
	public void prettyPrint(){
		System.out.println(this.getName() + " " + this.getFrequency());
	}

	public void setTema(String tema) {
        this.tema = tema;
	}

	public String getTema() {
        return this.tema;
	}

	public EntidadNombrada createEntity(String namedEntity, String category){
		EntidadNombrada ne;
		if(category == null){
			ne = new EntidadNombrada(namedEntity, "unknown", 1);
		} else if(category == "persona"){
			ne = new persona(namedEntity, "persona", 1, 0);
		} else if(category == "lugar"){
			ne = new lugar(namedEntity, "lugar", 1);
		} else if(category == "organizacion"){
			ne = new organizacion(namedEntity, "organizacion", 1);
		} else if(category == "empresa"){
			ne = new empresa(namedEntity, "empresa", 1);
		} else if(category == "producto"){
			ne = new producto(namedEntity, "producto", 1);
		} else if(category == "obra"){
			ne = new obra(namedEntity, "obra", 1);
		} else if(category == "evento"){
			ne = new evento(namedEntity, "evento", 1);
		} else if(category == "tiempo"){
			ne = new tiempo(namedEntity, "tiempo", 1);
		} else if(category == "obra"){
			ne = new obra(namedEntity, "obra", 1);
		} else if(category == "obra"){
			ne = new obra(namedEntity, "obra", 1);
		} else if(category == "obra"){
			ne = new obra(namedEntity, "obra", 1);
		} else if(category == "obra"){
			ne = new obra(namedEntity, "obra", 1);
		} else if(category == "obra"){
			ne = new obra(namedEntity, "obra", 1);
		} else if(category == "obra"){
			ne = new obra(namedEntity, "obra", 1);
		} else if(category == "obra"){
			ne = new obra(namedEntity, "obra", 1);
		} else if(category == "obra"){
			ne = new obra(namedEntity, "obra", 1);
		} else {
			ne = new EntidadNombrada(namedEntity, "unknown", 1);
		}
		return ne;
	}
}


