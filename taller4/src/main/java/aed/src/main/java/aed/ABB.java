package aed;

import java.util.*;

public class ABB<T extends Comparable<T>> implements Conjunto<T> {

    private class Nodo {
        T valor;
        Nodo izquierdo;
        Nodo derecho;

        public Nodo(T valor) {
            this.valor = valor;
            izquierdo = null;
            derecho = null;
        }
    }

    private Nodo raiz;
    private int tamaño;

    public ABB() {
        raiz = null;
        tamaño = 0;
    }

    public int cardinal() {
        return tamaño;
    }

    public T minimo() {
        if (raiz == null) {
            return null;
        }
        Nodo nodo = obtenerNodoMinimo(raiz);
        return nodo.valor;
    }

    public T maximo() {
        if (raiz == null) {
            return null;
        }
        Nodo nodo = obtenerNodoMaximo(raiz);
        return nodo.valor;
    }

    public void insertar(T elem) {
        raiz = insertarRecursivo(raiz, elem);
    }

    public boolean pertenece(T elem) {
        return perteneceRecursivo(raiz, elem);
    }

    public void eliminar(T elem) {
        raiz = eliminarRecursivo(raiz, elem);
    }

    
    private Nodo eliminarRecursivo(Nodo nodo, T elem) {
        if (nodo == null) {
            return nodo;
        }
    
        int comparacion = elem.compareTo(nodo.valor);
    
        if (comparacion < 0) {
            nodo.izquierdo = eliminarRecursivo(nodo.izquierdo, elem);
        } else if (comparacion > 0) {
            nodo.derecho = eliminarRecursivo(nodo.derecho, elem);
        } else {
            if (nodo.izquierdo == null && nodo.derecho == null) {
                tamaño--;
                return null;
            } else if (nodo.izquierdo == null) {
                tamaño--;
                return nodo.derecho;
            } else if (nodo.derecho == null) {
                tamaño--;
                return nodo.izquierdo;
            }
            nodo.valor = obtenerNodoMinimo(nodo.derecho).valor;
            nodo.derecho = eliminarRecursivo(nodo.derecho, nodo.valor);
        }
    
        return nodo;
    }
    
    private Nodo insertarRecursivo(Nodo nodo, T elem) {
        if (nodo == null) {
            tamaño++;
            return new Nodo(elem);
        }
    
        int comparacion = elem.compareTo(nodo.valor);
    
        if (comparacion < 0) {
            nodo.izquierdo = insertarRecursivo(nodo.izquierdo, elem);
        } else if (comparacion > 0) {
            nodo.derecho = insertarRecursivo(nodo.derecho, elem);
        }
    
        return nodo;
    }
    
    
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        imprimirEnOrden(raiz, sb);
        sb.append("}");
        return sb.toString().replaceAll(",\\s*", ",");
    }
    

    private boolean perteneceRecursivo(Nodo nodo, T elem) {
        if (nodo == null) {
            return false;
        }

        int comparacion = elem.compareTo(nodo.valor);

        if (comparacion < 0) {
            return perteneceRecursivo(nodo.izquierdo, elem);
        } else if (comparacion > 0) {
            return perteneceRecursivo(nodo.derecho, elem);
        } else {
            return true;
        }
    }

    private Nodo obtenerNodoMinimo(Nodo nodo) {
        Nodo actual = nodo;
        while (actual.izquierdo != null) {
            actual = actual.izquierdo;
        }
        return actual;
    }

    private Nodo obtenerNodoMaximo(Nodo nodo) {
        Nodo actual = nodo;
        while (actual.derecho != null) {
            actual = actual.derecho;
        }
        return actual;
    }

    private void imprimirEnOrden(Nodo nodo, StringBuilder sb) {
        if (nodo != null) {
            imprimirEnOrden(nodo.izquierdo, sb);
            if (sb.length() > 1) {
                sb.append(", ");
            }
            sb.append(nodo.valor.toString());
            imprimirEnOrden(nodo.derecho, sb);
        }
    }

    public Iterador<T> iterador() {
        return new ABBIterador();
    }

    private class ABBIterador implements Iterador<T> {
        private Stack<Nodo> pila;

        public ABBIterador() {
            pila = new Stack<>();
            apilarNodos(raiz);
        }

        private void apilarNodos(Nodo nodo) {
            while (nodo != null) {
                pila.push(nodo);
                nodo = nodo.izquierdo;
            }
        }

        public boolean haySiguiente() {
            return !pila.isEmpty();
        }

        public T siguiente() {
            if (!haySiguiente()) {
                throw new NoSuchElementException("No hay más elementos");
            }
            Nodo nodo = pila.pop();
            apilarNodos(nodo.derecho);
            return nodo.valor;
        }
    }
}
