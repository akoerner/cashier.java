package cashier.tools;

import java.util.ArrayList;

import org.joda.time.DateTime;

import cashier.net.Resource;
import cashier.resource.Person;
import cashier.resource.Timecard;
import cashier.resource.User;

public class ResourceSearch {

	// sequential search
	public static ArrayList<Resource> findTimecardsByUserId(int userId,
			ArrayList<Resource> timecards) {
		ArrayList<Resource> subset = new ArrayList<Resource>();
		Timecard tempTimecard;

		for (Resource timecardResource : timecards) {
			tempTimecard = (Timecard) timecardResource;
			if (tempTimecard.getUserId() == userId)
				subset.add(tempTimecard);
		}

		return subset;
	}

	// sequential search
	public static ArrayList<Resource> findTimecardsInRange(DateTime startDate,
			DateTime endDate, ArrayList<Resource> timecards) {
		ArrayList<Resource> subset = new ArrayList<Resource>();
		Timecard tempTimecard;
		DateTime tempTimecardStartDateTime;
		if(timecards == null || timecards.size() == 0)return timecards;
		for (Resource timecardResource : timecards) {
			tempTimecard = (Timecard) timecardResource;
			tempTimecardStartDateTime = new DateTime(tempTimecard.getBegin());
			if (tempTimecardStartDateTime.getMillis() <= startDate.getMillis()
					&& tempTimecardStartDateTime.getMillis() >= endDate
							.getMillis()) {
				subset.add(tempTimecard);
			}
		}

		return subset;
	}

	// sequential search
	public static Person findPersonById(int id, ArrayList<Resource> persons) {
		Person person;

		for (Resource personResource : persons) {
			person = (Person) personResource;
			if (person.getId() == id) {
				return person;
			}
		}

		return null;
	}

}
