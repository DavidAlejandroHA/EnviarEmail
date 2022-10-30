package dad.enviarEmail;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

//David Alejandro Hernández Alonso 2º DAM A
public class Model {
	
	private StringProperty nombreHostTexto = new SimpleStringProperty();
	private IntegerProperty puertoNumero = new SimpleIntegerProperty();
	
	private BooleanProperty ssl = new SimpleBooleanProperty();
	
	private StringProperty remitenteTexto = new SimpleStringProperty();
	private StringProperty contrasenaTexto = new SimpleStringProperty();
	private StringProperty destinataroTexto = new SimpleStringProperty();
	private StringProperty asuntoTexto = new SimpleStringProperty();
	private StringProperty mensajeTexto = new SimpleStringProperty();
	
	
	public final StringProperty nombreHostTextoProperty() {
		return this.nombreHostTexto;
	}
	
	public final String getNombreHostTexto() {
		return this.nombreHostTextoProperty().get();
	}
	
	public final void setNombreHostTexto(final String nombreHostTexto) {
		this.nombreHostTextoProperty().set(nombreHostTexto);
	}
	
	public final IntegerProperty puertoNumeroProperty() {
		return this.puertoNumero;
	}
	
	public final int getPuertoNumero() {
		return this.puertoNumeroProperty().get();
	}
	
	public final void setPuertoNumero(final int puertoNumero) {
		this.puertoNumeroProperty().set(puertoNumero);
	}
	
	public final BooleanProperty sslProperty() {
		return this.ssl;
	}
	
	public final boolean isSsl() {
		return this.sslProperty().get();
	}
	
	public final void setSsl(final boolean ssl) {
		this.sslProperty().set(ssl);
	}
	
	public final StringProperty remitenteTextoProperty() {
		return this.remitenteTexto;
	}
	
	public final String getRemitenteTexto() {
		return this.remitenteTextoProperty().get();
	}
	
	public final void setRemitenteTexto(final String remitenteTexto) {
		this.remitenteTextoProperty().set(remitenteTexto);
	}
	
	public final StringProperty contrasenaTextoProperty() {
		return this.contrasenaTexto;
	}
	
	public final String getContrasenaTexto() {
		return this.contrasenaTextoProperty().get();
	}
	
	public final void setContrasenaTexto(final String contrasenaTexto) {
		this.contrasenaTextoProperty().set(contrasenaTexto);
	}
	
	public final StringProperty destinataroTextoProperty() {
		return this.destinataroTexto;
	}
	
	public final String getDestinataroTexto() {
		return this.destinataroTextoProperty().get();
	}
	
	public final void setDestinataroTexto(final String destinataroTexto) {
		this.destinataroTextoProperty().set(destinataroTexto);
	}
	
	public final StringProperty asuntoTextoProperty() {
		return this.asuntoTexto;
	}
	
	public final String getAsuntoTexto() {
		return this.asuntoTextoProperty().get();
	}
	
	public final void setAsuntoTexto(final String asuntoTexto) {
		this.asuntoTextoProperty().set(asuntoTexto);
	}
	
	public final StringProperty mensajeTextoProperty() {
		return this.mensajeTexto;
	}
	
	public final String getMensajeTexto() {
		return this.mensajeTextoProperty().get();
	}
	
	public final void setMensajeTexto(final String mensajeTexto) {
		this.mensajeTextoProperty().set(mensajeTexto);
	}
	
	
	
}
