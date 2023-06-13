Nota 8
Falta una vuelta de rosca para utilizar las abstracciones dadas, pero quitando eso está muy bien. 

# Comentarios 

- La idea de un script para generar las clases es muy buena. 
- No tienen getters para tomar la frecuencia, que está accesible publicamente. 
- con -ne falla en ejecución (class not def). Creo que el problema es que tienen una interfaz `Otro` y una clase `otro`. Y el script python está generando clases rotas (o falta explicar cómo funciona?). 
- Se zarparon de abstracción con el SuscriptionParser :-) Tampoco tiene sentido la abstracción de GeneralParser, o al menos, no la tiene en este contexto (en ningún momento usan un parser que puede ser un SubscriptionParser o un FeedParser) 
- Buena la abstracción del FeedParser. 
- Las dos ramas del main tiene código repetido. Y doParse podría también evitar un poco de repetición, aprovechando la interfaz común de los parsers. 
