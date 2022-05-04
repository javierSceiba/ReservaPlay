package persistencia;

import com.google.common.collect.ImmutableMap;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import play.db.Database;
import play.db.Databases;
import play.test.WithApplication;
import play.db.evolutions.Evolutions;

public class BaseDeDatos extends WithApplication {

    private static Database db;

    public Database getDb() {
        return db;
    }

    @BeforeClass
    public static void conexionBaseDeDatos() {
        Config configuracion = ConfigFactory.load("application");
        String driver = configuracion.getString("db-prueba.driver");
        String url = configuracion.getString("db-prueba.url");
        String usuario = configuracion.getString("db-prueba.username");
        String contrasena = configuracion.getString("db-prueba.password");

        db = Databases.createFrom(
                driver,
                url,
                ImmutableMap.of(
                        "username", usuario,
                        "password", contrasena
                )
        );
    }


    @AfterClass
    public static void cerrarConexionBaseDeDatos() {
        Evolutions.cleanupEvolutions(db);
        db.shutdown();
    }
}
