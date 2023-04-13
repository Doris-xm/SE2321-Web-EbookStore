package org.reins.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Hint")
public class HintServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public HintServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String hint = "";
		String a[] = new String[30];

		System.out.println("Hint invoked!");

		try {
			a[0] = "Anna";
			a[1] = "Brittany";
			a[2] = "Cinderella";
			a[3] = "Diana";
			a[4] = "Eva";
			a[5] = "Fiona";
			a[6] = "Gunda";
			a[7] = "Hege";
			a[8] = "Inga";
			a[9] = "Johanna";
			a[10] = "Kitty";
			a[11] = "Linda";
			a[12] = "Nina";
			a[13] = "Ophelia";
			a[14] = "Petunia";
			a[15] = "Amanda";
			a[16] = "Raquel";
			a[17] = "Cindy";
			a[18] = "Doris";
			a[19] = "Eve";
			a[20] = "Evita";
			a[21] = "Sunniva";
			a[22] = "Tove";
			a[23] = "Unni";
			a[24] = "Violet";
			a[25] = "Liza";
			a[26] = "Elizabeth";
			a[27] = "Ellen";
			a[28] = "Wenche";
			a[29] = "Vicky";

			String q = request.getParameter("q");
			System.out.println(q);
			if (q.length() > 0) {
				for (int i = 0; i < 30; i++)
					if (a[i].indexOf(q) >= 0) {
						System.out.println(a[i].indexOf(q));
						if (hint == "")
							hint = a[i];
						else
							hint = hint + ", " + a[i];
					}

			}

			if (hint == "") {
				out.println("no suggestion");
			} else {
				out.println(hint);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}


	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	public String getServletInfo() {
		return "Short description";
	}//

}
