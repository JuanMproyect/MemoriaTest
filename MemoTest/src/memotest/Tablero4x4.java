/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package memotest;
import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
/**
 *
 * @author juanz
 */
public class Tablero4x4 extends Tablero {
    private int[][] numeros;
    private JButton[][] botones;

    public Tablero4x4(JPanel panelTablero, JLabel lblPuntos, JLabel lblMovimientos) {
        super(4, panelTablero, lblPuntos, lblMovimientos);
        this.numeros = generarPares();
        this.botones = new JButton[tamaño][tamaño];
        iniciarTablero();
    }

    private int[][] generarPares() {
        int[][] nums = new int[tamaño][tamaño];
        Random random = new Random();
        int[] posiblesNumeros = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] pares = new int[tamaño * tamaño];

        //Genera números del 0 al 9 aleatoriamente
        for (int i = 0; i < pares.length / 2; i++) {
            int numIndex = random.nextInt(posiblesNumeros.length);
            pares[2 * i] = posiblesNumeros[numIndex];
            pares[2 * i + 1] = posiblesNumeros[numIndex];
        }

        //Mezclar los números
        for (int i = pares.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            int temp = pares[i];
            pares[i] = pares[j];
            pares[j] = temp;
        }

        //Asignan numeros mezclados a la matriz bidimensional
        for (int i = 0, k = 0; i < tamaño; i++) {
            for (int j = 0; j < tamaño; j++, k++) {
                nums[i][j] = pares[k];
            }
        }
        return nums;
    }

    protected JButton crearBoton(int numero, int fila, int columna) {
        JButton btn = new JButton();
        btn.setPreferredSize(new Dimension(100, 100)); //Tamaño del botón
        btn.setBackground(new Color(102, 0, 204));//Color de fondo del botón
        btn.setFont(new Font("Segoe UI Black", Font.PLAIN, 24));//Fuente del texto del boton

        btn.addActionListener(e -> {
            if (validarPar || !btn.getText().isEmpty()) {
                return; //Si ya se está validando un par no hacer nada
            }
            btn.setText(String.valueOf(numero));
            if (primerBoton == null) {
                primerBoton = btn;
                primerNumero = numero;
            } else {  //Si los botones son seleccionados
                segundoBoton = btn;
                segundoNumero = numero;
                validarPar = true; //Valida par
                new Timer().schedule(new TimerTask() {
                    public void run() {
                        SwingUtilities.invokeLater(() -> {
                            verificarPareja();
                            movimientos++;
                        });
                    }
                }, 1000); //1 segundo
            }
        });
        return btn;
    }

    //Crea y añade los botones al panel del tablero
    protected void iniciarTablero() {
        panelTablero.removeAll();
        panelTablero.setLayout(new GridLayout(tamaño, tamaño));

        for (int i = 0; i < tamaño; i++) {
            for (int j = 0; j < tamaño; j++) {
                JButton btn = crearBoton(numeros[i][j], i, j);
                botones[i][j] = btn;
                panelTablero.add(btn);
            }
        }
        panelTablero.revalidate();//Validar el panel para refrescar la interfaz
        panelTablero.repaint();
    }

    public JPanel generarTablero() {
        return panelTablero;
    }

    public char[][] devolverTablero() {
        return null; //retorna el tablero
    }
}