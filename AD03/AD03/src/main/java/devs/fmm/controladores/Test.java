package devs.fmm.controladores;

import devs.fmm.DAO.GenericoDAO;
import devs.fmm.Entities.CiudadGen;
import devs.fmm.Entities.IdiomaPaisGen;
import devs.fmm.Entities.PaisGen;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
       /* CiudadDAO ciudadDAO = new CiudadDAO();
        Ciudad ciudad = new Ciudad(123, "Murcia", "ESP", "Sur", 300000);
        ArrayList<Ciudad> ciudades = ciudadDAO.getAllCiudades();
        for (Ciudad c : ciudades){
            System.out.println(c);
        }
        ciudadDAO.crearCiudad(ciudad);
        ciudades = ciudadDAO.getAllCiudades();
        for (Ciudad c : ciudades){
            System.out.println(c);
        }*/

       /* PaisDAO paisDAO = new PaisDAO();
        System.out.println("Codigos de país disponibles: ");
        ArrayList<String> codigosPais = paisDAO.getCodigosPais();
        for (String codigo : codigosPais) {
            System.out.printf("%s ", codigo);
        }
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 6; i++) {
            System.out.println("Introduce codigo");
            String codigo = sc.nextLine();
            System.out.println(codigosPais.contains(codigo));
        }*/


      /*  PaisDAO paisDAO = new PaisDAO();
        System.out.println("Campos disponibles");
        System.out.println("`Code`, `Name`, `Continent`, `Region`, `SurfaceArea`, `IndepYear`, `Population`, `LifeExpectancy`, `GNP`, `GNPOld`, `LocalName`, `GovernmentForm`, `HeadOfState`, `Capital`, `Code2`");

        ArrayList<Pais> paises = paisDAO.getPaisesBy("Code", "AAA");
        System.out.println(paises);*/


  /*      IdiomaPais ip1 = new IdiomaPais("AAA", ";DROP TABLE countrylanguage", true, 10.0);
        IdiomaPaisDAO idiomaPaisDAO = new IdiomaPaisDAO();
        idiomaPaisDAO.crearIdiomaPais(ip1);*/

      /*  GenericoDAO<Pais> gp = new GenericoDAO<>(Pais.class, "country");
        System.out.println();
        GenericoDAO<IdiomaPais> gip = new GenericoDAO<>(IdiomaPais.class, "countrylanguage");
        System.out.println();*/



      /*  GenericoDAO<CiudadGen> gc = new GenericoDAO<>(CiudadGen.class, "city");
        System.out.println();
        ArrayList<String> campos = new ArrayList<>();
        campos.add("Name");
        campos.add("CountryCode");
        campos.add("District");
        campos.add("Population");

        ArrayList<CiudadGen> resultado = gc.resultado(campos);

        System.out.printf("%-10s %34s %10s %20s %20s%n", "ID", "Nombre", "Codigo", "Distrito", "Poblacion");
        for(CiudadGen ciudadGen: resultado){
            System.out.println(ciudadGen);
        }
*/


      /*  GenericoDAO<PaisGen> gp = new GenericoDAO<>(PaisGen.class, "country");
        System.out.println();
        ArrayList<String> camposPais = new ArrayList<>();
        camposPais.add("Continent");
        camposPais.add("Region");
        camposPais.add("GNP");
        camposPais.add("HeadOfState");

        ArrayList<PaisGen> resultadoPais = gp.resultado(camposPais);

        System.out.printf("%s %30s %10s %22s %10s %9s %10s %10s %10s %10s %30s %40s %30s %10s %10s%n", "Codigo", "Nombre", "Continente", "Region", "Superficie", "AnioIndep", "Poblacion", "ExpVida", "GNP", "GNPOld", "NombreLocal", "FormaGobierno", "JefeEstado", "Capital", "Codigo2");

        for(PaisGen paisGen: resultadoPais){
            System.out.println(paisGen);
        }*/


       /* GenericoDAO<IdiomaPaisGen> ipg = new GenericoDAO<>(IdiomaPaisGen.class, "countrylanguage");
        System.out.println();
        ArrayList<String> camposIdiomaPais = new ArrayList<>();
        camposIdiomaPais.add("CountryCode");
        camposIdiomaPais.add("Language");
        camposIdiomaPais.add("Percentage");

        ArrayList<IdiomaPaisGen> resultadoIdiomaPais = ipg.resultado(camposIdiomaPais);

        System.out.printf("%10s %25s %15s %15s%n%n", "CodigoPais", "Idioma", "Es oficial", "Porcentaje");

        for(IdiomaPaisGen idiomaPaisGen: resultadoIdiomaPais){
            System.out.println(idiomaPaisGen);
        }*/
    }
}
