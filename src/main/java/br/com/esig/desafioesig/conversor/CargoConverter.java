package br.com.esig.desafioesig.conversor;

import br.com.esig.desafioesig.domain.Cargo;
import br.com.esig.desafioesig.repository.CargoRepository;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.servlet.ServletContext;
import java.util.Optional;


@SessionScoped
@Component
@FacesConverter(value = "cargoConverter", forClass = Cargo.class)
public class CargoConverter implements Converter{

    private CargoRepository repository;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        if (value != null && !value.trim().isEmpty()) {
            try {
                ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext((ServletContext) facesContext.getExternalContext().getContext());
                repository = context.getBean(CargoRepository.class);
                Optional<Cargo> resp = repository.findById(Integer.valueOf(value));
                return resp.orElse(null);
            } catch (Exception e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error: ", "Inválido"));
            }
        } else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object object) {
        if (object != null) {
            return String.valueOf(((Cargo) object).getId());
        }else {
            return null;
        }
    }
}
