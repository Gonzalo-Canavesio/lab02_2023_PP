# Decisiones de diseño

## Generalizacion del parser

Primero pensamos definir una clase abstracta de la cual hereden todos los parsers, incluido SubscriptionParser.
Pero nos dimos cuenta que SubscriptionParser quizas no encaja tan bien con los parsers que devuelven un feed, como RssParser o RedditParser ya que devuelven tipos de datos distintos y hacen cosas ligeramente distintas.

Así que lo que se nos ocurrio es hacer una clase abstracta para los parsers que devuelvan un feed y otra para los parsers de configuracion.
Teniendo en cuenta que si por ejemplo en un futuro se decide añadir un nuevo formato de archivo para las configuraciones (por ejemplo YAML, TOML, etc) seria sencillo de agregar si ya tenemos una generalizacion para los parsers de subscripciones.

Ademas de esto tambien definimos una interfaz comun para todos los parsers utilizando genericos para poder tener una interfaz consistente en todos los parsers, independientemente del tipo de dato que devuelvan.

## Manejo de dependencias

Una de las primeras cosas de las que nos tuvimos que encargar al comenzar a trabajar en este laboratorio fue el investigar los metodos existentes para añadir librerias a un proyecto java.

Probamos un par de opciones, como Maven y Gradle, sin embargo nos parecio que añadian mucha mas complejidad de la que necesitabamos, por lo cual decidimos tomar el camino mas sencillo y crear un directorio donde pondriamos las dependencias externas del proyecto, para luego añadir dicho directorio al classpath a la hora de compilar.

## Compilacion

Como algunos de los integrantes del grupo tuvieron un par de problemas al momento de compilar el laboratorio decidimos crear un makefile sencillo que facilite la compilacion y ejecucion del codigo.

Optamos por elegir esta opcion por sobre usar alguna otra herramienta ya que era lo más sencillo que podiamos hacer sin añadir complejidad innecesaria.

## Crear producto cartesiano de entidades y temas

Como el crear el producto cartesiano de entidades y temas era un proceso tedioso y largo, decidimos elegir algunas entidades y algunos temas y utilizar un script auxiliar escrito en python para crear classes con todas las posibles combinaciones. Esto tiene el beneficio de que si en un futuro se decide agregar una nueva entidad o un nuevo tema, o si se quieren crear clases para todas las posibles combinaciones solo basta con agrear los nombres adecuados al script.

## Demasiadas clases para crear

Como debiamos crear demasiadas clases en el producto cartesiano entre entidades y temas, decidimos solo crear algunas de manera representativa y para que se vea que entendimos el concepto, pero luego se deberian extender y completar todas las clases en caso de que se quiera utilizar el proyecto de manera seria/profesional.

## Crear la clase RoughFeed

Sentimos que pasar directamente del pedido HTTP a la clase Feed era demasiado, por lo cual decidimos crear una clase intermedia llamada RoughFeed que representa un feed sin procesar, es decir, un feed que no tiene los articulos aún procesados pero si tienen alguna información ya procesada y organización. Esto permite que el codigo sea mas modular y separar mejor las responsabilidades, quitandole al main algunas responsabilidades que no deberia tener.

## (Punto extra) Implementación de parser para Reddit

Decidimos implementar un parser para Reddit ya que nos parecio que era una buena idea tener un parser para un sitio que no es un RSS, para demostrar que el codigo es facilmente extensible y que se puede agregar un parser para cualquier sitio web que se desee.

La detección de entidades nombradas no funciona muy bien en Reddit ya que los posts no tienen un formato estandarizado, por lo cual es dificil detectar entidades nombradas en los posts y decidimos no detectar entidades nombradas en los posts de Reddit.

## Limite de cantidad de caracteres en el texto/descripcion de un articulo

Al realizar el parser de Reddit, la mayoria de los posts tenian un texto muy largo, por lo cual decidimos limitar la cantidad de caracteres que se muestran en el texto/descripcion de un articulo a 250 caracteres, para que no se vea tan mal en la consola. También eliminamos los saltos de linea para que se vea mejor en la consola y no se vea tan desordenado.

## Mejoramos la detección de argumentos para la funcionalidad extra

Al principio la detección de argumentos para la funcionalidad extra de deteccion de entidades nombradas se activaba siempre que la llamada tuviera un argumento, pero luego nos dimos cuenta que esto no era lo que se queria, ya que se queria que se activara solo cuando se pasara el argumento --ne, por lo cual cambiamos la deteccion de argumentos para que se active solo cuando se pase el argumento --ne como primer argumento de la llamada y ese sea el unico argumento.

## Simular herencia multiple en java para utilizar las dos jerasquias de clases

Como java no permite herencia multiple, tuvimos que buscar una manera de simularla para poder utilizar las dos jerarquias de clases solicitadas en el enunciado (categoria y tema). 

Para esto utilizamos interfaces, ya que estas si permiten herencia multiple, y definimos una interfaz para cada tema. Las categorias se representan mediante clases y luego se realiza el producto cartesiano entre las categorias y los temas para obtener todas las posibles combinaciones.

## Cantidad de ocurrencias de entidades nombradas

En el enunciado se solicita que se muestre la cantidad de ocurrencias de cada entidad nombrada y para eso utilizamos un campo en la clase Entity que representa la cantidad de ocurrencias de dicha entidad nombrada.

El enunciado también solicita la cantidad de ocurrencias globales, por clase y por subclase, así que para eso utilizamos un campo estático en las diferentes clases que representa la cantidad de ocurrencias de todas las entidades nombradas de esa clase.

# Crítica al enunciado

Sentimos que en general el enunciado es poco claro, aunque los articulos que se nos presentaban en los archivos como ayuda nos sirvieron mucho de guia para saber como encarar el problema. El mayor problema fue el punto 4 del enunciado, para nosotros no se explica bien que es lo que se quiere que hagamos y además las funcionalidades solicitadas pueden llegar a ser antiiintuitivas sin una explicación más detallada o un contexto de uso. Capaz una buena mejora para próximos proyectos sería dar algunos ejemplos de uso de las funcionalidades solicitadas para que se entienda mejor que es lo que se quiere que hagamos y un poco de contexto para entender el porque lo que estamos haciendo es útil (Sobre todo nos estamos centrando en el punto 4 de la consigna, ya que los primeros 3 puntos estan bastante claros y algunos hasta tienen ejemplos que nos guiaron y ayudaron a entender).

