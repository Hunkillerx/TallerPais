/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PaísTest.java,v 1.0 2017/02/17 08:09 lacobo Exp $
 * Universidad EAN (Bogotá - Colombia)
 * Departamento de Sistemas - Facultad de Ingeniería
 * Licenciado bajo el esquema Academic Free License version 2.1
 * <p>
 * Ejercicio: Pais
 * Autor: Luis Cobo - Ago 27, 2017
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package ean.programacionavanzada.pais

import ean.programacionavanzada.pais.Utils.obtenerDepartamentos
import ean.programacionavanzada.pais.Utils.obtenerMunicipios
import org.apache.commons.lang3.builder.ToStringExclude
import org.testng.Assert.assertEquals
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

internal class PaisTest {
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------
    private val pais: Pais = Pais()

    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------

    /**
     * Configura el objeto país con el cual desarrollaremos los diversos ejercicios
     * dentro del taller.
     */
    @BeforeMethod
    fun setUp() {
        obtenerDepartamentos(pais)
        obtenerMunicipios(pais)
    }

    @Test
    fun test1() {
        val n = pais.numDepartamentos()

        assertEquals(33, n, "El numero de deptos esta mal!")
    }

    @Test
    fun test3() {
        val n = pais.municipioDepto("Amazonas")

        assertEquals(11, n)
    }

    @Test
    fun test2() {
        val n = pais.numMunicipios()
        assertEquals(1122, n, "El numero de municipios esta mal!")
    }

    @Test
    fun test33(){
        val n = pais.numDepartamentos()
        assertEquals(33,n)
    }
    @Test
    fun test34(){
        val n = pais.poblTotal("Amazonas")
        assertEquals(74541,n)
    }

    @Test
    fun test4() {
        val capital = pais.obtenerCapitalDepartamento("Vichada")

        assertEquals("Puerto Carreño", capital)
    }

    @Test
    fun test5(): Unit {
        // TODO: Imprima aquí el nombre, IDH, superficie y año de creación del departamento Arauca
        // Compruebe que la superficie del departamento es 23818 y el año de creación fue 1911
        val d = pais.obtenerDepartamento("Arauca")

        println("Nombre = ${d?.nombre}\nIDH = ${d?.IDH}\nAño de creción = ${d?.añoCreación}")
        assertEquals(23818.0, d?.superficie)

    }

    @Test
    fun test6() {
        // Poblacion de Antioquia
        // TODO: Compruebe que la poblacion total de Antioquia es de 6299886
        assertEquals(x,pais.poblTotal("Antioquia"))
    }

    @Test
    fun test7() {
        // Departamentos creados en la decada de los años 10
        var n = pais.deptosDecadaDiez()
        assertEquals(4,n)
    }

    @Test
    fun test8(): Unit {
        // TODO: Después de obtener el departamento más grande, compruebe que el nombre es "Amazonas"
        // y que la superficie de ese departamento es 109665
        var n = pais.deptMasGrande()
        assertEquals("Amazonas",n)
    }

    @Test
    fun test9(): Unit {
        // Densidad del departamento del Quindío
        var densidad = pais.densiPobla("Quindío")
        // TODO: Obtener aquí la densidad del departamento de Quindío
        // Y comprueba que su densidad es 309.1506 de la siguiente manera

        assertEquals(309.1506, densidad, 0.0001)

    }

    @Test
    fun test10(){
        var n = pais.munNoPoblaUrba()
        assertEquals(0,n)
    }


}