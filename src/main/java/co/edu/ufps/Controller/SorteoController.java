package co.edu.ufps.Controller;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.ufps.Dao.SorteoDao;
import co.edu.ufps.Entities.Boleta;
import co.edu.ufps.Entities.Sorteo;

@Controller
public class SorteoController {
	
	protected final Log logger = LogFactory.getLog(this.getClass());
	
	@Autowired
	private SorteoDao sorteoDao;

	@GetMapping({"/registrar","/"})
	public String sorteo() {
		return "sorteo";
	}
	
	@PostMapping("/insertar")
	public String registrar(@RequestParam(name="nombre") String nombre, @RequestParam(name="boletas")int boletas,
			@RequestParam(name="numeros") int numeros,@RequestParam(name="maximo") int maximo) {
		
					
		Sorteo s = new Sorteo();
		s.setNombre(nombre);
		s.setBoletas(boletas);
		s.setNumeros(numeros);
		s.setMaximo(maximo);
		s.setFecha(new Date());
				 
		sorteoDao.save(s);
		
		
		return "redirect:/listar";
	}
	
	@GetMapping("/listar")
	public String listar(Model model) {
		List<Sorteo> sorteos = sorteoDao.findAll();
		model.addAttribute("sorteos", sorteos);
		return "listar";
	}
	
	@GetMapping("/sorteo/generarBoleta/{id}")
	public String GenerarBoleta(@RequestParam(name="id") int id) {
		Sorteo sorteo=sorteoDao.findById(id).get();
		Boleta boleta=new Boleta();
		boleta.setSorteoBean(sorteo);	
		
		return null;
	}
}
