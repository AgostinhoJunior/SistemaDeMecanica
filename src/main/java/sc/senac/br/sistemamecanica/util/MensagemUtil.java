package sc.senac.br.sistemamecanica.util;

import javax.faces.context.FacesContext;
import java.util.ResourceBundle;

public class MensagemUtil {

	private static ResourceBundle bundle = ResourceBundle.getBundle("idiomas.idioma",
			FacesContext.getCurrentInstance().getViewRoot().getLocale());

	public static void addMensagem(javax.faces.application.FacesMessage.Severity severity, String messageKey) {
		String message = bundle.getString(messageKey);
		javax.faces.application.FacesMessage facesMessage = new javax.faces.application.FacesMessage(severity, message,
				message);
		FacesContext fc = FacesContext.getCurrentInstance();
		fc.addMessage("idiomas.idioma", facesMessage);
	}

	public static void addMensagem(javax.faces.application.FacesMessage.Severity severity, String messageKey,
			String messagKeyDetail) {
		String message = bundle.getString(messageKey);
		String messageDetail = bundle.getString(messagKeyDetail);
		javax.faces.application.FacesMessage facesMessage = new javax.faces.application.FacesMessage(severity, message,
				messageDetail);
		FacesContext fc = FacesContext.getCurrentInstance();
		fc.addMessage("idiomas.idioma", facesMessage);
	}

	public static void addMensagemInfo(String messageKey) {
		addMensagem(javax.faces.application.FacesMessage.SEVERITY_INFO, messageKey);
	}

	public static void addMensagemError(String messageKey) {
		addMensagem(javax.faces.application.FacesMessage.SEVERITY_ERROR, messageKey);
	}

	public static void addMensagemWarn(String messageKey) {
		addMensagem(javax.faces.application.FacesMessage.SEVERITY_WARN, messageKey);
	}

	public static void addMensagemInfo(String messageKey, String messageDetail) {
		addMensagem(javax.faces.application.FacesMessage.SEVERITY_INFO, messageKey, messageDetail);
	}

	public static void addMensagemError(String messageKey, String messageDetail) {
		addMensagem(javax.faces.application.FacesMessage.SEVERITY_ERROR, messageKey, messageDetail);
	}

	public static void addMensagemWarn(String messageKey, String messageDetail) {
		addMensagem(javax.faces.application.FacesMessage.SEVERITY_WARN, messageKey, messageDetail);
	}
}
