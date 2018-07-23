package abc.da;

import java.util.List;

import model3.Pic;
import model3.TripSightseeing;

public interface InvI {

	TripSightseeing getTrsightbyid(String ids);

	List<Pic> picsForSightTrip(String idSight);

	void deletPic(String idpic);

}
