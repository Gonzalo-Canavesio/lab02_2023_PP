# Decisiones de diseño
## Generalizacion del parser
Primero pensamos definir una clase abstracta de la cual hereden todos los parsers,
incluido SubscriptionParser.
Pero nos dimos cuenta que SubscriptionParser quizas no encaja tan bien con los parsers que
devuelven un feed, como RssParser o RedditParser ya que devuelven tipos de datos
distintos y hacen cosas ligeramente distintas.

Así que lo que se nos ocurrio es hacer una clase abstracta para los parsers que devuelvan un
feed y otra para los parsers de configuracion.
Teniendo en cuenta que si por ejemplo en un futuro se decide añadir un nuevo
formato de archivo para las configuraciones (por ejemplo YAML, TOML, etc) seria
sencillo de agregar si ya tenemos una generalizacion para los parsers de subscripciones.

Ademas de esto tambien definimos una interfaz comun para todos los parsers utilizando
genericos para poder tener una interfaz consistente en todos los parsers,
independientemente del tipo de dato que devuelvan.

## Manejo de dependencias
Una de las primeras cosas de las que nos tuvimos que encargar al comenzar a
trabajar en este laboratorio fue el investigar los metodos existentes para
añadir librerias a un proyecto java.

Probamos un par de opciones, como Maven y Gradle, sin embargo nos parecio que
añadian mucha mas complejidad de la que necesitabamos, por lo cual decidimos tomar
el camino mas sencillo y crear un directorio donde pondriamos las dependencias externas
del proyecto, para luego añadir dicho directorio al classpath a la hora de compilar.

## Compilacion
Como algunos de los integrantes del grupo tuvieron un par de problemas al momento
de compilar el laboratorio decidimos crear un makefile sencillo que facilite la
compilacion y ejecucion del codigo.

Optamos por elegir esta opcion por sobre usar alguna otra herramienta ya que
era lo más sencillo que podiamos hacer sin añadir complejidad innecesaria.

## Crear producto cartesiano de entidades y temas
Como el crear el producto cartesiano de entidades y temas era un proceso tedioso
y largo, decidimos elegir algunas entidades y algunos temas y utilizar un script
auxiliar escrito en python para crear classes con todas las posibles combinaciones.
Esto tiene el beneficio de que si en un futuro se decide agregar una nueva entidad
o un nuevo tema, o si se quieren crear clases para todas las posibles combinaciones
solo basta con agrear los nombres adecuados al script.


