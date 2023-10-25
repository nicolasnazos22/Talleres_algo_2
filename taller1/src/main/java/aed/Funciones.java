package aed;

class Funciones {
    int cuadrado(int x) {
         // COMPLETAR
         return x * x;
     }
 
     double distancia(double x, double y) {
         return Math.sqrt (x * x + y * y);
     }
 
     boolean esPar(int n) {
         
         return (n % 2 == 0);
     }
 
     boolean esBisiesto(int n) {
        
         return ((n % 400 == 0) || (n % 4 == 0 && n % 100 != 0));
 
     }
 
     int factorialIterativo(int n) {
         int res = 1;
         if(n <= 1)
         {
             res = 1;
         }
         else
         {
             for(int i = 1; i <= n; i++)
         {
             res *= i;
         }
         }
 
         return res;
     }
 
     int factorialRecursivo(int n) {
         
         if(n <= 0)
         {
            return 1;
         }
         else
         {
            return n * factorialRecursivo(n-1);
         }
     }
 
     boolean esPrimo(int n) {
     
     if (n <= 1) {
         return false;
     }
     
    
     if (n <= 3) {
         return true;
     }
 
     
     if (n % 2 == 0 || n % 3 == 0) {
         return false;
     }
 
     
     for (int i = 5; i * i <= n; i += 6) {
         if (n % i == 0 || n % (i + 2) == 0) {
             return false;
         }
     }
 
    
     return true;
 }
             
 
     int sumatoria(int[] numeros) {
         int res = 0;
         for(int i = 0; i < numeros.length; i++)
         {
             res += numeros[i];
         }
         return res;
     }
 
     int busqueda(int[] numeros, int buscado) {
         int indice = -1;
         for(int i = 0; i < numeros.length; i++)
         {
             if(numeros[i] == buscado)
             {
                 indice = i;
                 
             }
         }
         return indice;
     }
 
     boolean tienePrimo(int[] numeros) {
        
         for(int i = 0; i < numeros.length; i++)
         {
             if(esPrimo(numeros[i]))
             {
                 return true;
             }
         }
        
        
        return false;
     }
 
     boolean todosPares(int[] numeros) {
        boolean res = true;
         for(int i = 0; i < numeros.length; i++){
             if(esPar(numeros[i]) == false)
             {
                 res = false;
                 break;
             }
 
        }
         return res;
     }
 
     boolean esPrefijo(String s1, String s2) {
     
         if (s1.length() > s2.length()) {
         return false;
     }
 
     
     for (int i = 0; i < s1.length(); i++) {
         if (s1.charAt(i) != s2.charAt(i)) {
             return false; 
     }
     }
 
     return true;
 }
 
     boolean esSufijo(String s1, String s2) {
         int s1Length = s1.length();
         int s2Length = s2.length();
 
     
     if (s1Length > s2Length) {
         return false;
     }
     return esPrefijo(s1, s2.substring(s2Length - s1Length));
 }
 
 }
 