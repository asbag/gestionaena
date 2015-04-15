package com.innova4b.controlador;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.innova4b.modelo.Aeropuerto;
import com.innova4b.servicio.AeropuertoServicio;
import com.innova4b.servicio.AvionServicio;
import com.innova4b.servicio.impl.AeropuertoServicioImpl;
import com.innova4b.servicio.impl.AvionServicioImpl;

/**
 * Servlet implementation class AeropuertoServlet
 */
public class AeropuertoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AeropuertoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if ("/listar_aeropuertos".equals(request.getPathInfo()) || "/num_puertas".equals(request.getPathInfo())){
			AeropuertoServicio as = new AeropuertoServicioImpl();
			List<String> listaAeropuertos = as.obtenerAeropuertos();
			request.getSession().setAttribute("listaAeropuertos",listaAeropuertos);
			request.getRequestDispatcher("/WEB-INF/views/aeropuerto/listaaeropuertos.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if ("/seleccionado".equals(request.getPathInfo())) {
			String aeropuerto = request.getParameter("aerop");
			AeropuertoServicio aerServ = new AeropuertoServicioImpl();
			int puertas = aerServ.numPuertasEmbarque(aeropuerto);
			request.getSession().setAttribute("puertas", puertas);
			request.getSession().setAttribute("aerop", aeropuerto);
			request.getRequestDispatcher("/WEB-INF/views/aeropuerto/numpuertas.jsp").forward(request, response);
		} 
	}

}
