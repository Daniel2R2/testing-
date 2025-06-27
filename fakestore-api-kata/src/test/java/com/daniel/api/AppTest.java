package com.daniel.api;

// Importamos las clases necesarias de JUnit para poder crear y ejecutar pruebas unitarias.
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Esta clase representa una prueba unitaria para la aplicación principal (App).
 * Aquí se definen los tests que verifican que el comportamiento básico de la aplicación sea correcto.
 */
public class AppTest 
    extends TestCase // Heredamos de TestCase para aprovechar las funcionalidades de JUnit.
{
    /**
     * Constructor de la clase de prueba.
     * Recibe el nombre del test y lo pasa a la clase base.
     * Esto permite identificar cada prueba por su nombre.
     *
     * @param testName nombre del caso de prueba
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * Este método estático crea una suite de pruebas.
     * Una suite es simplemente un conjunto de pruebas que se pueden ejecutar juntas.
     * Aquí, la suite contiene todas las pruebas definidas en esta clase.
     *
     * @return la suite de pruebas que se va a ejecutar
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Este es un test de ejemplo muy básico.
     * Solo verifica que la condición 'true' es verdadera, lo cual siempre pasa.
     * Sirve como plantilla o punto de partida para agregar pruebas más útiles.
     */
    public void testApp()
    {
        assertTrue( true );
    }
}
