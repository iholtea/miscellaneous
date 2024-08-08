package ionuth.algorithms.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/*
 * There are a number of teams which compete against each other in a tournament
 * Each team competes against all the other teams just once 
 * and one of the teams always wins - there are no ties
 * Winning team gets 3 points, loser gets 0 points.
 * 
 * Write a function which returns the winning team
 * 
 * The input has the format
 * competition = [
 * 		[Steaua, Dinamo],
 * 		[Dinamo, Rapid],
 * 		[Rapid, Steaua]
 * ]
 * 
 * And the results are [ 0, 0, 1 ]
 * 0 means the away(2nd) team wins
 * 1 means the home(1st) team wins
 * 
 * Dinamo beats Steaua
 * Rapid beats Dinamo
 * Rapid beats Steaua
 * 
 * Steaua 0p
 * Dinamo 3p
 * Rapid  6p
 * 
 */

public class AE04TournamentWinner {
	
	/*
	 * build a HashMap<teamName, teamPoints>
	 * and compute the maximum points in the HashMap afterward
	 * 
	 * Note : probably a slight optimization would be to keep track when building
	 * the hashMap of team with maximum points so we do not compute it
	 * afterwards as a separate step
	 */
	public static String winnerV1( List<List<String>> competitions, List<Integer> results ) {
		
		String winningTeam;
		Map<String, Integer> teamScore = new HashMap<String, Integer>();
		
		for( int i=0;i<competitions.size();i++) {
			String teamH = competitions.get(i).get(0);
			String teamA = competitions.get(i).get(1);
			if( results.get(i)==0 ) {
				//team away wins
				winningTeam = teamA;
			} else {
				winningTeam = teamH;
			}
			teamScore.compute(winningTeam, (key, val) -> (val==null) ? 3 : val+3);
		}
		
		Optional<Map.Entry<String, Integer>> optMax = teamScore.entrySet().stream().max( 
				(entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1 );
		
		return optMax.get().getKey();
	}
	
	public static void main(String[] args) {
		
		List<List<String>> competitions = new ArrayList<List<String>>();
		List<Integer> results = new ArrayList<Integer>();
		List<String> match;
		
		match = new ArrayList<String>();
		match.add("Steaua");
		match.add("Dinamo");
		competitions.add(match);
		results.add(0);
		
		match = new ArrayList<String>();
		match.add("Dinamo");
		match.add("Rapid");
		competitions.add(match);
		results.add(0);
		
		match = new ArrayList<String>();
		match.add("Rapid");
		match.add("Steaua");
		competitions.add(match);
		results.add(1);
		
		String winner = winnerV1(competitions, results);
		System.out.println(winner);
	}
}
