package edu.co.sena.ejemploimagenes.vista.managerbeans;

import edu.co.sena.ejemploimagenes.modelo.entities.Imagenes;
import edu.co.sena.ejemploimagenes.vista.managerbeans.util.JsfUtil;
import edu.co.sena.ejemploimagenes.vista.managerbeans.util.JsfUtil.PersistAction;
import edu.co.sena.ejemploimagenes.controles.ejbs.ImagenesFacade;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@Named("imagenesController")
@SessionScoped
public class ImagenesController implements Serializable {

    @EJB
    private edu.co.sena.ejemploimagenes.controles.ejbs.ImagenesFacade ejbFacade;
    private List<Imagenes> items = null;
    private Imagenes selected;

    public ImagenesController() {
    }

    private StreamedContent content; // getter and setter

    public StreamedContent getImageF() {

        if (selected != null) {
            if (selected.getImagen() != null) {
                InputStream is = new ByteArrayInputStream(selected.getImagen());
                System.out.println("Byte :" + selected.getImagen());
                content = new DefaultStreamedContent(is, "", selected.getNombre());
                System.out.println("ddd ------------------------------- " + content);
                return content;
            }
        }
        content = null;
        return content ;
    }
    public StreamedContent conversoImagen(Imagenes imagen) {

        if (imagen != null) {
            if (imagen.getImagen() != null) {
                InputStream is = new ByteArrayInputStream(imagen.getImagen());
                System.out.println("Byte :" + imagen.getImagen());
                content = new DefaultStreamedContent(is, "", imagen.getNombre());
                System.out.println("ddd ------------------------------- " + content);
                return content;
            }
        }
        content = null;
        return content ;
    }

 

    public Imagenes getSelected() {
        return selected;
    }

    public void setSelected(Imagenes selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ImagenesFacade getFacade() {
        return ejbFacade;
    }

    public Imagenes prepareCreate() {
        selected = new Imagenes();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ImagenesCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ImagenesUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ImagenesDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Imagenes> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Imagenes getImagenes(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Imagenes> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Imagenes> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Imagenes.class)
    public static class ImagenesControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ImagenesController controller = (ImagenesController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "imagenesController");
            return controller.getImagenes(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Imagenes) {
                Imagenes o = (Imagenes) object;
                return getStringKey(o.getIdimagenes());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Imagenes.class.getName()});
                return null;
            }
        }

    }

}
