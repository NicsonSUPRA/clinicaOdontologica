/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programe.io.managers;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import programe.io.models.Convenio;
import programe.io.services.ConvenioService;

/**
 *
 * @author nicsondev
 */
@Named
@ViewScoped
public class ManagerConvenio implements Serializable{
    @EJB
    private ConvenioService convenioService;
    private Convenio convenio;
    private List<Convenio> convenios;
    private Convenio convenioSelecionado;
    
    @PostConstruct
    private void instanciar(){
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String vizualizar = params.get("vizualizar");
        if(vizualizar != null){
            convenio = convenioService.findById(Long.parseLong(vizualizar));
            System.out.println("##############"+convenio);
        } else {
            this.convenio = new Convenio();
        }
        pesquisar();
    }
    
    public void salvar(){
        convenioService.salvar(convenio);
        this.convenio = new Convenio();
        pesquisar();
    }
    
    public void teste(){
        System.out.println(convenio);
    }
    
    public void pesquisar(){
        convenios = convenioService.findByName(convenio);
        for(Convenio c : convenios){
            System.out.println(c.getNome());
        }
    }

    public Convenio getConvenio() {
        return convenio;
    }

    public void setConvenio(Convenio convenio) {
        this.convenio = convenio;
    }

    public List<Convenio> getConvenios() {
        return convenios;
    }

    public void setConvenios(List<Convenio> convenios) {
        this.convenios = convenios;
    }

    public Convenio getConvenioSelecionado() {
        return convenioSelecionado;
    }

    public void setConvenioSelecionado(Convenio convenioSelecionado) {
        this.convenioSelecionado = convenioSelecionado;
    }
    
    
      
}
