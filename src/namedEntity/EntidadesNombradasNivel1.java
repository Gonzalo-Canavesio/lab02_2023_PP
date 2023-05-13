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

