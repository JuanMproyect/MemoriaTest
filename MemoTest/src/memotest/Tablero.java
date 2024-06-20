/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package memotest;
import javax.swing.*;
/**
 *
 * @author juanz
 */
public abstract class Tablero {
    protected int tamaño;
    protected JPanel panelTablero;
    protected JLabel lblPuntos;
    protected JLabel lblMovimientos;
    protected int puntos;
    protected int movimientos;
    protected int paresEncontrados;
    protected int totalPares;
    protected int[] numeros;
    protected JButton[] botones;
    protected JButton primerBoton;
    protected JButton segundoBoton;
    protected int primerNumero;
    protected int segundoNumero;
    protected boolean validarPar;

    public Tablero(int tamaño, JPanel panelTablero, JLabel lblPuntos, JLabel lblMovimientos) {
        this.tamaño = tamaño;
        this.panelTablero = panelTablero;
        this.lblPuntos = lblPuntos;
        this.lblMovimientos = lblMovimientos;
        this.puntos = 0;
        this.movimientos = 1;
        this.paresEncontrados = 0;
        this.validarPar = false;
        this.totalPares = tamaño * tamaño / 2;
    }

    protected boolean todosLosParesEncontrados() {
        return paresEncontrados == totalPares;
    }
    //Verifica si los botones seleccionados forman un par.
    protected void verificarPareja() {
        if (primerNumero == segundoNumero) {
            primerBoton.setVisible(false); //Verifica si los botones seleccionados forman un par.
            segundoBoton.setVisible(false);
            puntos += 5;
            paresEncontrados++;
            if (todosLosParesEncontrados()) {
                actualizarPuntosYMovimientos();
                JOptionPane.showMessageDialog(panelTablero, "¡FELICIDADES! Encontraste todos los pares.");
            }
        } else {
            primerBoton.setText("");// Si los números no son iguales se reincia.
            segundoBoton.setText("");
            puntos--;
        }
        primerBoton = null;// Reinicio de los botones
        segundoBoton = null;
        validarPar = false;
        actualizarPuntosYMovimientos(); //puntos y movimientos en la interfaz gráfica
        panelTablero.revalidate();// Refrescar tablero
        panelTablero.repaint();
    }

    protected void actualizarPuntosYMovimientos() {
        lblPuntos.setText(String.valueOf(puntos));
        lblMovimientos.setText(String.valueOf(movimientos));
    }

    protected abstract void iniciarTablero();

    public abstract char[][] devolverTablero();

    public abstract JPanel generarTablero();
}