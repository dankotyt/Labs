import java.util.Random;
import java.util.Scanner;

public class Fixed {

    public static void main(String[] args) {

        Random random = new Random();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Вас приветствует программа\nбыстрого перемножения матриц методом Штрассена\n");

        int height1 = getPositiveInput(scanner, "Введите высоту первой матрицы:");
        int width1 = getPositiveInput(scanner, "Введите ширину первой матрицы:");
        int height2 = getPositiveInput(scanner, "Введите высоту второй матрицы:");
        int width2 = getPositiveInput(scanner, "Введите ширину второй матрицы:");

        int[][] M1 = new int[height1][width1];
        int[][] M2 = new int[height2][width2];

        int fillOption = getOptionInput(scanner, "Выберите способ заполнения матриц\n1 - ввод вручную\n" +
                "2 - случайным образом");
        fillMatrix(M1, scanner, random, fillOption);
        fillMatrix(M2, scanner, random, fillOption);

        System.out.println("\nМатрица 1:");
        printMatrix(M1);
        System.out.println("\nМатрица 2:");
        printMatrix(M2);

        int length = getNextPowerOfTwo(Math.max(Math.max(height1, width1), Math.max(height2, width2)));
        int[][] M3 = extendMatrix(M1, length);
        int[][] M4 = extendMatrix(M2, length);

        System.out.println("\nПриведенная матрица #1:");
        printMatrix(M3);
        System.out.println("\nПриведенная матрица #2:");
        printMatrix(M4);

        int[][][] subMatrices1 = splitMatrix(M3);
        int[][][] subMatrices2 = splitMatrix(M4);

        //промежуточные
        int[][] p1 = multiply(add(subMatrices1[0], subMatrices1[3]), add(subMatrices2[0], subMatrices2[3]));
        int[][] p2 = multiply(add(subMatrices1[2], subMatrices1[3]), subMatrices2[0]);
        int[][] p3 = multiply(subMatrices1[0], subtract(subMatrices2[1], subMatrices2[3]));
        int[][] p4 = multiply(subMatrices1[3], subtract(subMatrices2[2], subMatrices2[0]));
        int[][] p5 = multiply(add(subMatrices1[0], subMatrices1[1]), subMatrices2[3]);
        int[][] p6 = multiply(subtract(subMatrices1[2], subMatrices1[0]), add(subMatrices2[0], subMatrices2[1]));
        int[][] p7 = multiply(subtract(subMatrices1[1], subMatrices1[3]), add(subMatrices2[2], subMatrices2[3]));

        //сборка результирующей
        int[][] mat9 = add(subtract(add(p1, p4), p5), p7);
        int[][] mat10 = add(p3, p5);
        int[][] mat11 = add(p2, p4);
        int[][] mat12 = add(subtract(add(p1, p3), p2), p6);
        int[][] M5 = combineMatrices(mat9, mat10, mat11, mat12, length);

        int[][] M6 = trimMatrix(M5, height1, width2);

        System.out.println("\nРезультирующая матрица:");
        printMatrix(M6);
    }

    private static int getPositiveInput(Scanner scanner, String message) {
        int input;
        do {
            System.out.println(message);
            input = scanner.nextInt();
        } while (input <= 0);
        return input;
    }

    //принятие значения ввода
    private static int getOptionInput(Scanner scanner, String message) {
        int input;
        do {
            System.out.println(message);
            input = scanner.nextInt();
        } while (input < 1 || input > 2);
        return input;
    }

    private static void fillMatrix(int[][] matrix, Scanner scanner, Random rand, int option) {
        if (option == 1) {
            System.out.println("Введите элементы матрицы/Fill the matrix");
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    matrix[i][j] = scanner.nextInt();
                }
            }
        } else if (option == 2) {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    matrix[i][j] = rand.nextInt(10);
                }
            }
        } else {
            System.out.println("Введено неверное значение/Wrong value");
        }
    }

    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int col : row) {
                System.out.print(col + " ");
            }
            System.out.println();
        }
    }

    //следующая степень двойки
    private static int getNextPowerOfTwo(int n) {
        int power = 1;
        while (power < n) {
            power *= 2;
        }
        return power;
    }

    //Метод расширения матрицы
    private static int[][] extendMatrix  (int[][] matrix, int newSize) {
        int[][] extendMatrix = new int[newSize][newSize];
        for (int i = 0; i < matrix.length; i++) {
            System.arraycopy(matrix[i], 0, extendMatrix[i], 0, matrix[i].length);
        }
        return extendMatrix;
    }

    //разбиение на 4 подматрицы
    private static int[][][] splitMatrix(int[][] matrix) {
        int size = matrix.length / 2;
        int[][][] subMatrices = new int[4][size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                subMatrices[0][i][j] = matrix[i][j];
                subMatrices[1][i][j] = matrix[i][j + size];
                subMatrices[2][i][j] = matrix[i + size][j];
                subMatrices[3][i][j] = matrix[i + size][j + size];
            }
        }
        return subMatrices;
    }

    //объединение 4 матриц в одну
    private static int[][] combineMatrices(int[][] matrix1, int[][] matrix2, int[][] matrix3, int[][] matrix4, int size) {
        int[][] result = new int[size][size];
        int halfSize = size / 2;
        for (int i = 0; i < halfSize; i++) {
            for (int j = 0; j < halfSize; j++) {
                result[i][j] = matrix1[i][j];
                result[i][j + halfSize] = matrix2[i][j];
                result[i + halfSize][j] = matrix3[i][j];
                result[i + halfSize][j + halfSize] = matrix4[i][j];
            }
        }
        return result;
    }

    //обрезка матрицы до нужного размера
    private static int[][] trimMatrix(int[][] matrix, int newHeight, int newWidth) {
        int[][] trimmed = new int[newHeight][newWidth];
        for (int i = 0; i < newHeight; i++) {
            System.arraycopy(matrix[i], 0, trimmed[i], 0, newWidth);
        }
        return trimmed;
    }

    private static int[][] add(int[][] A, int[][] B) {
        int sizeA = A.length;
        int[][] result = new int[sizeA][sizeA];
        for (int i = 0; i < sizeA; i++) {
            for (int j = 0; j < sizeA; j++) {
                result[i][j] = A[i][j] + B[i][j];
            }
        }
        return result;
    }

    private static int[][] subtract(int[][] A, int[][] B) {
        int sizeA = A.length;
        int[][] result = new int[sizeA][sizeA];
        for (int i = 0; i < sizeA; i++) {
            for (int j = 0; j < sizeA; j++) {
                result[i][j] = A[i][j] - B[i][j];
            }
        }
        return result;
    }

    private static int[][] multiply(int[][] A, int[][] B) {
        int sizeA = A.length;
        int[][] result = new int[sizeA][sizeA];
        for (int i = 0; i < sizeA; i++) {
            for (int j = 0; j < sizeA; j++) {
                for (int k = 0; k < sizeA; k++) {
                    result[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return result;
    }
}

