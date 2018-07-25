package controlc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import abc.da.Dac;
import abc.da.IDac;
import model3.Pic;
import model3.Trip;
import model3.TripSightseeing;

/**
 * Servlet implementation class largetripxplor
 */
@WebServlet("/largetripxplor")
public class largetripxplor extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String explorerl = "/largetripxplore.jsp";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	IDac TBL;

	public largetripxplor() {
		super();
		// TODO Auto-generated constructor stub
		TBL = new Dac();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String forward = explorerl;
		String idcity = request.getParameter("city");

		Vector ans = new Vector<>();
		List<Trip> listtr = TBL.GetTripsByCity(idcity);

		if (listtr == null)
			request.setAttribute("err", 1);
		Iterator<Trip> myIterator = listtr.iterator();
		while (myIterator.hasNext()) {
			Map<String, Object> arl = new HashMap<String, Object>();
			Trip trip = myIterator.next();
			String idtrip=trip.getIdtrip()+"";
			arl.put("date",trip.getTripDate());
			ArrayList<Map<String, Object>> arl1 = new ArrayList<Map<String, Object>>();
			List<TripSightseeing> listtr1 = TBL.SightseeingforTrip(idtrip);
			if (listtr == null)
				request.setAttribute("err1", 1);
			Iterator<TripSightseeing> myIterator1 = listtr1.iterator();
			while (myIterator1.hasNext()) {
				TripSightseeing t = myIterator1.next();
				List<Pic> lpic = TBL.picsForSightTrip(t.getIdtripSightseeing() + "");
				Map<String, Object> tmp = new HashMap<String, Object>();
				tmp.put("sightname", t.getSightseeing().toString());
				tmp.put("piclist", lpic);
				tmp.put("sizel", lpic.size());
				arl1.add(tmp);
			}

			arl.put("cur",arl1);
			ans.addElement(arl);
			/*
			 * Trip t= myIterator.next(); List<Pic> lpic = TBL.PicbyTrip(t.getIdtrip()+"");
			 * Map<String, Object> tmp=new HashMap<String, Object>(); tmp.put("tripdate",
			 * t.getTripDate()); tmp.put("piclist",lpic); tmp.put("sizel",lpic.size());
			 * arl.add(tmp);
			 */
		}
		request.setAttribute("tarrl", ans);

		/*
		 * String idtrip=request.getParameter("tripid"); ArrayList<Map<String, Object>>
		 * arl=new ArrayList<Map<String, Object>>(); List<TripSightseeing>
		 * listtr=TBL.SightseeingforTrip(idtrip); if(listtr==null)
		 * request.setAttribute("err", 1); Iterator<TripSightseeing> myIterator =
		 * listtr.iterator(); while (myIterator.hasNext()) { TripSightseeing t=
		 * myIterator.next(); List<Pic> lpic =
		 * TBL.picsForSightTrip(t.getIdtripSightseeing()+""); Map<String, Object>
		 * tmp=new HashMap<String, Object>(); tmp.put("sightname",
		 * t.getSightseeing().toString()); tmp.put("piclist",lpic);
		 * tmp.put("sizel",lpic.size()); arl.add(tmp); } request.setAttribute("tarrl",
		 * arl);
		 */
		RequestDispatcher dispatcher = request.getRequestDispatcher(forward);
		dispatcher.forward(request, response);

		response.getWriter().append("Served at: ").append(request.getContextPath());
		// }
		// response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
