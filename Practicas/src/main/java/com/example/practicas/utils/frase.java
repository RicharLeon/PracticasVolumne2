package com.example.practicas.utils;

public class frase {



        // Método de cifrado César
        public static String cifradoCesar(String texto, int clave) {
            StringBuilder resultado = new StringBuilder();

            for (int i = 0; i < texto.length(); i++) {
                char caracter = texto.charAt(i);
                if (Character.isLetter(caracter)) {
                    char base = Character.isUpperCase(caracter) ? 'A' : 'a';
                    caracter = (char) (((caracter - base + clave) % 26) + base);
                }
                resultado.append(caracter);
            }

            return resultado.toString();
        }

        // Método de cifrado monoalfabético
        public static String cifradoMonoalfabetico(String texto, String clave) {
            StringBuilder resultado = new StringBuilder();
            int longitudClave = clave.length();

            for (int i = 0; i < texto.length(); i++) {
                char caracter = texto.charAt(i);
                if (Character.isLetter(caracter)) {
                    char base = Character.isUpperCase(caracter) ? 'A' : 'a';
                    int desplazamiento = clave.charAt(i % longitudClave) - 'A';
                    caracter = (char) (((caracter - base + desplazamiento) % 26) + base);
                }
                resultado.append(caracter);
            }

            return resultado.toString();
        }

        // Método de cifrado polialfabético
        public static String cifradoPolialfabetico(String texto, String clave) {
            StringBuilder resultado = new StringBuilder();
            int longitudClave = clave.length();
            int j = 0;

            for (int i = 0; i < texto.length(); i++) {
                char caracter = texto.charAt(i);
                if (Character.isLetter(caracter)) {
                    char base = Character.isUpperCase(caracter) ? 'A' : 'a';
                    int desplazamiento = clave.charAt(j % longitudClave) - 'A';
                    caracter = (char) (((caracter - base + desplazamiento) % 26) + base);
                    j++;
                }
                resultado.append(caracter);
            }

            return resultado.toString();
        }

        public static void main(String[] args) {
            // Texto a cifrar
            String texto = "Muchos años después, frente al pelotón de fusilamiento, el coronel Aureliano Buendía había de recordar aquella tarde remota en que su padre lo llevó a conocer el hielo.";

            // Cifrado César con clave 3
            String cifradoCesar = cifradoCesar(texto, 3);
            System.out.println("Cifrado César: " + cifradoCesar);

            // Cifrado monoalfabético con clave "sol"
            String cifradoMonoalfabetico = cifradoMonoalfabetico(texto, "sol");
            System.out.println("Cifrado Monoalfabético: " + cifradoMonoalfabetico);

            // Cifrado polialfabético con clave "macondo"
            String cifradoPolialfabetico = cifradoPolialfabetico(texto, "macondo");
            System.out.println("Cifrado Polialfabético: " + cifradoPolialfabetico);
        }




}
