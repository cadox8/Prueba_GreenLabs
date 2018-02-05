# Prueba GreenLabs

Pequeño proyecto que se me pidió como prueba para acceder al equipo de desarrollo de GreenLab (antiguo Minplay), que nunca fue utilizado.

No está probado, por lo que su funcionamiento es desconocido, además de que (creo) está inacabado.

## Requisitos
- Java 8.
- Maven 3.
- Una base de datos MongoDB
- Spigot 1.12 (debería funcionar en todas las versiones, si es que funciona...)

## Compilación
Instala dependencias y compila.

```sh
$ mvn clean install
```

## Información

Toda la información obtenida para hacer el plugin (documentación de MongoDB)
 ha sido sacada de [mongodb.github.io](http://mongodb.github.io/mongo-java-driver/2.13/getting-started/quick-tour/)
 y [mkyong.com](https://www.mkyong.com/mongodb/java-mongodb-update-document/).
 
 Yo, cadox8, uso Lombok durante el desarrollo del plugin, ya que agiliza la escritura de código estiliza las distintas clases.
 [Lombok](https://projectlombok.org)
