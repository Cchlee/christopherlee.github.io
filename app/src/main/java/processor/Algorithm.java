package processor;

import java.util.*;
import data.*;

public class Algorithm {


    private static Set<ArrayList<Integer>> getAllKLength(ArrayList<Integer> set, int k)
    {
        int n = set.size();
        ArrayList<Integer> empty = new ArrayList<Integer>();
        Set<ArrayList<Integer>> permutations = new HashSet<ArrayList<Integer>>();
        return getAllKLengthRec(permutations, set, empty, n, k);
    }

    private static Set<ArrayList<Integer>> getAllKLengthRec(Set<ArrayList<Integer>> permutations, ArrayList<Integer> set, ArrayList<Integer> prefix,int n, int k)
    {
        if (k == 0)
        {
            permutations.add(prefix);
            return permutations;
        }
        for (int i = 0; i < n; ++i)
        {
            ArrayList<Integer> newPrefix = new ArrayList<Integer>(prefix);
            newPrefix.add(set.get(i));
            permutations = getAllKLengthRec(permutations, set, newPrefix, n, k - 1);
        }
        return permutations;
    }



    private static HashMap<CommunityMember, HashSet<ProjectWrapper>> getPeoplesPreferences(Community comm, HashSet<ProjectWrapper> pwraps)
    {
        HashMap<CommunityMember, HashSet<ProjectWrapper>> map = new HashMap<CommunityMember, HashSet<ProjectWrapper>>();
        for (CommunityMember person : comm.getMembers())
        {
            map.put(person, new HashSet<ProjectWrapper>());
            for (ProjectWrapper pw: pwraps)
            {
                if (person.getPreferences().containsKey(pw.role))
                {
                    map.get(person).add(pw);
                }
            }
        }
        return map;
    }

    private static int calculateCost(HashMap<Project, Integer> permutation, Community comm,
                                     HashMap<CommunityMember, HashSet<ProjectWrapper>> peopleToProjs, HashSet<ProjectWrapper> projectWrappers)
    {
        HashSet<ProjectWrapper> opt =  calculateOptimal(permutation, comm, peopleToProjs, projectWrappers);
        int cost = 0;
        for (ProjectWrapper pw : opt)
        {
            cost += Math.max(0, pw.project.getRoles().get(pw.role) - pw.assignment.size());
        }
        return cost;
    }


