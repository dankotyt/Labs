import java.util.Scanner;
import java.util.Random;

public class Current {

    public static void main(String[] args) {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);

        int n1, m1, n2, m2, k, l = 2;

        System.out.println("Вас приветствует программа\nбыстрого перемножения матриц методом Штрассена\n");

        // ввод размеров матрицы
        do {
            System.out.println("Введите размеры первой матрицы");
            n1 = scanner.nextInt();
            m1 = scanner.nextInt();
        } while (n1 <= 0 || m1 <= 0);

        do {
            System.out.println("Введите размеры второй матрицы");
            n2 = scanner.nextInt();
            m2 = scanner.nextInt();
        } while (n2 <= 0 || m2 <= 0);

        int[][] M1 = new int[n1][m1];
        int[][] M2 = new int[n2][m2];

        // выбор способа заполнения и заполнение матриц
        do {
            System.out.println("Выберите способ заполнения матриц\n1 - Вручную\n2 - Случайным образом");
            k = scanner.nextInt();
        } while (k < 1 || k > 2);

        switch (k) {
            case 1:
                System.out.println("Введите элементы первой матрицы:");
                for (int i = 0; i < n1; i++) {
                    for (int j = 0; j < m1; j++) {
                        M1[i][j] = scanner.nextInt();
                    }
                }
                System.out.println("Введите элементы второй матрицы:");
                for (int i = 0; i < n2; i++) {
                    for (int j = 0; j < m2; j++) {
                        M2[i][j] = scanner.nextInt();
                    }
                }
                break;

            case 2:
                for (int i = 0; i < n1; i++) {
                    for (int j = 0; j < m1; j++) {
                        M1[i][j] = random.nextInt(10);
                    }
                }
                for (int i = 0; i < n2; i++) {
                    for (int j = 0; j < m2; j++) {
                        M2[i][j] = random.nextInt(10);
                    }
                }
                break;
        }

