package Imgg;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import abc.da.Dac;
import abc.da.InvI;
import model3.Pic;
import model3.TripSightseeing;

public class StacInv {
	public static boolean a =false;
	static Vector<Map> imgdetails;
	InvI TBLoc;
	public StacInv() {
		if(!a)
		imgdetails = new Vector<>();
		a=true;
		TBLoc= (Dac)new Dac();
		// TODO Auto-generated constructor stub
	}

	public void addItem(Pic pic, TripSightseeing tripsight) {
		Map<String, Serializable> tmp = new HashMap<String, Serializable>();
		tmp.put("addr", pic.getPicsAddr());
		tmp.put("idpic", pic.getIdpics());
		tmp.put("trs", tripsight);
		imgdetails.add(tmp);
	}

	public void clearItems() {
		imgdetails.clear();
	}

	public void removeItem(String ind) {
		int indi = Integer.parseInt(ind);
		TBLoc.deletPic((imgdetails.get(indi)).get("idpic")+"");
		imgdetails.remove(indi);
	}

	public Vector<Map> listItems(int sightid) {
		this.clearItems();
		TripSightseeing ts= TBLoc.getTrsightbyid(sightid+"");
		List<Pic> lp= TBLoc.picsForSightTrip(sightid+"");
		lp.forEach(pic -> {
			this.addItem(pic, ts);
		}
		);
		return imgdetails;
	}
}
