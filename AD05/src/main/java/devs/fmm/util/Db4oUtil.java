package devs.fmm.util;

import java.io.File;

public class Db4oUtil {
    public static final String PATH = "." + File.separator + "DB4O";
    public static final String EXTENSION = ".db4o";
    public static final String CLIENTE_PATH = PATH + File.separator + "Cliente" + EXTENSION;
    public static final String PRODUCTO_PATH = PATH + File.separator + "Producto" + EXTENSION;
    public static final String VENTA_PATH = PATH + File.separator + "Venta" + EXTENSION;
    public static final String VENTA_CLIENTE_PATH = PATH + File.separator + "Venta_Cliente" + EXTENSION;

    private Db4oUtil() {
    }
}