    private static HashSet<ProjectWrapper> calculateOptimal(HashMap<Project, Integer> permutation,
                                                            Community comm, HashMap<CommunityMember, HashSet<ProjectWrapper>> peopleToProjs, HashSet<ProjectWrapper> projectWrappers)
    {
        HashMap<Project, HashMap<String, HashSet<CommunityMember>>> capablePeople = new HashMap<Project, HashMap<String, HashSet<CommunityMember>>>();
        HashMap<Project, HashMap<String, HashSet<CommunityMember>>> conflictedPeople = new HashMap<Project, HashMap<String, HashSet<CommunityMember>>>();
        for (Project p: comm.getProjects())
        {
            capablePeople.put(p, new HashMap<String, HashSet<CommunityMember>>());
            conflictedPeople.put(p, new HashMap<String, HashSet<CommunityMember>>());
            for (String role: p.getRoles().keySet())
            {
                capablePeople.get(p).put(role, new HashSet<CommunityMember>());
                conflictedPeople.get(p).put(role, new HashSet<CommunityMember>());
            }
        }

        HashMap<CommunityMember, HashSet<ProjectWrapper>> capableProjects = new HashMap<CommunityMember, HashSet<ProjectWrapper>>();
        HashMap<CommunityMember, HashSet<ProjectWrapper>> conflictedProjects = new HashMap<CommunityMember, HashSet<ProjectWrapper>>();

        //HashSet<ProjectWrapper> set = new HashSet<ProjectWrapper>();
        for (CommunityMember person: comm.getMembers())
        {
            conflictedProjects.put(person, new HashSet<ProjectWrapper>());
            capableProjects.put(person, new HashSet<ProjectWrapper>());
            HashMap<Integer, Integer> timeConflictCount = new HashMap<Integer, Integer>(); // time, #activities

            for (ProjectWrapper proj: peopleToProjs.get(person))
            {
                Integer time = permutation.get(proj.project);
                if (person.getSchedule()[time.intValue()])
                {
                    if (timeConflictCount.containsKey(time))
                        timeConflictCount.put(time, timeConflictCount.get(time) + 1);
                    else
                        timeConflictCount.put(time, 1);
                    conflictedProjects.get(person).add(proj);
                    conflictedPeople.get(proj.project).get(proj.role).add(person);
                }
            }
            boolean conflicted = false;
            for (String role: person.getPreferences().keySet())
            {
                int freeTime = person.getPreferences().get(role);
                for (ProjectWrapper pw: conflictedProjects.get(person))
                {
                    if (pw.role.equals(role))
                    {
                        freeTime --;
                    }
                }
                if (freeTime > 0)
                {
                    System.out.println("IDK WAHT TO DO HERE");
                }
                else
                {
                    conflicted = true;
                    break;
                }
            }

            if (! conflicted)
            {
                Set<ProjectWrapper> copy = (Set<ProjectWrapper>) conflictedProjects.get(person).clone();
                for (ProjectWrapper pw: copy)
                {
                    if (timeConflictCount.get(permutation.get(pw.project)) == 1)
                    {
                        pw.addPerson(person);
                        conflictedProjects.get(person).remove(pw);
                        capableProjects.get(person).add(pw);
                        conflictedPeople.get(pw.project).get(pw.role).remove(person);
                        capablePeople.get(pw.project).get(pw.role).add(person);
                    }
                }
            }
        }

        for (ProjectWrapper pw: projectWrappers)
        {
            if (capablePeople.get(pw.project).get(pw.role).size() >= pw.timeWanted)
            {
                for (CommunityMember ex: conflictedPeople.get(pw.project).get(pw.role))
                {
                    conflictedProjects.get(ex).remove(pw);
                    capableProjects.get(ex).add(pw);
                }
                conflictedPeople.get(pw.project).get(pw.role).clear();
                pw.assignment = capablePeople.get(pw.project).get(pw.role);
                pw.time = permutation.get(pw.project);
                System.out.println("life is good");
            }
//			else
//			{
//				//System.out.println("error :(");
//			}
        }
        for (CommunityMember person: conflictedProjects.keySet())
        {
            for (String role: person.getPreferences().keySet())
            {
                int time = person.getPreferences().get(role);
                for (ProjectWrapper pw: capableProjects.get(person))
                {
                    if (pw.role.equals(role)) { time--;}
                }
                while (time > 0)
                {
                    for (ProjectWrapper pw: conflictedProjects.get(person))
                    {
                        if (pw.role.equals(role))
                        {
                            time--;
                            pw.addPerson(person);
                            capableProjects.get(person).add(pw);
                            capablePeople.get(pw.project).get(role).add(person);
                            if (time <= 0) {break;}
                        }
                    }
                }
            }
        }
        return projectWrappers;
    }

    private static ArrayList<HashSet<CommunityMember>> getTimeSlots(Community comm)
    {
        ArrayList<HashSet<CommunityMember>> timeslots = new ArrayList<HashSet<CommunityMember>>(24*7);
        for (int i = 0; i<24*7; i++)
        {
            timeslots.add(i, new HashSet<CommunityMember>());
        }

        for (CommunityMember user : comm.getMembers())
        {
            boolean[] schedule = user.getSchedule();
            if (timeslots.size() != schedule.length)
            {
                System.out.println(timeslots.size()  + "   " + schedule.length + "  Error: 24*7 != 7*24");
            }
            for (int i=0; i<timeslots.size(); i++)
            {
                if (schedule[i])
                {
                    timeslots.get(i).add(user);
                }
            }
        }

        return timeslots;
    }



