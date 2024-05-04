package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexicalAndSyntaxAnalyzer {
    private Map<String, String> symbolTable = new HashMap<>();
    private Map<String, String> errorTable = new HashMap<>();
    private int index = 0;
    private String[] tokens;

    public void analyze(String input) {
        tokens = input.split("\\s+");
        try {
            programa();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void programa() throws Exception {
        sentencia();
        if (index < tokens.length && tokens[index].equals("int")) {
            programa();
        }
    }

    private void sentencia() throws Exception {
        if (index < tokens.length && tokens[index].equals("int")) {
            index++;
            if (index < tokens.length && tokens[index].matches("[a-zA-Z]+")) {
                System.out.println("Identificador: " + tokens[index]);
                index++;
                if (index < tokens.length && tokens[index].equals("=")) {
                    index++;
                    if (index < tokens.length && tokens[index].matches("\\d+")) {
                        System.out.println("Número: " + tokens[index]);
                        index++;
                        if (index < tokens.length && tokens[index].equals(";")) {
                            index++;
                        } else {
                            throw new Exception("Error sintáctico: Se esperaba ';'");
                        }
                    } else {
                        throw new Exception("Error sintáctico: Se esperaba un número");
                    }
                } else {
                    throw new Exception("Error sintáctico: Se esperaba '='");
                }
            } else {
                throw new Exception("Error sintáctico: Se esperaba un identificador");
            }
        } else {
            throw new Exception("Error sintáctico: Se esperaba 'int'");
        }
    }

    public static void main(String[] args) {
        LexicalAndSyntaxAnalyzer analyzer = new LexicalAndSyntaxAnalyzer();
        String input = "int x = 10; int y = 20;";

        analyzer.analyze(input);
    }

    // Palabras reservadas
    private static final String[] PALABRAS_RESERVADAS = {
            "int", "if", "else", "while", "do", "for", "return", "break", "continue", "switch",
            "case", "default", "void", "char", "short", "int", "long", "float", "double",
            "struct", "typedef", "enum", "union", "const", "volatile", "static", "extern",
            "register", "auto", "signed", "unsigned", "sizeof", "typedef"
    };

    // Tipos de operaciones permitidas
    private static final String[] OPERACIONES_PERMITIDAS = {
            "+", "-", "*", "/", "=", "==", "!=", "<", ">", "<=", ">=", "&&", "||", "!"
    };

    // Símbolos permitidos
    private static final String[] SIMBOLOS_PERMITIDOS = {
            "{", "}", "(", ")", "[", "]", ",", ";"
    };

    // Estructuras de instrucciones permitidas
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


