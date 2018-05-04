/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Pais.java,v 1.0 2017/02/17 08:09 lacobo Exp $
 * Universidad EAN (Bogotá - Colombia)
 * Departamento de Sistemas - Facultad de Ingeniería
 * Licenciado bajo el esquema Academic Free License version 2.1
 * <p>
 * Ejercicio: Pais
 * Autor: Universidad EAN - Abril 27, 2018
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package ean.programacionavanzada.pais

import ean.collections.AVLTree
import ean.collections.IBinarySearchTree
import ean.collections.IList
import ean.collections.asLinkedList
import ean.programacionavanzada.pais.Municipio

class Pais {
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    private val deptos: IBinarySearchTree<Departamento> = AVLTree()
    private val municipios: IBinarySearchTree<Municipio> = AVLTree()

    //-----------------------------------------------------------------
    // Constructor
    //-----------------------------------------------------------------
    constructor()

    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------

    /**
     * Crea un nuevo departamento a partir de los datos de entrada y lo agrega al árbol de departamentos del pais
     * @param nombre
     * @param pobl
     * @param superficie
     * @param IDH
     * @param añoCreación
     */
    fun agregarDepartamento(nombre: String, pobl: Int, superficie: Double, IDH: Double, añoCreación: Int) {
        val d = Departamento(nombre, pobl, superficie, IDH, añoCreación)
        deptos.add(d)
    }

    /**
     * Permite saber el número de departamentos que posee el país.
     * @return el tamaño de la lista de departamentos
     */
    fun numDepartamentos(): Int {
        fun numDeptos(arbol: IBinarySearchTree<Departamento>): Int =
                when {
                    arbol.isEmpty -> 0
                    else -> 1 + numDeptos(arbol.left) + numDeptos(arbol.right)
                }
        // Usamos la función anterior
        return numDeptos(deptos)

    }


    /**
     * Crea un objeto municipio con los datos de entrada especificados y lo agrega al final de la lista de departamentos
     * @param código
     * @param nombre
     * @param departamento
     * @param poblaciónUrbana
     * @param poblaciónRural
     * @param esCapital
     */
    fun agregarMunicipio(código: Int, nombre: String, departamento: String, poblaciónUrbana: Int, poblaciónRural: Int, esCapital: Boolean) {
        val m = Municipio(código, nombre, departamento, poblaciónUrbana, poblaciónRural, esCapital)
        municipios.add(m)
    }


    /**
     * Obtiene el número de municipios que hay en la lista
     * @return el tamaño de la lista de municipios
     */
    fun numMunicipios(): Int {
        fun numMuni(arbol: IBinarySearchTree<Municipio>): Int =
                when {
                    arbol.isEmpty -> 0
                    else -> 1 + numMuni(arbol.left) + numMuni(arbol.right)
                }
        // Usamos la función anterior
        return numMuni(municipios)
    }

    /**
     * Permite obtener el número de municipios que hacen parte del departamento que
     * tiene el nombre dado. Realice una función recursiva, de manera obligatoria
     * @param nomDepto el departamento a buscar
     * @return el número de municipios de ese departamento
     */
    fun municipioDepto(nomDepto: String): Int {
        // Función interna recursiva
        fun contar(arbol: IBinarySearchTree<Municipio>, nomd: String): Int =
                TODO("Completar")

        // Función principal
        return contar(municipios, nomDepto)
    }

    /**
     * Dado un departamento, retorna el nombre del municipio de ese departamento que es la capital
     * Utilice una función recursiva para solucionar el problema.
     *  @param nomDepto el departamento a buscar
     * @return el nombre del municipio, o la cadena vacía "" si no hay capital para ese departamento
     */
    fun obtenerCapitalDepartamento(nomDepto: String): String {
        fun buscar(a: IBinarySearchTree<Municipio>, d: String): String {
            if (a.isEmpty) {
                return ""
            } else {
                val raiz = a.root
                if (raiz.departamento == d && raiz.esCapital) {
                    return raiz.nombre
                }
                var res = buscar(a.left, d)
                if (res == "") {
                    res = buscar(a.right, d)
                }
                return res
            }
        }
        return buscar(municipios, nomDepto)
    }

    /**
     * Obtener el departamento que tiene un nombre dado
     * @return el objeto departamento que tiene el  nombre dado, o null si no existe un depto
     */
    fun obtenerDepartamento(nombreDepto: String): Departamento? {
        fun buscar(a: IBinarySearchTree<Departamento>, d: String): Departamento? {
            if (a.isEmpty) {
                return null
            } else if (a.root.nombre == d) {
                return a.root
            } else if (a.root.nombre < d) {
                return buscar(a.right, d)
            } else {
                return buscar(a.left, d)
            }
        }

        return buscar(deptos, nombreDepto)
    }

    /**
     * Obtener el departamento más grande del país, el de mayor superficie. Debe obtener todo el
     * departamento.
     * @return un objeto de la clase departamento
     */
    fun deptMasGrande():Departamento?{
        fun buscar(a: IBinarySearchTree<Departamento>):String {
            var bandera = 0.0
            var bandera2 = ""

            if (a.isEmpty) {
                bandera2 = ""
            } else if (a.root.superficie>bandera) {
                bandera = a.root.superficie
                bandera2 = ""
            } else if (a.root.superficie<bandera) {
                return buscar(a.right)
            } else {
                return buscar(a.left)
            }
            return bandera2
        }
        return obtenerDepartamento(buscar(deptos))
    }

    /**
     * Obtener la población total de un departamento dado. Suma las poblaciones rurales y urbanas
     * de todos los municipios que tiene ese departamento
     * @return la suma de las poblaciones
     */

    fun poblTotal(depto:String):Int {
        var n = obtenerDepartamento(depto)
        return n!!.población
    }


    /**
     * Obtener el número de departamentos creados en la decada de los 10 del siglo pasado
     * @return la cantidad de departamentos
     */

    fun deptosDecadaDiez(): Int {
        fun numMuni(arbol: IBinarySearchTree<Departamento>): Int =
                when {
                    arbol.isEmpty -> 0
                    arbol.root.añoCreación in 1910..1919 -> 1
                    else -> numMuni(arbol.left) + numMuni(arbol.right)
                }
        return numMuni(deptos)
    }

    /**
     * Obtiene la densidad poblacional de un departamento. La densidad es la división entre la
     * población total de ese departamento y la superficie de ese departamento.
     * @param nombreDepto el nombre del departamento
     * @return la densidad = total_pobl / superficie
     */

    fun densiPobla(depto: String):Double {
        var n = obtenerDepartamento(depto)
        return n!!.población / n!!.superficie
    }


    /**
     * Cuántos municipios tienen no tienen población urbana
     */

    fun munNoPoblaUrba():Int{
        fun numMuni(arbol: IBinarySearchTree<Municipio>): Int =
                when {
                    arbol.isEmpty -> 0
                    arbol.root.poblaciónRural == 0 ->1
                    else -> numMuni(arbol.left) + numMuni(arbol.right)
                }
        return numMuni(municipios)
    }


}

