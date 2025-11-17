package controllers;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author jeusu
 */
import dao.RemesaDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "RemesaCobrarServlet", urlPatterns = {"/remesa/cobrar"})
public class RemesaCobrarServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String pin = req.getParameter("pin");

        try {
            RemesaDAO dao = new RemesaDAO();
            dao.cobrarRemesa(pin);   // ESTE MÉTODO LO ARMAMOS AHORA MISMO

            req.setAttribute("pin", pin);
            req.getRequestDispatcher("/remesa/comprobanteCobro.jsp").forward(req, resp);

        } catch (Exception ex) {
            req.setAttribute("error", ex.getMessage());
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }
}