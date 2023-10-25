package aed;

import java.util.*;

public class ListaEnlazada<T> {

    private class Nodo {
        T dato;
        Nodo anterior;
        Nodo siguiente;

        Nodo(T dato) {
            this.dato = dato;
        }
    }

    private Nodo primero;
    private Nodo ultimo;
    private int size;

    public ListaEnlazada() {
        primero = null;
        ultimo = null;
        size = 0;
    }

    public int longitud() {
        return size;
    }

    public void agregarAdelante(T elem) {
        Nodo nuevoNodo = new Nodo(elem);
        if (primero == null) {
            primero = nuevoNodo;
            ultimo = nuevoNodo;
        } else {
            nuevoNodo.siguiente = primero;
            primero.anterior = nuevoNodo;
            primero = nuevoNodo;
        }
        size++;
    }

    public void agregarAtras(T elem) {
        Nodo nuevoNodo = new Nodo(elem);
        if (ultimo == null) {
            primero = nuevoNodo;
            ultimo = nuevoNodo;
        } else {
            nuevoNodo.anterior = ultimo;
            ultimo.siguiente = nuevoNodo;
            ultimo = nuevoNodo;
        }
        size++;
    }

    public T obtener(int i) {
        if (i < 0 || i >= size) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }
        Nodo actual = primero;
        for (int j = 0; j < i; j++) {
            actual = actual.siguiente;
        }
        return actual.dato;
    }

    public void eliminar(int i) {
        if (i < 0 || i >= size) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }
        if (size == 1) {
            primero = null;
            ultimo = null;
        } else if (i == 0) {
            primero = primero.siguiente;
            primero.anterior = null;
        } else if (i == size - 1) {
            ultimo = ultimo.anterior;
            ultimo.siguiente = null;
        } else {
            Nodo actual = primero;
            for (int j = 0; j < i; j++) {
                actual = actual.siguiente;
            }
            actual.anterior.siguiente = actual.siguiente;
            actual.siguiente.anterior = actual.anterior;
        }
        size--;
    }

    public void modificarPosicion(int i, T elem) {
        if (i < 0 || i >= size) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }
        Nodo actual = primero;
        for (int j = 0; j < i; j++) {
            actual = actual.siguiente;
        }
        actual.dato = elem;
    }

    public ListaEnlazada<T> copiar() {
        ListaEnlazada<T> copia = new ListaEnlazada<>();
        Nodo actual = primero;
        while (actual != null) {
            copia.agregarAtras(actual.dato);
            actual = actual.siguiente;
        }
        return copia;
    }

    public ListaEnlazada(ListaEnlazada<T> lista) {
        this();
        Nodo actual = lista.primero;
        while (actual != null) {
            agregarAtras(actual.dato);
            actual = actual.siguiente;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Nodo actual = primero;
        while (actual != null) {
            sb.append(actual.dato);
            if (actual.siguiente != null) {
                sb.append(", ");
            }
            actual = actual.siguiente;
        }
        sb.append("]");
        return sb.toString();
    }

    public class ListaIterador implements Iterador<T> {
        private Nodo actual = primero;
        private Nodo anterior = null;

        public boolean haySiguiente() {
            return actual != null;
        }

        public boolean hayAnterior() {
            return anterior != null;
        }

        public T siguiente() {
            if (!haySiguiente()) {
                throw new NoSuchElementException("No hay elemento siguiente");
            }
            T dato = actual.dato;
            anterior = actual;
            actual = actual.siguiente;
            return dato;
        }

        public T anterior() {
            if (!hayAnterior()) {
                throw new NoSuchElementException("No hay elemento anterior");
            }
            actual = anterior; // Moverse al nodo anterior
            anterior = anterior.anterior; // Actualizar la referencia al nodo anterior
            return actual.dato; // Devolver el valor del nodo anterior
        }
    }

    public ListaIterador iterador() {
        return new ListaIterador();
    }
}
