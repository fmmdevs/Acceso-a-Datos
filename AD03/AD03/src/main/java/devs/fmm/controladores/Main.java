package devs.fmm.controladores;


import devs.fmm.DAO.CiudadDAO;
import devs.fmm.DAO.GenericoDAO;
import devs.fmm.DAO.IdiomaPaisDAO;
import devs.fmm.DAO.PaisDAO;
import devs.fmm.Entities.*;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        // Scanner para la entrada de datos por consola
        Scanner sc = new Scanner(System.in);
        // String con la opcion del menú
        String opcion;
        // Patter con un regex que solo nos permite los numeros del 1 al 8
        Pattern pattern = Pattern.compile("^[1-8]$");
        Matcher matcher;

        // Creamos los objetos de conexion solo una vez
        PaisDAO servicioPais = new PaisDAO();
        CiudadDAO servicioCiudad = new CiudadDAO();
        IdiomaPaisDAO servicioIdiomaPais = new IdiomaPaisDAO();

        // Para la opcion 7 del menu
        GenericoDAO<PaisGen> pg = new GenericoDAO<>(PaisGen.class, "country");
        GenericoDAO<IdiomaPaisGen> ipg = new GenericoDAO<>(IdiomaPaisGen.class, "countrylanguage");
        GenericoDAO<CiudadGen> cg = new GenericoDAO<>(CiudadGen.class, "city");

        do {
            System.out.println("""
                                        
                    1. Cargar la información en las tablas. (Se insertarán 5 filas en cada tabla)
                    2. Mostrar toda la información de una tabla (Se pedirá al usuario que escoja entre las tres disponibles)
                    3. Mostrar los países permitiendo filtrar por forma de gobierno (Se pedirá al usuario el campo forma de gobierno para filtrar).
                    4. Insertar una nueva ciudad solicitando uno a uno los datos necesarios
                    5. Borrar una ciudad a partir de su id. Antes de pedir el id, muestra todas las ciudades para que el usuario elija.
                    6. Modificar el país al que pertenece una ciudad
                    7. Extra: Consulta SELECT en cualquier tabla y cualquier campo
                    8. Salir 
                                        
                    """);

            System.out.println("Introduzca una opción del menú");
            opcion = sc.nextLine();
            matcher = pattern.matcher(opcion);
            if (!matcher.matches()) {
                System.err.println("Opción inválida. Introduzca un entero del 1 al 7");
            } else {
                switch (opcion) {
                    case "1":
                        System.out.println("Opcion 1");
                        Pais p1 = new Pais("AAA", "Alandia", "Europa", "Sur", 12342.5, 2024, 10000, 99.9, 1000, null, "Aaaaaaa", "Dictadura", "Antonio I", 12312, "AA");
                        Pais p2 = new Pais("BBB", "Blandia", "Asia", "Sur", 12342.5, 2024, 10000, 88.5, 1000, null, "beeeeee", "Republica bananera", "Anton", 11111, "BB");
                        Pais p3 = new Pais("CCC", "Clandia", "Asia", "Sur", 12342.5, 2024, 10000, 77.7, 1000, null, "ceeeeee", "Dictadura", "Victoria V", 22222, "CC");
                        Pais p4 = new Pais("DDD", "Dlandia", "Europa", "Sur", 12342.5, 2024, 10000, 98.3, 1000, null, "deeeee", "Republica bananera", "Bowie", 9999, "DD");
                        Pais p5 = new Pais("EEE", "Elandia", "Europa", "Sur", 12342.5, 2024, 10000, 98.2, 1000, null, "eeeeeeee", "Dictadura", "Borat", 12331, "EE");


                        Ciudad c1 = new Ciudad(100, "Alpha", "AAA", "Sur", 12312);
                        Ciudad c2 = new Ciudad(101, "Bravo", "BBB", "Este", 11111);
                        Ciudad c3 = new Ciudad(102, "Charlie", "CCC", "Suroeste", 22222);
                        Ciudad c4 = new Ciudad(103, "Delta", "DDD", "Nordeste", 9999);
                        Ciudad c5 = new Ciudad(104, "Echo", "EEE", "Norte", 12331);

                        IdiomaPais ip1 = new IdiomaPais("AAA", "Español", true, 99.9);
                        IdiomaPais ip2 = new IdiomaPais("BBB", "Español", true, 99.9);
                        IdiomaPais ip3 = new IdiomaPais("CCC", "Español", true, 99.9);
                        IdiomaPais ip4 = new IdiomaPais("DDD", "Español", true, 99.9);
                        IdiomaPais ip5 = new IdiomaPais("EEE", "Español", true, 99.9);

                        servicioPais.crearPais(p1);
                        servicioPais.crearPais(p2);
                        servicioPais.crearPais(p3);
                        servicioPais.crearPais(p4);
                        servicioPais.crearPais(p5);

                        servicioCiudad.crearCiudad(c1);
                        servicioCiudad.crearCiudad(c2);
                        servicioCiudad.crearCiudad(c3);
                        servicioCiudad.crearCiudad(c4);
                        servicioCiudad.crearCiudad(c5);

                        servicioIdiomaPais.crearIdiomaPais(ip1);
                        servicioIdiomaPais.crearIdiomaPais(ip2);
                        servicioIdiomaPais.crearIdiomaPais(ip3);
                        servicioIdiomaPais.crearIdiomaPais(ip4);
                        servicioIdiomaPais.crearIdiomaPais(ip5);

                        break;
                    case "2":
                        System.out.println("Opcion 2");
                        // Elegir tabla
                        System.out.println("""
                                Elija una tabla 
                                1. Tabla city (mostrar ciudades)
                                2. Tabla country (mostrar paises)
                                3. Tabla countrylanguage (mostrar los idiomas que se hablan en cada país)
                                4. Volver al menú principal""");
                        // Regex para comprobar que la opcion2 es un entero del 1 al 4
                        String opcion2 = sc.nextLine();
                        Pattern pattern2 = Pattern.compile("^[1-4]$");
                        Matcher matcher2 = pattern2.matcher(opcion2);
                        if (!matcher2.matches()) {
                            System.err.println("Opcion inválida. Introduzca un entero del 1 al 4");
                        } else {
                            switch (opcion2) {
                                case "1":
                                    // Obtenemos la consulta y la mostramos alineada
                                    ArrayList<Ciudad> ciudades = servicioCiudad.getAllCiudades();
                                    System.out.printf("%-10s %34s %10s %20s %20s%n", "ID", "Nombre", "Codigo", "Distrito", "Poblacion");
                                    for (Ciudad c : ciudades) {
                                        System.out.print(c);
                                    }
                                    break;
                                case "2":
                                    // Obtenemos la consulta y la mostramos alineada
                                    ArrayList<Pais> paises = servicioPais.getAllPaises();
                                    System.out.printf("%s %30s %10s %22s %10s %9s %10s %10s %10s %10s %30s %40s %30s %10s %10s%n", "Codigo", "Nombre", "Continente", "Region", "Superficie", "AnioIndep", "Poblacion", "ExpVida", "GNP", "GNPOld", "NombreLocal", "FormaGobierno", "JefeEstado", "Capital", "Codigo2");

                                    for (Pais p : paises) {
                                        System.out.println(p);
                                    }
                                    break;

                                case "3":
                                    // Obtenemos la consulta y la mostramos alineada
                                    ArrayList<IdiomaPais> idiomasPaises = servicioIdiomaPais.getAllIdiomasPaises();
                                    System.out.printf("%10s %25s %15s %15s%n%n", "CodigoPais", "Idioma", "Es oficial", "Porcentaje");

                                    for (IdiomaPais ip : idiomasPaises) {
                                        System.out.println(ip);
                                    }
                                    break;
                                case "4":
                                    break;
                            }
                        }
                        break;
                    case "3":
                        System.out.println("Opcion 3");
                        System.out.println("Filtrar paises por forma de gobierno");

                        // Consulta que nos permite saber cuales son las distintas formas de gobierno en la tabla country.
                        //SELECT DISTINCT GovernmentForm FROM `country`;

                        // Evitamos que el usuario introduzca directamente la forma de gobierno con mostrando una lista
                        System.out.println("""
                                Elija forma de gobierno:
                                1.  República
                                2.  Coprincipado parlamentario
                                3.  República Federal
                                4.  Monarquía Constitucional, Federación
                                5.  Federación
                                6.  Monarquía Constitucional
                                7.  Parte de Dinamarca
                                8.  Territorio dependiente de Reino Unido
                                9.  Territorio dependiente de Noruega
                                10. Estado Eclesiástico Independiente
                                11. Volver al menú principal
                                """);

                        // keep it up!
                        String opcion3 = sc.nextLine();
                        // Regex que solo permite enteros del 1 al 11.
                        // (Se podría simplemente haber pueste el error en el default de switch, en lugar de tanto regex)
                        Pattern pattern3 = Pattern.compile("^([1-9])|(10)|(11)$");
                        Matcher matcher3 = pattern3.matcher(opcion3);
                        if (!matcher3.matches()) {
                            System.err.println("Opcion inválida. Introduzca un entero del 1 al 11");
                        } else {
                            ArrayList<Pais> paises = new ArrayList<>();
                            switch (opcion3) {
                                case "1":
                                    paises = servicioPais.getPaisesByFormaGobierno("Republic");
                                    break;
                                case "2":
                                    paises = servicioPais.getPaisesByFormaGobierno("Parliamentary Coprincipality");
                                    break;

                                case "3":
                                    paises = servicioPais.getPaisesByFormaGobierno("Federal Republic");
                                    break;
                                case "4":
                                    paises = servicioPais.getPaisesByFormaGobierno("Constitutional Monarchy, Federation");
                                    break;
                                case "5":
                                    paises = servicioPais.getPaisesByFormaGobierno("Federation");
                                    break;
                                case "6":
                                    paises = servicioPais.getPaisesByFormaGobierno("Constitutional Monarchy");
                                    break;
                                case "7":
                                    paises = servicioPais.getPaisesByFormaGobierno("Part of Denmark");
                                    break;
                                case "8":
                                    paises = servicioPais.getPaisesByFormaGobierno("Dependent Territory of the UK");
                                    break;
                                case "9":
                                    paises = servicioPais.getPaisesByFormaGobierno("Dependent Territory of Norway");
                                    break;
                                case "10":
                                    paises = servicioPais.getPaisesByFormaGobierno("Independent Church State");
                                    break;
                                case "11":
                                    break;
                            }
                            // Si la opcion no es la 11 mostramos por consola el resultado
                            if (!opcion3.equals("11")) {

                                System.out.printf("%s %30s %10s %22s %10s %9s %10s %6s %10s %10s %30s %40s %30s %10s %10s%n", "Codigo", "Nombre", "Continente", "Region", "Superficie", "AnioIndep", "Poblacion", "ExpVida", "GNP", "GNPOld", "NombreLocal", "FormaGobierno", "JefeEstado", "Capital", "Codigo2");

                                for (Pais p : paises) {
                                    System.out.println(p);
                                }
                            }
                        }
                        break;
                    case "4":

                        String id;
                        String nombre, codigoPais, distrito, poblacion;

                        System.out.println("Opcion 4. Crear Ciudad");
                        System.out.println("Introduzca el ID. Entero entre 1 y 999999");

                        id = sc.nextLine();
                        Pattern pattern4 = Pattern.compile("^([1-9])|(\\d{2,6})$");
                        Matcher matcher4 = pattern4.matcher(id);

                        if (!matcher4.matches()) {
                            System.err.println("Ha introducido un ID no válido.");
                            break;
                        } else {
                            System.out.println("Introduzca el nombre de la ciudad");
                            nombre = sc.nextLine();

                            pattern4 = Pattern.compile("(\\w[ ]?)+");
                            matcher4 = pattern4.matcher(nombre);
                            if (!matcher4.matches()) {
                                System.err.println("Ha introducido un nombre de ciudad no válido.");
                                break;
                            } else {
                                //System.out.println("Introduzca el código del pais (3 letras)");
                                System.out.println("Codigos de país disponibles: ");
                                ArrayList<String> codigosPais = servicioPais.getCodigosPais();
                                int i = 1;
                                for (String codigo : codigosPais) {
                                    i++;
                                    if (i % 8 == 0) {
                                        System.out.printf("%s ", codigo);
                                    } else {
                                        System.out.println();
                                    }
                                }
                                System.out.println();
                                System.out.println("Introduzca uno de los códigos de país de los disponibles");
                                codigoPais = sc.nextLine().toUpperCase();

                                /*pattern4 = Pattern.compile("^[A-Z]{3}$");
                                matcher4 = pattern4.matcher(codigoPais);*/

                                // Al tener los codigos de Pais puedo comprobar si el codigo de pais introducido es válido
                                // con el metodo contains, en lugar de usar regex
                                if (!codigosPais.contains(codigoPais)) {
                                    System.err.println("Ha introducido código de país no válido");
                                    break;
                                } else {
                                    System.out.println("Introduzca el distrito");
                                    distrito = sc.nextLine();
                                    pattern4 = Pattern.compile("(\\w[ ]?)+");
                                    matcher4 = pattern4.matcher(distrito);
                                    if (!matcher4.matches()) {
                                        System.err.println("Ha introducido un nombre de distrito no válido");
                                    } else {
                                        System.out.println("Introduzca la población. (Entero del 1 al 9999999)");
                                        poblacion = sc.nextLine();
                                        pattern4 = Pattern.compile("^([1-9])|(\\d{2,7})$");
                                        matcher4 = pattern4.matcher(poblacion);
                                        if (!matcher4.matches()) {
                                            System.err.println("Ha introducido un número de población no válido");
                                        } else {
                                            Ciudad c = new Ciudad(Integer.parseInt(id), nombre, codigoPais, distrito, Integer.parseInt(poblacion));
                                            servicioCiudad.crearCiudad(c);
                                        }
                                    }
                                }
                            }
                        }
                        break;

                    case "5":
                        System.out.println("Opcion 5. Borrar Ciudad por id");
                        ArrayList<Ciudad> ciudades = servicioCiudad.getAllCiudades();
                        System.out.printf("%-10s %34s %10s %20s %20s%n", "ID", "Nombre", "Codigo", "Distrito", "Poblacion");
                        for (Ciudad c : ciudades) {
                            System.out.print(c);
                        }
                        System.out.println("Introduzca el ID de la ciudad que desea borrar");
                        id = sc.nextLine();
                        Pattern pattern5 = Pattern.compile("^([1-9])|(\\d{2,6})$");
                        Matcher matcher5 = pattern5.matcher(id);
                        if (!matcher5.matches()) {
                            System.err.println("El ID no es válido");
                        } else if (servicioCiudad.borrarCiudad(Integer.parseInt(id))) {
                            System.out.println("Ciudad con id %s borrada con éxito".formatted(id));
                        }
                        break;
                    case "6":
                        System.out.println("Opcion 6. Modificar País al que pertenece una Ciudad (cambiando el CodigoPais)");
                        System.out.println("Códigos de paises disponibles: ");
                        ArrayList<String> paisesNombreCodigo = servicioPais.getCodigosNombrePais();
                        int i = 1;
                        for (String paises : paisesNombreCodigo) {
                            if (i % 8 == 0) {
                                System.out.println();
                            } else {
                                System.out.print(paises + " ");

                            }
                            i++;
                        }

                        System.out.println("\nCiudades disponibles:");
                        ciudades = servicioCiudad.getAllCiudades();
                        System.out.printf("%-10s %34s %10s %20s %20s%n", "ID", "Nombre", "Pais", "Distrito", "Poblacion");
                        int n = 1;
                        for (Ciudad c : ciudades) {
                            System.out.print(c);
                        }

                        System.out.println("Introduzca el ID de la ciudad que desea cambiar de País");
                        id = sc.nextLine();
                        System.out.println("Introduzca código del nuevo País de entre los que hay disponibles");
                        codigoPais = sc.nextLine();

                        ArrayList<String> codigosDisponibles = servicioPais.getCodigosPais();

                        if (!codigosDisponibles.contains(codigoPais)) {
                            System.err.println("El codigo de pais no esta entre los disponibles");
                        } else {
                            //
                            servicioCiudad.cambiarPaisCiudad(Integer.valueOf(id), codigoPais);
                        }

                        break;
                    case "7":
                        
                        System.out.println("Tablas disponibles");
                        // Obtenemos la lista de tablas permitidas de cualquier objeto GenericoDAO
                        System.out.println(pg.getTablasPermitidas());
                        System.out.println("Introduzca la tabla de la que desea hacer la consulta");
                        String tabla = sc.nextLine();
                        // Comprobamos que la tabla este entre las permitidas para evitar inyecciones de código
                        if (!pg.getTablasPermitidas().contains(tabla)) {
                            System.err.println("Tabla no permitida");
                        } else {
                            System.out.println("Campos disponibles de la tabla " + tabla);
                            // El nombre de los campos de cada una de las tablas y se los mostramos al usuario
                            ArrayList<String> camposPais = pg.getCampos();
                            ArrayList<String> camposIdiomaPais = ipg.getCampos();
                            ArrayList<String> camposCiudad = cg.getCampos();
                            if (tabla.equals("country")) System.out.println(camposPais);
                            if (tabla.equals("countrylanguage")) System.out.println(camposIdiomaPais);
                            if (tabla.equals("city")) System.out.println(camposCiudad);
                            System.out.println("Introduza los campos que desee separados por un espacio");

                            // Obtenemos un array con los campos a partir de la entrada que le hemos indicado al usuario
                            String[] campos = sc.nextLine().split(" ");

                            ArrayList<String> camposDeseados = new ArrayList<>();
                            // Transformamos el String[] a ArrayList<String>
                            for (String campo : campos) {
                                camposDeseados.add(campo);
                            }

                            // flag que indica si el usuario ha introducido un campo no válido
                            boolean campoInvalido = false;

                            // Según el nombre de la tabla comprobamos el nombre de los campos introducidos,
                            // si alguno no es válido salimos al menú principal y no permitimos que se realice
                            // una consulta con cualquier cadena que no este entre los campos válidos.
                            if (tabla.equals("country")) {
                                // Comprobamos que los campos son válidos
                                for (String campo : campos) {

                                    if (!camposPais.contains(campo)) {
                                        System.err.println("El campo %s no es válido".formatted(campo));
                                        campoInvalido = true;
                                        break;
                                    } else {
                                        camposDeseados.add(campo);
                                    }

                                }

                                // Si algún campo no es válido no permitimos que ejecuta la sentencia SQL
                                if (campoInvalido) break;

                                // Obtenemos el resultado, mostramos el nombre de los campos seguido de cada
                                // uno de los registros. Si algún campo no se ha solicitado su valor aparecerá con guiones
                                ArrayList<PaisGen> resultado = pg.getAnything(camposDeseados);
                                System.out.printf("%s %30s %10s %22s %10s %9s %10s %10s %10s %10s %30s %40s %30s %10s %10s%n", "Codigo", "Nombre", "Continente", "Region", "Superficie", "AnioIndep", "Poblacion", "ExpVida", "GNP", "GNPOld", "NombreLocal", "FormaGobierno", "JefeEstado", "Capital", "Codigo2");

                                for (PaisGen paisGen : resultado) {
                                    System.out.println(paisGen);
                                }
                            } else if (tabla.equals("city")) {
                                // Comprobamos que los campos son validos
                                for (String campo : campos) {

                                    if (!camposCiudad.contains(campo)) {
                                        System.err.println("El campo %s no es válido".formatted(campo));
                                        campoInvalido = true;
                                        break;
                                    } else {
                                        camposDeseados.add(campo);
                                    }

                                }

                                if (campoInvalido) break;

                                ArrayList<CiudadGen> resultado = cg.getAnything(camposDeseados);

                                System.out.printf("%-10s %34s %10s %20s %20s%n", "ID", "Nombre", "Codigo", "Distrito", "Poblacion");

                                for (CiudadGen ciudadGen : resultado) {
                                    System.out.println(ciudadGen);
                                }

                            } else if (tabla.equals("countrylanguage")) {
                                // Comprobamos que los campos son validos
                                for (String campo : campos) {

                                    if (!camposIdiomaPais.contains(campo)) {
                                        System.err.println("El campo %s no es válido".formatted(campo));
                                        campoInvalido = true;
                                        break;
                                    } else {
                                        camposDeseados.add(campo);
                                    }

                                }

                                if (campoInvalido) break;

                                ArrayList<IdiomaPaisGen> resultado = ipg.getAnything(camposDeseados);

                                System.out.printf("%10s %25s %15s %15s%n%n", "CodigoPais", "Idioma", "Es oficial", "Porcentaje");

                                for (IdiomaPaisGen idiomaPaisGen : resultado) {
                                    System.out.println(idiomaPaisGen);
                                }
                            }

                        }

                }
            }

        }
        while (!opcion.equals("8"));

        System.out.println("¡Hasta pronto!");

    }
}