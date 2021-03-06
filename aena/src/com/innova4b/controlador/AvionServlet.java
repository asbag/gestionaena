package com.innova4b.controlador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.innova4b.listas.ListaAviones;
import com.innova4b.modelo.Avion;
import com.innova4b.servicio.AvionServicio;
import com.innova4b.servicio.impl.AvionServicioImpl;

/**
 * Servlet implementation class AvionServlet
 */
public class AvionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AvionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if ("/listar_caducados".equals(request.getPathInfo())){
			AvionServicio as = new AvionServicioImpl();
			List<String> listAviones = as.listarAvionesCaducados();
			request.getSession().setAttribute("listAviones",listAviones);
			request.getRequestDispatcher("/listavcaduc.jsp").forward(request, response);
		} else if ("/listar_espanioles".equals(request.getPathInfo())) {
			AvionServicio as = new AvionServicioImpl();
			List<String> listAviones = as.listarAvionesEspanioles();
			request.getSession().setAttribute("listAviones",listAviones);
			request.getRequestDispatcher("/listavcaduc.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
