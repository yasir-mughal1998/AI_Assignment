/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author yasir
 */
public class Node {
    int pathCost;
    Node parent;
    int state;
    int action;

    public Node(int pathCost, Node parent, int state, int action) {
        this.pathCost = pathCost;
        this.parent = parent;
        this.state = state;
        this.action = action;
    }
}