    private static ArrayList<HashMap<Project, Integer>> getPermutations(Community comm, ArrayList<HashSet<CommunityMember>> timeslots)
    {
        ArrayList<Project> projs = new ArrayList<Project>(comm.getProjects());
        ArrayList<Integer> set = new ArrayList<Integer>();
        for (int i=0; i<timeslots.size(); i++)
        {
            if (timeslots.get(i).size() > 0)
            {
                set.add(new Integer(i));
            }
        }

        Set<ArrayList<Integer>> perms = getAllKLength(set, projs.size());
        ArrayList<HashMap<Project, Integer>> timeAssignments = new ArrayList<HashMap<Project, Integer>>(perms.size());
        for (ArrayList<Integer> perm : perms)
        {
            HashMap<Project, Integer> map = new HashMap<Project, Integer>();
            if (perm.size() != projs.size())
            {
                System.out.println("Confusion, " + perm.size() + " " + projs.size());
            }
            int i = 0;
            for (Integer time: perm)
            {
                map.put(projs.get(i), time);
                i++;
            }
            timeAssignments.add(map);
        }
        return timeAssignments;
    }

    static HashSet<ProjectWrapper> getWrappers(Set<Project> projects)
    {
        HashSet<ProjectWrapper> wrappers = new HashSet<ProjectWrapper>();
        for (Project p: projects)
        {
            for (String role: p.getRoles().keySet())
            {
                wrappers.add(new ProjectWrapper(p, role));
            }
        }
        return wrappers;
    }

    public static String intToDayAndTime(int time)
    {
        String day = "";
        switch(time / 7)
        {
            case 0:
                day = "Sunday";
                break;

            case 1:
                day = "Monday";
                break;

            case 2:
                day = "Tuesday";
                break;

            case 3:
                day = "Wednesday";
                break;

            case 4:
                day = "Thursday";
                break;

            case 5:
                day = "Friday";
                break;

            case 6:
                day = "Saturday";
                break;
        }
        time = time % 24;
        if (time > 12)
        {
            day = day +  " " + String.valueOf(time - 12) + "pm";
        }
        else
        {
            day = day +  " " + String.valueOf(time) + "am";
        }
        return day;
    }

    static void printSchedule(Community c)
    {
        Set<Project> projs = c.getProjects();
        for (Project p: projs)
        {
            System.out.println("Project " + p.getName() + " will meet at " + intToDayAndTime(p.getTimeslot()));
            for (String role: p.getUserAssignment().keySet())
            {
                System.out.println("Working the role " + role + " will be:");
                for (User s: p.getUserAssignment().get(role))
                {
                    System.out.print(" | " + s.getDisplayName());
                }
                System.out.println();
            }
            System.out.println('\n');
        }
    }

    private static void postprocessing(HashMap<Project, Integer> bestPermutation, Community comm, HashSet<ProjectWrapper> output)
    {
        for (ProjectWrapper pw: output)
        {
            pw.project.setTimeslot(bestPermutation.get(pw.project));
            Set<User> users = new HashSet<User>();
            for (User s: pw.assignment)
            {
                users.add(s);
            }
            pw.project.addUserAssignment(pw.role, users);
        }
    }

    public static void runAlgorithm(Community comm)
    {
        HashSet<ProjectWrapper> projWrappers = getWrappers(comm.getProjects());
        HashMap<CommunityMember, HashSet<ProjectWrapper>> peopleToProjs = getPeoplesPreferences(comm, projWrappers);

        ArrayList<HashSet<CommunityMember>> timeslots = getTimeSlots(comm);
        ArrayList<HashMap<Project, Integer>> timePermutations = getPermutations(comm, timeslots);

        HashMap<Project, Integer> bestPermutation = null;
        int minCost = Integer.MAX_VALUE;
        for (HashMap<Project, Integer> permutation: timePermutations)
        {
            int cost = calculateCost(permutation, comm, peopleToProjs, projWrappers);
            if (cost < minCost)
            {
                minCost = cost;
                bestPermutation = permutation;
            }
        }
        HashSet<ProjectWrapper> output = calculateOptimal(bestPermutation, comm, peopleToProjs, projWrappers);
        postprocessing(bestPermutation, comm, output);
    }
}
