import java.util.Set;
import java.util.ArrayList;
import java.util.Queue;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Stack;
import java.util.LinkedList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author yasir
 */
public class GraphSearch{
    int States;
    int Actions;
    int testCases;
    
    ArrayList<String> states = new ArrayList<>();
    ArrayList<String> actions = new ArrayList<>();
    ArrayList<String> testcases = new ArrayList<>();
    int[][] transitionTable;
    Node goalState;
    
    public void goalBaseAgent(){
       Scanner input = new Scanner(System.in);
        
        States = input.nextInt();
        Actions = input.nextInt();
        testCases = input.nextInt();
        
        input.nextLine();
        input.nextLine();
        for(int i=0;i<States;i++)
            states.add(input.nextLine());
        
        input.nextLine();
        for(int i=0;i<Actions;i++)
            actions.add(input.nextLine());
        
        input.nextLine();
        transitionTable = new int[States][Actions];
        for(int i=0;i<States;i++){
            for(int j=0;j<Actions;j++){
                transitionTable[i][j]=input.nextInt();
            }
        }
        
        input.nextLine();
        input.nextLine();
        for(int i=0;i<testCases;i++)
            testcases.add(input.nextLine());
        
        for(int i=0; i<testcases.size(); i++){
            String[] test_Cases = testcases.get(i).split("\t");
            Node startState = new Node(0, null, getState(test_Cases[0]), -1);
            goalState = new Node(0, null, getState(test_Cases[1]), -1);
            
            Queue<Node> frontier = new LinkedList<>(); 
            Set<Integer> exploredSet = new HashSet<>();
        
            frontier.add(startState);
            
            if(getGoalState(frontier.peek()))
                System.out.println("Goal state");
            
            else{
                while(!frontier.isEmpty()&&!getGoalState(frontier.peek())){
                    Node n = frontier.poll();
                    exploredSet.add(n.state);
                    for(int j=0;j<actions.size();j++){
                        Node childs = new Node(n.pathCost++, n, transitionTable[n.state][j], j);
                        if(!exploredSet.contains(childs.state))
                            frontier.add(childs);
                    }
                }
                goalState = frontier.poll();
                if(goalState==null)
                    System.out.println(" No Goal State");
                else{
                    Stack<Node> stack = new Stack<>();
                    while(goalState.parent!=null){
                        stack.push(goalState);
                        goalState=goalState.parent;
                    }
                    while(!stack.empty()){
                        System.out.print(getAction(stack.pop().action));
                        if(!stack.isEmpty())
                            System.out.print("->");
                    }
                    System.out.println();
                    stack.clear();
                }
            }
            frontier.clear();
            exploredSet.clear();
        }
    }
    
    public boolean getGoalState(Node n){
        if(n.state==goalState.state)
            return true;
        return false;
    }
    
    public int getState(String s){
        for(int i=0;i<states.size();i++){
            if(states.get(i).equals(s))
                return i;
        }
        return -1;
    }
    
    public String getAction(int i){
        return actions.get(i);
    }
}