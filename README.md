# Personajes Marvel
Marvel API app con patron MVI
La app muestra un listado con los primeros 50 personajes de marvel obtenidos con la llamada a sus servicios. Si se llega hasta el final del listado se vuelve a llamar de nuevo al servicio para obtener los siguientes 50 que son añadidos a continuacion de los 50 primeros.
Una vez se hace click en un personaje, se abre una nueva pantalla con la imagen, el nombre y la descripcion, si la hay, del personaje.

## MVI

MVI es la abreviatura de "Model, View, Intent". Durante el último año, este patrón de arquitectura recibió cada vez más atención entre los desarrolladores de Android. Es similar a otros patrones comúnmente conocidos como MVP o MVVM , pero introduce dos conceptos nuevos: la intención y el estado .
La intención es un evento enviado al modelo de vista por la vista para realizar una tarea en particular. Puede ser activado por el usuario o por otras partes de su aplicación. Como resultado de eso, se establece un nuevo estado en ViewModel que a su vez actualiza la interfaz de usuario. En la arquitectura MVI, View escucha el estado. Cada vez que cambia el estado, se notifica a la Vista.

## Como ejecutar el proyecto
Añade tus [Marvel Developer](https://developer.marvel.com/) API keys en apikey.properties en la raiz del proyecto.
```xml
PUBLIC_API_KEY="YOUR_PUBLIC_API_KEY"
    PRIVATE_API_KEY="YOUR_PRIVATE_API_KEY"
```