        System.out.println("\nМатрица 1:");
        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < m1; j++) {
                System.out.print(M1[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("\nМатрица 2:");
        for (int i = 0; i < n2; i++) {
            for (int j = 0; j < m2; j++) {
                System.out.print(M2[i][j] + " ");
            }
            System.out.println();
        }

        // Приведение матриц к требуемому размеру
        while (l < n1 || l < n2 || l < m1 || l < m2) {
            l *= 2;
        }

        int[][] M3 = new int[l][l];
        int[][] M4 = new int[l][l];

        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < m1; j++) {
                M3[i][j] = M1[i][j];
            }
        }

        for (int i = 0; i < n2; i++) {
            for (int j = 0; j < m2; j++) {
                M4[i][j] = M2[i][j];
            }
        }

        System.out.println("Приведенные матрицы");
        System.out.println("\nМатрица 1:");
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < l; j++) {
                System.out.print(M3[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("\nМатрица 2:");
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < l; j++) {
                System.out.print(M4[i][j] + " ");
            }
            System.out.println();
        }

        // Разбиение матриц на подматрицы
        int[][] mat1 = new int[l / 2][l / 2];
        for (int i = 0; i < l / 2; i++) {
            for (int j = 0; j < l / 2; j++) {
                mat1[i][j] = M3[i][j];
            }
        }

        int[][] mat2 = new int[l / 2][l / 2];
        for (int i = 0; i < l / 2; i++) {
            for (int j = 0; j < l / 2; j++) {
                mat2[i][j] = M3[i][j + l / 2];
            }
        }

        int[][] mat3 = new int[l / 2][l / 2];
        for (int i = 0; i < l / 2; i++) {
            for (int j = 0; j < l / 2; j++) {
                mat3[i][j] = M3[i + l / 2][j];
            }
        }

        int[][] mat4 = new int[l / 2][l / 2];
        for (int i = 0; i < l / 2; i++) {
            for (int j = 0; j < l / 2; j++) {
                mat4[i][j] = M3[i + l / 2][j + l / 2];
            }
        }

        int[][] mat5 = new int[l / 2][l / 2];
        for (int i = 0; i < l / 2; i++) {
            for (int j = 0; j < l / 2; j++) {
                mat5[i][j] = M4[i][j];
            }
        }

        int[][] mat6 = new int[l / 2][l / 2];
        for (int i = 0; i < l / 2; i++) {
            for (int j = 0; j < l / 2; j++) {
                mat6[i][j] = M4[i][j + l / 2];
            }
        }

        int[][] mat7 = new int[l / 2][l / 2];
        for (int i = 0; i < l / 2; i++) {
            for (int j = 0; j < l / 2; j++) {
                mat7[i][j] = M4[i + l / 2][j];
            }
        }

        int[][] mat8 = new int[l / 2][l / 2];
        for (int i = 0; i < l / 2; i++) {
            for (int j = 0; j < l / 2; j++) {
                mat8[i][j] = M4[i + l / 2][j + l / 2];
            }
        }

        // Промежуточные матрицы
        int[][] p1 = new int[l / 2][l / 2];
        int[][] p2 = new int[l / 2][l / 2];
        int[][] p3 = new int[l / 2][l / 2];
        int[][] p4 = new int[l / 2][l / 2];
        int[][] p5 = new int[l / 2][l / 2];
        int[][] p6 = new int[l / 2][l / 2];
        int[][] p7 = new int[l / 2][l / 2];

        for (int i = 0; i < l / 2; i++) {
            for (int j = 0; j < l / 2; j++) {
                p1[i][j] = 0;
                for (int z = 0; z < l / 2; z++) {
                    p1[i][j] += (mat1[i][z] + mat4[i][z]) * (mat5[z][j] + mat8[z][j]);
                }
                p2[i][j] = 0;
                for (int z = 0; z < l / 2; z++) {
                    p2[i][j] += (mat3[i][z] + mat4[i][z]) * mat5[z][j];
                }
                p3[i][j] = 0;
                for (int z = 0; z < l / 2; z++) {
                    p3[i][j] += mat1[i][z] * (mat6[z][j] - mat8[z][j]);
                }
                p4[i][j] = 0;
                for (int z = 0; z < l / 2; z++) {
                    p4[i][j] += mat4[i][z] * (mat7[z][j] - mat5[z][j]);
                }
                p5[i][j] = 0;
                for (int z = 0; z < l / 2; z++) {
                    p5[i][j] += (mat1[i][z] + mat2[i][z]) * mat8[z][j];
                }
                p6[i][j] = 0;
                for (int z = 0; z < l / 2; z++) {
                    p6[i][j] += (mat3[i][z] - mat1[i][z]) * (mat5[z][j] + mat6[z][j]);
                }
                p7[i][j] = 0;
                for (int z = 0; z < l / 2; z++) {
                    p7[i][j] += (mat2[i][z] - mat4[i][z]) * (mat7[z][j] + mat8[z][j]);
                }
            }
        }

        // Вспомогательные матрицы
        int[][] mat9 = new int[l / 2][l / 2];
        int[][] mat10 = new int[l / 2][l / 2];
        int[][] mat11 = new int[l / 2][l / 2];
        int[][] mat12 = new int[l / 2][l / 2];

        for (int i = 0; i < l / 2; i++) {
            for (int j = 0; j < l / 2; j++) {
                mat9[i][j] = p1[i][j] + p4[i][j] - p5[i][j] + p7[i][j];
                mat10[i][j] = p3[i][j] + p5[i][j];
                mat11[i][j] = p2[i][j] + p4[i][j];
                mat12[i][j] = p1[i][j] - p2[i][j] + p3[i][j] + p6[i][j];
            }
        }

        // Результирующая матрица
        int[][] M5 = new int[l][l];

        for (int i = 0; i < l / 2; i++) {
            for (int j = 0; j < l / 2; j++) {
                M5[i][j] = mat9[i][j];
                M5[i][j + l / 2] = mat10[i][j];
                M5[i + l / 2][j] = mat11[i][j];
                M5[i + l / 2][j + l / 2] = mat12[i][j];
            }
        }

        // Выравнивание границ
        int x = 0, f = 100, s = 100;
        for (int i = 0; i < l; i++) {
            x = 0;
            for (int j = 0; j < l; j++) {
                if (M5[i][j] != 0) {
                    x++;
                    f = 100;
                }
            }
            if (x == 0 && i < f) {
                f = i;
            }
        }

        for (int i = 0; i < l; i++) {
            x = 0;
            for (int j = 0; j < l; j++) {
                if (M5[j][i] != 0) {
                    x++;
                    s = 100;
                }
            }
            if (x == 0 && i < s) {
                s = i;
            }
        }

        int[][] M6 = new int[f][s];
        for (int i = 0; i < f; i++) {
            for (int j = 0; j < s; j++) {
                M6[i][j] = M5[i][j];
            }
        }

        System.out.println("\nРезультирующая матрица\n");
        for (int i = 0; i < f; i++) {
            for (int j = 0; j < s; j++) {
                System.out.print(M6[i][j] + " ");
            }
            System.out.println();
        }
    }
}