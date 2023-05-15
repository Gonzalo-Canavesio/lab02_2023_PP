package namedEntity;

class apellido extends persona{
    String origen;
    static int apellidoFrequency = 0;
    apellido(String name, String category, int frequency, int id, String origen) {
        super(name, category, frequency, id);
        this.origen = origen;
        apellidoFrequency++;
    }
}

class nombre extends persona{
    String origen;
    String formasAlt;
    static int nombreFrequency = 0;
    nombre(String name, String category, int frequency, int id, String origen, String formasAlt) {
        super(name, category, frequency, id);
        this.origen = origen;
        this.formasAlt = formasAlt;
        nombreFrequency++;
    }
}

class titulo extends persona{
    String profesional;
    static int tituloFrequency = 0;
    titulo(String name, String category, int frequency, int id, String profesional) {
        super(name, category, frequency, id);
        this.profesional = profesional;
        tituloFrequency++;
    }
}

class pais extends lugar{
    int poblacion;
    String lenguaOficial;
    static int paisFrequency = 0;
    pais (String name, String category, int frequency, int id, int poblacion, String lenguaOficial) {
        super(name, category, frequency, id);
        this.poblacion = poblacion;
        this.lenguaOficial = lenguaOficial;
        paisFrequency++;
    }
}

class ciudad extends lugar{
    pais pais;
    String capital;
    int poblacion;
    static int ciudadFrequency = 0;
    ciudad (String name, String category, int frequency, int id, pais pais, String capital, int poblacion) {
        super(name, category, frequency, id);
        this.pais = pais;
        this.capital = capital;
        this.poblacion = poblacion;
        ciudadFrequency++;
    }
}

class direccion extends lugar{
    ciudad ciudad;
    static int direccionFrequency = 0;
    direccion (String name, String category, int frequency, int id, ciudad ciudad) {
        super(name, category, frequency, id);
        this.ciudad = ciudad;
        direccionFrequency++;
    }
}

class OtroLugar extends lugar{
    static int OtroLugarFrequency = 0;
    OtroLugar(String name, String category, int frequency, int id) {
        super(name, category, frequency, id);
        OtroLugarFrequency++;
    }
}
