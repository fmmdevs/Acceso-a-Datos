package devs.fmm;


import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    // Ejercicio 1. Crear estructura de directorios y ficheros
    static void ejercicio1() {
        // Creamos la ruta relativa al directorio raíz de nuestro proyecto usando Paths.get(String first, String... more),
        // Esto nos permite que simplemente introduciendo el nombre del primer subdirectorio y opcionalmente los siguientes
        // nos cree un objeto Path con la ruta correcta adaptada a nuestro sistema operativo.
        // (en Linux para separar directorios en rutas se usa / y en Windows \)
        Path directorios1 = Paths.get("Carpeta_1", "Carpeta_1_1");
        Path fichero11 = directorios1.resolve("fichero_1_1.txt");
        Path fichero12 = directorios1.resolve("../fichero_1_2.txt");

        Path directorios2 = Paths.get("Carpeta_2");
        Path fichero2 = directorios2.resolve("fichero_2.txt");


        Path directorios3 = Paths.get("Carpeta_3");
        Path fichero3 = directorios3.resolve("Carpeta_3_1.txt");

        try {
            Files.createDirectories(directorios1);
            //System.out.println("Directorio creado en: " + directorios1.toAbsolutePath());
            Files.createFile(fichero11);
            Files.createFile(fichero12);

            Files.createDirectory(directorios2);
            Files.createFile(fichero2);

            Files.createDirectory(directorios3);
            Files.createFile(fichero3);


        } catch (FileAlreadyExistsException e) {
            System.err.println("El archivo o directorio ya existe en la ruta: \"%s\". ".formatted(e.getMessage()));
        } catch (IOException e) {
            System.err.println("Error al crear la estructura de directorios y archivos. Verifica los permisos y la disponibilidad de la ruta. Detalle: ");
        }
    }

    // Ejercicio 2. Escribir en un fichero usando Java.io.File
    static void ejercicio2(String rutaArchivo) {
        // Texto que vamos a escribir
        String texto = "Fichero escrito utilizando Java.io.FileWriter";

        // Creamos Path con la ruta relativa al archivo desde la raiz del proyecto
        Path fichero = Paths.get(rutaArchivo);

        // Como FileWriter implementa la interfaz funcional AutoCloseable, podemos usar try-with-resources, que al salir
        // del bloque try llama al método close de FileWriter(en lugar de hacer esto en el bloque finally)
        try (FileWriter fileWriter = new FileWriter(fichero.toFile())) {

            fileWriter.write(texto);

        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo \"" + rutaArchivo + "\". " +
                    "Verifica que el archivo sea accesible y que tengas permisos de escritura. Detalle: " + e.getMessage());
        }
    }

    // Ejercicio 3. Copiar la información de un fichero usando Java.io.File
    static void ejercicio3(String rutaOrigen, String rutaDestino) {


        Path origen = Paths.get(rutaOrigen);
        Path destino = Paths.get(rutaDestino);

        try (
                // FileReader tambien implementa AutoCloseable por lo que podemos usar try-with-resources con ambas clases
                FileReader fileReader = new FileReader(origen.toFile());
                FileWriter fileWriter1 = new FileWriter(destino.toFile())
        ) {
            // Declaramos e inicializamos la variable int c que contiene el codigo numero al que corresponde el caracter
            // leido con el metodo read() de FileReader
            int c = fileReader.read();
            // Cuando llegemos al final del fichero el método read devuelve -1, utilizamos esta condicion de terminación
            // para recorrer todos los caracteres del fichero.
            while (c != -1) {
                // Escribimos el caracter que hemos leído en el fichero que hemos indicado.
                // Los char en Java internamente se representan con enteros y el metodo write nos permite utilizar directamente
                // el entero en lugar de tener que hacer casting a char.
                fileWriter1.write(c);

                // Obtenemos el siguiente caracter, si es distinto a -1 se repite el bucle.
                c = fileReader.read();
            }

        } catch (FileNotFoundException e) {
            System.err.println("Error: El archivo " + rutaOrigen + " no se encontró. " +
                    "Verifica que la ruta de origen sea correcta y que el archivo exista. Detalle: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error de E/S al copiar datos de " + rutaOrigen + " a " + rutaDestino + ". " +
                    "Verifica que los archivos sean accesibles y que tengas permisos de lectura/escritura. Detalle: " + e.getMessage());
        }
    }

    // Ejercicio 4. Utilizando Java.nio.Path mueve el directorio Carpeta_2 y su contenido al interior del directorio
    static void ejercicio4(String rutaDirOrigen, String rutaDirDestino) {
        // Carpeta_3_1

        Path dirOrigen = Paths.get(rutaDirOrigen);
        Path dirDestino = Paths.get(rutaDirDestino);
        try {
            // Con Files.move utilizamos dos objetos de tipo Path, el primero con la ruta del directorio que queremos
            // mover y el segundo con la ruta de destino
            Files.move(dirOrigen, dirDestino);
        } catch (IOException e) {
            System.err.println("Error al mover el directorio de " + rutaDirOrigen + " a " + rutaDirDestino + ". " +
                    "Verifica que el directorio de origen exista, que el de destino esté disponible, " +
                    "y que tengas permisos suficientes.");
        }

    }

    // Ejercicio 5. Utilizando Java.nio.Path copia la informacion de un fichero a otro
    static void ejercicio5(String rutaOrigen, String rutaDestino) {


        // Ruta del fichero que vamos a copiar los datos
        Path input = Paths.get(rutaOrigen);
        // Ruta del fichero donde vamos a escribir los datos copiados
        Path output = Paths.get(rutaDestino);

        try (BufferedReader inputReader = Files.newBufferedReader(input);
             BufferedWriter outputWriter = Files.newBufferedWriter(output);
        ) {
            String line = inputReader.readLine();
            while (line != null) {
                outputWriter.write(line);
                line = inputReader.readLine();
            }

        } catch (IOException e) {
            System.err.println("Error de E/S al copiar datos de " + rutaOrigen + " a " + rutaDestino + ". " +
                    "Verifica que los archivos existan y que tengas permisos de lectura/escritura. " +
                    "Detalle: " + e.getMessage());
        }
    }

    // Ejercicio 6. Construye un archivo xml a partir del fichero "Biblioteca.txt". Debe contener al menos dos niveles de nodos
    static void ejercicio6() {
        // Leermos el fichero Biblioteca.txt y obtenemos los datos


        // Creamos el documento
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();
            Document document = (Document) implementation.createDocument(null, "Biblioteca", null);
            document.setXmlVersion("1.0");

            // Creamos elemento raiz
            Element raiz = document.createElement("libros");

            // Añadimos el elemento raiz al documento
            document.getDocumentElement().appendChild(raiz);

            // Cada línea del archivo Biblioteca.txt corresponde con un elemento libro, este elemento libro, a su vez,
            // esta compuesto por los elementos titulo, autor, genero y año. He decidido que los datos del elemento libro
            // esten en subelementos como texto en lugar de en atributos del propio elemento libro por su versatilidad,
            // de esta forma, por ejemplo, un libro podria tener varios generos, todos incluidos en el elemento genero
            // Esta es la estructura:
       /*         <libros>
                    <libro>
                        <titulo></titulo>
                        <autor></autor>
                        <genero></genero>
                        <año></año>
                    </libro>
                </libros>
         */

            // Obtenemos los datos de Biblioteca.txt
            Path entrada = Paths.get("Biblioteca.txt");
            BufferedReader entradaReader = Files.newBufferedReader(entrada);
            String lineaLibro = entradaReader.readLine();
            String[] datosLibro;
            while (lineaLibro != null) {
                // Obtenemos los datos de los subelementos de libro en cada posicion del array
                datosLibro = lineaLibro.split(";");

                // Creamos elemento hijo libro
                Element libro = document.createElement("libro");

                // Creamos elementos hijos de libro
                Element titulo = document.createElement("titulo");
                Element autor = document.createElement("autor");
                Element genero = document.createElement("genero");
                Element año = document.createElement("año");

                // Creamos los Text para cada uno de los elementos con los datos que hemos extraido de la linea del .txt
                // el genero " autoayuda" del libro de Marian Rojas tenia un espacio por lo que me aseguro de eliminar
                // cada uno de los espacios que puedan aparecer delante o detras de cada elemento con trim
                Text textTitulo = document.createTextNode(datosLibro[0].trim());
                Text textAutor = document.createTextNode(datosLibro[1].trim());
                Text textGenero = document.createTextNode(datosLibro[2].trim());
                Text textAño = document.createTextNode(datosLibro[3].trim());

                // Relacionamos cada uno de los Text con correspondiente elemento
                titulo.appendChild(textTitulo);
                autor.appendChild(textAutor);
                genero.appendChild(textGenero);
                año.appendChild(textAño);

                // Los añadimos como hijos de libro
                libro.appendChild(titulo);
                libro.appendChild(autor);
                libro.appendChild(genero);
                libro.appendChild(año);

                // Añadimos libro como hijo del elemento raiz libros
                raiz.appendChild(libro);
                // Obtenemos la siguiente línea del txt, mientras no sea null (EOF) se repite el bucle.
                lineaLibro = entradaReader.readLine();
            }

            // Generamos el XML
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File("Biblioteca.xml"));
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            // Para que el fichero xml aparecza indentado
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);


        } catch (ParserConfigurationException e) {
            System.err.println("Error en la configuracion de parser XML:" + e);
        } catch (IOException e) {
            System.err.println("Error de entrada/salida al intentar leer el archivo XML. Verifica que el archivo exista y sea accesible:\n\t" + e);
        } catch (TransformerConfigurationException e) {
            System.err.println("Error en la configuración del transformador XML. Verifica las opciones de configuración del transformador:\n\t" + e);
        } catch (TransformerException e) {
            System.err.println("Error durante la transformación del XML. Verifica la estructura del documento y el destino de salida:\n\t" + e);
        }
    }

    // Ejercicio 7. Lee el XML "Peliculas.xml" y muestra los datos de forma legible por pantalla
    static void ejercicio7() {
        try {
            // Creamos el documento
            DocumentBuilderFactory factory1 = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory1.newDocumentBuilder();
            Document document = builder.parse(new File("Peliculas.xml"));

            // Normalizamos (quitamos espacios y retornos de carro en el texto)
            document.getDocumentElement().normalize();

            // Recogemos todas las etiquetas <Pelicula>
            NodeList peliculas = document.getElementsByTagName("Pelicula");

            // Declaramos las variables que van a contener los datos que estamos buscando del fichero xml
            String id, genero, estado, pais, titulo, director, estreno, nombrePremio, categoriaPremio, añoPremio, nombreActor, papelActor;
            // Recorremos los nodo Pelicula, obtenemos los datos
            for (int i = 0; i < peliculas.getLength(); i++) {
                System.out.println("---------------------------------------------------------------");
                // Obtenemos el primer nodo <pelicula>
                Node nodoPelicula = peliculas.item(i);
                if (nodoPelicula.getNodeType() == Node.ELEMENT_NODE) {
                    // Si es de tipo element
                    // to-do diferencias entre tipos de Node
                    Element pelicula = (Element) nodoPelicula;
                    id = pelicula.getAttribute("id");

                    // Datos Generales. Por que item(0)
                    Element datosGenerales = (Element) pelicula.getElementsByTagName("DatosGenerales").item(0);
                    genero = datosGenerales.getAttribute("genero");
                    estado = datosGenerales.getAttribute("estado");
                    pais = datosGenerales.getAttribute("pais");
                    titulo = datosGenerales.getElementsByTagName("Titulo").item(0).getTextContent();
                    director = datosGenerales.getElementsByTagName("Director").item(0).getTextContent();
                    estreno = datosGenerales.getElementsByTagName("Estreno").item(0).getTextContent();
                    //System.out.printf("ID=%s %nTitulo\t%s%n %s %s %s %s %s %n", id, titulo,genero, estado, pais, director, estreno);
                    System.out.print("""
                            Película %s : %s 
                                Genero: %s
                                País: %s
                                Director: %s
                                Estado: %s
                                %s
                                Premios: 
                            """.formatted(id, titulo, genero, pais, estado, director, estreno));

                    // Premios
                    NodeList listaPremios = pelicula.getElementsByTagName("Premio");
                    //System.out.println("\tPremios");
                    for (int j = 0; j < listaPremios.getLength(); j++) {
                        // No comprobamos si es Node.ELEMENT_NODE?
                        Element premio = (Element) listaPremios.item(j);
                        nombrePremio = premio.getAttribute("nombre");
                        categoriaPremio = premio.getAttribute("categoria");
                        añoPremio = premio.getAttribute("año");
                        System.out.printf("\t\t%s %s %s %n", nombrePremio, categoriaPremio, añoPremio);
                    }

                    // Actores Principales
                    NodeList listaActores = pelicula.getElementsByTagName("Actor");
                    System.out.println("\tActores Principales:");
                    for (int k = 0; k < listaActores.getLength(); k++) {
                        Element actor = (Element) listaActores.item(k);
                        nombreActor = actor.getAttribute("nombre");
                        papelActor = actor.getAttribute("papel");
                        System.out.printf("\t\t%s interpretando a %s%n", nombreActor, papelActor);
                    }

                }
            }

            System.out.println("---------------------------------------------------------------");
            // Recorremos nodos hijos de Pelicula (DatosGenerales, Premios, ActoresPrincipales) y obtenemos sus atributos

            // Recorremos subelementos de estos nodos hijos de Pelicula y obtenemos sus atributos


        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {


        ejercicio1();
        ejercicio2("Carpeta_1/Carpeta_1_1/fichero_1_1.txt");
        ejercicio3("Carpeta_1/Carpeta_1_1/fichero_1_1.txt", "Carpeta_3/Carpeta_3_1.txt");
        ejercicio4("Carpeta_2", "Carpeta_3/Carpeta_2");
        ejercicio5("Carpeta_3/Carpeta_3_1.txt", "Carpeta_3/Carpeta_2/fichero_2.txt");
        ejercicio6();
        ejercicio7();


    }
}
