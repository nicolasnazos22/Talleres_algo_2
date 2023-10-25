package aed;
class Debugging {
    boolean xor(boolean a, boolean b) {
        return a != b;
    }

    boolean iguales(int[] xs, int[] ys) {
        if (xs.length != ys.length) {
            return false;
        } else {
            for (int i = 0; i < xs.length; i++) {
                if (xs[i] != ys[i]) {
                    return false;
                }
            }
            return true;
        }
    }

    boolean ordenado(int[] xs) {
        for (int i = 0; i < (xs.length - 1); i++) {
            if (xs[i] > xs[i + 1]) {
                return false;
            }
        }
        return true;
    }

    int maximo(int[] xs) {
        int res = Integer.MIN_VALUE;

        for (int x : xs) {
            if (x > res) {
                res = x;
            }
        }
        return res;
    }

    boolean todosPositivos(int[] xs) {
        for (int x : xs) {
            if (x <= 0) {
                return false;
            }
        }
        return true;
    }
}

