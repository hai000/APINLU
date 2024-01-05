package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Node {
	int n;// số môn cần xếp
	Week state;
	List<Node> neighbours;

	public List<Node> getNeighbours() {
		if (state.size() == n) {
			return null;
		} else {
			int i = 0;
			Lesson less = null;
			for (Map.Entry<Integer, Lesson> entry : ToolSortTKB.data.entrySet()) {
//				System.out.println(entry.getValue());
				boolean res = false;
				if (i == 0) {
					for (int j = 0; j < 6; j++) {
						try {
							for (Lesson les : state.getLessonsOfDay(j + 2)) {
								if (entry.getValue().getMa_mon().equals(les.getMa_mon())) {
									res = true;

								}
							}
						} catch (Exception e) {
							// TODO: handle exception
						}
					}
					if (!res) {
						i++;

						less = entry.getValue();

						if (place(less, state) != null) {
							Node newNode = new Node(n);
							newNode.state = place(less, state);
							this.addNeighbour(newNode);
						}

					}
				} else {

					if (entry.getValue().getMa_mon().equals(less.getMa_mon())) {
						if (place(entry.getValue(), state) != null) {
							Node newNode = new Node(n);
							newNode.state = place(entry.getValue(), state);
							this.addNeighbour(newNode);
						}
					}

				}

			}

		}
		return neighbours;
	}

	private Week place(Lesson x, Week weekGeneral) {
		if (lessonValid(x, weekGeneral)) {
			Week clone = new Week();
			for (Map.Entry<Integer, List<Lesson>> entry : weekGeneral.data.entrySet()) {

				List<Lesson> val = entry.getValue();
				for (Lesson lesson : val) {
					clone.setLessonOfDay(lesson.getThu_kieu_so(), lesson);
					if(lesson.getLessonLab()!=null) {
						clone.setLessonOfDay(lesson.getLessonLab().getThu_kieu_so(), lesson.getLessonLab());
					}
				}

			}
			clone.setLessonOfDay(x.getThu_kieu_so(), x);
			if(x.getLessonLab()!=null) {
				clone.setLessonOfDay(x.getLessonLab().getThu_kieu_so(), x.getLessonLab());
			}

			return clone;
		} else {
			return null;
		}

	}

	private boolean lessonValid(Lesson lesson, Week weekGeneral) {
		if (lesson == null) {
			return false;
		}

		try {
			for (Lesson less : weekGeneral.getLessonsOfDay(lesson.getThu_kieu_so())) {
				if (less.getTiet_bat_dau() == lesson.getTiet_bat_dau()) {
					return false;
				}
				if (lesson.getLessonLab() != null) {
					if (lesson.getLessonLab().getTiet_bat_dau() == less.getTiet_bat_dau()) {
						return false;
					}
				}
			}
			for (int i = 2; i <= 8; i++) {
				for (Lesson l : weekGeneral.getLessonsOfDay(i)) {
					if (lesson.getMa_mon().equalsIgnoreCase(l.getMa_mon())) {
						return false;
					}

				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return true;
	}

	public Node(int n) {
		this.n = n;
		this.state = new Week();
		this.neighbours = new ArrayList<>();

	}

	public Node(int n, Week state) {
		this.n = n;
		this.state = state;
		this.neighbours = new ArrayList<>();

	}

	public void addNeighbour(Node node) {
		neighbours.add(node);
	}

	public List<Lesson> getNeighboursLesson() {
		List<Lesson> nei = new ArrayList<>();

		int i = 0;
		Lesson less = null;
		for (Map.Entry<Integer, Lesson> entry : ToolSortTKB.data.entrySet()) {
//				System.out.println(entry.getValue());
			boolean res = false;
			if (i == 0) {
				for (int j = 0; j < 6; j++) {
					try {
						for (Lesson les : state.getLessonsOfDay(j + 2)) {
							if (entry.getValue().getMa_mon().equals(les.getMa_mon())) {
								res = true;

							}
						}
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
				if (!res) {
					i++;

					less = entry.getValue();

					if (place(less, state) != null) {
						nei.add(less);

					}

				}
			} else {

				if (entry.getValue().getMa_mon().equals(less.getMa_mon())) {
					if (place(entry.getValue(), state) != null) {
						nei.add(entry.getValue());
					}
				}

			}

		}

		return nei;
	}

}
