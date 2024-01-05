package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class DFS {
	public Node dfsUsingStack(Node initial, int goal) {

		Stack<Node> stack = new Stack<>();
		stack.push(initial);
		while (!stack.isEmpty()) {
			Node current = stack.pop();

			if (current.state.size() == goal) {

				return current;
			} else {

				current.getNeighbours();
//				System.out.println(current.state.size());

				for (Node node : current.neighbours) {

					stack.add(node);
				}

			}
		}
		return initial;

	}
}
