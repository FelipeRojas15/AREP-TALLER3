# Laboratory 3
> For this laboratory you will find the implementation of a java server with a maven project structure. 
Where the server is the one that waits for the client connection by the port 36000 and if it is by local web heroku by the 5000, and the client is the one that launches the requests to the machine where the server is running by the mentioned port. After connection is established it is treated as an I/O stream

## Requirements
You need to have it installed on your computer:
```sh
$ Java 1.8
$ Maven 3.6.3
$ Netbeans or IDE Java of preference
```
with its respective configuration

## Instalation and execute project
> **Clone the repository:** 
```sh
$ https://github.com/FelipeRojas15/AREP-TALLER3
```
> **Compile the project:**
```sh
$ mvn clean install
$ mvn package 
```
> **Run the program:** 
```sh
$ java -cp target/MyWebServer-1.0-SNAPSHOT.jar edu.escuelaing.arep.SparkB
```
> **Open the application with heroku:** 
- Home Page
 ```sh
 $ https://pacific-peak-25979.herokuapp.com/index.html
```
- Image Page
![startAplication](https://i.ibb.co/HgKgZq7/inicio-Formulario.png)
```sh
$ https://pacific-peak-25979.herokuapp.com/pinguino.png
```
![imageAplication](https://i.ibb.co/h8rJLk9/pinguino.png)

## Design of the project
![Diagarama de Clases](https://i.ibb.co/8Km4bRm/diagrama-Lab3-Arep.png)
## License
> This project has a License for MIT, for more details click on [license](https://github.com/FelipeRojas15/AREP-TALLER3/blob/master/LICENSE.txt)

## Autor 
> **Brayan Felipe Rojas Bernal**











