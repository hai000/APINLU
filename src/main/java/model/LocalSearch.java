package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class LocalSearch {

	public Week run(Node initial) {

		System.out.println("Initial state is: " + initial.state);
		Node node = initial;
		SortedMap<Integer, Node> neighbours = generateNeighbours(node);
		Integer bestHeuristic = neighbours.firstKey();
		System.out.println("heu:" + bestHeuristic);
		while (bestHeuristic < heuristic(node)) {
			System.out.println("heu find:" + bestHeuristic);
			node = neighbours.get(bestHeuristic);
			neighbours = generateNeighbours(node);
			bestHeuristic = neighbours.firstKey();
			System.out.println("Best state now: " + node.state);
		}
		return node.state;
	}

	private SortedMap<Integer, Node> generateNeighbours(Node node) {
		// TODO Auto-generated method stub
		SortedMap<Integer, Node> sortedMap = new TreeMap<Integer, Node>();

		Node nodeCoppy = new Node(node.n, cloneListState(node.state));
		for (String mamh : ToolSortTKB.isCheck) {

			for (int i = 0; i < 6; i++) {// xóa môn
				try {
					for (Lesson less : nodeCoppy.state.getLessonsOfDay(i + 2)) {

						if (less.getMa_mon().equalsIgnoreCase(mamh)||less.getMa_mon().equalsIgnoreCase(mamh+"L")) {
							nodeCoppy.state.getLessonsOfDay(i + 2).remove(less);
							
						}

					}

				} catch (Exception e) {
					// TODO: handle exception
				}
			}

			for (Lesson less : nodeCoppy.getNeighboursLesson()) {
				for (int i = 0; i < 6; i++) {// xóa môn
					try {
						for (Lesson lesss : nodeCoppy.state.getLessonsOfDay(i + 2)) {
							if (lesss.getMa_mon().equalsIgnoreCase(mamh)||lesss.getMa_mon().equalsIgnoreCase(mamh+"L")) {
								nodeCoppy.state.getLessonsOfDay(i + 2).remove(lesss);
								
							}
						}

					} catch (Exception e) {
						// TODO: handle exception
					}
				}

				int heu = tryPlace(nodeCoppy, less);
				System.out.println("heu current:" + heu);

				if (!sortedMap.containsKey(heu)) {
					sortedMap.put(heu, new Node(nodeCoppy.n, cloneListState(nodeCoppy.state)));

				}

			}
		}

		return sortedMap;

	}

	public int tryPlace(Node node, Lesson less) {
		node.state.setLessonOfDay(less.getThu_kieu_so(), less);
		if (less.getLessonLab() != null) {
			node.state.setLessonOfDay(less.getLessonLab().getThu_kieu_so(), less.getLessonLab());
		}

		return heuristic(node);
	}

	public int heuristic(Node node) {
		int res = 0;
		for (int i = 0; i < 6; i++) {
			int start = 0;
			try {
				List<Lesson> lessons = node.state.getLessonsOfDay(i + 2);
				Collections.sort(lessons);
				for (Lesson less : lessons) {
					if (start == 0) {
						start = less.getTiet_bat_dau();
						res++;
					} else {
						if (start + 3 == less.getTiet_bat_dau()) {

						} else {
							res++;
						}
						start = less.getTiet_bat_dau();
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return res;

	}

	private Week cloneListState(Week state) {
		Week weekclone = new Week();
		for (int i = 0; i < 6; i++) {
			try {
				for (Lesson lesson : state.getLessonsOfDay(i + 2)) {
					weekclone.setLessonOfDay(lesson.getThu_kieu_so(), lesson);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		return weekclone;
	}
}
