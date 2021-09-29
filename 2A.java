import java.util.*;

public class 2A {
	public static void main(String[] args) { 
        int N;
		int aux;

        Scanner S1 = new Scanner(System.in); 
        N = S1.nextInt();

		ArrayList<ArrayList<Integer>> campeonato = new ArrayList<ArrayList<Integer>>();

		for (int i = 0; i < (N * (N - 1)) / 2; i++) {
            ArrayList<Integer> auxL = new ArrayList<Integer>();
			for (int j = 0; j < 4; j++) {
				aux = S1.nextInt();
                auxL.add(aux);
			}
            campeonato.add(auxL);
		}

        Championship ch = new Championship(campeonato,N);
        ch.GameResults();
	}
}

class Championship {
    int nTeams;
    ArrayList<ArrayList<Integer>> games;
    ArrayList<Team> participants;

    public Championship(ArrayList<ArrayList<Integer>> matches, Integer N) {
        games = matches;
        nTeams = N;

        participants = new ArrayList<Team>();        ;

        for (int i = 0; i < N; i++) {
            Team t = new Team(i+1);
            participants.add(t);
        }

    }

    public void GameResults() {
        for (int i = 0; i < (nTeams * (nTeams - 1)) / 2; i++) {
            int p1 = games.get(i).get(1);
            int p2 = games.get(i).get(3);
            if (games.get(i).get(1) > games.get(i).get(3)) {
                participants.get(games.get(i).get(0)-1).addPoints(2,p2,p1);
                participants.get(games.get(i).get(2)-1).addPoints(1,p1,p2);
            }
            else {
                participants.get(games.get(i).get(2)-1).addPoints(2,p1,p2);
                participants.get(games.get(i).get(0)-1).addPoints(1,p2,p1);
            }
        }

        Collections.sort(participants, new SortTeams());

        for (int i = 0; i < nTeams; i++) {
            System.out.print(participants.get(i).getId());
            System.out.print(" ");
        }
    }
}
    
class Team {
    int id;
    int points;
    int average;
    int pointsTaken;
    int pointsMade;

    public Team(Integer Id) {
        id = Id;
        points = 0;
        pointsTaken = 0;
        pointsMade = 0;
    }

    public void addPoints(Integer p, Integer pt, Integer pm) {
        points = points + p;
        pointsTaken = pointsTaken + pt;
        pointsMade = pointsMade + pm;

        if(pointsTaken != 0)
            average = pointsMade / pointsTaken;
        else
            average = pointsMade;
    }

    public int getId() {
		return id;
	}
    
    public int getPoints() {
		return points;
	}

    public int getPointsMade() {
		return pointsMade;
	}

    public int getAverage() {
		return average;
	}
}

class SortTeams implements Comparator<Team> {
    public int compare(Team t1, Team t2) {
        if(t1.getPoints() > t2.getPoints()) {
            return t2.getPoints() - t1.getPoints();
        }
        else if(t1.getPoints() == t2.getPoints() && t1.getAverage() > t2.getAverage()) {
            return t2.getAverage() - t1.getAverage();
        }
        else if(t1.getPoints() == t2.getPoints() && t1.getAverage() == t2.getAverage() && t1.getPointsMade() > t2.getPointsMade()) {
            return t2.getPointsMade() - t1.getPointsMade();
        }
        else if(t1.getPoints() == t2.getPoints() && t1.getAverage() == t2.getAverage() && t1.getPointsMade() == t2.getPointsMade()){
            return t1.getId() - t2.getId();
        }
        else
            return 0;
    }
}

/*
5
1 102 2 62
1 128 3 127
1 144 4 80
1 102 5 101
2 62 3 61
2 100 4 80
2 88 5 82
3 79 4 90
3 87 5 100
4 110 5 99

3
1 100 2 200
1 200 3 100
2 100 3 200

3
1 100 2 200
1 200 3 100
2 100 3 400
*/
