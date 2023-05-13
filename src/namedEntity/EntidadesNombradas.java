package namedEntity;

class persona extends EntidadNombrada{
    int id;
    persona(String name, String category, int frequency, int id) {
        super(name, category, frequency);
        this.id = id;
    }
}

class lugar extends EntidadNombrada{
    lugar(String name, String category, int frequency, int id) {
        super(name, category, frequency);
    }
}

class organizacion extends EntidadNombrada{
    int NroMiembros;
    String tipoOrg;
    organizacion(String name, String category, int frequency, int NroMiembros, String tipoOrg){
        super(name, category, frequency);
        this.NroMiembros = NroMiembros;
        this.tipoOrg = tipoOrg;
    }
}

class producto extends EntidadNombrada{
    String Comercial;
    String Productor;
    producto(String name, String category, int frequency, String Comercial, String Productor) {
        super(name, category, frequency);
        this.Comercial = Comercial;
        this.Productor = Productor;
    }
}

class evento extends EntidadNombrada{
    String fecha;
    int recurrente;
    evento(String name, String category, int frequency, String fecha, int recurrente) {
        super(name, category, frequency);
        this.fecha = fecha;
        this.recurrente = recurrente;
    }
}

class fecha extends EntidadNombrada{
    String fechaPrecisa;
    fecha(String name, String category, int frequency, String fechaPrecisa) {
        super(name, category, frequency);
        this.fechaPrecisa = fechaPrecisa;
    }
}

class otro extends EntidadNombrada {
    String Comments;
    otro(String name, String category, int frequency, String Comments) {
        super(name, category, frequency);
        this.Comments = Comments;
    }
}

class apellido extends persona{
    String origen;
    apellido(String name, String category, int frequency, int id, String origen) {
        super(name, category, frequency, id);
        this.origen = origen;
    }
}

class nombre extends persona{
    String origen;
    String formasAlt;
    nombre(String name, String category, int frequency, int id, String origen, String formasAlt) {
        super(name, category, frequency, id);
        this.origen = origen;
        this.formasAlt = formasAlt;
    }
}

class titulo extends persona{
    String profesional;
    titulo(String name, String category, int frequency, int id, String profesional) {
        super(name, category, frequency, id);
        this.profesional = profesional;
    }
}

class pais extends lugar{
    int poblacion;
    String lenguaOficial;;
    pais (String name, String category, int frequency, int id, int poblacion, String lenguaOficial) {
        super(name, category, frequency, id);
        this.poblacion = poblacion;
        this.lenguaOficial = lenguaOficial;
    }
}

class ciudad extends lugar{
    pais pais;
    String capital;
    int poblacion;
    ciudad (String name, String category, int frequency, int id, pais pais, String capital, int poblacion) {
        super(name, category, frequency, id);
        this.pais = pais;
        this.capital = capital;
        this.poblacion = poblacion;
    }
}

class direccion extends lugar{
    ciudad ciudad;
    direccion (String name, String category, int frequency, int id, ciudad ciudad) {
        super(name, category, frequency, id);
        this.ciudad = ciudad;
    }
}

