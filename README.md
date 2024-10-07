# Money Talks

Bienvenido al convertidor precios de un conjunto de monedas usando [API de Exchange Rate](https://www.exchangerate-api.com/).

## Descripcion

El programa Money Talk presenta un menu con diversas alternativas de cambio de divisas. Luego de hacer la seleccion e ingresar el monto, se hace una consulta a una API que retorna el resultado directamente sin mayores calculos usando el punto de acceso:

```html
GET https://v6.exchangerate-api.com/v6/YOUR-API-KEY/pair/EUR/GBP/AMOUNT
```

El programa luego pregunta si se desea continuar y el ciclo se repite.

## Dependencias

Primero se investigo el tratamiento de dependencias en VSCode, las opciones Maven o Gradle parecen muy grandes para esta aplicacion.

- [vscode-java-dependency](https://github.com/microsoft/vscode-java-dependency?tab=readme-ov-file#manage-dependencies)

Luego, se intento agregar `GSON` pero la documentacion para `JSONObject` estaba mas disponible con abundantes ejemplos. Debido a que ejecutables o pre compilados (.jar) no se suben a Github, es necesario bajar esta dependencia en la carpeta `./lib` y si no esta la carpeta hay que crearla al mismo nivel de `./src`

- [JSON In Java » 20240303](https://mvnrepository.com/artifact/org.json/json/20240303)

## Tratamiento de errores

Se han implemntado algunos manejos de Errores y Excepciones, que luego de una investigacion preliminar parece abundante y compleja, por lo que el tratamiento de errores es mas bien limitado a `try-catch`, y cuando no a `throw error`.

## Folder Structure en VSCode

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management en VSCode

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).
