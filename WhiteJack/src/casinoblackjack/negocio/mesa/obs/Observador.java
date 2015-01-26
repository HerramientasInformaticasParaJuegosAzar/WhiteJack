package casinoblackjack.negocio.mesa.obs;

import casinoblackjack.negocio.cartas.Decision;

/*Los observadores deben implementar la siguiente interfaz, es decir,
*tienen un metodo actualizar() para reaccionar a cambios del sujeto
*/
public interface Observador {
    public void actualizar();
    public boolean isSetJuegoUI();
}