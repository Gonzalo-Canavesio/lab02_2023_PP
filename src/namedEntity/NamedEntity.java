package namedEntity;


/*Esta clase modela la nocion de entidad nombrada*/

public class NamedEntity {
	String name;
	String category; 
	int frequency;
	
	public NamedEntity(String name, String category, int frequency) {
		super();
		this.name = name;
		this.category = category;
		this.frequency = frequency;
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

	@Override
	public String toString() {
		return "ObjectNamedEntity [name=" + name + ", frequency=" + frequency + "]";
	}
	public void prettyPrint(){
		System.out.println(this.getName() + " " + this.getFrequency());
	}

	public class persona extends NamedEntity{
		private int id;
		public persona(String name, String category, int frequency, int id) {
			super(name, category, frequency);
			this.id = id;
		}
	}
	public class lugar extends NamedEntity{
		public lugar(String name, String category, int frequency, int id) {
			super(name, category, frequency);
		}
	}
	public class organizacion extends NamedEntity{
		private int NroMiembros;
		private String tipoOrg;
		public organizacion(String name, String category, int frequency, int NroMiembros, String tipoOrg){
			super(name, category, frequency);
			this.NroMiembros = NroMiembros;
			this.tipoOrg = tipoOrg;
		}
	}
	public class producto extends NamedEntity{
		private String Comercial;
		private String Productor;
		public producto(String name, String category, int frequency, String Comercial, String Productor) {
			super(name, category, frequency);
			this.Comercial = Comercial;
			this.Productor = Productor;
		}
	}
	public class evento extends NamedEntity{
		private String fecha;
		private int recurrente;
		public evento(String name, String category, int frequency, String fecha, int recurrente) {
			super(name, category, frequency);
			this.fecha = fecha;
			this.recurrente = recurrente;
		}
	}
	public class fecha extends NamedEntity{
		private String fechaPrecisa;
		public fecha(String name, String category, int frequency, String fechaPrecisa) {
			super(name, category, frequency);
			this.fechaPrecisa = fechaPrecisa;
		}
	}
	public class otro extends NamedEntity {
		private String Comments;
		public otro(String name, String category, int frequency, String Comments) {
			super(name, category, frequency);
			this.Comments = Comments;
		}
	}
	public class apellido extends persona{
		private String origen;
		public apellido(String name, String category, int frequency, int id, String origen) {
			super(name, category, frequency, id);
			this.origen = origen;
		}
	}
	public class nombre extends persona{
		private String origen;
		private String formasAlt;
		public nombre(String name, String category, int frequency, int id, String origen, String formasAlt) {
			super(name, category, frequency, id);
			this.origen = origen;
			this.formasAlt = formasAlt;
		}
	}
	public class titulo extends persona{
		private String profesional;
		public titulo(String name, String category, int frequency, int id, String profesional) {
			super(name, category, frequency, id);
			this.profesional = profesional;
		}
	}
	public class pais extends lugar{
		private int poblacion;
		private String lenguaOficial;;
		public pais (String name, String category, int frequency, int id, int poblacion, String lenguaOficial) {
			super(name, category, frequency, id);
			this.poblacion = poblacion;
			this.lenguaOficial = lenguaOficial;
		}
	}
	public class ciudad extends lugar{
		private pais pais;
		private String capital;
		private int poblacion;
		public ciudad (String name, String category, int frequency, int id, pais pais, String capital, int poblacion) {
			super(name, category, frequency, id);
			this.pais = pais;
			this.capital = capital;
			this.poblacion = poblacion;
		}
	}
	public class direccion extends lugar{
		private ciudad ciudad;
		public direccion (String name, String category, int frequency, int id, ciudad ciudad) {
			super(name, category, frequency, id);
			this.ciudad = ciudad;
		}
	}
}



