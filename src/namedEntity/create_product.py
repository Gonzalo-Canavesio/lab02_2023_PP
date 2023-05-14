import itertools


def generate_java_classes(classes, interfaces, output_file):
    combinations = list(itertools.product(classes, interfaces))

    with open(output_file, 'w') as file:
        file.write("package namedEntity;\n\n")
        for class_name, interface_name in combinations:
            new_class = class_name + interface_name.title()
            file.write(
                f"class {new_class} extends {class_name} implements {interface_name} {{\n")
            file.write("    // Class implementation goes here\n")
            file.write("}\n\n")


if __name__ == "__main__":
    class_list = ["apellido", "nombre", "titulo",
                  "pais", "ciudad", "direccion", "OtroLugar"]
    interface_list = [
        "Deporte", "Futbol", "Basquet", "Tenis",
        "Formula1", "OtrosDeportes", "Cultura", "Cine", "Musica", "OtrosCultura",
        "Politica", "Nacional", "Internacional", "OtrosPolitica", "Otros"
    ]

    output_filename = "EntidadesConTema.java"
    generate_java_classes(class_list, interface_list, output_filename)
