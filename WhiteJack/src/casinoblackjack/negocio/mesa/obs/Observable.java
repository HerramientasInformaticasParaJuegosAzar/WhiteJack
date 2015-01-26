package casinoblackjack.negocio.mesa.obs;

/*Esta clase abstracta representa los objetos susceptibles de ser
 *observados. Incorpora metodos para agregar y eliminar observadores
 *y un metodo de notificacion. La asociacion Observable-Observadores
 *se implementa mediante un vector de observadores
 */
import casinoblackjack.negocio.cartas.Decision;
import java.util.ArrayList;

public abstract class Observable {

    //El constructor crea el vector con la asociacion Observable-Observador
    public Observable() {
        _observador = null;
    }

    //Agregar y eliminar sencillamente operan sobre vector _observadores...
    public void setObservador(Observador o) {
        _observador = o;
    }

    //Notificacion: Para cada observador se invoca el método actualizar().
    public void notificarObservadores() {
        _observador.actualizar();
    }

    public boolean isSetJuegoUI() {
        return _observador.isSetJuegoUI();
    }
    
    //Este atributo privado mantiene el vector con los observadores
    private Observador _observador;
}
