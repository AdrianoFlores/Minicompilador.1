package org.example;

import java.util.ArrayList;
import java.util.List;

public class AnalizadorSintactico {
    private static int index = 0;
    private static List<String> tokens;

    public static void main(String[] args) {
        String input = "int x; int y;";
        tokens = tokenize(input);

        try {
            programa();
            if (index == tokens.size()) {
                System.out.println("Análisis sintáctico exitoso.");
            } else {
                throw new Exception("Error sintáctico: Tokens adicionales no esperados.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void programa() throws Exception {
        sentencia();
        if (index < tokens.size() && tokens.get(index).equals("int")) {
            programa();
        }
    }

    private static void sentencia() throws Exception {
        if (index < tokens.size() && tokens.get(index).equals("int")) {
            index++;
            if (index < tokens.size() && tokens.get(index).matches("[a-zA-Z]+")) {
                System.out.println("Identificador: " + tokens.get(index));
                index++;
                if (index < tokens.size() && tokens.get(index).equals(";")) {
                    index++;
                } else {
                    throw new Exception("Error sintáctico: Se esperaba ';'");
                }
            } else {
                throw new Exception("Error sintáctico: Se esperaba un identificador");
            }
        } else {
            throw new Exception("Error sintáctico: Se esperaba 'int'");
        }
    }

    private static List<String> tokenize(String input) {
        List<String> tokens = new ArrayList<>();
        for (String token : input.split("\\s+")) {
            tokens.add(token);
        }
        return tokens;
    }

    // Palabras reservadas
    private static final String[] PALABRAS_RESERVADAS = {
            "int", "if", "else", "while", "do", "for", "return", "break", "continue", "switch",
            "case", "default", "void", "char", "short", "long", "float", "double",
            "struct", "typedef", "enum", "union", "const", "volatile", "static", "extern",
            "register", "auto", "signed", "unsigned", "sizeof", "goto"
    };

    // Tipos de operaciones permitidas
    private static final String[] OPERACIONES_PERMITIDAS = {
            "+", "-", "*", "/", "=", "==", "!=", "<", ">", "<=", ">=", "&&", "||", "!"
    };

    // Símbolos permitidos
    private static final String[] SIMBOLOS_PERMITIDOS = {
            "{", "}", "(", ")", "[", "]", ",", ";"
    };

    // Estructuras de instrucción permitidas
    /*
     * if (condición) {
     *     instrucciones
     * } else {
     *     instrucciones
     * }
     *
     * while (condición) {
     *     instrucciones
     * }
     *
     * do {
     *     instrucciones
     * } while (condición);
     *
     * for (inicialización; condición; incremento) {
     *     instrucciones
     * }
     *
     * switch (expresión) {
     *     case valor1:
     *         instrucciones
     *         break;
     *     case valor2:
     *         instrucciones
     *         break;
     *     default:
     *         instrucciones
     * }
     *
     * break;
     * continue;
     * return expresión;
     */

    // Ejemplos
    private static final String[] EJEMPLOS = {
            "int x = 10;",
            "if (x == 10) { x = x + 1; } else { x = x - 1; }",
            "while (x > 0) { x = x - 1; }",
            "do { x = x - 1; } while (x > 0);",
            "for (int i = 0; i < 10; i++) { x = x + i; }",
            "switch (x) { case 1: x = x + 1; break; case 2: x = x + 2; break; default: x = 0; }",
            "break;",
            "continue;",
            "return x;"
    };
}


