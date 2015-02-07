package casinoblackjack.negocio.mesa.obs;

/*Los observadores deben implementar la siguiente interfaz, es decir,
*tienen un metodo actualizar() para reaccionar a cambios del sujeto
*/
public interface Observador {
    public void actualizarApuestas();
    public void actualizarCartas();
}